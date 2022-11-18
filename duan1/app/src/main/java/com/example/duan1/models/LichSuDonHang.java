package com.example.duan1.models;

import java.util.Date;

public class LichSuDonHang {
    public int maHoaDon;
    public int soluongSP;
    public long gia;
    public Date ngayMua;
    public String hinhAnhNho;


    public LichSuDonHang(int maHoaDon, int soluongSP, long gia, Date ngayMua, String hinhAnhNho) {
        this.maHoaDon = maHoaDon;
        this.soluongSP = soluongSP;
        this.gia = gia;
        this.ngayMua = ngayMua;
        this.hinhAnhNho = hinhAnhNho;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSoluongSP() {
        return soluongSP;
    }

    public void setSoluongSP(int soluongSP) {
        this.soluongSP = soluongSP;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getHinhAnhNho() {
        return hinhAnhNho;
    }

    public void setHinhAnhNho(String hinhAnhNho) {
        this.hinhAnhNho = hinhAnhNho;
    }
}
