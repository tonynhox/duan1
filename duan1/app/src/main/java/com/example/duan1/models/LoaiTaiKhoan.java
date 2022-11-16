package com.example.duan1.models;

public class LoaiTaiKhoan {
    public int maLoaiTaiKhoan;
    public String tenLoaiTaiKhoan;

    public LoaiTaiKhoan(int maLoaiTaiKhoan, String tenLoaiTaiKhoan) {
        this.maLoaiTaiKhoan = maLoaiTaiKhoan;
        this.tenLoaiTaiKhoan = tenLoaiTaiKhoan;
    }

    public int getMaLoaiTaiKhoan() {
        return maLoaiTaiKhoan;
    }

    public void setMaLoaiTaiKhoan(int maLoaiTaiKhoan) {
        this.maLoaiTaiKhoan = maLoaiTaiKhoan;
    }

    public String getTenLoaiTaiKhoan() {
        return tenLoaiTaiKhoan;
    }

    public void setTenLoaiTaiKhoan(String tenLoaiTaiKhoan) {
        this.tenLoaiTaiKhoan = tenLoaiTaiKhoan;
    }
}
