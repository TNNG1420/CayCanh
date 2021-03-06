package com.example.caycanh.Frame.activity.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.caycanh.Frame.activity.Main;
import com.example.caycanh.Frame.activity.TimKiem;
import com.example.caycanh.Frame.api.RetrofitClient;
import com.example.caycanh.Frame.adapter.ProductAdapter;
import com.example.caycanh.Frame.adapter.ProductRecentAdapter;
import com.example.caycanh.Frame.adapter.SearchAdapter;
import com.example.caycanh.Frame.adapter.ViewPagerAdapter;
import com.example.caycanh.Frame.model.Photo;
import com.example.caycanh.Frame.model.Product;
import com.example.caycanh.Frame.model.Search;
import com.example.caycanh.Frame.model.Tree;
import com.example.caycanh.Frame.sqlite.ProductSQLite;
import com.example.caycanh.R;
import com.example.caycanh.databinding.ActivityTrangChuBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChu extends Fragment {
    ActivityTrangChuBinding binding;
    Timer mTimer;
    SearchAdapter searchAdapter;
    ViewPagerAdapter viewPagerAdapter;
    ProductAdapter productAdapter;
    Tree tree;
    List<Product> productList;
    List<Product> productListRecent;
    ProductSQLite productSQLite;
    private ProductRecentAdapter productRecentAdapter;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_trang_chu);
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_trang_chu, container, false);
        View view = binding.getRoot();
        SetEvents();
        return view;
    }

    private void SetEvents() {

//        binding.searchEvent
        // search_item
        List<Search> searchList = getDataSearch();
        setSearchAdapter(searchList);
        // viewpager_item
        List<Photo> photoList = getDataPhoto();
        setViewPagerAdapter(photoList);
        //product_item
        setProductAdapter();
        //set rcv xemganday
        setRcvRecent();
        //
        binding.searchEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TimKiem.class);
                intent.putExtra("Text", "" );
                startActivity(intent);
            }
        });

    }

    private void setRcvRecent() {

            productSQLite = new ProductSQLite(this.getActivity());
//            productSQLite.dropTable();

            productListRecent = new ArrayList<>();
            try {
                if(productSQLite.getAllProduct() != null){

                    productListRecent = productSQLite.getAllProduct();
                }
            }catch (Exception e){
                Log.e("tana", e.toString());
            }
        try {
            if (productListRecent.size() != 0) {
                productRecentAdapter = new ProductRecentAdapter(getActivity(), productListRecent);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                binding.productRecentRcv.setLayoutManager(layoutManager);
                binding.productRecentRcv.setAdapter(productRecentAdapter);
            }
        } catch (Exception e){
            Log.e("tana", e.toString());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setProductAdapter() {
        Call<Tree> call = RetrofitClient.getInstance().getProductAPI().getAllTreeAPI();
        call.enqueue(new Callback<Tree>() {
            @Override
            public void onResponse(Call<Tree> call, Response<Tree> response) {
                tree = response.body();
                assert tree != null;
                productList = tree.getTree();
                if (productList.size() != 0) {
                    productAdapter = new ProductAdapter(getActivity(), productList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                    binding.product1Rcv.setLayoutManager(layoutManager);
                    binding.product1Rcv.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<Tree> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Call API failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public  void autoSlideIMage(List<Photo> mlistphoto) {
        try {
            if (mlistphoto == null || mlistphoto.isEmpty()) {
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
                    new Handler(Looper.getMainLooper()).post(() -> {
                        int currentItem = binding.viewpager.getCurrentItem();
                        int totalItem = getDataPhoto().size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            binding.viewpager.setCurrentItem(currentItem);
                        } else
                            binding.viewpager.setCurrentItem(0);
                    });
                }
            }
        }, 3000, 5000);
    }

    private void setViewPagerAdapter(List<Photo> photoList) {
        viewPagerAdapter = new ViewPagerAdapter(getActivity(), photoList);
        binding.viewpager.setAdapter(viewPagerAdapter);
        autoSlideIMage(photoList);
    }

    private List<Photo> getDataPhoto() {
        List<Photo> photoList = new ArrayList<>();
        photoList.add(new Photo("https://webcaycanh.com/wp-content/uploads/2021/05/cay-van-loc-de-ban.jpg"));
        photoList.add(new Photo("https://webcaycanh.com/wp-content/uploads/2021/05/cay-troc-bac-thuy-sinh.jpg"));
        photoList.add(new Photo("https://webcaycanh.com/wp-content/uploads/2021/05/cay-sao-sang-thuy-sinh.jpg"));
        photoList.add(new Photo("https://webcaycanh.com/wp-content/uploads/2021/04/cay-trau-ba-la-lo.jpg"));
        photoList.add(new Photo("https://webcaycanh.com/wp-content/uploads/2020/12/ngu-gia-bi-thuy-sinh.jpg"));
        photoList.add(new Photo("https://webcaycanh.com/wp-content/uploads/2020/12/cay-trau-ba-de-vuong-thuy-sinh.jpg"));
        photoList.add(new Photo("https://webcaycanh.com/wp-content/uploads/2020/08/Duong-xi-cam-thach.jpg"));
        photoList.add(new Photo("https://webcaycanh.com/wp-content/uploads/2020/10/cay-thuy-tung.jpg"));
        return photoList;
    }

    private List<Search> getDataSearch() {
        List<Search> searchList = new ArrayList<>();
        searchList.add(new Search(1, "C??y c???nh phong th???y"));
        searchList.add(new Search(2, "C??y c???nh trong nh??"));
        searchList.add(new Search(3, "C??y c???nh ????? b??n"));
        searchList.add(new Search(4, "C??y c???nh v??n ph??ng"));
        searchList.add(new Search(5, "C??y th???y sinh"));
        searchList.add(new Search(6, "C??y sen ????"));
        searchList.add(new Search(7, "C??y d??y leo"));
        searchList.add(new Search(8, "C??y x????ng r???ng c???nh"));
        return searchList;
    }

    private void setSearchAdapter(List<Search> searchList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        binding.searchRcv.setLayoutManager(layoutManager);
        searchAdapter = new SearchAdapter(getActivity(), searchList, (context, text) -> {
            TimKiem timKiem = new TimKiem();
//                String text = searchList.get(position).getNameSearch();
            Intent intent = new Intent(context, timKiem.getClass());
            intent.putExtra("Text", text );
            context.startActivity(intent);
        });
        binding.searchRcv.setAdapter(searchAdapter);
    }

}