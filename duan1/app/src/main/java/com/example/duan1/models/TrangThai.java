package com.example.duan1.models;

import java.util.Date;

public class TrangThai {

    public String tenSp;
    public int soluongSP;
    public long gia;
    public Date ngayMua;
    public String hinhAnhLon;

    public TrangThai(String tenSp, int soluongSP, long gia, Date ngayMua, String hinhAnhLon) {
        this.tenSp = tenSp;
        this.soluongSP = soluongSP;
        this.gia = gia;
        this.ngayMua = ngayMua;
        this.hinhAnhLon = hinhAnhLon;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
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

    public String getHinhAnhLon() {
        return hinhAnhLon;
    }

    public void setHinhAnhLon(String hinhAnhLon) {
        this.hinhAnhLon = hinhAnhLon;
    }
}
