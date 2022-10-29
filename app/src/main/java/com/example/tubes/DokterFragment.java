package com.example.tubes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentDokterBinding;

public class DokterFragment extends Fragment {
    public DokterFragment(){}

    FragmentDokterBinding binding;
    private Adapter adapter;

    public static DokterFragment newInstance() {
        DokterFragment dokterFragment = new DokterFragment();
        return dokterFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDokterBinding.inflate(inflater);
        binding.btnPlus.setOnClickListener(this::onClick);
        this.adapter = new Adapter(getActivity());
        binding.lvDokter.setAdapter(adapter);

        return binding.getRoot();
    }

    private void onClick(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 21);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
