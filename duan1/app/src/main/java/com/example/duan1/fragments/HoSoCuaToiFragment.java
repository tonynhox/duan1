package com.example.duan1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.adapter.SanPhamTH_SearchAdapter;
import com.example.duan1.models.TaiKhoan;
import com.example.duan1.models.TimKiemSanPham;
import com.example.duan1.others.ShowNotifyUser;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HoSoCuaToiFragment extends Fragment {
    ArrayList<TaiKhoan> list;
    TextView txtHoten,txtUser,txtDate,txtSDT,txtEmail,txtDoiMK;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hosocuatoi, container, false);
        txtHoten = view.findViewById(R.id.txtHoten);
        txtUser = view.findViewById(R.id.txtUser);
        txtDate = view.findViewById(R.id.txtDate);
        txtSDT = view.findViewById(R.id.txtSDT);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtDoiMK = view.findViewById(R.id.txtDoiMK);
        DemoCallAPI(StaticOthers.username);
        return view;
    }

    private void DemoCallAPI(String tenTK) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getTaiKhoan(tenTK)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<TaiKhoan> info) {
        //Xử lý chức năng
        list=info;
        if(list.size()>0){
            txtHoten.setText("Họ tên: "+list.get(0).hoTen);
            txtUser.setText("Tên đăng nhập: "+list.get(0).tenTaiKhoan);
            txtDate.setText("Ngày sinh: "+list.get(0).namSinh.substring(0,10));
            txtSDT.setText("Số điện thoại "+list.get(0).soDienThoai);
            txtEmail.setText("Email: "+list.get(0).email);
        }





    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay",error+"");
    }

}
