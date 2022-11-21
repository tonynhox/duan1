package com.example.duan1;

import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TaiKhoan;
import com.example.duan1.models.ThuongHieu;

import java.util.ArrayList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAPI {
    String BASE_Service = "https://ahuytran.kynalab.com/";
    //https://ahuytran.kynalab.com/api/GetAllSanPham
//https://ahuytran.kynalab.com/checkTaiKhoan
    @GET("api/all-ThuongHieu")
    Observable<ArrayList<ThuongHieu>> getAllThuongHieu();

    @GET("api/GetAllSanPham")
    Observable<ArrayList<SanPham>> getALLSanPham();
    @GET("api/checkTaiKhoan")
    Observable<ArrayList<CheckTaiKhoan>> checkTaiKhoan(@Query("tenTaiKhoan") String tenTaiKhoan,
                                                       @Query("matKhau") String matKhau);

}


