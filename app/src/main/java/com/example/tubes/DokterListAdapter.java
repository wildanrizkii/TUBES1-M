package com.example.tubes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tubes.databinding.ListDokterBinding;
import java.util.ArrayList;
import java.util.List;

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

        ViewHolder viewHolder = new ViewHolder(view);
        Dokter dokter = (Dokter) getItem(i);

        viewHolder.bind(dokter);
        return itemView;
    }

    private class ViewHolder{
        private TextView tvnama,tvdetail,tvnotelpon;

        ViewHolder(View view){
            tvnama = view.findViewById(R.id.tv_namadokter);
            tvdetail = view.findViewById(R.id.tv_detail);
            tvnotelpon = view.findViewById(R.id.tv_nohp);
        }

        void bind(Dokter dokter){
            tvnama.setText(dokter.getNama());
            tvdetail.setText(dokter.getDetail());
            tvnotelpon.setText(dokter.getNoTelpon());
        }
    }




}
