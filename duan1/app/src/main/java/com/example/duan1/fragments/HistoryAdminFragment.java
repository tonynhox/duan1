package com.example.duan1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.activity.ChiTietDonHangActivity;
import com.example.duan1.adapter.LichSuHoaDonAdapter;
import com.example.duan1.adapter.TrangThaiDonHangAdminAdapter;
import com.example.duan1.models.ChiTietHoaDon;
import com.example.duan1.models.HoaDon;
import com.example.duan1.others.ItemOnClickHD;
import com.example.duan1.others.ShowNotifyUser;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryAdminFragment extends Fragment implements ItemOnClickHD {
    ArrayList<HoaDon> listHD;
    RecyclerView listViewTTHD;
    ArrayList<HoaDon> listTTHD;
    ListView listViewHD;
    public static int maHD;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangthaidonhang, container, false);

        listViewTTHD = view.findViewById(R.id.list_trangthaidonhang);
        listViewHD = view.findViewById(R.id.lvLichsudonhang);
        CallAPI();
        return view;
    }
    private void CallAPI() {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getAllTrangThaiHoaDon()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<HoaDon> info) {
        //Xử lý chức năng
        Log.d("us",StaticOthers.username+"");

            listTTHD=info;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            listViewTTHD.setLayoutManager(linearLayoutManager);
            TrangThaiDonHangAdminAdapter adapter = new TrangThaiDonHangAdminAdapter(listTTHD,getContext(),this);
            listViewTTHD.setAdapter(adapter);
            ShowNotifyUser.dismissProgressDialog();

        CallAPI2();



    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay","error");
    }

    private void CallAPI2(){

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getAllLichSuHoaDon()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse2, this::handleError2)
        );
    }

    private void handleResponse2(ArrayList<HoaDon> info) {
        //Xử lý chức năng
        if (info.size() > 0) {
            listHD = info;
            LichSuHoaDonAdapter lichSuHoaDonAdapter = new LichSuHoaDonAdapter(listHD, getContext(),this);
            listViewHD.setAdapter(lichSuHoaDonAdapter);
            ShowNotifyUser.dismissProgressDialog();
        }

    }

    private void handleError2(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay","loi");
        ShowNotifyUser.dismissProgressDialog();

        Toast.makeText(getContext(), "Lỗi hệ thống, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void OnClickHoaDon(int maHD) {
        this.maHD=maHD;
//        Fragment fragment = new ChiTietHoaDonAdminFragment();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
        Intent intent= new Intent(getContext(), ChiTietDonHangActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnClickBtnNhan(int maHD) {
        ShowNotifyUser.showProgressDialog(getContext(),"Loading");
        CallAPIEditTrangThai(maHD,"Đang giao hàng");
    }

    @Override
    public void OnClickBtnHuy(int maHD) {
        ShowNotifyUser.showProgressDialog(getContext(),"Loading");
        CallAPIEditTrangThai(maHD,"Đã hủy");
        CallAPISl(maHD);
    }

    private void CallAPIEditTrangThai(int maHD,String ttHD){

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.capNhatTrangThaiHD(maHD,ttHD)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse3, this::handleError3)
        );
    }

    private void handleResponse3(int info) {
        //Xử lý chức năng
        if(info==1){
            Toast.makeText(getContext(),"Nhấn thành công", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getContext(), "Nhấn thất bại", Toast.LENGTH_SHORT).show();
        CallAPI();


    }

    private void handleError3(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay","loi");
        ShowNotifyUser.dismissProgressDialog();
        Toast.makeText(getContext(), "Lỗi hệ thống, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();

    }

    public void CallAPISl(int a) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getSanPhamHD(a)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse4, this::handleError4)
        );
    }

    private void handleResponse4(ArrayList<ChiTietHoaDon> info) {
        //Xử lý chức năng
        for (ChiTietHoaDon item:info
        ) {
            CallAPIFixSL(item.getMaSP(),item.getSoLuongSP()+item.getSoLuong());
        }

    }

    private void handleError4(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay", "loi");
        Toast.makeText(getContext(), "Lỗi hệ thống, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();

    }

    //uploadSoLuong
    private void CallAPIFixSL(int maSP, int soLuong) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.capNhatSoLuongSP(maSP,soLuong)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse5, this::handleError5)
        );
    }

    private void handleResponse5(int info) {

        Log.d("vo roi",  "so luong ok");

    }

    private void handleError5(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("loi", error + "");
        Toast.makeText(getContext(), "Lỗi hệ thống, xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();

        ShowNotifyUser.dismissProgressDialog();
    }
}
