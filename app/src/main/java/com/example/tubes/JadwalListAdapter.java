package com.example.tubes;

import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



import com.example.tubes.databinding.ListJadwalBinding;


import java.util.ArrayList;

public class JadwalListAdapter extends BaseAdapter {
    private Context context;
    ListJadwalBinding binding;
    private ArrayList<Jadwal> jadwalslist = new ArrayList<>();

    public void setJadwalslist(ArrayList<Jadwal> jadwalslist){
        this.jadwalslist = jadwalslist;
    }

    public JadwalListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return jadwalslist.size();
    }

    @Override
    public Object getItem(int i) {
        return jadwalslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = ListJadwalBinding.inflate(inflater);

        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.list_jadwal, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        Jadwal jadwal = (Jadwal) getItem(i);

        viewHolder.bind(jadwal);
        return binding.getRoot();
    }



    private class ViewHolder{

        ViewHolder(View view){

        }

        void bind(Jadwal jadwal){
            binding.tvNamapasien.setText(jadwal.getNama());
            binding.tvNamadokter.setText(jadwal.getDokter());
            binding.tvTanggal.setText(jadwal.getTanggal());
            binding.tvWaktu.setText(jadwal.getWaktu());
            binding.tvKeluhan.setText(jadwal.getKeluhan());
        }
    }
}
