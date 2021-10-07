package com.example.caycanh.Frame.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caycanh.Frame.OOP.Search;
import com.example.caycanh.Frame.TimKiem;
import com.example.caycanh.R;


import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    List<Search> searchList;

    public SearchAdapter(Context context, List<Search> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_name_buton,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        holder.txt_search.setText(searchList.get(position).getNameSearch());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // hthi thong tin tim kiem
                TimKiem timKiem = new TimKiem();
                String text = searchList.get(position).getNameSearch();
                Intent intent = new Intent(context, timKiem.getClass());
                intent.putExtra("Text", text );
                context.startActivity(intent);
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView txt_search;
        public SearchViewHolder(@NonNull  View itemView) {
            super(itemView);
            txt_search = itemView.findViewById(R.id.search_name);
        }
    }
}