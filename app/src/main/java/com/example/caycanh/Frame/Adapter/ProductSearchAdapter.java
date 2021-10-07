package com.example.caycanh.Frame.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.caycanh.Frame.Main;
import com.example.caycanh.Frame.OOP.Product;
import com.example.caycanh.Frame.SQLite.ProductSQLite;
import com.example.caycanh.Frame.ShowInfor.ShowInforProduct;
import com.example.caycanh.R;


import java.util.List;


public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ProductSearchViewHolder> {
    Context context;
    List<Product> productList;

    public ProductSearchAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductSearchViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_san_pham,parent,false);
        return new ProductSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSearchAdapter.ProductSearchViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.tv_ten.setText(product.getName());
        holder.tv_gia.setText(String.valueOf(product.getPrice()));
        String[] img = product.getImages().split(",");

        if(img != null)
        {
            Glide.with(context)
                    .load(img[0])
                    .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(12)))
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // add table
                ProductSQLite productSQLite = new ProductSQLite(context.getApplicationContext());
                try {
//                    if (productSQLite.getProduct(product.getMsp()) == null) {

                    productSQLite.InsertProduct(product);
//                    }
                }catch (Exception e){
                    Log.e("ERROR", e.toString());
                }

                Intent intent = new Intent(context,ShowInforProduct.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ProductSearchViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_ten;
        TextView tv_gia;
        public ProductSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_sanpham);
            tv_ten = itemView.findViewById(R.id.txt_tensp);
            tv_gia = itemView.findViewById(R.id.txt_giasp);
        }
    }
}
