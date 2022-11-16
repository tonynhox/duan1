package com.example.duan1.models;

public class HoaDon {
    public int MaHoaDon;
    public int MaTaiKhoan;
    public String TrangThaiHD;
    public int NgayMua;
    public String DiaChi;

    public HoaDon(int maHoaDon, int maTaiKhoan, String trangThaiHD, int ngayMua, String diaChi) {
        MaHoaDon = maHoaDon;
        MaTaiKhoan = maTaiKhoan;
        TrangThaiHD = trangThaiHD;
        NgayMua = ngayMua;
        DiaChi = diaChi;
    }

    public HoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public int getMaTaiKhoan() {
        return MaTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        MaTaiKhoan = maTaiKhoan;
    }

    public String getTrangThaiHD() {
        return TrangThaiHD;
    }

    public void setTrangThaiHD(String trangThaiHD) {
        TrangThaiHD = trangThaiHD;
    }

    public int getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(int ngayMua) {
        NgayMua = ngayMua;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
}
