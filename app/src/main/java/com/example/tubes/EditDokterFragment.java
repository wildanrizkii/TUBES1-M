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

    public static EditDokterFragment newInstance(String title) {
        Bundle args = new Bundle();
        EditDokterFragment fragment = new EditDokterFragment();
        args.putString("title", title);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditDokterBinding.inflate(inflater);
        return binding.getRoot();
    }
}
