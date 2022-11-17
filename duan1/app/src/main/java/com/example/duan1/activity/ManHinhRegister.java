package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.duan1.R;

public class ManHinhRegister extends AppCompatActivity {
    Button btnRegister,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        btnRegister =  findViewById(R.id.register);
        btnCancel = findViewById(R.id.cancel);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManHinhRegister.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ManHinhRegister.this,ManHinhLogin.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManHinhRegister.this, "Đã hủy đăng ký", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ManHinhRegister.this,ManHinhLogin.class);
                startActivity(intent);
            }
        });
    }
}