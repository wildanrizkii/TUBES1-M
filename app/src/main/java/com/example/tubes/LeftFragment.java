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
    public static LeftFragment newInstance() {
        LeftFragment fragment = new LeftFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLeftBinding.inflate(inflater);
        binding.btnExit.setOnClickListener(this::onClickChangePage);
        binding.btnHome.setOnClickListener(this::onClickChangePage);
        binding.btnDokter.setOnClickListener(this::onClickChangePage);
        binding.btnPertemuan.setOnClickListener(this::onClickChangePage);
        binding.btnPengaturan.setOnClickListener(this::onClickChangePage);
        return binding.getRoot();
    }

    private void onClickChangePage(View view){
        if(view == binding.btnHome){
            Bundle result = new Bundle();
            result.putInt("page", 1);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view == binding.btnDokter){
            Log.d("debug", "onClickDokter");
            Bundle result = new Bundle();
            result.putInt("page", 2);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view == binding.btnPertemuan){
            Log.d("debug", "onClickDokter");
            Bundle result = new Bundle();
            result.putInt("page", 3);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
        else if(view == binding.btnPengaturan){
            Bundle result = new Bundle();
            result.putInt("page", 4);
            getParentFragmentManager().setFragmentResult("changePage", result);
        } else {
            Bundle result = new Bundle();
            result.putInt("page", 0);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }
}
