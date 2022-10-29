package com.example.tubes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentPertemuanBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class PertemuanFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    FragmentPertemuanBinding binding;

    public PertemuanFragment(){     }
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
                DialogFragment datePicker = new DatePickerFragment();
            }
        });
        return binding.getRoot();
    }



    private void onClicked(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 1);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());


    }
}
