package com.example.tubes;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.tubes.databinding.FragmentPertemuanBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class PertemuanFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    public PertemuanFragment(){}
    FragmentPertemuanBinding binding;
    DatePickerFragment datePickerFragment;


    public static PertemuanFragment newInstance(String title) {
        Bundle args = new Bundle();
        PertemuanFragment pertemuanFragment = new PertemuanFragment();
        args.putString("title", title);
        pertemuanFragment.setArguments(args);
        return pertemuanFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPertemuanBinding.inflate(inflater);
        binding.btnPertemuan.setOnClickListener(this::onClicked);
        binding.ivDate.setOnClickListener(this::onClickDate);
        return binding.getRoot();
    }

    private void onClickDate(View view) {
        DialogFragment datePicker = new DialogFragment();
        datePicker.show(getActivity().getSupportFragmentManager(), "date picker");
    }


    private void onClicked(View view) {
        Bundle result = new Bundle();
        result.putInt("page", 2);
        Log.d("debug", "ClickMe Clickeddd!");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        binding.etDate.setHint(currentDateString);

    }
}
