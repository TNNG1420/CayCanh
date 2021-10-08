package com.example.caycanh.Frame;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.caycanh.Frame.API.RetrofitClient;
import com.example.caycanh.Frame.Adapter.NewsAdapter;
import com.example.caycanh.Frame.OOP.ListNews;
import com.example.caycanh.Frame.OOP.News;
import com.example.caycanh.R;
import com.example.caycanh.databinding.FragmentTinTucBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TinTuc extends Fragment {

    public static final String TAG_NAME_TINTUC = TinTuc.class.getName();
FragmentTinTucBinding tintuc;
NewsAdapter newsAdapter;
List<News> newsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        View view;
        tintuc = DataBindingUtil.inflate(inflater, R.layout.fragment_tin_tuc,container,false);
        view = tintuc.getRoot();
        setEvents();
        return view;
    }

    private void setEvents() {
        setRcvByAdapter();
    }

    private void setRcvByAdapter() {
        Call<ListNews> call = RetrofitClient.getInstance().getProductAPI().getAllNewsAPI();
        call.enqueue(new Callback<ListNews>() {
            @Override
            public void onResponse(Call<ListNews> call, Response<ListNews> response) {

                ListNews listNews = response.body();
                newsList = listNews.getNews();
                if(newsList.size() != 0){
                    newsAdapter = new NewsAdapter(getActivity(),newsList);
                    RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                    tintuc.rcvTintuc.setLayoutManager(layoutManager);
                    tintuc.rcvTintuc.setAdapter(newsAdapter);

                }

            }

            @Override
            public void onFailure(Call<ListNews> call, Throwable t) {
                Toast.makeText(getActivity(), "Call API failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        getFragmentManager().popBackStack();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        getFragmentManager().popBackStack();
//    }
}