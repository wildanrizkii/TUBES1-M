package com.example.tubes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes.databinding.ListDokterBinding;

import java.util.ArrayList;

public class DokterListAdapter extends BaseAdapter {
    private Context context;
    ListDokterBinding binding;

    private ArrayList<Dokter> dokterslist = new ArrayList<>();

    public void setDokterslist(ArrayList<Dokter> dokterslist){
        this.dokterslist = dokterslist;
    }

    public DokterListAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return dokterslist.size();
    }

    @Override
    public Object getItem(int i) {
        return dokterslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        binding = ListDokterBinding.inflate(inflater);

        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.list_dokter, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);
        Dokter dokter = (Dokter) getItem(i);
        viewHolder.bind(dokter);
        binding.ivDelete.setOnClickListener(this::onDelete);
        return binding.getRoot();
    }

    private void onDelete(View view) {

    }

    private class ViewHolder{

        ViewHolder(View view){

        }

        void bind(Dokter dokter){
            binding.tvNamadokter.setText(dokter.getNama());
            binding.tvDetail.setText(dokter.getDetail());
            binding.ivDelete.setTag(dokter.getid());
        }
    }
}
