package com.example.tubes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes.databinding.FragmentDokterBinding;

public class DokterFragment extends Fragment {
    FragmentDokterBinding binding;
    private DokterListAdapter dokterListAdapter;

    public DokterFragment(){    }

    public static DokterFragment newInstance() {
        DokterFragment dokterFragment = new DokterFragment();
        return dokterFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDokterBinding.inflate(inflater);
        binding.btnPlus.setOnClickListener(this::onClick);
        this.dokterListAdapter = new DokterListAdapter(getActivity());
        binding.lstDokter.setAdapter(dokterListAdapter);

        this.getParentFragmentManager().setFragmentResultListener("itemDokter",
                this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nama = result.getString("nama");
                String detail = result.getString("detail");
                String noTelpon = result.getString("noTelpon");
                add(nama,detail,noTelpon);
                System.out.println(nama + " " + detail + " " + noTelpon);
            }
        });

        this.getParentFragmentManager().setFragmentResultListener("itemDokter2",
                this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nama = result.getString("nama");
                String detail = result.getString("detail");
                String noTelpon = result.getString("noTelpon");
                add(nama,detail,noTelpon);
                System.out.println(nama + " " + detail + " " + noTelpon);
            }
        });


        return binding.getRoot();
    }

    void add(String nama,String detail,String noTelpon){
        Dokter dokter = new Dokter(nama,detail,noTelpon);
        dokterListAdapter.add(dokter);
    }
    private void onClick(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 21);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
