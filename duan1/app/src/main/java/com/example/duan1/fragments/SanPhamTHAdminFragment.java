package com.example.duan1.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.activity.ManHinhChinh;
import com.example.duan1.activity.ManHinhChinhAdmin;
import com.example.duan1.activity.ManHinhLoading;
import com.example.duan1.activity.ManHinhLogin;
import com.example.duan1.adapter.SanPhamHotAdapter;
import com.example.duan1.adapter.SanPhamTHAdminAdapter;
import com.example.duan1.adapter.SanPhamTH_SearchAdapter;
import com.example.duan1.models.SanPham;
import com.example.duan1.models.TimKiemSanPham;
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

public class SanPhamTHAdminFragment extends Fragment {
    ArrayList<TimKiemSanPham> list;
    RecyclerView listViewSP;
    Button btnThem,btnHuy;
    String tenSP,mota,hinhAnhLon,hinhAnhNho;
    int soLuong,thuongHieu;
    long gia;
    ImageView ivHinhAnhLon,ivHinhAnhNho,ivAdd;
    private Uri imagePath,imagePath2;
    private HashMap config = new HashMap();
    int a=0;
    HashMap<Integer,String> map=new HashMap<Integer,String>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham_admin, container, false);
        listViewSP = view.findViewById(R.id.recyclerView);
        ivAdd = view.findViewById(R.id.ivAdd);

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        a=0;
        DemoCallAPI(ManHinhChinhAdmin.b);
            configCloudinary();

        return view;
    }

    private void DemoCallAPI(String thuongHieu) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.timKiemThuongHieu(thuongHieu)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<TimKiemSanPham> info) {
        //Xử lý chức năng
        list=info;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listViewSP.setLayoutManager(linearLayoutManager);
        SanPhamTHAdminAdapter adapter = new SanPhamTHAdminAdapter(list,getContext());
        listViewSP.setAdapter(adapter);
    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay",error+"");
    }



    //dialog thêm
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_admin, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();

        map.put(1,"IPHONE");
        map.put(2,"SAMSUNG");
        map.put(3,"OPPO");
        map.put(4,"VIVO");
        map.put(5,"XIAOMI");
        map.put(6,"REDMI");
        btnThem = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);
        EditText edtTenSP = view.findViewById(R.id.edtTenSP);
        TextView edtMaThuongHieu = view.findViewById(R.id.edtMaThuongHieu);
        EditText edtGiaSP = view.findViewById(R.id.edtGiaSP);
        EditText edtSoLuongSP = view.findViewById(R.id.edtSoLuongSP);
        EditText edtMoTaSP = view.findViewById(R.id.edtMoTaSP);
        ivHinhAnhLon = view.findViewById(R.id.ivHinhAnhLon);
        ivHinhAnhNho = view.findViewById(R.id.ivHinhAnhNho);
        ivHinhAnhLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
//
        ivHinhAnhNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage2();

            }
        });

        for(Map.Entry m : map.entrySet()){
            Log.d("key ne", m.getKey()+"-"+m.getValue());
            if(ManHinhChinhAdmin.b.equalsIgnoreCase(m.getValue()+""))
                edtMaThuongHieu.setText("Mã thương hiệu:"+m.getKey());
        }
//        if(a==2){
//            upload();
//            upload2();
//            a=0;



        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tenSP = edtTenSP.getText().toString();
                mota = edtMoTaSP.getText().toString();
                soLuong = Integer.parseInt(edtSoLuongSP.getText().toString());
                thuongHieu = Integer.parseInt(edtMaThuongHieu.getText().toString().substring(edtMaThuongHieu.getText().toString().length()-1));
                gia = Long.parseLong(edtGiaSP.getText().toString());

                upload();
                upload2();
//                tenSP,mota,hinhAnhLon,hinhAnhNho;
//                int soLuong,thuongHieu;
//                long gia;


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




    private void CallAPIAdd(SanPham sanPham) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.addSanPham(sanPham.getTenSp(),sanPham.getGiaSp(),sanPham.getMaThuongHieu()
                        ,sanPham.getMotaSp(),sanPham.getSoLuongSp(),sanPham.getHinhAnhLon(), sanPham.getHinhAnhNho())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse2, this::handleError2)
        );
    }

    private void handleResponse2(int info) {
        //Xử lý chức năng
        if(info == 1){
            Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError2(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay",error+"");
    }



//    //Ảnh
    private void upload() {
        MediaManager.get().upload(imagePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                Log.d("CHECK", "onStart");
                ShowNotifyUser.showProgressDialog(getContext(),"Đợi load ảnh");
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                Log.d("CHECK", "onProgress");

            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                Log.d("CHECK", "onSuccess");
                StringBuilder stringBuilder2 = new StringBuilder(resultData.get("url").toString());
                char ch = 's';
                stringBuilder2.insert(4, ch);

                hinhAnhLon = stringBuilder2.toString();
                ShowNotifyUser.dismissProgressDialog();
                a+=1;
            }

            @Override
            public void onError(String requestId, ErrorInfo error) {
                Log.d("CHECK", "onError: " + error);
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
                Log.d("CHECK", "onReschedule: " + error);
            }
        }).dispatch();
    }

    private void chooseImage() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setType("image/*");// if you want to you can use pdf/gif/video
            intent.setAction(Intent.ACTION_GET_CONTENT);
            someActivityResultLauncher.launch(intent);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                // There are no request codes
                Intent data = result.getData();
                imagePath = data.getData();
                Glide.with(getContext()).load(imagePath).into(ivHinhAnhLon);

            }
        }
    });


    private void upload2() {
        MediaManager.get().upload(imagePath2).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                Log.d("CHECK", "onStart");
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                Log.d("CHECK", "onProgress");
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                Log.d("CHECK", "onSuccess");
                StringBuilder stringBuilder2 = new StringBuilder(resultData.get("url").toString());
                char ch = 's';
                stringBuilder2.insert(4, ch);

                hinhAnhNho = stringBuilder2.toString();



                SanPham sanPham= new SanPham();
                sanPham.setTenSp(tenSP);
                sanPham.setMotaSp(mota);
                sanPham.setSoLuongSp(soLuong);
                sanPham.setMaThuongHieu(thuongHieu);
                sanPham.setGiaSp(gia);
                sanPham.setHinhAnhLon(hinhAnhLon);
                sanPham.setHinhAnhNho(hinhAnhNho);
                CallAPIAdd(sanPham);
                hinhAnhLon=null;
                hinhAnhNho=null;

            }

            @Override
            public void onError(String requestId, ErrorInfo error) {
                Log.d("CHECK", "onError: " + error);
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
                Log.d("CHECK", "onReschedule: " + error);
            }
        }).dispatch();
    }

    private void chooseImage2() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setType("image/*");// if you want to you can use pdf/gif/video
            intent.setAction(Intent.ACTION_GET_CONTENT);
            someActivityResultLauncher2.launch(intent);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                // There are no request codes
                Intent data = result.getData();
                imagePath2 = data.getData();
                Glide.with(getContext()).load(imagePath2).into(ivHinhAnhNho);

            }
        }
    });

    private void configCloudinary() {

    try {
        config.put("cloud_name", "tonynhox");
        config.put("api_key", "963587178662998");
        config.put("api_secret", "BEBgWDeGvrlKoZYQcu71S5p1j6A");
        MediaManager.init(getActivity(), config);
    }catch (Exception a){
        }


    }
}

