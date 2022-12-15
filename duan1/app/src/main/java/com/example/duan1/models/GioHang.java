package com.example.duan1.models;

public class GioHang {
    private int soLuong;
    private String tenSp;
    public long giaSp;
    public String hinhAnhLon;

    public GioHang(int soLuong, String tenSp, long giaSp, String hinhAnhLon) {
        this.soLuong = soLuong;
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.hinhAnhLon = hinhAnhLon;
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
