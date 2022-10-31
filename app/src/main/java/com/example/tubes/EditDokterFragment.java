package com.example.tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes.databinding.FragmentEditDokterBinding;

public class EditDokterFragment extends Fragment {
    FragmentEditDokterBinding binding;
    Dokter dokter;
    public EditDokterFragment(){}

    public static EditDokterFragment newInstance() {
        EditDokterFragment fragment = new EditDokterFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditDokterBinding.inflate(inflater);
        binding.btnSimpan.setOnClickListener(this::onClick);
        binding.btnBatal.setOnClickListener(this::onClick);


        this.getParentFragmentManager().setFragmentResultListener("dataDokter", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                dokter = result.getParcelable("Dokter");
                System.out.println(dokter.getNama() + " here");

            }
        });
        return binding.getRoot();
    }

    private void onClick(View view) {
        Bundle result = new Bundle();
        if(view == binding.btnSimpan){
            result.putInt("page", 22);
            getParentFragmentManager().setFragmentResult("changePage", result);
        } else if (view == binding.btnBatal){
            result.putInt("page", 22);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }
}
