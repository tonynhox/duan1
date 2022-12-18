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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.example.duan1.R;
import com.example.duan1.activity.ManHinhChinhAdmin;
import com.example.duan1.models.GioHang;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TimKiemSanPham;
import com.example.duan1.others.ItemOnClick;
import com.example.duan1.others.StaticOthers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamTH_SearchAdapter extends RecyclerView.Adapter<SanPhamTH_SearchAdapter.ViewHolder> {
    ArrayList<TimKiemSanPham> list;
    Context context;
    ItemOnClick itemOnClick;

    public SanPhamTH_SearchAdapter(ArrayList<TimKiemSanPham> list, Context context,ItemOnClick itemOnClick) {
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
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtGia.setText(String.valueOf(formatter.format(list.get(i).getGiaSp())));
        holder.gioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimKiemSanPham timKiemSanPham = list.get(holder.getAdapterPosition());
                if (timKiemSanPham.getSoLuong()>0){
                    GioHang gioHang= new GioHang();
                    gioHang.setTenSp(timKiemSanPham.getTenSp());
                    gioHang.setGiaSp(timKiemSanPham.getGiaSp());
                    gioHang.setSoLuong(1);
                    gioHang.setHinhAnhLon(timKiemSanPham.getHinhAnhLon());
                    gioHang.setMaSP(timKiemSanPham.getMaSp());
                    gioHang.setSoLuongTon(timKiemSanPham.getSoLuong());

                    int count=0;
                    int a=-1;
                    for (GioHang item :StaticOthers.listGH) {
                        a++;
                        if (item.getMaSP()==(gioHang.getMaSP())&&item.getSoLuong()==item.getSoLuongTon()){
                            Toast.makeText(context, "Số lượng sản phẩm không đủ", Toast.LENGTH_SHORT).show();
                            count=1;
                            continue;
                        }
                        if(item.getMaSP()==(gioHang.getMaSP())){
                            GioHang gioHang2;
                            gioHang2=gioHang;
                            gioHang2.setSoLuong(item.getSoLuong()+1);
                            gioHang2.setHinhAnhLon(timKiemSanPham.getHinhAnhLon());
                            StaticOthers.listGH.set(a,gioHang2);
                            count=1;
                        }
                    }

                    if (count==0){
                        StaticOthers.listGH.add(gioHang);
                    }
                    long b=0;
                    for (GioHang item2:StaticOthers.listGH
                    ) {
                        b+=item2.getSoLuong()*item2.getGiaSp();
                        if(b>1000000000){
                            gioHang.setSoLuong(gioHang.getSoLuong()-1);
                            Toast.makeText(context, "Giá phải nhỏ hơn 1 tỉ", Toast.LENGTH_SHORT).show();
                            if(item2.getSoLuong()==0){
                                StaticOthers.listGH.remove(item2);
                            }
                            return;
                        }
                    }
                    //fix
                    Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Hết hàng", Toast.LENGTH_SHORT).show();

                }


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
        TextView txtTen, txtGia,txtChiTiet;
        ImageView ivLon,gioHang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLon = itemView.findViewById(R.id.imageView2);
            txtTen = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGiaSP);
            gioHang= itemView.findViewById(R.id.themGioHang);
            txtChiTiet= itemView.findViewById(R.id.txtChiTiet);
        }
    }




}
