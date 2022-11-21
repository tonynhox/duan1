package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.models.HoaDon;
import com.example.duan1.models.LichSuDonHang;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TrangThai;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LichSuDonHangAdapter extends BaseAdapter {
    ArrayList<LichSuDonHang> list;
    Context context;

    public LichSuDonHangAdapter(ArrayList<LichSuDonHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView txtsoluong, txtmahoadon, txtgiasanpham, txtngaymua;
        public ImageView ivNho;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder ;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_lichsudonhang, null);
            holder.txtsoluong = (TextView) view.findViewById(R.id.txtSoLuongSP);
            holder.txtmahoadon = (TextView) view.findViewById(R.id.txtMaHoaDon);
            holder.txtgiasanpham = (TextView) view.findViewById(R.id.txtGiaSP);
            holder.txtngaymua = (TextView) view.findViewById(R.id.txtNgayMua);
            holder.ivNho = (ImageView) view.findViewById(R.id.imageView2);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        LichSuDonHang lichSuDonHang = (LichSuDonHang) getItem(position);
        holder.txtsoluong.setText(lichSuDonHang.getSoluongSP());
        holder.txtmahoadon.setText(lichSuDonHang.getMaHoaDon());
        holder.txtgiasanpham.setText((int) lichSuDonHang.getGia());
        holder.txtngaymua.setText((CharSequence) lichSuDonHang.getNgayMua());
            Glide.with(context).load(list.get(position).hinhAnhNho).into(holder.ivNho);

<<<<<<< HEAD
        } else{
            viewHolder = (ViewHolder) convertView.getTag();{
        }


            LichSuDonHang lichSuDonHang = (LichSuDonHang) getItem(position);
            viewHolder.txtsoluong.setText(lichSuDonHang.getSoluongSP());
            viewHolder.txtmahoadon.setText(lichSuDonHang.getMaHoaDon());
            viewHolder.txtgiasanpham.setText((int) lichSuDonHang.getGia());
            viewHolder.txtngaymua.setText((CharSequence) lichSuDonHang.getNgayMua());
            Glide.with(context).load(list.get(position).hinhAnhNho).into(viewHolder.ivNho);

            return convertView;
=======
            return view;
>>>>>>> 9fece34068ce963254012b7009e2b815204daee6
        }
}