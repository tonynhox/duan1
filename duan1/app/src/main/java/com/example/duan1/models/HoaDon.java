package com.example.duan1.models;

import java.util.Date;

public class HoaDon {
    public int maHoaDon;
    public int maTaiKhoan;
    public String trangThaiHD;
    public Date ngayMua;
    public String diaChi;



    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int maTaiKhoan, String trangThaiHD, Date ngayMua, String diaChi) {
        this.maHoaDon = maHoaDon;
        this.maTaiKhoan = maTaiKhoan;
        this.trangThaiHD = trangThaiHD;
        this.ngayMua = ngayMua;
        this.diaChi = diaChi;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public String getTrangThaiHD() {
        return trangThaiHD;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public String getDiaChi() {
        return diaChi;
    }
}
