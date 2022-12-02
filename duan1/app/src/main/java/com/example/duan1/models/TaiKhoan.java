package com.example.duan1.models;

import java.util.Date;

public class TaiKhoan {
    public String tenTaiKhoan;
    public String hoTen;
    public String namSinh;
    public String email;
    public String soDienThoai;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenTaiKhoan, String hoTen, String namSinh, String email, String soDienThoai) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
