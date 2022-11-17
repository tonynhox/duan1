package com.example.duan1.models;

public class SanPham {
    public int maSP;
    public int maTH;
    public int  giaSp;
    public int soluongSp;
    public String motaSP;
    public String tenSP;
    public String hinhanhLon;
    public String hinhanhNho;


    public SanPham(int maSP, int maTH, int giaSp, int soluongSp, String motaSP, String tenSP, String hinhanhLon, String hinhanhNho) {
        this.maSP = maSP;
        this.maTH = maTH;
        this.giaSp = giaSp;
        this.soluongSp = soluongSp;
        this.motaSP = motaSP;
        this.tenSP = tenSP;
        this.hinhanhLon = hinhanhLon;
        this.hinhanhNho = hinhanhNho;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaTH() {
        return maTH;
    }

    public void setMaTH(int maTH) {
        this.maTH = maTH;
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

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinhanhLon() {
        return hinhanhLon;
    }

    public void setHinhanhLon(String hinhanhLon) {
        this.hinhanhLon = hinhanhLon;
    }

    public String getHinhanhNho() {
        return hinhanhNho;
    }

    public void setHinhanhNho(String hinhanhNho) {
        this.hinhanhNho = hinhanhNho;
    }
}
