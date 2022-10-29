package com.example.tubes;

import static android.content.Intent.ACTION_DIAL;

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
        binding.btnKembali.setOnClickListener(this::onClick);
        binding.btnHubungi.setOnClickListener(this::onClick);
        binding.btnEdit.setOnClickListener(this::onClick);
        return binding.getRoot();

    }

    private void onClick(View view) {
        if (view == binding.btnKembali){
            Bundle result = new Bundle();
            Log.d("debug", "kembali clicked");
            result.putInt("page", 2);
            getParentFragmentManager().setFragmentResult("changePage", result);
        } else if (view == binding.btnHubungi){
            //Intent DIAL
            Log.d("debug", "hubungi clicked");
            Intent intent = new Intent(ACTION_DIAL, Uri.parse("tel:089646436360"));
            launcher.launch(intent);
        } else if (view == binding.btnEdit){
            Log.d("debug", "edit clicked");
            Bundle result = new Bundle();
            result.putInt("page", 6);
        }
    }

}
