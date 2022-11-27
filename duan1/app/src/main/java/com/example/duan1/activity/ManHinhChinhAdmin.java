package com.example.duan1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.fragments.HistoryFragment;
import com.example.duan1.fragments.HomeAdminFragment;
import com.example.duan1.fragments.HomeFragment;
import com.example.duan1.fragments.MeFragment;
import com.example.duan1.fragments.QuanLyKhachHangFragment;
import com.example.duan1.fragments.SanPhamTHAdminFragment;
import com.example.duan1.fragments.SanPhamTHFragment;
import com.example.duan1.fragments.SearchSanPhamFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class  ManHinhChinhAdmin extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    EditText editText;
    Fragment fragment;
    FragmentManager fragmentManager;
    public static String b;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahinhchinh_admin);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        editText = findViewById(R.id.edtTim);
        txtTitle = findViewById(R.id.txtTitle);



        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_foreground);

        fragmentManager = getSupportFragmentManager();
        fragment = new HomeAdminFragment();
        fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();

        //bắt sự kiên enter
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                        (event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    b= editText.getText().toString();
                    fragment = new SearchSanPhamFragment();
                    fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
                    Toast.makeText(ManHinhChinhAdmin.this, editText.getText(), Toast.LENGTH_SHORT).show();
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
                        fragment = new SanPhamTHAdminFragment();
                        b= "samsung";
                        txtTitle.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        txtTitle.setText("SAMSUNG");
                        break;
                    case R.id.dtVV:
                        fragment = new SanPhamTHAdminFragment();
                        b= "vivo";
                        txtTitle.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        txtTitle.setText("VIVO");
                        break;
                    case R.id.dtIP:
                        fragment = new SanPhamTHAdminFragment();
                        b= "iphone";
                        txtTitle.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        txtTitle.setText("IPHONE");
                        break;
                    case R.id.dtOP:
                        fragment = new SanPhamTHAdminFragment();
                        b= "oppo";
                        txtTitle.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        txtTitle.setText("OPPO");
                        break;
                    case R.id.dtRM:
                        fragment = new SanPhamTHAdminFragment();
                        b= "redmi";
                        txtTitle.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        txtTitle.setText("REDMI");
                        break;
                    case R.id.dtXM:
                        fragment = new SanPhamTHAdminFragment();
                        b= "xiaomi";
                        txtTitle.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        txtTitle.setText("XIAOMI");
                        break;
                    case R.id.dsMember:
                        fragment = new QuanLyKhachHangFragment();
                        txtTitle.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        txtTitle.setText("Quản lý khách hàng");
                        break;

                }
                fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
        BottomNavigationView bottomNavigationView;
        HomeAdminFragment homeFragment = new HomeAdminFragment();
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, homeFragment).commit();
                        break;
                    case R.id.history:
                        txtTitle.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        txtTitle.setText("Trạng thái đơn hàng");
                        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, historyFragment).commit();
                        break;
                    case R.id.me:
                        txtTitle.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        txtTitle.setText("Hồ sơ của tôi");
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
}