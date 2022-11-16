package com.example.duan1.models;

public class TaiKhoan {
    public int MaTaiKhoan;
    public String TenTaiKhoan;
    public String MatKhau;
    public String HoTen;
    public int NamSinh;
    public int SoDienThoai;
    public String Email;
    public int MaLoaiTaiKhoan;

    public int getMaTaiKhoan() {
        return MaTaiKhoan;
    }
    public void setMaTaiKhoan(int maTaiKhoan) {
        MaTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        TenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public int getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(int namSinh) {
        NamSinh = namSinh;
    }

    public int getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(int soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getMaLoaiTaiKhoan() {
        return MaLoaiTaiKhoan;
    }

    public void setMaLoaiTaiKhoan(int maLoaiTaiKhoan) {
        MaLoaiTaiKhoan = maLoaiTaiKhoan;
    }

    public TaiKhoan(int maTaiKhoan) {
        MaTaiKhoan = maTaiKhoan;
    }
}
