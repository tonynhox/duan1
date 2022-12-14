package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.fragments.HomeFragment;
import com.example.duan1.fragments.SanPhamTHFragment;
import com.example.duan1.fragments.SearchSanPhamFragment;
import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.models.GioHang;
import com.example.duan1.models.MaHoaDon;
import com.example.duan1.models.SanPham;
import com.example.duan1.others.ShowNotifyUser;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThanhToanActivity extends AppCompatActivity {
    EditText edtDiaChi,edtSoDienThoai;
    Button thanhToan,huy;
    ImageView ivZalo,ivCod;
    int soluong=0,temp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_thanh_toan);
        edtDiaChi= findViewById(R.id.edtDiaChi);
        edtSoDienThoai= findViewById(R.id.edtSDT);
        thanhToan= findViewById(R.id.btnThanhToan);
        huy= findViewById(R.id.btnHuy);
        ivZalo= findViewById(R.id.ivZalo);
        ivCod= findViewById(R.id.ivShipCod);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivZalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivCod.setBackground(getDrawable(R.color.white));
                ivCod.setScaleX(1);
                ivCod.setScaleY(1);
                ivZalo.setBackground(getDrawable(R.drawable.custom_line));
                ivZalo.setScaleX(1.2F);
                ivZalo.setScaleY(1.2F);
                //code
                temp=1;
            }
        });
        ivCod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivZalo.setBackground(getDrawable(R.color.white));
                ivZalo.setScaleX(1);
                ivZalo.setScaleY(1);
                ivCod.setBackground(getDrawable(R.drawable.custom_line));
                ivCod.setScaleX(1.2F);
                ivCod.setScaleY(1.2F);
                temp=2;
                //code
            }
        });

            thanhToan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(temp!=0){
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
            soluong=item.getSoLuong();
            CallAPIAddSanPham(item.getMaSP(),maHD,item.getSoLuong(),item.getGiaSp());
            CallAPIFixSL(item.getMaSP(), item.getSoLuongTon()-item.getSoLuong());
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

    private void CallAPIFixSL(int maSP, int soLuong) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.capNhatSoLuongSP(maSP,soLuong)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse3, this::handleError3)
        );
    }

    private void handleResponse3(int info) {

        Log.d("vo roi",  "so luong ok");

    }

    private void handleError3(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("loi", error + "");
        ShowNotifyUser.dismissProgressDialog();
    }


}