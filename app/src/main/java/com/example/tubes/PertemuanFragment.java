package com.example.tubes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentPertemuanBinding;

import java.util.Calendar;

public class PertemuanFragment extends Fragment{

    public PertemuanFragment(){}
    FragmentPertemuanBinding binding;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;


    public static PertemuanFragment newInstance() {
        PertemuanFragment pertemuanFragment = new PertemuanFragment();
        return pertemuanFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPertemuanBinding.inflate(inflater);
        binding.btnPertemuan.setOnClickListener(this::onClicked);

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
                Log.d("debug", "onDateSet: dd/mm/yyyy " + day + "/" + month + "/" + year);

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
                Log.d("debug", "onTimeSet: hh/mm " + hour + ":" + minute);
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
        return binding.getRoot();
    }

    private void onClicked(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 1);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);

    }
}
