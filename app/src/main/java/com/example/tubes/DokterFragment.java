package com.example.tubes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes.databinding.FragmentDokterBinding;

public class DokterFragment extends Fragment {
    FragmentDokterBinding binding;
    private DokterListAdapter dokterListAdapter;

    public DokterFragment(){}

    public static DokterFragment newInstance() {
        DokterFragment dokterFragment = new DokterFragment();
        return dokterFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDokterBinding.inflate(inflater);
        binding.btnPlus.setOnClickListener(this::onClick);
        binding.listDokter.setOnItemClickListener(this::onClick);
        this.dokterListAdapter = new DokterListAdapter(getActivity());
        binding.listDokter.setAdapter(dokterListAdapter);

        this.getParentFragmentManager().setFragmentResultListener("itemDokter",
                this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nama = result.getString("nama");
                String detail = result.getString("detail");

                add(nama,detail);
                System.out.println(nama + " " + detail);
            }
        });

        this.getParentFragmentManager().setFragmentResultListener("itemDokter2",
                this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nama = result.getString("nama");
                String detail = result.getString("detail");
                add(nama,detail);
                System.out.println(nama + " " + detail);
            }
        });
        return binding.getRoot();
    }

    private void onClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle result = new Bundle();
        result.putInt("page", 22);
        Log.d("debug", "ListView Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    void add(String nama,String detail){
        Dokter dokter = new Dokter(nama, detail);
        dokterListAdapter.add(dokter);
    }
    private void onClick(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 21);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
