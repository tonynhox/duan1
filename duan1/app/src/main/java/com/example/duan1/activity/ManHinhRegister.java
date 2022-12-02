package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.adapter.SanPhamHotAdapter;
import com.example.duan1.models.SanPham;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManHinhRegister extends AppCompatActivity {


    Button btnRegister,btnCancel;
    EditText edt_name,edt_username,edt_password,edt_phone,edt_email;
    TextView edt_birthday;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String name,user,pass,phone,email,birthday;
    Date birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        btnRegister =  findViewById(R.id.register);
        btnCancel = findViewById(R.id.cancel);
        edt_name = findViewById(R.id.edt_name);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);

        edt_birthday = findViewById(R.id.edt_birthday);
        edt_phone = findViewById(R.id.edt_phone);
        edt_email = findViewById(R.id.edt_email);

        Calendar calendar = Calendar.getInstance();

        edt_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ManHinhRegister.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";

                                if (dayOfMonth < 10){
                                    ngay = "0" +dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }

                                if ((month + 1) < 10){
                                    thang = "0" + (month + 1);
                                }else {
                                    thang = String.valueOf(month+1);
                                }
                                edt_birthday.setText(year + "-" + thang + "-" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });



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
            Toast.makeText(this, "Tạo thành công", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"Tạo Không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();

    }
}