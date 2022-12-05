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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.models.HoaDon;
import com.example.duan1.models.LichSuDonHang;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TrangThai;
import com.example.duan1.others.ItemOnClickHD;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LichSuHoaDonAdapter extends BaseAdapter {
    ArrayList<HoaDon> list;
    Context context;
    ItemOnClickHD itemOnClickHD;
    public LichSuHoaDonAdapter(ArrayList<HoaDon> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public LichSuHoaDonAdapter(ArrayList<HoaDon> list, Context context, ItemOnClickHD itemOnClickHD) {
        this.list = list;
        this.context = context;
        this.itemOnClickHD = itemOnClickHD;
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
        public TextView txtMahoadon,txtNgaymua,txtTongSoLuong, txtTongGiaTien,txtDiaChi,txtTrangThai;
        CardView cvItem;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder ;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_lichsudonhang, null);
            holder.txtMahoadon = (TextView) view.findViewById(R.id.txtMaHoaDon);
            holder.txtTongSoLuong = (TextView) view.findViewById(R.id.txtSoLuongSP);
            holder.txtTongGiaTien = (TextView) view.findViewById(R.id.GiaSP);
            holder.txtNgaymua = (TextView) view.findViewById(R.id.txtNgayMua);
            holder.txtDiaChi = (TextView) view.findViewById(R.id.txtDiaChi);
            holder.txtTrangThai= (TextView) view.findViewById(R.id.txtTrangThai);
            holder.cvItem = view.findViewById(R.id.cvItem);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        HoaDon hoaDon = (HoaDon) getItem(position);
        holder.txtTongSoLuong.setText(String.valueOf(hoaDon.getTongSoLuong()));
        holder.txtMahoadon.setText(String.valueOf(hoaDon.getMaHoaDon()));
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtTongGiaTien.setText(String.valueOf(formatter.format(hoaDon.getTongGiaTien())));
        holder.txtNgaymua.setText(hoaDon.getNgayMua());
        holder.txtDiaChi.setText(hoaDon.getDiaChi());
        holder.txtTrangThai.setText(hoaDon.getTrangThaiHD());
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClickHD.OnClickHoaDon(hoaDon.getMaHoaDon());
            }
        });
        return view;
        }
}