package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.models.HoaDon;
import com.example.duan1.models.TaiKhoan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class QuanLyKhachHangAdapter extends RecyclerView.Adapter<QuanLyKhachHangAdapter.MyViewHolder>{

    ArrayList<TaiKhoan> list;
    Context context;
//    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public QuanLyKhachHangAdapter(ArrayList<TaiKhoan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_customer_admin,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.txtTenTK.setText("Tên tài khoản: "+list.get(i).getTenTaiKhoan());
        holder.txtHoTen.setText("Họ tên: "+list.get(i).getHoTen());
        holder.txtNamSinh.setText("Năm Sinh: "+list.get(i).getNamSinh().substring(0,10));
        holder.txtEmail.setText("Email: "+list.get(i).getEmail());
        holder.txtSDT.setText("Số điện thoại: "+list.get(i).getSoDienThoai());


        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView txtTenTK,txtHoTen,txtNamSinh,txtEmail,txtSDT;
        ImageView ivDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenTK = itemView.findViewById(R.id.txtTenTK);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtNamSinh = itemView.findViewById(R.id.txtNamSinh);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            ivDelete = itemView.findViewById(R.id.ivDelete);


        }

    }
}
