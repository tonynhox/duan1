package com.example.duan1.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TimKiemSanPham;
import com.example.duan1.others.ItemOnClick;
import com.example.duan1.others.ShowNotifyUser;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SanPhamTHAdminAdapter extends RecyclerView.Adapter<SanPhamTHAdminAdapter.ViewHolder> {

    ArrayList<TimKiemSanPham>list;
    Context context;
    ItemOnClick itemClick;


    public SanPhamTHAdminAdapter(ArrayList<TimKiemSanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public SanPhamTHAdminAdapter(ArrayList<TimKiemSanPham> list, Context context, ItemOnClick itemClick) {
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
        holder.ivsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        holder.ivxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimKiemSanPham timKiemSanPham = list.get(holder.getAdapterPosition());
                int a= timKiemSanPham.getMaSp();

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle(timKiemSanPham.getTenSp());
                builder.setMessage("Bạn có chắc chắn?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CallAPIDelte(a);
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


    //dialog thêm
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_admin, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();


        Button btnThem = view.findViewById(R.id.btnLuu);
        Button btnHuy = view.findViewById(R.id.btnHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();




    }


    //API Xóa
    private void CallAPIDelte(int maSP) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.xoaSP(maSP)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(Integer info) {
        //Xử lý chức năng
        if(info == 1){
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Sản phẩm không tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();

    }









}
