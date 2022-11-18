package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.models.HoaDon;
import com.example.duan1.models.LichSuDonHang;
import com.example.duan1.models.SanPham;

import java.util.List;

public class LichSuDonHangAdapter extends RecyclerView.Adapter<LichSuDonHangAdapter.MyViewHolder> {
    List<LichSuDonHang>list;
    Context context;

    public LichSuDonHangAdapter(List<LichSuDonHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lichsudonhang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Glide.with(context).load(list.get(i).hinhAnhNho).into(holder.ivNho);
        holder.txtsoluong.setText(list.get(i).getSoluongSP());
        holder.txtmahoadon.setText(String.valueOf(list.get(i).getMaHoaDon()));
        holder.txtngaymua.setText(String.valueOf(list.get(i).getNgayMua()));
        holder.txtgiasanpham.setText(String.valueOf(list.get(i).getGia()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView txtsoluong, txtmahoadon,txtgiasanpham, txtngaymua;
        ImageView ivNho;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNho = itemView.findViewById(R.id.imageView2);
            txtsoluong = itemView.findViewById(R.id.txtSoluong);
            txtmahoadon = itemView.findViewById(R.id.txtMaHoaDon);
            txtgiasanpham = itemView.findViewById(R.id.GiaSP);
            txtngaymua = itemView.findViewById(R.id.txtNgayMua);
        }
    }
}
