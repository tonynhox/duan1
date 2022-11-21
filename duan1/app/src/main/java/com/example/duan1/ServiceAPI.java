package com.example.duan1;

import android.database.Observable;

import com.example.duan1.models.SanPham;
import com.example.duan1.models.ThuongHieu;

import java.util.ArrayList;

import retrofit2.http.GET;

public interface ServiceAPI {
    String BASE_Service = "https://ahuytran.kynalab.com/";
    //https://ahuytran.kynalab.com/api/all-ThuongHieu

    @GET("api/all-ThuongHieu")
    Observable<ArrayList<ThuongHieu>> getAllThuongHieu();

    @GET("api/GetAllSanPham")
    Observable<ArrayList<SanPham>> getALLSanPham();



}


