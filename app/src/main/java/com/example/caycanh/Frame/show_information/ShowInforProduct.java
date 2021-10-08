package com.example.caycanh.Frame.show_information;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.caycanh.Frame.adapter.ViewPagerAdapter;
import com.example.caycanh.Frame.activity.Main;
import com.example.caycanh.Frame.model.Photo;
import com.example.caycanh.Frame.model.Product;
import com.example.caycanh.Frame.sqlite.GioHangSQLite;
import com.example.caycanh.Frame.activity.fragment.TrangChu;
import com.example.caycanh.R;
import com.example.caycanh.databinding.ThongTinSanPhamBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ShowInforProduct extends Fragment {

    ThongTinSanPhamBinding binding;
    Product mproduct;
    ViewPagerAdapter viewPagerAdapter;
    List<Photo> photoList;
    Timer mTimer;
    GioHangSQLite gioHangSQLite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.thong_tin_san_pham, container, false);
        View view = binding.getRoot();

        setEvents();

        return view;
    }

    private void setEvents() {
        if (mproduct == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ShowInforProduct.this.getActivity());
            builder.setTitle("Thông báo");
            builder.setMessage("Không thể truy xuất thông tin của mặt hàng này.");
            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    binding.msp.setText("");
                    binding.tensanpham.setText("");
                    binding.motasanpham.setText("");
                    binding.themgiohang.setVisibility(View.INVISIBLE);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
//            photoList = mproduct.getImages();
            String[] img = mproduct.getImages().split(",");
            if (img.length > 0) {
                photoList = new ArrayList<>();
                for (int i = 0; i < img.length; i++) {
                    photoList.add(new Photo(img[i]));
                    System.out.println("hihi + " + img[i]);
                }
            }
            setViewPagerAdapter(photoList);
            binding.msp.setText(mproduct.getMsp());
            binding.tenSp.setText(mproduct.getName());
            binding.motaSp.setText(mproduct.getDescript());
            binding.gia.setText(mproduct.getPrice() + "");
            binding.themgiohang.setVisibility(View.VISIBLE);
            binding.themGh.setOnClickListener(v -> setThemGh());
            binding.imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //finish();
                    Context context1 = v.getContext();
                    Main main = (Main) context1;

                    main.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_frame, new TrangChu())
                            .addToBackStack(null).commit();
                }
            });
        }
    }

    private void setThemGh() {
        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thêm sản phẩm vào giỏ hàng?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //them vao gio hang
                    gioHangSQLite = new GioHangSQLite(getContext());
    //                if(gioHangSQLite.getAllProduct() == null){
//                    //ktra bang trongs
//                    gioHangSQLite.InsertProduct(mproduct);
//                }
//                else
                if (gioHangSQLite.getProduct(mproduct.getMsp()) == null) {
                    //ktra sp co trong bang chua
                    gioHangSQLite.InsertProduct(mproduct);
                    //dialog
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setTitle("Thông báo");
                    builder1.setMessage("Thêm sản phẩm vào giỏ hàng thành công!");
                    builder1.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//code
                            Main main = (Main) getContext();
                            main.setNumber();
                        }
                    });


                    Dialog dialog1 = builder1.create();
                    dialog1.show();
                    //

                } else {
                    //san pham da trong gio hang

                    Toast.makeText(getContext(), "Sản phẩm đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();

                    //dialog
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
//                    builder1.setTitle("Thông báo");
//                    builder1.setMessage("Sản phẩm đã có trong giỏ hàng!");
//                    builder1.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////code
//                        }
//                    });
//
//
//                    Dialog dialog1 = builder1.create();
//                    dialog1.show();
                    //
                }


            }

        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // khong them sp vao gio hang
            }
        });
        Dialog dialog = builder.create();
        dialog.show();


    }

    private void setViewPagerAdapter(List<Photo> photoList) {
        viewPagerAdapter = new ViewPagerAdapter(getActivity(), photoList);
        binding.viewpagerProduct.setAdapter(viewPagerAdapter);
        autoSlideIMage(photoList);
    }

    public void setDataProduct(Product product) {
        this.mproduct = product;
    }

    public void autoSlideIMage(List<Photo> mlistphoto) {
        try {
            if (mlistphoto == null || mlistphoto.isEmpty() || binding.viewpagerProduct == null) {
                return;
            }
        } catch (Exception e) {
            Intent intent = new Intent(getActivity(), Main.class);
            startActivity(intent);
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = binding.viewpagerProduct.getCurrentItem();
                            int totalItem = photoList.size() - 1;
                            if (currentItem < totalItem) {
                                currentItem++;
                                binding.viewpagerProduct.setCurrentItem(currentItem);
                            } else
                                binding.viewpagerProduct.setCurrentItem(0);
                        }
                    });
                }
            }
        }, 2000, 5000);
    }
}
