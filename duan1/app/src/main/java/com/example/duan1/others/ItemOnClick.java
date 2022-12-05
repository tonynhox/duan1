package com.example.duan1.others;

import android.content.Intent;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;

import com.example.duan1.models.SanPham;
import com.example.duan1.models.TimKiemSanPham;

public interface ItemOnClick {
    void onClickItem(TimKiemSanPham timKiemSanPham);
    void onClickItem(SanPham sanPham);
    void onClickXoa(int a);
    void onClickDialog(int a);

}