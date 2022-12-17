package com.example.duan1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.fragments.HistoryFragment;
import com.example.duan1.fragments.HomeFragment;
import com.example.duan1.fragments.MeFragment;
import com.example.duan1.fragments.SanPhamTHFragment;
import com.example.duan1.fragments.SearchSanPhamFragment;
import com.example.duan1.models.SanPham;
import com.example.duan1.others.ShowNotifyUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Map;

public class  ManHinhChinh extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    EditText editText;
    Fragment fragment;
    public static ImageView image;
    FragmentManager fragmentManager;
    public static String a,gia1,gia2;
    public static TextView txtTitle;
    public static TextView txtSua;
    public static ImageView ivFiller;
    public EditText edtGia1,edtGia2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        editText = findViewById(R.id.edtTim);
        txtTitle = findViewById(R.id.txtTitle);
        image = findViewById(R.id.ivGioHang);
        ivFiller = findViewById(R.id.ivFiller);
        txtSua= findViewById(R.id.txtEdit);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinh.this, GioHangActivity.class);
                startActivity(intent);

            }
        });
        ivFiller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogFiller();
            }
        });



        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_foreground);
        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();

        //bắt sự kiên enter
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                        (event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    a= editText.getText().toString();
                    fragment = new SearchSanPhamFragment();
                    fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
                    Toast.makeText(ManHinhChinh.this, editText.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dtSS:
                        fragment = new SanPhamTHFragment();
                        a= "samsung";
                        changToolbar();
                        txtTitle.setText("SAMSUNG");
                        break;
                    case R.id.dtVV:
                        fragment = new SanPhamTHFragment();
                        a= "vivo";
                        changToolbar();
                        txtTitle.setText("VIVO");
                        break;
                    case R.id.dtIP:
                        fragment = new SanPhamTHFragment();
                        a= "iphone";
                        changToolbar();
                        txtTitle.setText("IPHONE");
                        break;
                    case R.id.dtOP:
                        fragment = new SanPhamTHFragment();
                        a= "oppo";
                        changToolbar();
                        txtTitle.setText("OPPO");
                        break;
                    case R.id.dtRM:
                        fragment = new SanPhamTHFragment();
                        a= "asus";
                        changToolbar();
                        txtTitle.setText("Asus");
                        break;
                    case R.id.dtXM:
                        fragment = new SanPhamTHFragment();
                        a= "xiaomi";
                        changToolbar();
                        txtTitle.setText("XIAOMI");
                        break;

                }
                fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
        BottomNavigationView bottomNavigationView;
        HomeFragment homeFragment = new HomeFragment();
        HistoryFragment historyFragment = new HistoryFragment();
        MeFragment meFragment = new MeFragment();

        bottomNavigationView = findViewById(R.id.navigationBottom);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        editText.setVisibility(View.VISIBLE);
                        txtTitle.setVisibility(View.GONE);
                        txtSua.setVisibility(View.GONE);
                        image.setVisibility(View.VISIBLE);
                        ivFiller.setVisibility(View.VISIBLE);

                        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, homeFragment).commit();
                        break;
                    case R.id.history:
                        changToolbar();

                        txtTitle.setText("Trạng thái đơn hàng");
                        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, historyFragment).commit();
                        break;
                    case R.id.me:
                        changToolbar();

                        txtTitle.setText("Tôi");
                        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, meFragment).commit();
                        break;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void changToolbar(){
        txtTitle.setVisibility(View.VISIBLE);
        editText.setVisibility(View.GONE);
        txtSua.setVisibility(View.GONE);
        image.setVisibility(View.VISIBLE);
        ivFiller.setVisibility(View.GONE);

    }

    private void showDialogFiller(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_locsanpham, null);
        builder.setView(view);
        AlertDialog alertDialogLoc = builder.create();
        edtGia1= view.findViewById(R.id.edtGia1);
        edtGia2= view.findViewById(R.id.edtGia2);
        Button btnTim=view.findViewById(R.id.btnSearch);
        Button btnCancel=view.findViewById(R.id.btnHuy);

        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String pGia1=edtGia1.getText().toString();
//                String pGia2=edtGia2.getText().toString();

                    gia1 = edtGia1.getText().toString();
                    gia2 = edtGia2.getText().toString();
                    String isNum= "\\d+";
                if(!gia1.matches(isNum)||!gia2.matches(isNum)){
                    Toast.makeText(ManHinhChinh.this, "Vui lòng nhập số", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    fragment = new SearchSanPhamFragment();
                    fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
                    alertDialogLoc.dismiss();
                }


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


}