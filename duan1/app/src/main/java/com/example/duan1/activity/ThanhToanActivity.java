package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.models.GioHang;
import com.example.duan1.models.MaHoaDon;
import com.example.duan1.others.ShowNotifyUser;
import com.example.duan1.others.StaticOthers;
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

public class ThanhToanActivity extends AppCompatActivity {
    EditText edtDiaChi,edtSoDienThoai;
    Button thanhToan,huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        edtDiaChi= findViewById(R.id.edtDiaChi);
        edtSoDienThoai= findViewById(R.id.edtSDT);
        thanhToan= findViewById(R.id.btnThanhToan);
        huy= findViewById(R.id.btnHuy);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diaChi= edtDiaChi.getText().toString();
                String sdt= edtSoDienThoai.getText().toString();
                String numPhone="0\\d{9}";
                if(diaChi.equals("")||sdt.equals("")){
                    Toast.makeText(ThanhToanActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }if (!sdt.matches(numPhone)){
                    Toast.makeText(ThanhToanActivity.this, "Số điện thoại không đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
                String date = df.format(Calendar.getInstance().getTime());
                Log.d("us me", StaticOthers.idUser+"");
                ShowNotifyUser.showProgressDialog(ThanhToanActivity.this,"Loading");
                CallAPIGioHang(date,diaChi,sdt);


            }
        });
    }

    private void CallAPIGioHang(String ngayMua, String diaChi,String sdt) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.addHoaDon(StaticOthers.idUser,ngayMua,diaChi,sdt)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(MaHoaDon info) {

        int maHD= info.getMaHoaDon();
        for (GioHang item:StaticOthers.listGH) {
            CallAPIAddSanPham(item.getMaSP(),maHD,item.getSoLuong(),item.getGiaSp());
        };
        Toast.makeText(this, "Mua hàng thành công", Toast.LENGTH_SHORT).show();
        ShowNotifyUser.dismissProgressDialog();
        StaticOthers.listGH.clear();
        finish();

    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("loi", error + "");
        Toast.makeText(this, "fail rồi error", Toast.LENGTH_SHORT).show();

        ShowNotifyUser.dismissProgressDialog();
    }

    private void CallAPIAddSanPham(int maSP, int maHD,int soLuong,long giaSP) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.addChiTietHoaDon(maSP,maHD,soLuong,giaSP)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse2, this::handleError2)
        );
    }

    private void handleResponse2(int info) {


    }

    private void handleError2(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("loi", error + "");
        ShowNotifyUser.dismissProgressDialog();
    }
}