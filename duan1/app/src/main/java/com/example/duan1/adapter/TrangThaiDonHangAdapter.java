package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.models.HoaDon;
import com.example.duan1.models.TrangThai;
import com.example.duan1.others.ItemOnClickHD;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TrangThaiDonHangAdapter extends RecyclerView.Adapter<TrangThaiDonHangAdapter.MyViewHolder>{

    ArrayList<HoaDon> list;
    Context context;
    ItemOnClickHD itemOnClickHD;
//    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public TrangThaiDonHangAdapter(ArrayList<HoaDon> list, Context context, ItemOnClickHD itemOnClickHD) {
        this.list = list;
        this.context = context;
        this.itemOnClickHD = itemOnClickHD;
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
        holder.txtTen.setText(list.get(i).getTenTaiKhoan());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtGia.setText(String.valueOf(formatter.format(list.get(i).getTongGiaTien())));
        holder.txtNgay.setText(list.get(i).getNgayMua());
        holder.txtSoLuong.setText(String.valueOf(list.get(i).getTongSoLuong()));
        holder.txtTrangThai.setText(list.get(i).getTrangThaiHD());
        holder.txtSDT.setText(list.get(i).getSoDienThoai());

        HoaDon hoaDon= list.get(holder.getAdapterPosition());
        if(list.get(i).getTrangThaiHD().equalsIgnoreCase("Đang giao hàng")){
            holder.btnHuy.setVisibility(View.GONE);
            holder.btnDaNhan.setVisibility(View.VISIBLE);

        }
        if(list.get(i).getTrangThaiHD().equalsIgnoreCase("Đang xác nhận")){
            holder.btnHuy.setVisibility(View.VISIBLE);
            holder.btnDaNhan.setVisibility(View.GONE);

        }
        holder.txtDiaChi.setText(list.get(i).getDiaChi());
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClickHD.OnClickHoaDon(hoaDon.getMaHoaDon());
            }
        });

        holder.btnDaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClickHD.OnClickBtnNhan(hoaDon.getMaHoaDon());
            }
        });

        holder.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClickHD.OnClickBtnHuy(hoaDon.getMaHoaDon());
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView txtTen,txtGia,txtNgay,txtSoLuong,txtTrangThai,txtDiaChi,txtSDT;
        Button btnHuy,btnDaNhan;
        ImageView ivLon;
        CardView cvItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLon = itemView.findViewById(R.id.imageView2);
            txtTen = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGiaSP);
            txtNgay = itemView.findViewById(R.id.txtNgayMua);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtDiaChi = itemView.findViewById(R.id.txtDiaChi);
            txtSDT = itemView.findViewById(R.id.txtSDT);

            cvItem = itemView.findViewById(R.id.cvItem);
            btnHuy = itemView.findViewById(R.id.btnHuy);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuongSP);
            btnDaNhan = itemView.findViewById(R.id.btnDaGiao);

        }

    }
}
