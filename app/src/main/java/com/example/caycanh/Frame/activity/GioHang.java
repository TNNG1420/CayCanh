package com.example.caycanh.Frame.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caycanh.Frame.adapter.ProductBuyAdapter;
import com.example.caycanh.Frame.model.Product;
import com.example.caycanh.Frame.sqlite.GioHangSQLite;
import com.example.caycanh.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHang extends AppCompatActivity {

    AppCompatButton btnThanhToan;
    BottomSheetDialog bottomSheetDialog;
    TextView textrong;
    List<Product> mProductList;
    RecyclerView mRecyclerView;
    GioHangSQLite gioHangSQLite;
    ProductBuyAdapter productBuyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

//        countNumberProduct = new CountNumberProduct(findViewById(R.id.icon_gh));
        btnThanhToan = findViewById(R.id.btn_thanhtoan);
        textrong = findViewById(R.id.textrong);
        mRecyclerView = findViewById(R.id.gioHangRecyclerView);

//        mRecyclerView.set

        gioHangSQLite = new GioHangSQLite(getApplicationContext());
        setRCVAdapter();
       btnThanhToan.setOnClickListener(v -> {
           if(mProductList == null || mProductList.size() == 0){
               AlertDialog.Builder builder = new AlertDialog.Builder(GioHang.this);
               builder.setTitle("Thông báo");
               builder.setMessage("Giỏ hàng trống!");
               builder.setPositiveButton("Đồng ý", (dialog, which) -> {
               });
               Dialog dialog = builder.create();
               dialog.show();
           }else{
           OpenBottomSheetDialog();
           }
       });

    }

    @SuppressLint("ResourceType")
    private void OpenBottomSheetDialog() {


        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_pay, findViewById(R.id.ln_container));

         bottomSheetDialog = new BottomSheetDialog(GioHang.this ,
                R.style.BottomSheetDialogThem);
        bottomSheetDialog.setContentView(view);
        ((View)view.getParent()).setBackgroundResource(getResources().getColor(android.R.color.transparent));
        bottomSheetDialog.show();

        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0");
//
        TextView tv_tongtien, tv_tienhang, tv_thue, tv_vanchuyen;
        tv_tongtien = view.findViewById(R.id.tv_tongtien);
        tv_tienhang = view.findViewById(R.id.tv_tienhang);
        tv_thue = view.findViewById(R.id.tv_thue);
        tv_vanchuyen = view.findViewById(R.id.tv_vanchuyen);
        long tienhang=0, thue = 0, vanchuyen = 0, tongtien = 0;
        for (Product p:mProductList
             ) {
            tienhang += p.getPrice() * p.getSoLuong();
        }
        thue = (long) (tienhang * 0.05);
        vanchuyen = (long) (tienhang * 0.03);
        tongtien = tienhang + thue + vanchuyen;
        tv_tongtien.setText(decimalFormat.format(tongtien * 1000) + "");
        tv_tienhang.setText(decimalFormat.format(tienhang * 1000)+ "");
        tv_thue.setText(decimalFormat.format(thue* 1000) + "");
        tv_vanchuyen.setText(decimalFormat.format(vanchuyen* 1000)+ "");

        AppCompatButton btnthanhtoan1 = view.findViewById(R.id.btn_thanhtoan1);
        btnthanhtoan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openDialog();
            }
        });


    }

    public void ChangeData(){

        productBuyAdapter.notifyDataSetChanged();
    }

    public void setRCVAdapter(){
        mProductList = new ArrayList<>();
        try {
            if(gioHangSQLite.getAllProduct() != null){

                mProductList = gioHangSQLite.getAllProduct();
            }
        }catch (Exception e){
            Log.e("giohang", e.toString());
        }

        try {
            if (mProductList.size() != 0) {
                textrong.setVisibility(View.INVISIBLE);
//                ChangeData(gioHangSQLite.getAllProduct());
                productBuyAdapter = new ProductBuyAdapter(GioHang.this, mProductList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                mRecyclerView.setLayoutManager(layoutManager);
//                productBuyAdapter.ChangeData(gioHangSQLite.getAllProduct());
                mRecyclerView.setAdapter(productBuyAdapter);
            }
        } catch (Exception e){
            Log.e("tana", e.toString());
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Thanh toán thành công!");
        builder.setPositiveButton("Đồng ý", (dialog, which) -> {

            gioHangSQLite.deleteTable();
            productBuyAdapter = new ProductBuyAdapter(GioHang.this, mProductList);
            textrong.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
            setRCVAdapter();
            bottomSheetDialog.cancel();
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}