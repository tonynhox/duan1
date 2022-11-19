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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.fragments.HomeFragment;
import com.example.duan1.fragments.SanPhamTHFragment;
import com.google.android.material.navigation.NavigationView;

public class  ManHinhChinh extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    EditText editText;
    Fragment fragment;
    FragmentManager fragmentManager;
    public static String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        editText = findViewById(R.id.edtTim);

        // bắt sự kiên enter
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        Toast.makeText(ManHinhChinh.this, editText.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_foreground);

        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
//        SanPhamTHFragment.
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dtSS:
                        fragment = new SanPhamTHFragment();
                        a= "samsung";
                        Toast.makeText(ManHinhChinh.this, "Sam sung ne", Toast.LENGTH_SHORT).show();
                        Log.d("hien thi","co du lieu");
                        break;
                    case R.id.dtVV:
                        fragment = new SanPhamTHFragment();
                        a= "vivo";
                        Toast.makeText(ManHinhChinh.this, "vivo ne", Toast.LENGTH_SHORT).show();
                        Log.d("hien thi","co du lieu");
                        break;
                    case R.id.dtIP:
                        fragment = new SanPhamTHFragment();
                      a= "iphone";
                        Toast.makeText(ManHinhChinh.this, "iphone ne", Toast.LENGTH_SHORT).show();
                        Log.d("hien thi","co du lieu");
                        break;

                }
                fragmentManager.beginTransaction().replace(R.id.linearLayout, fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);

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