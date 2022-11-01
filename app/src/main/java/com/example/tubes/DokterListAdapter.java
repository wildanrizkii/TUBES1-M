package com.example.tubes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.tubes.databinding.ListDokterBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DokterListAdapter extends BaseAdapter {
    private Context context;
    DatabaseReference dokterdb;

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
        ListDokterBinding binding;
        ViewHolder viewHolder;
        Dokter dokter = (Dokter) getItem(i);
        dokterdb = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbDokter = dokterdb.child(Dokter.class.getSimpleName());


        binding = ListDokterBinding.inflate(inflater);
        binding.tvNamadokter.setText(dokter.getNama());
        binding.tvDetail.setText(dokter.getDetail());
        viewHolder = new ViewHolder(binding);
        viewHolder.update(dokter,i);
        binding.ivDelete.setTag(dokter.getid());
        System.out.println(binding.ivDelete.getTag());

        binding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dialogTitle = "Hapus Data";
                String dialogMessage = "Apakah anda yakin ingin menghapus data dokter ini?";
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(binding.getRoot().getContext());
                alertDialogBuilder.setTitle(dialogTitle);
                alertDialogBuilder.setMessage(dialogMessage).setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbDokter.child((String)binding.ivDelete.getTag()).removeValue();
                                Toast.makeText(binding.getRoot().getContext(), "Menghapus data...",Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        return binding.getRoot();
    }

    private class ViewHolder{
        ListDokterBinding binding;
        ViewHolder(ListDokterBinding view){
            binding = view;
        }
        void update(Dokter dokter,int i){
            binding.tvNamadokter.setText(dokter.getNama());
            binding.tvDetail.setText(dokter.getDetail());
        }
    }
}
