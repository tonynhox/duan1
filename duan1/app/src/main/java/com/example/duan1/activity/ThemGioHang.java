package com.example.duan1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.models.GioHang;
import com.example.duan1.models.SanPham;
import com.example.duan1.others.StaticOthers;

import java.text.DecimalFormat;

public class ThemGioHang extends AppCompatActivity {
    TextView txtGiaSP, txtTenSP, txtXemChiTiet;
    ImageView them, hinhAnhLon;
    Spinner spinner;
    SanPham sanPham;
    String tenSP;
    String anh;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_sanpham);
        initData();
        initView();
        intitControl();
    }

    private void intitControl() {
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themGioHang();
            }
        });
    }

    private void themGioHang() {
        if (StaticOthers.listGH.size() > 0) {
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0; i < StaticOthers.listGH.size(); i++) {
                if (StaticOthers.listGH.get(i).getTenSp() == sanPham.getTenSp()) {
                    StaticOthers.listGH.get(i).setSoLuong(soluong + StaticOthers.listGH.get(i).getSoLuong());
                    long gia = Long.parseLong(String.valueOf(sanPham.getGiaSp() * StaticOthers.listGH.get(i).getSoLuong()));
                    StaticOthers.listGH.get(i).setGiaSp(gia);
                    flag = true;
                }
            }
            if (flag == false) {
                
                long gia = sanPham.getGiaSp() * soluong;
                GioHang gioHang = new GioHang();
                gioHang.setGiaSp(gia);
                gioHang.setSoLuong(soluong);
                gioHang.setTenSp(tenSP);
                gioHang.setHinhAnhLon(anh);
                StaticOthers.listGH.add(gioHang);

            }
        } else {
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            long gia = sanPham.getGiaSp() * soluong;
            GioHang gioHang = new GioHang();
            gioHang.setGiaSp(gia);
            gioHang.setSoLuong(soluong);
            gioHang.setTenSp(tenSP);
            gioHang.setHinhAnhLon(anh);
            StaticOthers.listGH.add(gioHang);


        }
    }


    private void initView() {
        txtTenSP = findViewById(R.id.txtTenSP);
        txtGiaSP = findViewById(R.id.txtGiaSP);
        txtXemChiTiet = findViewById(R.id.txtChiTiet);
        them = findViewById(R.id.themGioHang);
        hinhAnhLon = findViewById(R.id.imageView2);


    }

    private void initData() {
        sanPham = (SanPham) getIntent().getSerializableExtra("sanpham");
        txtTenSP.setText(sanPham.getTenSp());
        Glide.with(getApplicationContext()).load(sanPham.getHinhAnhLon()).into(hinhAnhLon);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaSP.setText("Giá" + decimalFormat.format(sanPham.getGiaSp()) + "Đ");
        Integer[] so = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, so);
        spinner.setAdapter(adapter);


    }
}
