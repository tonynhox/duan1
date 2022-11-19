package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.duan1.R;

public class ManHinhLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        CountDownTimer countDownTimer = new CountDownTimer(5000,5000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent= new Intent(ManHinhLoading.this,ManHinhLogin.class);
                startActivity(intent);
            }
        };countDownTimer.start();
    }
}