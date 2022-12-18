package com.example.duan1.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.adapter.ChiTietHoaDonAdapter;
import com.example.duan1.adapter.GioHangAdapter;
import com.example.duan1.fragments.NoLoginFragment;
import com.example.duan1.models.ChiTietHoaDon;
import com.example.duan1.models.GioHang;
import com.example.duan1.others.StaticOthers;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    ArrayList<GioHang> list;
    RecyclerView listView;
    public static TextView txtTong;
    Button btnThanhToan;
    TextView btnTTMua;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_giohang);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        listView = findViewById(R.id.list_donhang);
        txtTong= findViewById(R.id.txtTong);
        btnThanhToan= findViewById(R.id.btnThanhToan);
        btnTTMua= findViewById(R.id.btnMua);
        list=StaticOthers.listGH;
        LoadData();
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticOthers.listGH.size()>0){
                    if(StaticOthers.idUser!=0){
                        Intent intent= new Intent(GioHangActivity.this,ThanhToanActivity.class);
                        startActivity(intent);
                    }else {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(GioHangActivity.this);
                        builder.setMessage("Vui lòng đăng nhập");
                        builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(GioHangActivity.this,ManHinhLogin.class));
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(GioHangActivity.this,"Đã hủy",Toast.LENGTH_SHORT).show();
                            }
                        });
                        android.app.AlertDialog alertDialog= builder.create();
                        alertDialog.show();

                    }







                }else
                    Toast.makeText(GioHangActivity.this, "Vui lòng thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();

            }
        });
        btnTTMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    void LoadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        GioHangAdapter adapter = new GioHangAdapter(list,this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        long a=0;
        LoadData();
        for (GioHang item:StaticOthers.listGH
        ) {
            a+=item.getSoLuong()*item.getGiaSp();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        txtTong.setText(formatter.format(a)+" VND");
    }
}
