package com.example.caycanh.Frame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.caycanh.Frame.API.RetrofitClient;
import com.example.caycanh.Frame.Adapter.ProductSearchAdapter;
import com.example.caycanh.Frame.Adapter.SearchAdapter;
import com.example.caycanh.Frame.OOP.Product;
import com.example.caycanh.Frame.OOP.Search;
import com.example.caycanh.Frame.OOP.Tree;
import com.example.caycanh.R;
import com.example.caycanh.databinding.ActivityTimKiemBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiem extends AppCompatActivity {

    ActivityTimKiemBinding binding;
    SearchAdapter searchAdapter;
    ProductSearchAdapter productAdapter;
    Tree tree;
    List<Product> productList;
    String[] search = {"Cây cảnh phong thủy", "Cây cảnh trong nhà",
            "Cây cảnh để bàn", "Cây cảnh văn phòng", "Cây thủy sinh",
            "Sen đá", "Xương rồng cảnh", "Tróc bạc", "Cây trầu bà"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tim_kiem);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tim_kiem);
        setEvent();
    }

    private void setEvent() {
        // search_item
        List<Search> searchList = getDataSearch();
        setSearchAdapter(searchList);
        //product_item
        setProductAdapter();
        //search Button
        setSearchButton();
        //setEditext
        setFillEditext();
        //
        setTextSearch();

        //
        binding.backImg.setOnClickListener(v -> onBackPressed());

    }

    private void setFillEditext() {
        ArrayAdapter adapterSearch
                = new ArrayAdapter(this, android.R.layout.simple_list_item_1, search);

        binding.edtSearch.setAdapter(adapterSearch);

        binding.edtSearch.setThreshold(1);

//        binding.edtSearch.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    public void setTextSearch() {
////        binding.edtSearch.setText(text);
        Intent intent = getIntent();
        String text = intent.getStringExtra("Text");
        if ( text.isEmpty()) {
            String search = binding.edtSearch.getText().toString();
            binding.tvSearch.setText(search);
        } else {
            binding.tvSearch.setText(text);
        }
    }

    private void setSearchButton() {
        if (binding.edtSearch.getText().toString().isEmpty()) {
//            binding.searchButton
        }

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = binding.edtSearch.getText().toString();
                binding.tvSearch.setText(search);
            }
        });

    }

    private List<Search> getDataSearch() {
        List<Search> searchList = new ArrayList<>();
        searchList.add(new Search(1, "Cây cảnh phong thủy"));
        searchList.add(new Search(2, "Cây cảnh trong nhà"));
        searchList.add(new Search(3, "Cây cảnh để bàn"));
        searchList.add(new Search(4, "Cây cảnh văn phòng"));
        searchList.add(new Search(5, "Cây thủy sinh"));
        searchList.add(new Search(6, "Cây sen đá"));
        searchList.add(new Search(7, "Cây dây leo"));
        searchList.add(new Search(8, "Cây xương rồng cảnh"));
        return searchList;
    }

    private void setSearchAdapter(List<Search> searchList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.searchRcv.setLayoutManager(layoutManager);
        searchAdapter = new SearchAdapter(this, searchList, new SearchAdapter.OnClickListener() {
            @Override
            public void onItemSearchClickListener(Context context, String text) {

                Intent intent = new Intent(context, TimKiem.class);
                intent.putExtra("Text", text );
                context.startActivity(intent);
                finish();
            }
        });
        binding.searchRcv.setAdapter(searchAdapter);
    }

    private void setProductAdapter() {
        Call<Tree> call = RetrofitClient.getInstance().getProductAPI().getAllTreeAPI();
        call.enqueue(new Callback<Tree>() {
            @Override
            public void onResponse(@NonNull Call<Tree> call, Response<Tree> response) {
                Toast.makeText(TimKiem.this, "Call API success", Toast.LENGTH_SHORT).show();
                tree = response.body();
                productList = tree.getTree();
                if (productList.size() != 0) {
                    productAdapter = new ProductSearchAdapter(TimKiem.this, productList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TimKiem.this, RecyclerView.VERTICAL, false);
                    binding.rcvSearchItem.setLayoutManager(layoutManager);
                    binding.rcvSearchItem.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<Tree> call, Throwable t) {
                Toast.makeText(TimKiem.this, "Call API failure", Toast.LENGTH_SHORT).show();
//                Toast.makeText(TimKiem.this, "", Toast.LENGTH_SHORT).show();
            }
        });

    }

}