package com.example.caycanh.Frame.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CareAdapter extends RecyclerView.Adapter<CareAdapter.CareViewHolder> {
    @NonNull
    @Override
    public CareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull  CareAdapter.CareViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CareViewHolder extends RecyclerView.ViewHolder{
        public CareViewHolder(@NonNull  View itemView) {
            super(itemView);
        }
    }
}
