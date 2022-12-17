package com.example.duan1.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.activity.DoiMatKhauActivity;
import com.example.duan1.activity.ManHinhChinh;
import com.example.duan1.activity.ManHinhRegister;
import com.example.duan1.adapter.SanPhamTH_SearchAdapter;
import com.example.duan1.models.TaiKhoan;
import com.example.duan1.models.TimKiemSanPham;
import com.example.duan1.others.ShowNotifyUser;
import com.example.duan1.others.StaticOthers;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HoSoCuaToiFragment extends Fragment {
    ArrayList<TaiKhoan> list;
    TextView txtHoten,txtUser,txtDate,txtSDT,txtEmail,txtDoiMK;
    String hoten,date,sdt,email;
    AlertDialog alertDialogLoc;
    Calendar calendar = Calendar.getInstance();
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hosocuatoi, container, false);
        txtHoten = view.findViewById(R.id.txtHoten);
        txtUser = view.findViewById(R.id.txtUser);
        txtDate = view.findViewById(R.id.txtDate);
        txtSDT = view.findViewById(R.id.txtSDT);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtDoiMK = view.findViewById(R.id.txtDoiMK);
        DemoCallAPI(StaticOthers.username);
        try {
            ManHinhChinh.image.setVisibility(View.GONE);
            ManHinhChinh.txtSua.setVisibility(View.VISIBLE);
        }catch (Exception e){

        }

        ManHinhChinh.txtSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSua();
            }
        });
        txtDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DoiMatKhauActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void DemoCallAPI(String tenTK) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getTaiKhoan(tenTK)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<TaiKhoan> info) {
        //Xử lý chức năng
        list=info;
        if(list.size()>0){
            hoten=list.get(0).hoTen;
            date= list.get(0).namSinh.substring(0,10);
            sdt= list.get(0).soDienThoai;
            email= list.get(0).email;

            txtHoten.setText("Họ tên: "+hoten);
            txtUser.setText("Tên đăng nhập: "+list.get(0).tenTaiKhoan);
            txtDate.setText("Ngày sinh: "+date);
            txtSDT.setText("Số điện thoại "+sdt);
            txtEmail.setText("Email: "+email);
        }





    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay",error+"");
    }


    private void showDialogSua(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_hosocanhan, null);
        builder.setView(view);
        alertDialogLoc = builder.create();

        EditText edtHoTen= view.findViewById(R.id.edtHoTen);
        EditText edtSDT= view.findViewById(R.id.edtSDT);
        TextView edtNamSinh= view.findViewById(R.id.edtNamSinh);
        EditText edtEmail= view.findViewById(R.id.edtEmail);
        Button btnLuu=view.findViewById(R.id.btnLuu);
        Button btnCancel=view.findViewById(R.id.btnHuy);


        edtHoTen.setText(hoten);
        edtNamSinh.setText(date);
        edtSDT.setText(sdt);
        edtEmail.setText(email);

        edtNamSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";

                                if (dayOfMonth < 10){
                                    ngay = "0" +dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }

                                if ((month + 1) < 10){
                                    thang = "0" + (month + 1);
                                }else {
                                    thang = String.valueOf(month+1);
                                }
                                edtNamSinh.setText(year + "-" + thang + "-" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numPhone="0\\d{9}";
                String pEmail="\\w+@\\w+(.\\w+){1,3}";//anhhuytran@fpt.edu.vn

                String nHoTen= edtHoTen.getText().toString();
                String nNamSinh= edtNamSinh.getText().toString();
                String nSdt= edtSDT.getText().toString();
                String nEmail= edtEmail.getText().toString();

                if(nHoTen.equals("")||nNamSinh.equals("")||nSdt.equals("")||nEmail.equals("")){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!nSdt.matches(numPhone)){
                    Toast.makeText(getContext(), "Số điện thoại không đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!nEmail.matches(pEmail)){
                    Toast.makeText(getContext(), "Email không đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
                CallAPISua(StaticOthers.idUser,nHoTen,nNamSinh,nSdt,nEmail);


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogLoc.dismiss();
            }
        });

        alertDialogLoc.show();
    }


    private void CallAPISua(int maUser,String hoten,String namSinh,String sdt,String email) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.capNhatTaiKhoan(maUser,hoten,namSinh,sdt,email)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse2, this::handleError2)
        );
    }

    private void handleResponse2(Integer info) {
        //Xử lý chức năng
        alertDialogLoc.dismiss();
        DemoCallAPI(StaticOthers.username);
        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

    }

    private void handleError2(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay",error+"");
    }

}
