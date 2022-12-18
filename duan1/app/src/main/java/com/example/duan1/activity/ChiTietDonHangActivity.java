package com.example.duan1.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.adapter.ChiTietHoaDonAdapter;
import com.example.duan1.adapter.SanPhamAdminAdapter;
import com.example.duan1.fragments.HistoryAdminFragment;
import com.example.duan1.fragments.HistoryFragment;
import com.example.duan1.models.ChiTietHoaDon;
import com.example.duan1.models.SanPham;
import com.example.duan1.others.ShowNotifyUser;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChiTietDonHangActivity extends AppCompatActivity {
    ArrayList<ChiTietHoaDon> list;
    RecyclerView listViewSP;
    TextView textView,btnThoat;
    int a = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chitietdonhang);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        listViewSP = findViewById(R.id.list_chitietsanpham);
        textView= findViewById(R.id.txtTongTien);
        btnThoat = findViewById(R.id.btnThoat);
        int maHD;

        if(StaticOthers.username.equalsIgnoreCase("admin")){
            maHD= HistoryAdminFragment.maHD;

        }else {
            maHD= HistoryFragment.maHD;

        }
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        CallAPI(maHD);
    }


    public void CallAPI(int a) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getSanPhamHD(a)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<ChiTietHoaDon> info) {
        //Xử lý chức năng
        list=info;
        for (ChiTietHoaDon item:info
             ) {
            a+=item.getGiaSP()* item.getSoLuong();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        textView.setText(formatter.format(a)+" VND");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listViewSP.setLayoutManager(linearLayoutManager);
        ChiTietHoaDonAdapter adapter = new ChiTietHoaDonAdapter(list,this);
        listViewSP.setAdapter(adapter);
        ShowNotifyUser.dismissProgressDialog();

    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(this, "Lỗi hệ thống, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
        Log.d("chay","loi");
    }

}
