package com.example.caycanh.Frame.show_information;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.caycanh.Frame.model.News;
import com.example.caycanh.R;
import com.example.caycanh.databinding.FragmentShowItemNewsBinding;

public class ShowItemNews extends Fragment {
    FragmentShowItemNewsBinding binding;
    News news;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_show_item_news,container,false);
        View view = binding.getRoot();
        setEvents();
        return view;
    }

    private void setEvents() {
        try {

            Glide.with(this)
                    .load(news.getImage_news())
                    .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(12)))
                    .into(binding.anhTinTuc);
            binding.tenTinTuc.setText(news.getName_news());
            binding.tvNoidung.setText(news.getDescript_news());
            binding.link.setText(news.getLink_news());
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

        //set access link
        ClickLink();
    }

    private void ClickLink() {
        binding.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("");
                        builder.setMessage("Bạn có muốn truy cập link: " + binding.link.getText());
                        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //call
                                Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(binding.link.getText().toString()));
                                startActivity(website);
                            }
                        });
                        Dialog dialog = builder.create();
                        dialog.show();

                    }

        });
    }

    public void setDataNews(News news){
        this.news = news;
    }
}