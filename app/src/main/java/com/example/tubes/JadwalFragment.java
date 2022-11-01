package com.example.tubes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentJadwalBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JadwalFragment extends Fragment {
    FragmentJadwalBinding binding;
    private JadwalListAdapter adapter;
    private ArrayList<Jadwal> jadwals;
    DatabaseReference dbJadwal;

    public JadwalFragment(){}

    public static JadwalFragment newInstance() {
        JadwalFragment jadwalFragment = new JadwalFragment();
        return jadwalFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentJadwalBinding.inflate(inflater);
        binding.btnKembali.setOnClickListener(this::onClickBack);
        dbJadwal = FirebaseDatabase.getInstance().getReference(Jadwal.class.getSimpleName());
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo isOnline = connectivityManager.getActiveNetworkInfo();
        if (isOnline == null){
            Toast.makeText(getContext(), "Anda sedang offline", Toast.LENGTH_LONG).show();
        }
        jadwals = new ArrayList<>();
        return binding.getRoot();
    }

    private void onClickBack(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 1);
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    @Override
    public void onStart() {
        super.onStart();
        dbJadwal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jadwals.clear();
                for (DataSnapshot jadwalSnapshot : snapshot.getChildren()){
                    Jadwal jadwal = jadwalSnapshot.getValue(Jadwal.class);
                    jadwals.add(jadwal);
                }
                JadwalListAdapter adapter = new JadwalListAdapter(getActivity());
                adapter.setJadwalslist(jadwals);
                binding.listJadwal.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Anda sedang offline", Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "Membutuhkan koneksi internet!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
