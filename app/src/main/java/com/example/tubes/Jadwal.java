package com.example.tubes;

import android.os.Parcel;
import android.os.Parcelable;

public class Jadwal implements Parcelable {
    private String id;
    private String nama;
    private String dokter;
    private String keluhan;
    private String tanggal;
    private String waktu;

    public Jadwal(){}

    protected Jadwal(Parcel in) {
        this.id = in.readString();
        this.nama = in.readString();
        this.dokter = in.readString();
        this.keluhan = in.readString();
        this.tanggal = in.readString();
        this.waktu = in.readString();
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setDokter(String dokter) {
        this.dokter = dokter;
    }
    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public String getDokter() {
        return dokter;
    }
    public String getKeluhan() {
        return keluhan;
    }
    public String getTanggal() {
        return tanggal;
    }
    public String getWaktu() {
        return waktu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.dokter);
        dest.writeString(this.keluhan);
        dest.writeString(this.tanggal);
        dest.writeString(this.waktu);
    }

    public static final Parcelable.Creator<Jadwal> CREATOR = new Parcelable.Creator<Jadwal>() {
        @Override
        public Jadwal createFromParcel(Parcel in) {
            return new Jadwal(in);
        }

        @Override
        public Jadwal[] newArray(int size) {
            return new Jadwal[size];
        }
    };
}
