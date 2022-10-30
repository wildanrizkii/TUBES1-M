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
    DatabaseReference mDatabase;
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
        System.out.println("im Here2");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        dokter = new Dokter();
        return binding.getRoot();
    }

    private void onClicksave(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        System.out.println(myRef + " ini dokter");

        myRef.push().setValue("Hello, World!");
        if(view == binding.btnSimpan){
            saveDokter();
        }

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
            binding.etNama.setError("Field ini tidak boleh kosong");
        }
        if(TextUtils.isEmpty(noHp)){
            isEmptyField = true;
            binding.etNama.setError("Field ini tidak boleh kosong");
        }

        if(!isEmptyField){
            Toast.makeText(getContext(),"Savind Data...",Toast.LENGTH_SHORT).show();

            DatabaseReference dbDokter = mDatabase.child(Dokter.class.getSimpleName());
            System.out.println(nama);
            System.out.println(detail);
            System.out.println(noHp);

            String id = dbDokter.push().getKey();
            System.out.println(dbDokter + " ini dokter");
            dokter.setId(id);
            dokter.setNama(nama);
            dokter.setDetail(detail);
            dokter.setNoTelpon(noHp);

            dbDokter.child(id);
            System.out.println(dbDokter.child(id));


            Bundle result = new Bundle();
            result.putInt("page", 2);
            Log.d("debug", "ClickMe Clickeddd!");
            getParentFragmentManager().setFragmentResult("changePage", result);


        }

    }

    private void onClickSimpan(View view) {
        Bundle result = new Bundle();
        Bundle nama = new Bundle();
        Bundle detail = new Bundle();
        Bundle noTelpon = new Bundle();

        Bundle all = new Bundle();

        nama.putString("nama", binding.etNama.getText().toString());
        getParentFragmentManager().setFragmentResult("itemDokter1", nama);
        detail.putString("detail", binding.etKategori.getText().toString());
        getParentFragmentManager().setFragmentResult("itemDokter2", detail);
        noTelpon.putString("noTelpon", binding.etNohp.getText().toString());
        getParentFragmentManager().setFragmentResult("itemDokter3", noTelpon);

        System.out.println(binding.etNama.getText().toString() + " " +binding.etKategori.getText().toString() + " "+binding.etNohp.getText().toString() );


        result.putInt("page", 2);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
