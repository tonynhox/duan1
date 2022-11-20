package com.example.duan1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.adapter.SanPhamHotAdapter;
import com.example.duan1.models.SanPham;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    ArrayList<SanPham> list;
//    SanPhamHotAdapter adapter;
    RecyclerView listViewSP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanphamhot, container, false);
        listViewSP = view.findViewById(R.id.rcSanPhamHot);
        DemoCallAPI();
        return view;
    }
    private void DemoCallAPI() {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getALLSanPham()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<SanPham> info) {
        list=info;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listViewSP.setLayoutManager(linearLayoutManager);
        SanPhamHotAdapter adapter = new SanPhamHotAdapter(list,getContext());
        listViewSP.setAdapter(adapter);
        //khi gọi API THÀNH CÔNG thì thực hiện xử lý ở đây
//        Log.d("chay",list.size()+"");
//        for (SanPham item:info
//             ) {
//            Log.d("item",""+item.getTenSP());
//
//        }
//        Log.d("chay",list.size()+"");

    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay","loi");
    }

}