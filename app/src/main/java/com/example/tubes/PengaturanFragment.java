package com.example.tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentPengaturanBinding;

public class PengaturanFragment extends Fragment {
    public PengaturanFragment(){}
    FragmentPengaturanBinding binding;

    public static PengaturanFragment newInstance(String title) {
        Bundle args = new Bundle();
        PengaturanFragment fragment = new PengaturanFragment();
        args.putString("title", title);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPengaturanBinding.inflate(inflater);
        return binding.getRoot();
    }
}
