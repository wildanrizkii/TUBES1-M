package com.example.tubes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentPertemuanBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PertemuanFragment extends Fragment{
    FragmentPertemuanBinding binding;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    DatabaseReference jadwalDB;
    private Jadwal jadwal;
    List<String> dokterNames;

    public PertemuanFragment(){}

    public static PertemuanFragment newInstance() {
        PertemuanFragment pertemuanFragment = new PertemuanFragment();
        return pertemuanFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPertemuanBinding.inflate(inflater);
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo isOnline = connectivityManager.getActiveNetworkInfo();
        if (isOnline != null){
            binding.btnPertemuan.setOnClickListener(this::onClickBuatPertemuan);
        }
        if (isOnline == null){
            checkNetworkStatus();
        }
        jadwalDB = FirebaseDatabase.getInstance().getReference();
        jadwal = new Jadwal();
        dokterNames = new ArrayList<>();
        binding.ivDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog= new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, dateSetListener, year, month, day);
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                binding.etDate.setText(date);
            }
        };

        binding.timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, timeSetListener, hour, minute, true);
                dialog.show();
            }
        });

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                String time = "";
                if (hour < 10){
                    if (minute < 10){
                        time = "0" + hour + ":" + "0" + minute;
                    } else {
                        time = "0" + hour + ":" + minute;
                    }
                } else {
                    if (minute < 10){
                        time = hour + ":" + "0" + minute;
                    } else {
                        time = hour + ":" + minute;
                    }
                }
                binding.etTime.setText(time);
            }
        };

        jadwalDB.child(Dokter.class.getSimpleName()).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childrenSnapshot:snapshot.getChildren()) {
                    String listName = childrenSnapshot.child("nama").getValue(String.class);
                    dokterNames.add(listName);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,dokterNames);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                binding.spDokter.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Terjadi Kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private void saveJadwal(){
        String nama = binding.etNama.getText().toString();
        String dokter = binding.spDokter.getSelectedItem().toString();
        String keluhan = binding.etKeluhan.getText().toString();
        String tanggal = binding.etDate.getText().toString();
        String waktu = binding.etTime.getText().toString();

        boolean isEmptyField = false;

        if(TextUtils.isEmpty(nama)){
            isEmptyField = true;
            binding.etNama.setError("Field ini tidak boleh kosong");
        }
        if(TextUtils.isEmpty(keluhan)){
            isEmptyField = true;
            binding.etKeluhan.setError("Field ini tidak boleh kosong");
        }
        if(TextUtils.isEmpty(tanggal)){
            isEmptyField = true;
            binding.etDate.setError("Field ini tidak boleh kosong");
        }
        if(TextUtils.isEmpty(waktu)){
            isEmptyField = true;
            binding.etTime.setError("Field ini tidak boleh kosong");
        }

        if(!isEmptyField){
            Toast.makeText(getContext(),"Jadwal telah dibuat",Toast.LENGTH_SHORT).show();
            DatabaseReference dbJadwal = jadwalDB.child(Jadwal.class.getSimpleName());

            String id = dbJadwal.push().getKey();
            jadwal.setId(id);
            jadwal.setNama(nama);
            jadwal.setDokter(dokter);
            jadwal.setKeluhan(keluhan);
            jadwal.setTanggal(tanggal);
            jadwal.setWaktu(waktu);
            dbJadwal.child(id).setValue(jadwal);
            Bundle result = new Bundle();
            result.putInt("page", 1);
            Log.d("debug", "ClickMe Clickeddd!");
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
        if (!binding.etNama.getText().toString().equals(null)){
            binding.etNama.getText().clear();
            binding.etKeluhan.getText().clear();
            binding.etDate.getText().clear();
            binding.etTime.getText().clear();
        }
    }

    private void onClickBuatPertemuan(View view) {
        saveJadwal();
    }

    public void checkNetworkStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
        } else {
            Toast.makeText(getContext(), "Anda sedang offline", Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(), "Membutuhkan koneksi internet!", Toast.LENGTH_LONG).show();
        }
    }
}
