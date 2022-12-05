package com.example.duan1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.activity.ChiTietSanPhamActivity;
import com.example.duan1.activity.ManHinhChinh;
import com.example.duan1.adapter.SanPhamTH_SearchAdapter;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TimKiemSanPham;
import com.example.duan1.others.ItemOnClick;
import com.example.duan1.others.ShowNotifyUser;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchSanPhamFragment extends Fragment implements ItemOnClick {
    ArrayList<TimKiemSanPham> list;
    RecyclerView listViewSP;
    public static int maSP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham, container, false);
        listViewSP = view.findViewById(R.id.recyclerView);
        ShowNotifyUser.showProgressDialog(view.getContext(), "Đang tìm kiếm");
        DemoCallAPI(ManHinhChinh.a);
        return view;
    }

    private void DemoCallAPI(String tenSP) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.timKiemSanPham(tenSP)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<TimKiemSanPham> info) {
        //Xử lý chức năng
        list=info;
        info.size();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listViewSP.setLayoutManager(linearLayoutManager);
        SanPhamTH_SearchAdapter adapter = new SanPhamTH_SearchAdapter(list,getContext(),this);
        listViewSP.setAdapter(adapter);
        ShowNotifyUser.dismissProgressDialog();
    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay",error+"");
        ShowNotifyUser.dismissProgressDialog();
    }

    @Override
    public void onClickItem(TimKiemSanPham timKiemSanPham) {
        maSP=timKiemSanPham.getMaSp();
        Log.d("ma san pham", maSP+"");
//        Fragment fragment = new ChiTietSanPhamFragment();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
        Intent intent = new Intent(getContext(), ChiTietSanPhamActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickItem(SanPham sanPham) {

    }

    @Override
    public void onClickXoa(int a) {

    }

    @Override
    public void onClickDialog(int a) {

    }
}
