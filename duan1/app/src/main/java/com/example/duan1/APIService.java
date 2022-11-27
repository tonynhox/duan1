package com.example.duan1;

import com.example.duan1.models.CapNhatMK;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    APIService  apiService = new Retrofit.Builder()
            .baseUrl("https://trongtre.kynalab.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService .class);


    @POST("api/CapNhatMatKhau")
    Observable<ArrayList<CapNhatMK>> CapNhatMatKhau(@Query("maTaiKhoan") int tk , @Query("matKhauCu") String matkhaucu,
                                                    @Query("matKhauMoi") String matKhau);
}

