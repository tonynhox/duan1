package com.example.duan1.fragments;

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
import com.example.duan1.activity.ManHinhLogin;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoLoginFragment extends Fragment {
    static public Button btnDangNhap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_login, container, false);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);

        SharedPreferences  sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        String typeU = sharedPreferences.getString("user", "");

        if(!typeU.isEmpty()){
            startActivity(new Intent(getContext(), ManHinhLogin.class));
        }

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ManHinhLogin.class));
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(StaticOthers.idUser!=0){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout,new MeFragment()).commit();
        }
    }
}
