package com.example.caycanh.Frame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caycanh.Frame.activity.fragment.ChamSoc;
import com.example.caycanh.Frame.activity.Main;
import com.example.caycanh.Frame.model.Search;
import com.example.caycanh.R;


import java.util.List;


public class SearchAdapter1 extends RecyclerView.Adapter<SearchAdapter1.SearchViewHolder> {

    Context context;
    List<Search> searchList;
    int count = 0;

    public SearchAdapter1(Context context, List<Search> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @Override
    public SearchAdapter1.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_name_buton,parent,false);
        return new SearchAdapter1.SearchViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull SearchAdapter1.SearchViewHolder holder, int position) {
        count = position;
        holder.txt_search.setText(searchList.get(position).getNameSearch());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "search id " + position, Toast.LENGTH_SHORT).show();
//
//
//                ChamSoc chamSoc = new ChamSoc();
//                chamSoc.hihihihi = position;
//                (ChamSoc) v.getContext().hihihihi =
                Context context1 = v.getContext();
                Main main = (Main) context1;
                ChamSoc chamSoc = new ChamSoc();
                chamSoc.setIndex(position);

                main.getSupportFragmentManager().
                        beginTransaction()
                        .replace(R.id.main_frame, chamSoc)
                        .addToBackStack(null).commit();
                

            }
        });
    }
    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return searchList.get(position).getId();
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
