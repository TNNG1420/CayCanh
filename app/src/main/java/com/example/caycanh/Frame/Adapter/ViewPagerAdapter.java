package com.example.caycanh.Frame.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.caycanh.Frame.OOP.Photo;
import com.example.caycanh.R;



import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    List<Photo> photoList;

    public ViewPagerAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @Override
    public int getCount() {
        return photoList.size()>0?photoList.size():0;
    }

    @Override
    public boolean isViewFromObject(@NonNull  View view, @NonNull  Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_viewpager,container,false);
        ImageView imageView = view.findViewById(R.id.img_viewpager);
        //
        Photo photo = photoList.get(position);
        if(photo!=null)
        {
            Glide.with(context)
                    .load(photo.getResource())
                    .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(12)))
                    .into(imageView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull  Object object) {

        container.removeView((View) object);
    }
}
