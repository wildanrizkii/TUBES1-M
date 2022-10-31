package com.example.tubes;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes.databinding.FragmentEditDokterBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditDokterFragment extends Fragment {
    FragmentEditDokterBinding binding;
    Dokter dokter;
    DatabaseReference dokterDB;
    public EditDokterFragment(){}

    public static EditDokterFragment newInstance() {
        EditDokterFragment fragment = new EditDokterFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditDokterBinding.inflate(inflater);
        binding.btnSimpan.setOnClickListener(this::onClick);
        binding.btnBatal.setOnClickListener(this::onClick);
        dokterDB = FirebaseDatabase.getInstance().getReference();

        this.getParentFragmentManager().setFragmentResultListener("editDokter", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                dokter = result.getParcelable("Dokter");
                setData(dokter);
            }
        });
        return binding.getRoot();
    }

    private void setData(Dokter dokter){
        binding.etNama.setText(dokter.getNama());
        binding.etDetail.setText(dokter.getDetail());
        binding.etNoTelpon.setText(dokter.getNoTelpon());
    }

    private void onClick(View view) {
        Bundle result = new Bundle();
        if(view == binding.btnSimpan){

            DatabaseReference dokter = dokterDB.child(Dokter.class.getSimpleName());

            String dokterId = this.dokter.getid();
            this.dokter.setNama(binding.etNama.getText().toString());
            this.dokter.setDetail(binding.etDetail.getText().toString());
            this.dokter.setNoTelpon(binding.etNoTelpon.getText().toString());

            dokter.child(dokterId).setValue(this.dokter);
            result.putInt("page", 22);
            getParentFragmentManager().setFragmentResult("changePage", result);
        } else if (view == binding.btnBatal){
            result.putInt("page", 22);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }
}
