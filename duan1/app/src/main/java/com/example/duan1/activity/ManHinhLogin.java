package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.others.ShowNotifyUser;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManHinhLogin extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String typeU,typePass ;
    int typeId;
    EditText user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        typeU = sharedPreferences.getString("user", "");
        typePass= sharedPreferences.getString("pass", "");
        typeId=sharedPreferences.getInt("idUser", 0);
        Button btnLogin = findViewById(R.id.login);
        Button btnRegister = findViewById(R.id.register);
        ImageView ivClose = findViewById(R.id.ivClose);

        user = findViewById(R.id.edt_username);
        pass = findViewById(R.id.edt_password);

        if(!typeU.isEmpty())
            DemoCallAPI(typeU,typePass);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user.getText().toString();
                String password = pass.getText().toString();


                if (username.length() > 0 && password.length() > 0) {
                    ShowNotifyUser.showProgressDialog(ManHinhLogin.this,"Loading");
                    DemoCallAPI(username, password);
                } else {
                    Toast.makeText(ManHinhLogin.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
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

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticOthers.idUser=0;
                StaticOthers.username=null;
                finish();
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
            StaticOthers.idUser= info.get(0).getMaTaiKhoan();
            StaticOthers.username= info.get(0).getTenTaiKhoan();
            int maLoaiTK= info.get(0).getMaLoaiTaiKhoan();
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putInt("idUser",StaticOthers.idUser);
            editor.putString("user",user.getText().toString());
            editor.putString("pass",pass.getText().toString());
            editor.commit();
            if(maLoaiTK!=1){
                finish();
//                Intent intent = new Intent(ManHinhLogin.this, ManHinhChinh.class);
//                startActivity(intent);
            } else {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(ManHinhLogin.this, ManHinhChinhAdmin.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        }
        ShowNotifyUser.dismissProgressDialog();
        return;
    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("loi", error + "");
        try {
            ShowNotifyUser.dismissProgressDialog();
        }catch (Exception e){

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        StaticOthers.idUser=0;
        StaticOthers.username=null;
    }
}



