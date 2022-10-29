package com.example.tubes;

public class Dokter {
    private String nama;
    private String detail;

    public Dokter(String nama, String detail) {
        this.nama = nama;
        this.detail = detail;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getNama() {
        return nama;
    }
    public String getDetail() {
        return detail;
    }

}
