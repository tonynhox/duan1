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
import com.example.duan1.models.SanPham;

import java.util.ArrayList;

public class SanPhamAdmin extends RecyclerView.Adapter<SanPhamAdmin.ViewHolder> {

    ArrayList<SanPham>list;
    Context context;

    public SanPhamAdmin(ArrayList<SanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Glide.with(context).load(list.get(i).hinhAnhNho).into(holder.ivsua);
        Glide.with(context).load(list.get(i).hinhAnhNho).into(holder.ivxoa);
        holder.txtTen.setText(list.get(i).getTenSp());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen;
        ImageView ivsua,ivxoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtName);
            ivsua = itemView.findViewById(R.id.ivEdit);
            ivxoa = itemView.findViewById(R.id.ivDelete);
        }
    }
}
