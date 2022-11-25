package com.example.duan1.models;

public class SanPham {
    private int maSp;
    private String tenSp;
    private long giaSp;
    private int maThuongHieu;
    private String motaSp;
    private int soLuongSp;
    private String hinhAnhLon;
    private String hinhAnhNho;
    private int tongSoLuong;

    public SanPham(int maSp, String tenSp, long giaSp, int maThuongHieu, String motaSp, int soLuongSp, String hinhAnhLon, String hinhAnhNho) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.maThuongHieu = maThuongHieu;
        this.motaSp = motaSp;
        this.soLuongSp = soLuongSp;
        this.hinhAnhLon = hinhAnhLon;
        this.hinhAnhNho = hinhAnhNho;
    }

    public SanPham() {
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

    public int getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(int maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public String getMotaSp() {
        return motaSp;
    }

    public void setMotaSp(String motaSp) {
        this.motaSp = motaSp;
    }

    public int getSoLuongSp() {
        return soLuongSp;
    }

    public void setSoLuongSp(int soLuongSp) {
        this.soLuongSp = soLuongSp;
    }

    public String getHinhAnhLon() {
        return hinhAnhLon;
    }

    public void setHinhAnhLon(String hinhAnhLon) {
        this.hinhAnhLon = hinhAnhLon;
    }

    public String getHinhAnhNho() {
        return hinhAnhNho;
    }

    public void setHinhAnhNho(String hinhAnhNho) {
        this.hinhAnhNho = hinhAnhNho;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }
}
