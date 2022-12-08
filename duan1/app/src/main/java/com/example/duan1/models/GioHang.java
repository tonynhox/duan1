package com.example.duan1.models;

public class GioHang {
    private int maSP;
    private int soLuong;
    private String tenSp;
    private long giaSp;
    private String hinhAnhLon;

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public GioHang(int soLuong, String tenSp, long giaSp, String hinhAnhLon) {
        this.soLuong = soLuong;
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.hinhAnhLon = hinhAnhLon;
    }

    public GioHang(String tenSp, long giaSp, String hinhAnhLon) {
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.hinhAnhLon = hinhAnhLon;
    }

    public GioHang() {
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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
