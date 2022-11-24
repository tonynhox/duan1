package com.example.duan1.models;

import java.util.Date;

public class AllHoaDon {
    public String tenTaiKhoan ;
    public Date ngayMua ;
    public int tongSoLuong ;
    public int tongGiaTien ;
    public String trangThaiHD ;

    public AllHoaDon() {
    }

    public AllHoaDon(String tenTaiKhoan, Date ngayMua, int tongSoLuong, int tongGiaTien, String trangThaiHD) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.ngayMua = ngayMua;
        this.tongSoLuong = tongSoLuong;
        this.tongGiaTien = tongGiaTien;
        this.trangThaiHD = trangThaiHD;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public int getTongGiaTien() {
        return tongGiaTien;
    }

    public void setTongGiaTien(int tongGiaTien) {
        this.tongGiaTien = tongGiaTien;
    }

    public String getTrangThaiHD() {
        return trangThaiHD;
    }

    public void setTrangThaiHD(String trangThaiHD) {
        this.trangThaiHD = trangThaiHD;
    }
}
