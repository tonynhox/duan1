package com.example.duan1.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;

import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoiMatKhauActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        EditText edt_passOld= findViewById(R.id.edt_passOld);
        EditText edt_passNew= findViewById(R.id.edt_passNew);
        EditText edt_passNew2= findViewById(R.id.edt_pass);
        Button btnHuy= findViewById(R.id.btnHuy);
        Button btnLuu= findViewById(R.id.btnLuu);



        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passOld,passNew,passNew2,passNewLast;
                passOld = edt_passOld.getText().toString();
                passNew= edt_passNew.getText().toString();
                passNew2= edt_passNew2.getText().toString();

                if(passOld.equals(passNew)){
                    Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu mới không được trùng với mật khẩu cũ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(passNew.equals(passNew2)){
                    passNewLast=passNew;
                }
                else
                    passNewLast="";

                DemoCallAPI(StaticOthers.idUser,passOld,passNewLast);
                Log.d("Id user", StaticOthers.idUser+"");
            }
        });
    }

    private void DemoCallAPI(int maTK, String passwordOld,String passwordNew) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.capNhatMatKhau(maTK, passwordOld,passwordNew)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(int info) {
        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);

        if(info==1){
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
        }



    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("loi", error + "");
        try {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();

        }catch (Exception e){

        }
    }
}
