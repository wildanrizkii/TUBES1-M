package com.example.tubes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

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


        dbDokter = FirebaseDatabase.getInstance().getReference("dokter");

        binding.btnPlus.setOnClickListener(this::onClick);
        dokters = new ArrayList<>();
//        this.getParentFragmentManager().setFragmentResultListener("itemDokter",
//                this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                String nama = result.getString("nama");
//                String detail = result.getString("detail");
//                String noTelpon = result.getString("noTelpon");
//                add(nama,detail,noTelpon);
//                System.out.println(nama + " " + detail + " " + noTelpon);
//            }
//        });
//
//        this.getParentFragmentManager().setFragmentResultListener("itemDokter2",
//                this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                String nama = result.getString("nama");
//                String detail = result.getString("detail");
//                String noTelpon = result.getString("noTelpon");
//                add(nama,detail,noTelpon);
//                System.out.println(nama + " " + detail + " " + noTelpon);
//            }
//        });
        return binding.getRoot();
    }

    void add(String nama,String detail,String noTelpon){
    }
    private void onClick(View view) {
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
                DokterListAdapter adapter = new DokterListAdapter(getActivity());
                adapter.setDokterslist(dokters);
                binding.listDokter.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Terjadi Kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
