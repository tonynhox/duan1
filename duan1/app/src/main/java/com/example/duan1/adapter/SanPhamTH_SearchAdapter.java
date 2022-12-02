package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.example.duan1.R;
import com.example.duan1.activity.ManHinhChinhAdmin;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TimKiemSanPham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamTH_SearchAdapter extends RecyclerView.Adapter<SanPhamTH_SearchAdapter.ViewHolder> {
    ArrayList<TimKiemSanPham> list;
    Context context;

    public SanPhamTH_SearchAdapter(ArrayList<TimKiemSanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    //Quản lý layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Glide.with(context).load(list.get(i).getHinhAnhLon()).into(holder.ivLon);
        holder.txtTen.setText(list.get(i).getTenSp());
        holder.txtGia.setText(String.valueOf(list.get(i).getGiaSp()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen, txtGia;
        ImageView ivLon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLon = itemView.findViewById(R.id.imageView2);
            txtTen = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGiaSP);
        }
    }




}
