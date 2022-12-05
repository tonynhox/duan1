package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.models.HoaDon;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TaiKhoan;
import com.example.duan1.others.ItemOnClickDel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class QuanLyKhachHangAdapter extends RecyclerView.Adapter<QuanLyKhachHangAdapter.MyViewHolder>{

    ArrayList<TaiKhoan> list;
    Context context;
    ItemOnClickDel itemOnClickDel;
//    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public QuanLyKhachHangAdapter(ArrayList<TaiKhoan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public QuanLyKhachHangAdapter(ArrayList<TaiKhoan> list, Context context, ItemOnClickDel itemOnClickDel) {
        this.list = list;
        this.context = context;
        this.itemOnClickDel = itemOnClickDel;
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
                TaiKhoan taiKhoan = list.get(holder.getAdapterPosition());
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle(taiKhoan.getTenTaiKhoan());
                builder.setMessage("Bạn có chắc chắn?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemOnClickDel.ItemClickDel(taiKhoan.getTenTaiKhoan());

                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,"Đã hủy",Toast.LENGTH_SHORT).show();
                    }
                });
                android.app.AlertDialog alertDialog= builder.create();
                alertDialog.show();

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
