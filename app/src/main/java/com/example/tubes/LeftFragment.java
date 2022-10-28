package com.example.tubes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentLeftBinding;

public class LeftFragment extends Fragment {
    FragmentLeftBinding binding;

    public LeftFragment(){  }
    public static LeftFragment newInstance(String title) {
        LeftFragment fragment = new LeftFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLeftBinding.inflate(inflater);
        binding.btnExit.setOnClickListener(this::onClickExit);
        binding.btnHome.setOnClickListener(this::onClickHome);
        binding.btnDokter.setOnClickListener(this::onClickDokter);
        binding.btnPertemuan.setOnClickListener(this::onClickPertemuan);
        return binding.getRoot();
    }

    private void onClickPertemuan(View view) {
        Log.d("debug", "onClickPertemuan");
        Bundle result = new Bundle();
        result.putInt("page", 1);
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    private void onClickDokter(View view) {
        Log.d("debug", "onClickDokter");
        Bundle result = new Bundle();
        result.putInt("page", 4);
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    private void onClickHome(View view) {
        Log.d("debug", "onClickHome");
        Bundle result = new Bundle();
        result.putInt("page", 2);
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    private void onClickExit(View view) {
        Log.d("debug", "exitApplication");
        Bundle result = new Bundle();
        result.putInt("page", 0);
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
