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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_lichsudonhang, null);
            viewHolder.txtsoluong = (TextView) convertView.findViewById(R.id.txtSoLuongSP);
            viewHolder.txtmahoadon = (TextView) convertView.findViewById(R.id.txtMaHoaDon);
            viewHolder.txtgiasanpham = (TextView) convertView.findViewById(R.id.txtGiaSP);
            viewHolder.txtngaymua = (TextView) convertView.findViewById(R.id.txtNgayMua);
            viewHolder.ivNho = (ImageView) convertView.findViewById(R.id.imageView2);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            LichSuDonHang lichSuDonHang = (LichSuDonHang) getItem(position);
            viewHolder.txtsoluong.setText(lichSuDonHang.getSoluongSP());
            viewHolder.txtmahoadon.setText(lichSuDonHang.getMaHoaDon());
            viewHolder.txtgiasanpham.setText((int) lichSuDonHang.getGia());
            viewHolder.txtngaymua.setText((CharSequence) lichSuDonHang.getNgayMua());
            Glide.with(context).load(list.get(position).hinhAnhNho).into(viewHolder.ivNho);

            return convertView;
        }
}