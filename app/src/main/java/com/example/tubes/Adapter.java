package com.example.tubes;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {

    private List<Dokter> listItems;
    private Activity activity;

    public Adapter(Activity activity){
        this.activity = activity;
        this.listItems = new ArrayList<Dokter>();
    }

    public void add(Dokter newItem){
        this.listItems.add(newItem);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Adapter.ViewHolder viewHolder;
        return convertView;
    }

    private class ViewHolder {
        protected TextView title;

        public ViewHolder (View view){
            this.title = view.findViewById(R.id.tv_title);
        }
        public void updateView(Dokter dokter){

        }
    }
}
