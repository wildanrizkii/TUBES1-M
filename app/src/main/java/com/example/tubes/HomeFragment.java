package com.example.tubes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    public HomeFragment(){}

    public static HomeFragment newInstance(String title) {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);
        binding.btnPertemuan.setOnClickListener(this::onClicked);
        return binding.getRoot();
    }

    private void onClicked(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 3);
        Log.d("debug", "ClickMe Clicked!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
