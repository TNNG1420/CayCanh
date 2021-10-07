package com.example.caycanh.Frame.Adapter;

import android.content.Context;
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
import com.example.caycanh.Frame.OOP.News;
import com.example.caycanh.Frame.ShowInfor.ShowItemNews;
import com.example.caycanh.R;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tin_tuc,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        News news = newsList.get(position);

        holder.textView.setText(news.getName_news());
        Glide.with(context)
                .load(news.getImage_news())
                .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(12)))
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, news.getName_news() + " hi", Toast.LENGTH_SHORT).show();
                Context context1 = v.getContext();
                Main main = (Main) context1;
                ShowItemNews showItemNews = new ShowItemNews();
                showItemNews.setDataNews(news);

                main.getSupportFragmentManager().
                        beginTransaction()
                        .replace(R.id.main_frame, showItemNews)
                        .addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size()>0?newsList.size():0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.anh_tin_tuc);
            textView = itemView.findViewById(R.id.ten_tin_tuc);
        }
    }
}
