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
import android.view.MenuItem;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.fragments.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class  ManHinhChinh extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_foreground);

        FragmentManager fragmentManager1 = getSupportFragmentManager();
        Fragment fragment1 = new HomeFragment();
        fragmentManager1.beginTransaction().replace(R.id.linearLayout, fragment1).commit();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dtSS:
                        Toast.makeText(ManHinhChinh.this, "Sam sung ne", Toast.LENGTH_SHORT).show();
                        Log.d("hien thi","co du lieu");
                        break;
                    case R.id.dtVV:
                        Toast.makeText(ManHinhChinh.this, "vivo ne", Toast.LENGTH_SHORT).show();
                        Log.d("hien thi","co du lieu");

                        break;
                    case R.id.dtIP:
                        Toast.makeText(ManHinhChinh.this, "iphone ne", Toast.LENGTH_SHORT).show();
                        Log.d("hien thi","co du lieu");

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