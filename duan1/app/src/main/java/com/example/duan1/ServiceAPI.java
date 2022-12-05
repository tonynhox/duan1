package com.example.duan1;

import com.example.duan1.models.CheckTaiKhoan;
import com.example.duan1.models.ChiTietHoaDon;
import com.example.duan1.models.DonHang;
import com.example.duan1.models.HoaDon;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TaiKhoan;
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

    //1
    @POST("api/addSanPham")
    Observable<Integer> addSanPham(@Query("tenSP") String tenSP,@Query("giaSP") long giaSP,
                                   @Query("maThuongHieu") int maThuongHieu,@Query("motaSP") String motaSP,
                                   @Query("soLuong") int soLuong,
                                   @Query("hinhAnhLon") String hinhAnhLon,
                                   @Query("hinhAnhNho") String hinhAnhNho);


    //2
    @POST("api/addTaiKhoan")
    Observable<Integer> addTaiKhoan(@Query("tenTaiKhoan") String tenTaiKhoan,
                                    @Query("matKhau") String matKhau,
                                    @Query("hoTen") String hoTen,
                                    @Query("namSinh")String namSinh,
                                    @Query("soDienThoai") String soDienThoai,
                                    @Query("email") String email);

    //3
    @POST("api/capNhatMatKhau")
    Observable<Integer> capNhatMatKhau(@Query("maTaiKhoan") int maTaiKhoan,
                                                       @Query("matKhauCu") String matKhauCu,
                                                        @Query("matKhauMoi") String matKhauMoi);


    //4
    @POST("api/capNhatSanPham")
    Observable<Integer> capNhatSanPham(@Query("maSP") int maSP,@Query("tenSP") String tenSP,
                                       @Query("giaSP") long giaSP,
                                       @Query("motaSP") String motaSP,
                                       @Query("soLuong") int soLuong,
                                       @Query("hinhAnhLon") String hinhAnhLon,
                                       @Query("hinhAnhNho") String hinhAnhNho);

    //5
    @POST("api/capNhatTrangThaiHD")
    Observable<Integer> capNhatTrangThaiHD(@Query("maHoaDon") int maHoaDon,
                                       @Query("trangThaiHD") String trangThaiHD);
    //6
    @POST("api/checkTaiKhoan")
    Observable<ArrayList<CheckTaiKhoan>> checkTaiKhoan(@Query("tenTaiKhoan") String tenTaiKhoan,
                                                       @Query("matKhau") String matKhau);

    //7
    @GET("api/getAllLichSuHoaDon")
    Observable<ArrayList<HoaDon>> getAllLichSuHoaDon();

    //8
    @GET("api/getAllTaiKhoanKH")
    Observable<ArrayList<TaiKhoan>> getAllTaiKhoanKH();

    //9
    @GET("api/getAllTrangThaiHoaDon")
    Observable<ArrayList<HoaDon>> getAllTrangThaiHoaDon();

    //10
    @GET("api/getLichSuHoaDonKH")
    Observable<ArrayList<HoaDon>> getLichSuHoaDonKH(@Query("tenTaiKhoan") String tenTaiKhoan);

    //11
    @GET("api/getSanPham")
    Observable<ArrayList<SanPham>> getSanPham(@Query("maSP") int maSP);

    //12
    @GET("api/getSanPhamHD")
    Observable<ArrayList<ChiTietHoaDon>> getSanPhamHD(@Query("maHD") int maHD);

    //13
    @GET("api/GetSanPhamHot")
    Observable<ArrayList<SanPham>> getSanPhamHot();

    //14
    @GET("api/getSPThuongHieu")
    Observable<ArrayList<TimKiemSanPham>> timKiemThuongHieu(@Query("tenThuongHieu") String tenThuongHieu);

    //15
    @GET("api/getTaiKhoan")
    Observable<ArrayList<TaiKhoan>> getTaiKhoan(@Query("tenTaiKhoan") String tenTaiKhoan);

    //16
    @GET("api/getTimKiemSanPham")
    Observable<ArrayList<TimKiemSanPham>> timKiemSanPham(@Query("tenSanPham") String tenSanPham);

    //17
    @GET("api/getTrangThaiHoaDonKH")
    Observable<ArrayList<HoaDon>> getTrangThaiHoaDonKH(@Query("tenTaiKhoan") String tenTaiKhoan);

    //18
    @POST("api/xoaSP")
    Observable<Integer> xoaSP(@Query("maSP") int maSP);

    //19
    @POST("api/xoaTaiKhoanKH")
    Observable<Integer> xoaTaiKhoanKH(@Query("tenTaiKhoan") String tenTaiKhoan);

    //20
    @POST("api/xoaTaiKhoan")
    Observable<Integer> xoaTaiKhoan(@Query("tenTaiKhoan") String tenTaiKhoan,@Query("matKhau") String matKhau);




























}


