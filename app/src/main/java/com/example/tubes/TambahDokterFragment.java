package com.example.tubes;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentTambahDokterBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahDokterFragment extends Fragment {
    FragmentTambahDokterBinding binding;
    DatabaseReference dokterDB;
    private Dokter dokter;

    public TambahDokterFragment(){}

    public static TambahDokterFragment newInstance() {
        TambahDokterFragment fragment = new TambahDokterFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTambahDokterBinding.inflate(inflater);
        binding.btnSimpan.setOnClickListener(this::onClicksave);
        binding.btnBatal.setOnClickListener(this::onClickbatal);
        dokterDB = FirebaseDatabase.getInstance().getReference();
        dokter = new Dokter();
        return binding.getRoot();
    }



    private void onClicksave(View view) {
        if(view == binding.btnSimpan){
            saveDokter();
            if (!binding.etNama.getText().toString().equals(null)){
                binding.etNama.getText().clear();
                binding.etKategori.getText().clear();
                binding.etNohp.getText().clear();
            }
        }
    }

    private void onClickbatal(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 2);
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    private void saveDokter(){
        String nama = binding.etNama.getText().toString();
        String detail = binding.etKategori.getText().toString();
        String noHp = binding.etNohp.getText().toString();

        boolean isEmptyField = false;

        if(TextUtils.isEmpty(nama)){
            isEmptyField = true;
            binding.etNama.setError("Field ini tidak boleh kosong");
        }
        if(TextUtils.isEmpty(detail)){
            isEmptyField = true;
            binding.etKategori.setError("Field ini tidak boleh kosong");
        }
        if(TextUtils.isEmpty(noHp)){
            isEmptyField = true;
            binding.etNohp.setError("Field ini tidak boleh kosong");
        }

        if(!isEmptyField){
            Toast.makeText(getContext(),"Savind Data...",Toast.LENGTH_SHORT).show();

            DatabaseReference dbDokter = dokterDB.child(Dokter.class.getSimpleName());

            String id = dbDokter.push().getKey();
            dokter.setId(id);
            dokter.setNama(nama);
            dokter.setDetail(detail);
            dokter.setNoTelpon(noHp);

            dbDokter.child(id).setValue(dokter);
            System.out.println(dbDokter.child(id));

            Bundle result = new Bundle();
            result.putInt("page", 2);
            Log.d("debug", "ClickMe Clickeddd!");
            getParentFragmentManager().setFragmentResult("changePage", result);

        }

    }
}
