package com.example.tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentLihatDokterBinding;

public class LihatDokterFragment extends Fragment {

    public LihatDokterFragment(){}

    FragmentLihatDokterBinding binding;

    public static LihatDokterFragment newInstance(String title) {
        Bundle args = new Bundle();
        LihatDokterFragment fragment = new LihatDokterFragment();
        args.putString("title", title);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLihatDokterBinding.inflate(inflater);

        return binding.getRoot();
    }
}
