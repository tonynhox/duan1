package com.example.duan1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.adapter.DonHangAdapter;
import com.example.duan1.others.StaticOthers;

public class GioHang extends AppCompatActivity {
    TextView txtTenSP, txtGiaSP;
    ImageView hinhAnh,gioHang;
    RecyclerView recyclerView;
    DonHangAdapter adapter;
    Toolbar toolbar;
    
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_giohang);
        initView();
        initControl();
    }

    private void initView() {
        txtTenSP = findViewById(R.id.txtTenSP);
        txtGiaSP = findViewById(R.id.txtGiaSP);
        recyclerView = findViewById(R.id.list_donhang);
        hinhAnh = findViewById(R.id.ivHinhAnhLon);
        gioHang = findViewById(R.id.ivGioHang);
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if(StaticOthers.listGH.size() == 0){
            gioHang.setVisibility(View.VISIBLE);
        }else{
            adapter = new DonHangAdapter(getApplicationContext(), StaticOthers.listGH);
            recyclerView.setAdapter(adapter);
        }
       
        
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

}
