package com.example.duan1.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.policy.TimeWindow;
import com.example.duan1.R;
import com.example.duan1.ServiceAPI;
import com.example.duan1.activity.ManHinhChinhAdmin;
import com.example.duan1.activity.ManHinhLogin;
import com.example.duan1.adapter.SanPhamAdminAdapter;
import com.example.duan1.adapter.SanPhamHotAdapter;
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

public class HomeAdminFragment extends Fragment implements ItemOnClick {

    public ArrayList<SanPham> list;
    //    SanPhamHotAdapter adapter;
    RecyclerView listViewSP;

    AlertDialog alertDialogSua;
    Button btnHuy,btnSua;
    String tenSP,mota,hinhAnhLon,hinhAnhNho;
    int soLuong,thuongHieu;
    long gia;
    ImageView ivHinhAnhLon,ivHinhAnhNho;
    private Uri imagePath,imagePath2;
    private HashMap config = new HashMap();
    HashMap<Integer,String> map=new HashMap<Integer,String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanphamhot, container, false);
        listViewSP = view.findViewById(R.id.rcSanPhamHot);
        ShowNotifyUser.showProgressDialog(getContext(), "Loading");
        DemoCallAPI();
        configCloudinary();
        return view;
    }
    public void DemoCallAPI() {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getSanPhamHot()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<SanPham> info) {
        //Xử lý chức năng
        list=info;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listViewSP.setLayoutManager(linearLayoutManager);
        SanPhamAdminAdapter adapter = new SanPhamAdminAdapter(list,getContext(),this);
        listViewSP.setAdapter(adapter);
        ShowNotifyUser.dismissProgressDialog();

    }

    private void handleError(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay","loi");
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

    //load ảnh 3 Sửa
    private void upload3(int a) {
        MediaManager.get().upload(imagePath2).constrain(TimeWindow.getDefault()).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                Log.d("CHECK", "onStart");
                ShowNotifyUser.showProgressDialog(getContext(),"đợi load ảnh thứ 2");
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
                ShowNotifyUser.dismissProgressDialog();
                SanPham sanPham= new SanPham();
                sanPham.setMaSp(a);
                sanPham.setTenSp(tenSP);
                sanPham.setMotaSp(mota);
                sanPham.setSoLuongSp(soLuong);
                sanPham.setGiaSp(gia);
                sanPham.setHinhAnhLon(hinhAnhLon);
                sanPham.setHinhAnhNho(hinhAnhNho);
                CallAPIEdit(sanPham);
                ShowNotifyUser.dismissProgressDialog();

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


    private void uploadSua(int a) {
        MediaManager.get().upload(imagePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                Log.d("CHECK", "onStart");
                ShowNotifyUser.showProgressDialog(getContext(),"Đợi load ảnh 1");

            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                Log.d("CHECK", "onProgress");

            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                hinhAnhLon=null;
                StringBuilder stringBuilder2 = new StringBuilder(resultData.get("url").toString());
                char ch = 's';
                stringBuilder2.insert(4, ch);
                hinhAnhLon = stringBuilder2.toString();
                ShowNotifyUser.dismissProgressDialog();
                SanPham sanPham= new SanPham();
                if (imagePath2!=null){
                    upload3(a);
                }else {
                    sanPham.setMaSp(a);
                    sanPham.setTenSp(tenSP);
                    sanPham.setMotaSp(mota);
                    sanPham.setSoLuongSp(soLuong);
                    sanPham.setGiaSp(gia);
                    sanPham.setHinhAnhLon(hinhAnhLon);
                    sanPham.setHinhAnhNho(hinhAnhNho);
                    CallAPIEdit(sanPham);
                    ShowNotifyUser.dismissProgressDialog();
                }


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

    private void configCloudinary() {

        try {
            config.put("cloud_name", "tonynhox");
            config.put("api_key", "963587178662998");
            config.put("api_secret", "BEBgWDeGvrlKoZYQcu71S5p1j6A");
            MediaManager.init(getActivity(), config);
        }catch (Exception a){
        }


    }


    @Override
    public void onClickItem(TimKiemSanPham timKiemSanPham) {

    }

    @Override
    public void onClickItem(SanPham sanPham) {

    }

    @Override
    public void onClickXoa(int a) {
        CallAPIDelete(a);
        ShowNotifyUser.showProgressDialog(getContext(), "Loading");
        DemoCallAPI();

    }

    private void CallAPIDelete(int maSP) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.xoaSP(maSP)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse1, this::handleError1)
        );
    }

    private void handleResponse1(Integer info) {
        //Xử lý chức năng
        if(info == 1){
            Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),"Sản phẩm không tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError1(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Toast.makeText(getContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onClickDialog(int maSP) {
        CallAPIAllSP(maSP);
    }




    public void CallAPIAllSP(int maSP) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getSanPham(maSP)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponsen, this::handleErrorn)
        );
    }

    private void handleResponsen(ArrayList<SanPham> listAllSP) {
        //Xử lý chức năng
//        listAllSP=info;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_admin, null);
        builder.setView(view);
        alertDialogSua = builder.create();

        map.put(1,"IPHONE");
        map.put(2,"SAMSUNG");
        map.put(3,"OPPO");
        map.put(4,"VIVO");
        map.put(5,"XIAOMI");
        map.put(6,"REDMI");
        btnSua = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);
        EditText edtTenSP = view.findViewById(R.id.edtTenSP);
        TextView edtMaThuongHieu = view.findViewById(R.id.edtMaThuongHieu);
        EditText edtGiaSP = view.findViewById(R.id.edtGiaSP);
        EditText edtSoLuongSP = view.findViewById(R.id.edtSoLuongSP);
        EditText edtMoTaSP = view.findViewById(R.id.edtMoTaSP);
        ivHinhAnhLon = view.findViewById(R.id.ivHinhAnhLon);
        ivHinhAnhNho = view.findViewById(R.id.ivHinhAnhNho);
        edtTenSP.setText(listAllSP.get(0).getTenSp());
        edtGiaSP.setText(listAllSP.get(0).getGiaSp()+"");
        edtSoLuongSP.setText(listAllSP.get(0).getSoLuongSp()+"");
        edtMoTaSP.setText(listAllSP.get(0).getMotaSp());
        hinhAnhLon=listAllSP.get(0).getHinhAnhLon();
        hinhAnhNho=listAllSP.get(0).getHinhAnhNho();
        Glide.with(getContext()).load(listAllSP.get(0).getHinhAnhNho()).into(ivHinhAnhNho);
        Glide.with(getContext()).load(listAllSP.get(0).getHinhAnhLon()).into(ivHinhAnhLon);

        ivHinhAnhLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        ivHinhAnhNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage2();
            }
        });

        for(Map.Entry m : map.entrySet()){
            Log.d("key ne", m.getKey()+"-"+m.getValue());
            if(listAllSP.get(0).getMaThuongHieu()== Integer.parseInt(m.getKey().toString()))
                edtMaThuongHieu.setText(m.getValue().toString());
        }



        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowNotifyUser.dismissProgressDialog();

                tenSP = edtTenSP.getText().toString();
                mota = edtMoTaSP.getText().toString();
                soLuong = Integer.parseInt(edtSoLuongSP.getText().toString());
                gia = Long.parseLong(edtGiaSP.getText().toString());
                try {
                    uploadSua(listAllSP.get(0).getMaSp());
                }catch (Exception e){
                    if(imagePath2!=null){
                        upload3(listAllSP.get(0).getMaSp());
                    }else {
                        SanPham sanPham= new SanPham();
                        sanPham.setMaSp(listAllSP.get(0).getMaSp());
                        sanPham.setTenSp(tenSP);
                        sanPham.setMotaSp(mota);
                        sanPham.setSoLuongSp(soLuong);
                        sanPham.setGiaSp(gia);
                        sanPham.setHinhAnhLon(hinhAnhLon);
                        sanPham.setHinhAnhNho(hinhAnhNho);
                        CallAPIEdit(sanPham);
                    }
                }


            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogSua.dismiss();
            }
        });

        alertDialogSua.show();

    }

    private void handleErrorn(Throwable error) {
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("chay","loi");
    }

    private void CallAPIEdit(SanPham sanPham) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(ServiceAPI.BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.capNhatSanPham(
                        sanPham.getMaSp(), sanPham.getTenSp(),sanPham.getGiaSp(),sanPham.getMotaSp(),sanPham.getSoLuongSp(),sanPham.getHinhAnhLon(),sanPham.getHinhAnhNho())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse3, this::handleError3)
        );
    }

    private void handleResponse3(int info) {
        //Xử lý chức năng
        if(info == 1){
            Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
            alertDialogSua.dismiss();
            imagePath=null;
            imagePath2=null;
            DemoCallAPI();
            ShowNotifyUser.dismissProgressDialog();

        }else{
            ShowNotifyUser.dismissProgressDialog();
            Toast.makeText(getContext(), "không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError3(Throwable error) {
        ShowNotifyUser.dismissProgressDialog();
        Log.d("chay",error+"");
    }

}
