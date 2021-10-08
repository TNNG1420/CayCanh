package com.example.caycanh.Frame.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.caycanh.Frame.activity.fragment.TrangChu;
import com.example.caycanh.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ProductRecentAdapter extends RecyclerView.Adapter<ProductRecentAdapter.ProductViewHolder> {

    Context context;
    List<Product> productRecentList;
    View view;

    public ProductRecentAdapter(Context context, List<Product> productRecentList) {
        this.context = context;
        this.productRecentList = productRecentList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_san_pham,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ProductRecentAdapter.ProductViewHolder holder, int position) {
        Product product = productRecentList.get(position);

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


                Context context1 = v.getContext();
                Main main = (Main) context1;
                ShowInforProduct showInforProduct = new ShowInforProduct();
                showInforProduct.setDataProduct(product);

                main.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, showInforProduct)
                        .addToBackStack(null).commit();
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa sản phẩm này?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                GioHangSQLite gioHangSQLite = new GioHangSQLite(context);
                ProductSQLite productSQLite = new ProductSQLite(context);
//                gioHangSQLite.deleteProduct(product.getMsp());
                productSQLite.deleteProduct(product.getMsp());
//                        Context context1 = v.getContext();
                        Context context1 = v.getContext();
                        Main main = (Main) context1;

                        main.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_frame, new TrangChu())
                                .addToBackStack(null).commit();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return productRecentList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_ten;
        TextView tv_gia;
        public ProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.img_sanpham);
            tv_ten = itemView.findViewById(R.id.txt_tensp);
            tv_gia = itemView.findViewById(R.id.txt_giasp);
        }
    }
}
