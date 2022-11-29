package com.example.duan1.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.duan1.R;
import com.example.duan1.activity.ManHinhChinh;
import com.example.duan1.activity.ManHinhChinhAdmin;

public class MeFragment extends Fragment {
    static public Button btnXoa,btnHoSo,btnDangXuat;
    Fragment fragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_canhan, container, false);
        btnHoSo= view.findViewById(R.id.btnHoSo);
        btnDangXuat= view.findViewById(R.id.btnDangXuat);
        btnXoa= view.findViewById(R.id.btnDelete);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        btnHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment= new HoSoCuaToiFragment();
                fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
//                if(ManHinhChinhAdmin.b!=null) {
//                    ManHinhChinh.txtTitle.setText("Hồ sơ của tôi");
//
//                }else if
//                    ManHinhChinhAdmin.txtTitle.setText("Hồ sơ của tôi");
//                if(ManHinhChinh.a!=null) {
//                    ManHinhChinh.txtTitle.setText("Hồ sơ của tôi");
//
//                }else
//                    ManHinhChinh.txtTitle.setText("Hồ sơ của tôi");
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                builder.setMessage("Bạn muốn thoát?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"Đã hủy",Toast.LENGTH_SHORT).show();
                    }
                });
                android.app.AlertDialog alertDialog= builder.create();
                alertDialog.show();

            }
        });
        return view;
    }
}
