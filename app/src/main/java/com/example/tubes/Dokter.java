package com.example.tubes;

public class Dokter {
    private String nama;
    private String detail;
    private String noTelpon;

    public Dokter(String nama, String detail,String noTelpon) {
        this.nama = nama;
        this.detail = detail;
        this.noTelpon = noTelpon;
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
    public String getNama() {
        return nama;
    }
    public String getDetail() {
        return detail;
    }
    public String getNoTelpon() {return noTelpon; }

}
