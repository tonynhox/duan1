package com.example.duan1.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.activity.ManHinhChinhAdmin;
import com.example.duan1.adapter.SanPhamTHAdminAdapter;
import com.example.duan1.fragments.HomeFragment;
import com.example.duan1.fragments.SanPhamTHFragment;
import com.example.duan1.fragments.SearchSanPhamFragment;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TimKiemSanPham;
import com.example.duan1.others.ShowNotifyUser;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView ivHinhAnhLon,ivHinhAnhNho,ivGioHang,icBack;
    TextView txtTenSP,txtGiaSP,txtSoLuongSP,txtTenThuongHieu,txtMoTaSP;
    HashMap<Integer,String> map=new HashMap<Integer,String>();
    int a=-1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chitietsanpham);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txtTenSP = findViewById(R.id.txtTenSP);
        txtGiaSP = findViewById(R.id.txtGiaSP);
        txtSoLuongSP = findViewById(R.id.txtSoLuongSP);
        txtTenThuongHieu = findViewById(R.id.txtTenThuongHieu);
        txtMoTaSP = findViewById(R.id.txtMoTaSP);
        ivHinhAnhLon = findViewById(R.id.ivHinhAnhLon);
        ivHinhAnhNho = findViewById(R.id.ivHinhAnhNho);
        ivGioHang = findViewById(R.id.ivGioHang);
        icBack = findViewById(R.id.ic_back);
        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtMoTaSP.setMovementMethod(new ScrollingMovementMethod());
        DemoCallAPI(HomeFragment.maSP);
        DemoCallAPI(SearchSanPhamFragment.maSP);
        DemoCallAPI(SanPhamTHFragment.maSP);

    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_chitietsanpham, container, false);
//        txtTenSP = view.findViewById(R.id.txtTenSP);
//        txtGiaSP = view.findViewById(R.id.txtGiaSP);
//        txtSoLuongSP = view.findViewById(R.id.txtSoLuongSP);
//        txtTenThuongHieu = view.findViewById(R.id.txtTenThuongHieu);
//        txtMoTaSP = view.findViewById(R.id.txtMoTaSP);
//        ivHinhAnhLon = view.findViewById(R.id.ivHinhAnhLon);
//        ivHinhAnhNho = view.findViewById(R.id.ivHinhAnhNho);
//        ivGioHang = view.findViewById(R.id.ivGioHang);
//        txtMoTaSP.setMovementMethod(new ScrollingMovementMethod());
//        DemoCallAPI(HomeFragment.maSP);
//        DemoCallAPI(SearchSanPhamFragment.maSP);
//        DemoCallAPI(SanPhamTHFragment.maSP);
//
//        return view;
//    }

    private void DemoCallAPI(int maSP) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getSanPham(maSP)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<SanPham> info) {
        //Xử lý chức năng
        if (info.size()>0){

            map.put(1,"IPHONE");
            map.put(2,"SAMSUNG");
            map.put(3,"OPPO");
            map.put(4,"VIVO");
            map.put(5,"XIAOMI");
            map.put(6,"REDMI");

            for(Map.Entry m : map.entrySet()){
                Log.d("key ne", m.getKey()+"-"+m.getValue());
                int a= (Integer) m.getKey();
                if(a==info.get(0).getMaThuongHieu())
                    txtTenThuongHieu.setText(m.getValue().toString());
            }
            txtTenSP.setText(info.get(0).getTenSp());
            DecimalFormat formatter = new DecimalFormat("###,###,###");

            txtGiaSP.setText(formatter.format(info.get(0).getGiaSp()));
            txtSoLuongSP.setText(info.get(0).getSoLuongSp()+"");
            txtMoTaSP.setText(info.get(0).getMotaSp()+"");
            Glide.with(this).load(info.get(0).getHinhAnhLon()).into(ivHinhAnhLon);
            Glide.with(this).load(info.get(0).getHinhAnhNho()).into(ivHinhAnhNho);

//            ivHinhAnhLon.setText(info.get(0).getTenSp());
//            ivHinhAnhNho.setText(info.get(0).getTenSp());
            HomeFragment.maSP=-1;
            SanPhamTHFragment.maSP=-1;
            SearchSanPhamFragment.maSP=-1;


        }


    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay",error+"");
        ShowNotifyUser.dismissProgressDialog();

    }
}
