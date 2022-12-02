package com.example.duan1.fragments;

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
import com.example.duan1.adapter.LichSuHoaDonAdapter;
import com.example.duan1.adapter.TrangThaiDonHangAdapter;
import com.example.duan1.adapter.TrangThaiDonHangAdminAdapter;
import com.example.duan1.models.HoaDon;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryAdminFragment extends Fragment {
    ArrayList<HoaDon> listHD;
    RecyclerView listViewTTHD;
    ArrayList<HoaDon> listTTHD;
    ListView listViewHD;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangthaidonhang, container, false);

        listViewTTHD = view.findViewById(R.id.list_trangthaidonhang);
        listViewHD = view.findViewById(R.id.lvLichsudonhang);
        CallAPI();
        CallAPI2();
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
        listTTHD=info;
        Log.d("us",StaticOthers.username+"");

        if (info.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            listViewTTHD.setLayoutManager(linearLayoutManager);
            TrangThaiDonHangAdminAdapter adapter = new TrangThaiDonHangAdminAdapter(listTTHD,getContext());
            listViewTTHD.setAdapter(adapter);
            Toast.makeText(getContext(), "hú", Toast.LENGTH_SHORT).show();

        }else
            Toast.makeText(getContext(), "huy loi", Toast.LENGTH_SHORT).show();


    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay","loi");
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
        listHD=info;
        LichSuHoaDonAdapter lichSuHoaDonAdapter = new LichSuHoaDonAdapter(listHD,getContext());
        listViewHD.setAdapter(lichSuHoaDonAdapter);
    }

    private void handleError2(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay","loi");
    }
}
