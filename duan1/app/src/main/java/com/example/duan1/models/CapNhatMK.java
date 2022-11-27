package com.example.duan1.models;

public class CapNhatMK {
    private int maTaiKhoan;
    private String matKhauCu;
    private String matKhauMoi;

    public CapNhatMK(int maTaiKhoan, String matKhauCu, String matKhauMoi) {
        this.maTaiKhoan = maTaiKhoan;
        this.matKhauCu = matKhauCu;
        this.matKhauMoi = matKhauMoi;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getMatKhauCu() {
        return matKhauCu;
    }

    public void setMatKhauCu(String matKhauCu) {
        this.matKhauCu = matKhauCu;
    }

    public String getMatKhauMoi() {
        return matKhauMoi;
    }

    public void setMatKhauMoi(String matKhauMoi) {
        this.matKhauMoi = matKhauMoi;
    }
}
