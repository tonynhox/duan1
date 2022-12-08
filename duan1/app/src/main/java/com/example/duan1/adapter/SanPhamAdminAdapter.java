package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.fragments.HomeAdminFragment;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TimKiemSanPham;
import com.example.duan1.others.ItemOnClick;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SanPhamAdminAdapter extends RecyclerView.Adapter<SanPhamAdminAdapter.ViewHolder> {

    ArrayList<SanPham>list;
    Context context;
    ItemOnClick itemClick;

    public SanPhamAdminAdapter(ArrayList<SanPham> list, Context context, ItemOnClick itemClick) {
        this.list = list;
        this.context = context;
        this.itemClick = itemClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.txtTen.setText(list.get(i).getTenSp());
        SanPham sanPham = list.get(holder.getAdapterPosition());

        holder.ivsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onClickDialog(sanPham.getMaSp());
            }
        });
        holder.ivxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sanPham = list.get(holder.getAdapterPosition());
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle(sanPham.getTenSp());
                builder.setMessage("Bạn có chắc chắn?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemClick.onClickXoa(sanPham.getMaSp());
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


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen;
        ImageView ivsua,ivxoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtName);
            ivsua = itemView.findViewById(R.id.ivEdit);
            ivxoa = itemView.findViewById(R.id.ivDelete);
        }
    }



    //API Xóa


}
