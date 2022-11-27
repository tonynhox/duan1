package com.example.duan1.activity;

import static com.example.duan1.activity.ManHinhLogin.taikhoan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.APIService;
import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.models.CapNhatMK;
import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.others.ShowNotifyUser;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoiMatKhau extends AppCompatActivity {


    EditText txtCurentPassword;
    EditText txtNewPassword;
    EditText txtRePassword;
    Button btnCancel;
    Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_doimatkhau);

        txtCurentPassword =  findViewById(R.id.edt_passOld );
        txtNewPassword =  findViewById(R.id.edt_passNew);
        txtRePassword =  findViewById(R.id.edt_pass);
        btnCancel = findViewById(R.id.register);
        btnAccept =  findViewById(R.id.login);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCurentPassword.setText(null);
                txtNewPassword.setText(null);
                txtRePassword.setText(null);

            }

        });
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
    public ArrayList<CapNhatMK> getlistTK(){
        APIService.apiService.CapNhatMatKhau()
    }
//    private void EventButton() {
//        txtCurentPassword =  findViewById(R.id.edt_passOld );
//        txtNewPassword =  findViewById(R.id.edt_passNew);
//        txtRePassword =  findViewById(R.id.edt_pass);
//        btnCancel = findViewById(R.id.register);
//        btnAccept =  findViewById(R.id.login);
//
//    }

//    private void initIteminActivity() {
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DoiMatKhau.this,ManHinhChinh.class);
//            }
//        });
//
//        btnAccept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (txtCurentPassword.getText().length() == 0 || txtNewPassword.getText().length() == 0 || txtRePassword.getText().length() == 0)
//                    Toast.makeText(getBaseContext(), "vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
//
//            else {
//                     String resmatkhau = txtCurentPassword.getText().toString();
//                     String matkhau = txtRePassword.getText().toString();
//                    if (resmatkhau.equals(matkhau)){
//                        Log.e("admin",matkhau+resmatkhau);
//                        Toast.makeText(getBaseContext(),"Mật Khẩu nhập lại không đúng",Toast.LENGTH_SHORT).show();
//                        doimatkhau();
//                        DemoCallAPI(taikhoan,resmatkhau,matkhau);
//                    }
//
//
//                }
//            }
//
//            private void doimatkhau() {
//                String matkhaucu = txtCurentPassword.getText().toString();
//                String matkhaumoi = txtNewPassword.getText().toString();
//                String matkhau = txtRePassword.getText().toString();
//
//            }
//
//            private void DemoCallAPI(String taikhoan, String resmatkhau, String matkhau) {
//
//                ServiceAPI requestInterface = new Retrofit.Builder()
//                        .baseUrl(ServiceAPI.BASE_Service)
//                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build().create(ServiceAPI.class);
//                new CompositeDisposable().add(requestInterface.CapNhatMatKhau(taikhoan,resmatkhau,matkhau)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeOn(Schedulers.io())
//                        .subscribe(this::handleResponse, this::handleError)
//                );
//            }
//                private void handleResponse(ArrayList<DoiMatKhau> info) {
//
//                if (info.size() > 0) {
//                    Intent intent = new Intent(DoiMatKhau.this, ManHinhChinh.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(DoiMatKhau.this, "sai", Toast.LENGTH_SHORT).show();
//                }
//            }
//            private void handleError(Throwable error) {
//                //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
//                Log.d("loi", error + "");
//                ShowNotifyUser.dismissProgressDialog();
//            }
//        });
//
//        }

    }


