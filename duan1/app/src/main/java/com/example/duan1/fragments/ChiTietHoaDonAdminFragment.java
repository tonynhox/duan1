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
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.adapter.ChiTietHoaDonAdapter;
import com.example.duan1.adapter.SanPhamAdminAdapter;
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

public class ChiTietHoaDonAdminFragment extends Fragment {
    ArrayList<ChiTietHoaDon> list;
    RecyclerView listViewSP;
    TextView textView;
    int a = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitietdonhang, container, false);
        listViewSP = view.findViewById(R.id.list_chitietsanpham);
        textView= view.findViewById(R.id.txtTongTien);
        int maHD;
        if(StaticOthers.username.equalsIgnoreCase("admin")){
            maHD=HistoryAdminFragment.maHD;

        }else {
            maHD=HistoryFragment.maHD;

        }

        CallAPI(maHD);
        return view;
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
            a+=item.getGiaSP();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        textView.setText(formatter.format(a)+" VND");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listViewSP.setLayoutManager(linearLayoutManager);
        ChiTietHoaDonAdapter adapter = new ChiTietHoaDonAdapter(list,getContext());
        listViewSP.setAdapter(adapter);
        ShowNotifyUser.dismissProgressDialog();

    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay","loi");
    }

}
