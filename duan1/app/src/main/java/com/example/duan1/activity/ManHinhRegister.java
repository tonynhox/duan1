package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.adapter.SanPhamHotAdapter;
import com.example.duan1.models.SanPham;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManHinhRegister extends AppCompatActivity {
    Button btnRegister,btnCancel;
    EditText edt_name,edt_username,edt_password,edt_birthday,edt_phone,edt_email;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String name,user,pass,phone,email,birthday;
    Date birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        btnRegister =  findViewById(R.id.register);
        btnCancel = findViewById(R.id.cancel);
        edt_name = findViewById(R.id.edt_name);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);

        edt_birthday = findViewById(R.id.edt_birthday);
        edt_phone = findViewById(R.id.edt_phone);
        edt_email = findViewById(R.id.edt_email);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    name= edt_name.getText().toString();
                    user= edt_username.getText().toString();
                    pass= edt_password.getText().toString();
                    birthday= edt_birthday.getText().toString();
                    birth = formatter.parse(birthday);
                    phone = edt_phone.getText().toString();
                    email = edt_email.getText().toString();

                }catch (Exception err){

                }
                Log.d("birth", ""+birth);
                Log.d("ed_birth", ""+birthday);
                DemoCallAPI(user,pass,name,birthday,phone,email);
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManHinhRegister.this, "Đã hủy đăng ký", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ManHinhRegister.this,ManHinhLogin.class);
                startActivity(intent);
            }
        });
    }
    private void DemoCallAPI(String tenTaiKhoan, String matKhau, String hoTen, String namSinh, String soDienThoai, String email) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.addTaiKhoan(tenTaiKhoan, matKhau,hoTen,namSinh,soDienThoai,email)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(Integer info) {
        //Xử lý chức năng
        if(info == 1){
            Toast.makeText(this, "thanh cong", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "khong thanh cong", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(this, "Tai khoản đã tồn tại", Toast.LENGTH_SHORT).show();

    }
}