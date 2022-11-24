package com.example.duan1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GioHang extends AppCompatActivity {
    ListView lvgiohang;
    TextView txttieptucmua;
    static TextView txttongtien;
    Button btnthanhtoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.fra);


    }
}
