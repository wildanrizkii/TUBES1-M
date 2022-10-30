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

    public static TambahDokterFragment newInstance() {
        TambahDokterFragment fragment = new TambahDokterFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTambahDokterBinding.inflate(inflater);
        binding.btnSimpan.setOnClickListener(this::onClickSimpan);
        return binding.getRoot();
    }

    private void onClickSimpan(View view) {
        Bundle result = new Bundle();
        Bundle nama = new Bundle();
        Bundle detail = new Bundle();

        nama.putString("nama", binding.etNama.getText().toString());
        getParentFragmentManager().setFragmentResult("itemDokter1", nama);
        detail.putString("detail", binding.etKategori.getText().toString());
        getParentFragmentManager().setFragmentResult("itemDokter2", detail);

        System.out.println(binding.etNama.getText().toString() + " " +binding.etKategori.getText().toString());


        result.putInt("page", 2);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
