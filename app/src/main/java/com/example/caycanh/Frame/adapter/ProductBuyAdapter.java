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
import com.example.caycanh.Frame.activity.GioHang;
import com.example.caycanh.Frame.model.Product;
import com.example.caycanh.Frame.sqlite.GioHangSQLite;
import com.example.caycanh.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ProductBuyAdapter extends RecyclerView.Adapter<ProductBuyAdapter.ProductViewHolder> {

    Context context;
    List<Product> productBuyList;
    GioHang gioHang;

    public ProductBuyAdapter(Context context, List<Product> productBuyList) {
        this.context = context;
        this.productBuyList = productBuyList;
        this.gioHang = (GioHang) context;
    }
//    public void ChangeData(List<Product> productBuyList){
//        this.productBuyList = productBuyList;
//    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gio_hang_sp, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductBuyAdapter.ProductViewHolder holder, int position) {

        Product product = productBuyList.get(position);

        holder.tv_ten.setText(product.getName());
        holder.tv_gia.setText(String.valueOf(product.getPrice()));
        String[] img = product.getImages().split(",");

        if (img != null) {
            Glide.with(context)
                    .load(img[0])
                    .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(12)))
                    .into(holder.imageView);
        }


        holder.btn_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int sl = Integer.valueOf(holder.tv_hienThi.getText().toString()) - 1;

                holder.tv_hienThi.setText(sl + "");
                if (sl == 1){
                    holder.btn_giam.setVisibility(View.INVISIBLE);
            }else

            {
                holder.btn_giam.setVisibility(View.VISIBLE);
            }
                productBuyList.get(position).setSoLuong(sl);
        }
    });
        holder.btn_them.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
            int sl = Integer.valueOf(holder.tv_hienThi.getText().toString()) + 1;
        holder.btn_giam.setVisibility(View.VISIBLE);
        holder.tv_hienThi.setText(sl + "");
            productBuyList.get(position).setSoLuong(sl);

    }
    });

        holder.itemView.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){


//                Context context1 = v.getContext();
//                Main main = (Main) context1;
//                ShowInforProduct showInforProduct = new ShowInforProduct();
//                showInforProduct.setDataProduct(product);
//
//                main.getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.main_frame, showInforProduct)
//                        .addToBackStack(null).commit();
    }
    });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick (View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa sản phẩm này?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                GioHangSQLite gioHangSQLite = new GioHangSQLite(context);
                gioHangSQLite.deleteProduct(product.getMsp());
                productBuyList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
//                gioHang.ChangeData(gioHangSQLite.getAllProduct());
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
        return productBuyList.size();
    }

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView tv_ten;
    TextView tv_gia;
    TextView tv_hienThi;
    TextView btn_giam, btn_them;

    public ProductViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img_sanpham);
        tv_ten = itemView.findViewById(R.id.txt_tensp);
        tv_gia = itemView.findViewById(R.id.txt_giasp);
        tv_hienThi = itemView.findViewById(R.id.tv_hien_thi);
        btn_giam = itemView.findViewById(R.id.btn_giam);
        btn_them = itemView.findViewById(R.id.btn_them);
    }
}
}
