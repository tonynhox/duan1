package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.models.GioHang;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TimKiemSanPham;
import com.example.duan1.others.ItemOnClick;
import com.example.duan1.others.StaticOthers;

import java.text.DecimalFormat;
import java.util.ArrayList;


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
        DecimalFormat formatter = new DecimalFormat("###,###,###");

        holder.txtGia.setText(String.valueOf(formatter.format(list.get(i).getGiaSp())));

        holder.txtSoLuongMua.setText(String.valueOf(list.get(i).getTongSoLuong()));
        holder.gioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Để ý models và tên biến do coppy làm biếng sửa
                SanPham timKiemSanPham = list.get(holder.getAdapterPosition());
                if (timKiemSanPham.getSoLuongSp()>0){
                GioHang gioHang= new GioHang();
                gioHang.setTenSp(timKiemSanPham.getTenSp());
                gioHang.setGiaSp(timKiemSanPham.getGiaSp());
                gioHang.setSoLuong(1);
                gioHang.setHinhAnhLon(timKiemSanPham.getHinhAnhLon());
                gioHang.setMaSP(timKiemSanPham.getMaSp());
                gioHang.setSoLuongTon(timKiemSanPham.getSoLuongSp());

                int count=0;
                int a=-1;
                for (GioHang item : StaticOthers.listGH) {
                    a++;
                    if (item.getMaSP()==(gioHang.getMaSP())&&item.getSoLuong()==item.getSoLuongTon()){
                        Toast.makeText(context, "Số lượng sản phẩm không đủ", Toast.LENGTH_SHORT).show();
                        count=1;
                        continue;
                    }
                    if(item.getTenSp().equals(gioHang.getTenSp())){
                        GioHang gioHang2;
                        gioHang2=gioHang;
                        gioHang2.setSoLuong(item.getSoLuong()+1);
                        gioHang2.setHinhAnhLon(timKiemSanPham.getHinhAnhLon());
                        StaticOthers.listGH.set(a,gioHang2);
                        count=1;
                        Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                if (count==0){
                    StaticOthers.listGH.add(gioHang);
                    Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    }
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
