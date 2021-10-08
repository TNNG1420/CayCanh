package com.example.caycanh.Frame.adapter;

import android.content.Context;
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
import com.example.caycanh.Frame.activity.Main;
import com.example.caycanh.Frame.model.Product;
import com.example.caycanh.Frame.sqlite.ProductSQLite;
import com.example.caycanh.Frame.show_information.ShowInforProduct;
import com.example.caycanh.R;

import java.util.List;



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_san_pham,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ProductAdapter.ProductViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.tv_ten.setText(product.getName());
        holder.tv_gia.setText(String.valueOf(product.getPrice()));
        String[] img = product.getImages().split(",");

        if(img != null)
        {
            Glide.with(context)
                    .load(img[0])
                    .apply(new RequestOptions()
                            .transform(new CenterCrop())
                            .transform(new RoundedCorners(12)))
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Context context1 = v.getContext();
                Main main = (Main) context1;
                ShowInforProduct showInforProduct = new ShowInforProduct();
                showInforProduct.setDataProduct(product);

                main.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, showInforProduct)
                        .addToBackStack(null).commit();
                // add table
                ProductSQLite productSQLite = new ProductSQLite(context.getApplicationContext());
                try {
//                    if (productSQLite.getProduct(product.getMsp()) == null) {

                        productSQLite.InsertProduct(product);
//                    }
                }catch (Exception e){
                        Log.e("ERROR", e.toString());
                    }


            }
        });
        

    }

    @Override
    public int getItemCount() {
        return productList.size()>0?productList.size():0;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_ten;
        TextView tv_gia;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_sanpham);
            tv_ten = itemView.findViewById(R.id.txt_tensp);
            tv_gia = itemView.findViewById(R.id.txt_giasp);
        }
    }
}
