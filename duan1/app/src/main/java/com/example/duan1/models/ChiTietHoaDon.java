package com.example.duan1.models;

public class ChiTietHoaDon {
    private int maSP;
    private int maHoaDon;
    private String tenSP;
    private int giaSP;
    private int soLuong;
    private String hinhAnhNho;


    public ChiTietHoaDon(int maSP, int maHoaDon, String tenSP, int giaSP, int soLuong) {
        this.maSP = maSP;
        this.maHoaDon = maHoaDon;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
    }

    public String getIvNho() {
        return hinhAnhNho;
    }

    public void setIvNho(String ivNho) {
        this.hinhAnhNho = ivNho;
    }

    public ChiTietHoaDon(String tenSP, int giaSP, int soLuong) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
    }

    public ChiTietHoaDon(String tenSP, int giaSP, int soluongSP, String ivNho) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soluongSP;
        this.hinhAnhNho = ivNho;
    }

    public ChiTietHoaDon() {
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
