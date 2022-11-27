package com.example.duan1;

import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.models.DonHang;
import com.example.duan1.models.HoaDon;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.ThuongHieu;
import com.example.duan1.models.TimKiemSanPham;

import java.util.ArrayList;
import java.util.Date;

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

    @POST("api/addTaiKhoan")
    Observable<Integer> addTaiKhoan(@Query("tenTaiKhoan") String tenTaiKhoan,
                                                   @Query("matKhau") String matKhau,
                                                   @Query("hoTen") String hoTen,
                                                   @Query("namSinh")String namSinh,
                                                   @Query("soDienThoai") String soDienThoai,
                                                   @Query("email") String email);
    @GET("api/getAllLichSuHoaDon")
    Observable<ArrayList<HoaDon>> getAllLichSuHoaDon();
    @GET("api/getAllTrangThaiHoaDon")
    Observable<ArrayList<HoaDon>> getAllTrangThaiHoaDon();

    @GET("api/getLichSuHoaDonKH")
    Observable<ArrayList<HoaDon>> getLichSuHoaDonKH(@Query("tenTaiKhoan") String tenTaiKhoan);
    @GET("api/getTrangThaiHoaDonKH")
    Observable<ArrayList<HoaDon>> getTrangThaiHoaDonKH(@Query("tenTaiKhoan") String tenTaiKhoan);

}


