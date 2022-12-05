package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.models.ChiTietHoaDon;
import com.example.duan1.models.HoaDon;
import com.example.duan1.models.SanPham;
import com.example.duan1.others.ItemOnClick;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietHoaDonAdapter extends RecyclerView.Adapter<ChiTietHoaDonAdapter.ViewHolder> {
    ArrayList<ChiTietHoaDon> list;
    Context context;
    public ChiTietHoaDonAdapter(ArrayList<ChiTietHoaDon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    //Quản lý layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_chitietdonhang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Glide.with(context).load(list.get(i).getIvNho()).into(holder.ivNho);
        holder.txtTenSanpham.setText(list.get(i).getTenSP());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtGiaTien.setText(formatter.format(list.get(i).getGiaSP()));
        holder.txtSoLuong.setText(String.valueOf(list.get(i).getSoLuong()));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenSanpham,txtSoLuong,txtGiaTien;
        ImageView ivNho;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSanpham = itemView.findViewById(R.id.txtTenSP);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuongSP);
            txtGiaTien =  itemView.findViewById(R.id.txtGiaSP);
            ivNho = itemView.findViewById(R.id.ivHinhAnhNho);
        }
    }





}