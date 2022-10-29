package com.example.tubes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes.databinding.ListDokterBinding;
import java.util.ArrayList;
import java.util.List;

public class DokterListAdapter extends BaseAdapter {
    private List<Dokter> listDokter;
    private Activity activity;

    public DokterListAdapter(Activity activity) {
        this.activity = activity;
        this.listDokter = new ArrayList<Dokter>();
    }

    @Override
    public int getCount() {
        return listDokter.size();
    }

    @Override
    public Object getItem(int i) {
        return listDokter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void add(Dokter newItem){
        this.listDokter.add(newItem);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ListDokterBinding binding;

        binding = ListDokterBinding.inflate(inflater);

        System.out.println(getCount());
        return binding.getRoot();
    }
}
