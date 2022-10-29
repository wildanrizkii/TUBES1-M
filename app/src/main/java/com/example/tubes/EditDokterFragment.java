package com.example.tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentEditDokterBinding;

public class EditDokterFragment extends Fragment {
    FragmentEditDokterBinding binding;

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
