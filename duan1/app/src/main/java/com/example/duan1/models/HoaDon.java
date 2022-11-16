package com.example.duan1.models;

public class HoaDon {
    public int maHoaDon;
    public int maTaiKhoan;
    public String trangThaiHD;
    public int ngayMua;
    public String diaChi;

    public HoaDon(int maHoaDon, int maTaiKhoan, String trangThaiHD, int ngayMua, String diaChi) {
        this.maHoaDon = maHoaDon;
        this.maTaiKhoan = maTaiKhoan;
        this.trangThaiHD = trangThaiHD;
        this.ngayMua = ngayMua;
        this.diaChi = diaChi;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTrangThaiHD() {
        return trangThaiHD;
    }

    public void setTrangThaiHD(String trangThaiHD) {
        this.trangThaiHD = trangThaiHD;
    }

    public int getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(int ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
