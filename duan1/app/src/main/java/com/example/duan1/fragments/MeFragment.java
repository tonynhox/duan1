package com.example.duan1.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.activity.ManHinhChinh;
import com.example.duan1.activity.ManHinhChinhAdmin;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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
                fragment = new HoSoCuaToiFragment();
                fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();

                if (StaticOthers.username.equalsIgnoreCase("admin"))
                    ManHinhChinhAdmin.txtTitle.setText("Hồ sơ của tôi");
                else
                    ManHinhChinh.txtTitle.setText("Hồ sơ của tôi");
            }

        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View viewa = inflater.inflate(R.layout.dialog_xacnhantaikhoan, null);
                builder.setView(viewa);
                AlertDialog alertDialogSua = builder.create();

                Button btnHuy= viewa.findViewById(R.id.btnHuy);
                Button btnDel= viewa.findViewById(R.id.btnDelete);
                EditText edtPass= viewa.findViewById(R.id.edt_pass);

                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String pass= edtPass.getText().toString();
                        DemoCallAPI(pass);
                    }
                });


                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialogSua.dismiss();
                    }
                });
                alertDialogSua.show();

            }
        });


        SharedPreferences  sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                builder.setMessage("Bạn muốn thoát?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        StaticOthers.idUser=0;
                        StaticOthers.username=null;
                        StaticOthers.listGH.clear();
//                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout,new NoLoginFragment()).commit();
                        Intent intent = new Intent(getContext(), ManHinhChinh.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
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

    private void DemoCallAPI(String pass) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.xoaTaiKhoan(StaticOthers.username,pass)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(Integer info) {
        //Xử lý chức năng
        if(info == 1){
            Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            SharedPreferences  sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.clear();
            editor.commit();
            StaticOthers.idUser=0;
            StaticOthers.username=null;
            Intent intent = new Intent(getContext(), ManHinhChinh.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            Toast.makeText(getContext(),"Xóa Không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(getContext(),"Xóa Không thành công", Toast.LENGTH_SHORT).show();

    }
}
