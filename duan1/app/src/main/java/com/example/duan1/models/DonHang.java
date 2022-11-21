package com.example.duan1.models;

public class DonHang {
    public  String tenSp;
    public long giaSp;
    public String hinhAnhLon;

    public DonHang(String tenSp, long giaSp, String hinhAnhLon) {
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.hinhAnhLon = hinhAnhLon;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public long getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(long giaSp) {
        this.giaSp = giaSp;
    }

    public String getHinhAnhLon() {
        return hinhAnhLon;
    }

    public void setHinhAnhLon(String hinhAnhLon) {
        this.hinhAnhLon = hinhAnhLon;
    }
}
