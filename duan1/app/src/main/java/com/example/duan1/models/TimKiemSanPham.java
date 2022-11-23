package com.example.duan1.models;

public class TimKiemSanPham {
    private int maSp;
    private String tenSp;
    private long giaSp;
    private String hinhAnhLon;

    public TimKiemSanPham() {
    }

    public TimKiemSanPham(int maSp, String tenSp, long giaSp, String hinhAnhLon) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.hinhAnhLon = hinhAnhLon;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
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
