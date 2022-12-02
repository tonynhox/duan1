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

import java.util.ArrayList;

public class TrangThaiDonHangAdminAdapter extends RecyclerView.Adapter<TrangThaiDonHangAdminAdapter.MyViewHolder>{

    ArrayList<HoaDon> list;
    Context context;
//    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public TrangThaiDonHangAdminAdapter(ArrayList<HoaDon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_trangthaidonhang_admin,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.txtTen.setText(list.get(i).getTenTaiKhoan());
        holder.txtGia.setText(String.valueOf(list.get(i).getTongGiaTien()));
        holder.txtNgay.setText(list.get(i).getNgayMua());
        holder.txtSoLuong.setText(String.valueOf(list.get(i).getTongSoLuong()));
        holder.txtDiaChi.setText(list.get(i).getDiaChi());
        holder.btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.btnHuy.setOnClickListener(new View.OnClickListener() {
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

        TextView txtTen,txtGia,txtNgay,txtSoLuong,txtDiaChi;
        Button btnHuy,btnXacNhan;
        ImageView ivLon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLon = itemView.findViewById(R.id.imageView2);
            txtTen = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGiaSP);
            txtNgay = itemView.findViewById(R.id.txtNgayMua);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuongSP);
            btnHuy = itemView.findViewById(R.id.btnHuyDon);
            btnXacNhan = itemView.findViewById(R.id.btnXacNhan);
            txtDiaChi = itemView.findViewById(R.id.txtDiaChi);

        }

    }
}
