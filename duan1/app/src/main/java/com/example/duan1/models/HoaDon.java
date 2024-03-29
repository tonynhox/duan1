package com.example.duan1.models;

import java.util.Date;

public class HoaDon {
    private int maHoaDon;
    private String tenTaiKhoan;
    private String ngayMua;
    private int tongSoLuong;
    private long tongGiaTien;
    private String trangThaiHD;
    private String soDienThoai;
    private String diaChi;
    private  String thanhToan;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, String tenTaiKhoan, String ngayMua, int tongSoLuong, int tongGiaTien, String trangThaiHD, String diaChi) {
        this.maHoaDon = maHoaDon;
        this.tenTaiKhoan = tenTaiKhoan;
        this.ngayMua = ngayMua;
        this.tongSoLuong = tongSoLuong;
        this.tongGiaTien = tongGiaTien;
        this.trangThaiHD = trangThaiHD;
        this.diaChi = diaChi;
    }

    public void setTongGiaTien(long tongGiaTien) {
        this.tongGiaTien = tongGiaTien;
    }

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public long getTongGiaTien() {
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
