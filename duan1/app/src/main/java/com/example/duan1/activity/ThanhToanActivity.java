package com.example.duan1.activity;

import androidx.annotation.RequiresApi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;

import com.example.duan1.models.GioHang;
import com.example.duan1.models.MaHoaDon;
import com.example.duan1.others.ShowNotifyUser;
import com.example.duan1.others.StaticOthers;
import com.example.duan1.zalopay.Api.CreateOrder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.json.JSONObject;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class ThanhToanActivity extends AppCompatActivity {
    EditText edtDiaChi,edtSoDienThoai;
    Button thanhToan,huy;
    ImageView ivZalo,ivCod;
    String etoken;
    int soluong=0,temp=0;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

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

        //zalopay
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);

        //<--end-->

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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(temp==2){
                    AddHoaDonTheoTT("Chưa thanh toán");
                }else if (temp==1){
                    CreateOrder orderApi = new CreateOrder();
                    try {
                        JSONObject data = orderApi.createOrder("100000");

                        String code = data.getString("return_code");
                        if (code.equals("1")) {
                            etoken = data.getString("zp_trans_token");

                            thanhToanZalo();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }else {
                    Toast.makeText(ThanhToanActivity.this, "Vui lòng chọn hình thức thanh toán", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void AddHoaDonTheoTT(String thanhToan){
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
        CallAPIGioHang(date,diaChi,sdt,thanhToan);
    }



    private void CallAPIGioHang(String ngayMua, String diaChi,String sdt,String thanhToan) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.addHoaDon(StaticOthers.idUser,ngayMua,diaChi,sdt,thanhToan)
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
//        if(temp==1){
//            CallAPIEditTrangThai(maHD,"Đang giao hàng");
//        }
        Toast.makeText(this, "Mua hàng thành công", Toast.LENGTH_SHORT).show();
        ShowNotifyUser.dismissProgressDialog();
        StaticOthers.listGH.clear();
//        Intent intent = new Intent(this, ManHinhLogin.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//        this.startActivity(intent);
        finish();





    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("loi", error + "");
        Toast.makeText(this, "Lỗi hệ thống, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "Lỗi hệ thống, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();

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
        Toast.makeText(this, "Lỗi hệ thống, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();

        Log.d("loi", error + "");
        ShowNotifyUser.dismissProgressDialog();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

    private void thanhToanZalo(){
        ZaloPaySDK.getInstance().payOrder(ThanhToanActivity.this, etoken, "demozpdk2://app", new PayOrderListener() {
            @Override
            public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                Toast.makeText(ThanhToanActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                AddHoaDonTheoTT("Đã thanh toán");
            }

            @Override
            public void onPaymentCanceled(String zpTransToken, String appTransID) {
                Toast.makeText(ThanhToanActivity.this, "Đã hủy thanh toán", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                Toast.makeText(ThanhToanActivity.this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void CallAPIEditTrangThai(int maHD,String ttHD){
//
//        ServiceAPI requestInterface = new Retrofit.Builder()
//                .baseUrl(ServiceAPI.BASE_Service)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(ServiceAPI.class);
//
//        new CompositeDisposable().add(requestInterface.capNhatTrangThaiHD(maHD,ttHD)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::handleResponse4, this::handleError4)
//        );
//    }
//
//    private void handleResponse4(int info) {
//
//    }
//
//    private void handleError4(Throwable error) {
//        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
//        Log.d("chay","loi");
//        Toast.makeText(this, "Lỗi hệ thống, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
//
//    }

}