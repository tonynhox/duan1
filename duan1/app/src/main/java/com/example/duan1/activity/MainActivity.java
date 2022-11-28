package com.example.duan1.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

import com.example.duan1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manghinhchao);
        CountDownTimer countDownTimer = new CountDownTimer(2000,2000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent= new Intent(MainActivity.this,ManHinhLoading.class);
                startActivity(intent);
            }
        };countDownTimer.start();
    }
}