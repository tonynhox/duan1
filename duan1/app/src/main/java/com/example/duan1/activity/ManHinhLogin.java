package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.others.ShowNotifyUser;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManHinhLogin extends AppCompatActivity {
    EditText user, pass;
    public static String taikhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnLogin = findViewById(R.id.login);
        Button btnRegister = findViewById(R.id.register);
        user = findViewById(R.id.edt_username);
        pass = findViewById(R.id.edt_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user.getText().toString();
                String password = pass.getText().toString();

                if (username.length() > 0 && password.length() > 0) {
                    ShowNotifyUser.showProgressDialog(ManHinhLogin.this, "Dang dang nhap");
                    DemoCallAPI(username, password);
                } else {
                    Toast.makeText(ManHinhLogin.this, "Nhajap ddaafy ddut thoong tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManHinhLogin.this, ManHinhRegister.class);
                startActivity(intent);
            }
        });
    }

    private void DemoCallAPI(String username, String password) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.checkTaiKhoan(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<CheckTaiKhoan> info) {

        if (info.size() > 0) {
            Intent intent = new Intent(ManHinhLogin.this, ManHinhChinh.class);
            taikhoan = user.getText().toString();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Sai", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("loi", error + "");
        ShowNotifyUser.dismissProgressDialog();
    }


}



