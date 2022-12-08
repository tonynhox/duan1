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

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.activity.GioHangActivity;
import com.example.duan1.models.GioHang;
import com.example.duan1.models.HoaDon;
import com.example.duan1.others.StaticOthers;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {

    List<GioHang> list;
    Context context;

    public GioHangAdapter(List<GioHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_giohang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Glide.with(context).load(list.get(i).getHinhAnhLon()).into(holder.ivLon);
        holder.txtTen.setText(list.get(i).getTenSp());
        holder.txtSoLuong.setText(list.get(i).getSoLuong()+"");
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtGia.setText(formatter.format(list.get(i).getGiaSp()));
        GioHang gioHang= list.get(holder.getAdapterPosition());
        holder.btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tmp=holder.getAdapterPosition();

                if(gioHang.getSoLuong()==1){
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                    builder.setTitle(gioHang.getTenSp());
                    builder.setMessage("Bạn có chắc chắn xóa?");
                    builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StaticOthers.listGH.remove(tmp);
                            Toast.makeText(context,"Đã xóa ra khỏi giỏ hàng",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"Đã hủy",Toast.LENGTH_SHORT).show();
//                            gioHang.setSoLuong(gioHang.getSoLuong()+1);
                        }
                    });
                    android.app.AlertDialog alertDialog= builder.create();
                    alertDialog.show();

                }


                if(gioHang.getSoLuong()>1){
                    gioHang.setSoLuong(gioHang.getSoLuong()-1);
                    holder.txtSoLuong.setText((gioHang.getSoLuong())+"");

                }


                list=StaticOthers.listGH;
                notifyDataSetChanged();
                LoadTongTien();
            }


        });
        holder.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gioHang.setSoLuong(gioHang.getSoLuong()+1);
                holder.txtSoLuong.setText((gioHang.getSoLuong())+"");
                LoadTongTien();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView txtTen, txtGia,txtSoLuong;
        ImageView ivLon;
        Button btnPrev,btnNext;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGiaSP);
            ivLon = itemView.findViewById(R.id.ivHinhAnhLon);
            txtSoLuong =  itemView.findViewById(R.id.txtSoLuong);
            btnPrev = itemView.findViewById(R.id.btnPrev);
            btnNext = itemView.findViewById(R.id.btnNext);


        }
    }

    void LoadTongTien(){
        long a=0;
        for (GioHang item:StaticOthers.listGH
        ) {
            a+=item.getSoLuong()*item.getGiaSp();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        GioHangActivity.txtTong.setText(formatter.format(a)+" VND");
    }
}


