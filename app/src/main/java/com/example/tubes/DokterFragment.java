package com.example.tubes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentDokterBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DokterFragment extends Fragment {
    FragmentDokterBinding binding;
    private DokterListAdapter adapter;
    private ArrayList<Dokter> dokters;
    DatabaseReference dbDokter;

    public DokterFragment(){    }

    public static DokterFragment newInstance() {
        DokterFragment dokterFragment = new DokterFragment();
        return dokterFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDokterBinding.inflate(inflater);
        dbDokter = FirebaseDatabase.getInstance().getReference(Dokter.class.getSimpleName());
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo isOnline = connectivityManager.getActiveNetworkInfo();
        if (isOnline != null){
            binding.btnPlus.setOnClickListener(this::onClickTambah);
        }
        if (isOnline == null){
            checkNetworkStatus();
        }
        binding.listDokter.setOnItemClickListener(this::onClickList);
        dokters = new ArrayList<>();
        return binding.getRoot();
    }

    private void onClickList(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle rs = new Bundle();
        Bundle result = new Bundle();
        result.putInt("page", 22);
        rs.putParcelable("Dokter",dokters.get(i));
        getParentFragmentManager().setFragmentResult("dataDokter",rs);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    private void onClickTambah(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 21);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    @Override
    public void onStart() {
        super.onStart();

        dbDokter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dokters.clear();
                for (DataSnapshot dokterSnapshot : snapshot.getChildren()){
                    Dokter dokter = dokterSnapshot.getValue(Dokter.class);
                    dokters.add(dokter);
                }
                DokterListAdapter adapter = new DokterListAdapter(getContext());
                adapter.setDokterslist(dokters);
                binding.listDokter.setAdapter(adapter);
                dokters.size();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Terjadi Kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkNetworkStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
        } else {
            Toast.makeText(getContext(), "Anda sedang offline", Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(), "Membutuhkan koneksi internet!", Toast.LENGTH_LONG).show();
        }
    }
}
