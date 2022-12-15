package com.example.duan1;

import com.example.duan1.models.CapNhatMK;
import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.models.DonHang;
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

    @POST("api/CapNhatMatKhau")
<<<<<<< HEAD
    Observable<ArrayList<CapNhatMK>> CapNhatMatKhau (@Query("maTaiKhoan") int tk , @Query("matKhauCu") String matkhaucu,
                                                    @Query("matKhauMoi") String matKhau);
=======
    Observable<ArrayList<CheckTaiKhoan>> CapNhatMatKhau(@Query("maTaiKhoan") Integer maTaiKhoan ,@Query("matKhauCu") String matKhauCu,
                                                       @Query("matKhauMoi") String matKhau);
>>>>>>> 01ed573fa2ea5b032312d64aea0f80503a9c8060

    // trả về int mà ??

    @POST("api/addTaiKhoan")
    Observable<Integer> addTaiKhoan(@Query("tenTaiKhoan") String tenTaiKhoan,
                                                   @Query("matKhau") String matKhau,
                                                   @Query("hoTen") String hoTen,
                                                   @Query("namSinh")String namSinh,
                                                   @Query("soDienThoai") String soDienThoai,
                                                   @Query("email") String email);

}



