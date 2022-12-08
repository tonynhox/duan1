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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.models.HoaDon;
import com.example.duan1.others.ItemOnClickHD;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrangThaiDonHangAdminAdapter extends RecyclerView.Adapter<TrangThaiDonHangAdminAdapter.MyViewHolder>{

    ArrayList<HoaDon> list;
    Context context;
    ItemOnClickHD itemOnClickHD;

    //    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public TrangThaiDonHangAdminAdapter(ArrayList<HoaDon> list, Context context, ItemOnClickHD itemOnClickHD) {
        this.list = list;
        this.context = context;
        this.itemOnClickHD = itemOnClickHD;
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
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtGia.setText(String.valueOf(formatter.format(list.get(i).getTongGiaTien())));
        holder.txtNgay.setText(list.get(i).getNgayMua());
        holder.txtSoLuong.setText(String.valueOf(list.get(i).getTongSoLuong()));
        holder.txtDiaChi.setText(list.get(i).getDiaChi());
        holder.txtSDT.setText(list.get(i).getSoDienThoai());

        HoaDon hoaDon= list.get(holder.getAdapterPosition());
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClickHD.OnClickHoaDon(hoaDon.getMaHoaDon());
            }
        });

        holder.btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClickHD.OnClickBtnNhan(hoaDon.getMaHoaDon());
                holder.btnXacNhan.setVisibility(View.GONE);
                holder.btnHuy.setVisibility(View.GONE);
            }
        });
        holder.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClickHD.OnClickBtnHuy(hoaDon.getMaHoaDon());
                holder.btnXacNhan.setVisibility(View.GONE);
                holder.btnHuy.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView txtTen,txtGia,txtNgay,txtSoLuong,txtDiaChi,txtSDT;
        Button btnHuy,btnXacNhan;
        ImageView ivLon;
        CardView cvItem;

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
            txtSDT = itemView.findViewById(R.id.txtSDT);

            cvItem = itemView.findViewById(R.id.cvItem);

        }

    }


}
