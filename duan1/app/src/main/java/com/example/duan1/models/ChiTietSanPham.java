package com.example.duan1.models;

public class ChiTietSanPham {
    public int maSP;
    public int giaSp;
    public int soluongSp;
    public String motaSP;
    public String hinhanhSP;
    public String tenSP;

    public ChiTietSanPham(int maSP, int giaSp, int soluongSp, String motaSP, String hinhanhSP, String tenSP) {
        this.maSP = maSP;
        this.giaSp = giaSp;
        this.soluongSp = soluongSp;
        this.motaSP = motaSP;
        this.hinhanhSP = hinhanhSP;
        this.tenSP = tenSP;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(int giaSp) {
        this.giaSp = giaSp;
    }

    public int getSoluongSp() {
        return soluongSp;
    }

    public void setSoluongSp(int soluongSp) {
        this.soluongSp = soluongSp;
    }

    public String getMotaSP() {
        return motaSP;
    }

    public void setMotaSP(String motaSP) {
        this.motaSP = motaSP;
    }

    public String getHinhanhSP() {
        return hinhanhSP;
    }

    public void setHinhanhSP(String hinhanhSP) {
        this.hinhanhSP = hinhanhSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
}
