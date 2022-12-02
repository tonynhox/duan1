package com.example.duan1.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.fragments.HomeFragment;
import com.example.duan1.models.DonHang;
import com.example.duan1.models.GioHang;
import com.example.duan1.models.SanPham;
import com.example.duan1.others.StaticOthers;

import java.util.ArrayList;
import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {

    List<DonHang> list;
    Context context;
    SanPhamHotAdapter sanPhamHotAdapter;
    public DonHangAdapter(List<DonHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public DonHangAdapter(Context applicationContext, ArrayList<GioHang> listGH) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        Glide.with(context).load(list.get(i).hinhAnhLon).into(holder.ivLon);
        holder.txtten.setText(list.get(i).getTenSp());
        holder.txtgia.setText(String.valueOf(list.get(i).getGiaSp()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView txtten, txtgia;
        ImageView ivLon,them;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtTenSP);
            txtgia = itemView.findViewById(R.id.txtGiaSP);
            ivLon = itemView.findViewById(R.id.ivHinhAnhLon);

        }
    }
}


