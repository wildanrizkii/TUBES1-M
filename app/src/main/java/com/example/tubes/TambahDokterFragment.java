package com.example.tubes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentTambahDokterBinding;

public class TambahDokterFragment extends Fragment {
    public TambahDokterFragment(){}
    FragmentTambahDokterBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTambahDokterBinding.inflate(inflater);
        binding.btnSimpan.setOnClickListener(this::onClickSimpan);
        return binding.getRoot();
    }

    private void onClickSimpan(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 2);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
