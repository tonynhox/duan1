package com.example.duan1;

import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.ThuongHieu;
import com.example.duan1.models.TimKiemSanPham;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI {
    String BASE_Service = "https://ahuytran.kynalab.com/";
    //https://ahuytran.kynalab.com/api/GetAllSanPham
//https://ahuytran.kynalab.com/checkTaiKhoan
    @GET("api/all-ThuongHieu")
    Observable<ArrayList<ThuongHieu>> getAllThuongHieu();

    @GET("api/GetAllSanPham")
    Observable<ArrayList<SanPham>> getALLSanPham();

    @GET("api/GetSanPhamHot")
    Observable<ArrayList<SanPham>> getSanPhamHot();

    @POST("api/checkTaiKhoan")
    Observable<ArrayList<CheckTaiKhoan>> checkTaiKhoan(@Query("tenTaiKhoan") String tenTaiKhoan,
                                                       @Query("matKhau") String matKhau);
    @GET("api/getSPThuongHieu")
    Observable<ArrayList<TimKiemSanPham>> timKiemThuongHieu(@Query("tenThuongHieu") String tenThuongHieu);

    @GET("api/getTimKiemSanPham")
    Observable<ArrayList<TimKiemSanPham>> timKiemSanPham(@Query("tenSanPham") String tenSanPham);

    @POST("api/CapNhatMatKhau")
    Observable<ArrayList<CheckTaiKhoan>> CapNhatMatKhau(@Query("maTaiKhoan") String tk ,@Query("matkhaucu") String matkhaucu,
                                                       @Query("matKhau") String matKhau);


}



