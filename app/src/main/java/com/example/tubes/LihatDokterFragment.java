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

import com.example.tubes.databinding.FragmentLihatDokterBinding;

public class LihatDokterFragment extends Fragment {

    public LihatDokterFragment(){}

    FragmentLihatDokterBinding binding;
    private ActivityResultLauncher launcher;

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
        return binding.getRoot();

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
            Log.d("debug", "edit clicked");
            Bundle result = new Bundle();
            result.putInt("page", 23);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }

}
