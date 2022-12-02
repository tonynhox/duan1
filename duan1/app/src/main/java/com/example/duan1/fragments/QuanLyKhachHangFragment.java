package com.example.duan1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.adapter.QuanLyKhachHangAdapter;
import com.example.duan1.adapter.SanPhamTHAdminAdapter;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TaiKhoan;
import com.example.duan1.models.TimKiemSanPham;
import com.example.duan1.others.ItemOnClick;
import com.example.duan1.others.ItemOnClickDel;
import com.example.duan1.others.ShowNotifyUser;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.Notification;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuanLyKhachHangFragment extends Fragment implements ItemOnClickDel {
    ArrayList<TaiKhoan> list;
    RecyclerView listViewKH;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanlytaikhoan_admin, container, false);
        listViewKH = view.findViewById(R.id.rcView);
        ShowNotifyUser.showProgressDialog(getContext(),"Loading");
        CallAPI();
        return view;
    }

    private void CallAPI() {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getAllTaiKhoanKH()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<TaiKhoan> info) {
        //Xử lý chức năng
        list=info;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listViewKH.setLayoutManager(linearLayoutManager);
        QuanLyKhachHangAdapter adapter = new QuanLyKhachHangAdapter(list,getContext(),this);
        listViewKH.setAdapter(adapter);
        ShowNotifyUser.dismissProgressDialog();
    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay",error+"");
    }

    private void CallAPIDel(String user) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.xoaTaiKhoanKH(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse2, this::handleError2)
        );
    }

    private void handleResponse2(Integer info) {
        //Xử lý chức năng
        if(info == 1){
            Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            CallAPI();
        }else{
            Toast.makeText(getContext(),"Xóa Không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError2(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(getContext(),"Xóa Không thành công", Toast.LENGTH_SHORT).show();

    }



    @Override
    public void ItemClickDel(String tenTK) {
        CallAPIDel(tenTK);
    }
}
