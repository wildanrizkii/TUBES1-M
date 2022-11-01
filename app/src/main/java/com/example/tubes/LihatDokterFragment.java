package com.example.tubes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes.databinding.FragmentLihatDokterBinding;

public class LihatDokterFragment extends Fragment {
    FragmentLihatDokterBinding binding;
    Dokter dokter;
    private ActivityResultLauncher launcher;

    public LihatDokterFragment(){}


    public static LihatDokterFragment newInstance() {
        LihatDokterFragment fragment = new LihatDokterFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLihatDokterBinding.inflate(inflater);
        binding.btnKembali.setOnClickListener(this::onClick);
        binding.btnHubungi.setOnClickListener(this::onClick);
        binding.btnEdit.setOnClickListener(this::onClick);

        this.getParentFragmentManager().setFragmentResultListener("dataDokter", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                dokter = result.getParcelable("Dokter");
                setData(dokter);
            }
        });

        return binding.getRoot();

    }

    void setData(Dokter dokter){
        binding.tvNamadokter.setText(dokter.getNama());
        binding.tvKategoridokter.setText(dokter.getDetail());
        binding.tvNohp.setText(dokter.getNoTelpon());
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void onClick(View view) {
        if (view == binding.btnKembali){
            Bundle result = new Bundle();
            Log.d("debug", "kembali clicked");
            result.putInt("page", 2);
            getParentFragmentManager().setFragmentResult("changePage", result);
        } else if (view == binding.btnHubungi){
            dialPhoneNumber(binding.tvNohp.getText().toString());
        } else if (view == binding.btnEdit){
            Bundle dokter = new Bundle();
            dokter.putParcelable("Dokter",this.dokter);
            Log.d("debug", "edit clicked");
            Bundle result = new Bundle();
            result.putInt("page", 23);
            getParentFragmentManager().setFragmentResult("editDokter",dokter);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }

}
