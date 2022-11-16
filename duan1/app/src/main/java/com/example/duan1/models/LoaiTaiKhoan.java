package com.example.duan1.models;

public class LoaiTaiKhoan {
    public int MaLoaiTaiKhoan;
    public String TenLoaiTaiKhoan;

    public LoaiTaiKhoan(String tenLoaiTaiKhoan) {
        TenLoaiTaiKhoan = tenLoaiTaiKhoan;
    }

    public LoaiTaiKhoan(int maLoaiTaiKhoan) {
        MaLoaiTaiKhoan = maLoaiTaiKhoan;
    }

    public int getMaLoaiTaiKhoan() {
        return MaLoaiTaiKhoan;
    }

    public void setMaLoaiTaiKhoan(int maLoaiTaiKhoan) {
        MaLoaiTaiKhoan = maLoaiTaiKhoan;
    }

    public String getTenLoaiTaiKhoan() {
        return TenLoaiTaiKhoan;
    }

    public void setTenLoaiTaiKhoan(String tenLoaiTaiKhoan) {
        TenLoaiTaiKhoan = tenLoaiTaiKhoan;
    }
}
