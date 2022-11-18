package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.models.TrangThai;

import java.util.List;

public class TrangThaiDonHangAdapter extends RecyclerView.Adapter<TrangThaiDonHangAdapter.MyViewHolder>{

    List<TrangThai> list;
    Context context;

    public TrangThaiDonHangAdapter(List<TrangThai> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_trangthaidonhang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Glide.with(context).load(list.get(i).hinhAnhLon).into(holder.ivLon);
        holder.txtten.setText(list.get(i).getTenSp());
        holder.txtgia.setText(String.valueOf(list.get(i).getGia()));
        holder.txtngay.setText(String.valueOf(list.get(i).getNgayMua()));
        holder.txtsoluong.setText(String.valueOf(list.get(i).getSoluongSP()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView txtten,txtgia,txtngay,txtsoluong;
        ImageView ivLon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLon = itemView.findViewById(R.id.imageView2);
            txtten = itemView.findViewById(R.id.txtTenSP);
            txtgia = itemView.findViewById(R.id.txtGiaSP);
            txtngay = itemView.findViewById(R.id.txtNgayMua);
            txtsoluong = itemView.findViewById(R.id.txtSoLuongSP);

        }

    }
}
