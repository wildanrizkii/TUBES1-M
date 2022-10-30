package com.example.tubes;

import android.os.Parcel;
import android.os.Parcelable;

public class Dokter implements Parcelable {
    private String id;
    private String nama;
    private String detail;
    private String noTelpon;

    public Dokter() {
    }

    protected Dokter(Parcel in) {
        this.id = in.readString();
        this.nama = in.readString();
        this.detail = in.readString();
        this.noTelpon = in.readString();
    }
    public void setId(String id){
        this.id=id;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }
    public String getid() {
        return this.id;
    }
    public String getNama() {
        return this.nama;
    }
    public String getDetail() {
        return this.detail;
    }
    public String getNoTelpon() {
        return this.noTelpon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.detail);
        dest.writeString(this.noTelpon);
    }
    public static final Parcelable.Creator<Dokter> CREATOR = new Parcelable.Creator<Dokter>() {
        @Override
        public Dokter createFromParcel(Parcel in) {
            return new Dokter(in);
        }

        @Override
        public Dokter[] newArray(int size) {
            return new Dokter[size];
        }
    };
}
