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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.fragments.ChiTietSanPhamFragment;
import com.example.duan1.fragments.HoSoCuaToiFragment;
import com.example.duan1.models.SanPham;
import com.example.duan1.others.ItemOnClick;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SanPhamHotAdapter extends RecyclerView.Adapter<SanPhamHotAdapter.ViewHolder> {
    ArrayList<SanPham> list;
    Context context;
    ItemOnClick itemOnClick;
    public SanPhamHotAdapter(ArrayList<SanPham> list, Context context,ItemOnClick itemOnClick) {
        this.list = list;
        this.context = context;
        this.itemOnClick=itemOnClick;
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
        holder.txtSoLuongMua.setText(String.valueOf(list.get(i).getTongSoLuong()));
        holder.gioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.txtChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClick.onClickItem(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen, txtGia,txtSoLuongMua,txtChiTiet;
        ImageView ivLon,gioHang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLon = itemView.findViewById(R.id.imageView2);
            txtTen = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGiaSP);
            txtSoLuongMua = itemView.findViewById(R.id.txtSoLuongMua);
            gioHang= itemView.findViewById(R.id.themGioHang);
            txtChiTiet= itemView.findViewById(R.id.txtChiTiet);
        }
    }




}
