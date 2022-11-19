package com.example.duan1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.R;
import com.example.duan1.activity.ManHinhChinh;

public class SanPhamTHFragment extends Fragment {
    TextView textViewSanPham;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham_samsung, container, false);
        textViewSanPham= view.findViewById(R.id.txtFragment);
        textViewSanPham.setText(ManHinhChinh.a);
        return view;
    }
}

