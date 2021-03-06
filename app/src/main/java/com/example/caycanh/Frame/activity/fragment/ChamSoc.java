package com.example.caycanh.Frame.activity.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.caycanh.Frame.api.RetrofitClient;
import com.example.caycanh.Frame.adapter.SearchAdapter1;
import com.example.caycanh.Frame.model.Care;
import com.example.caycanh.Frame.model.ListCare;
import com.example.caycanh.Frame.model.Search;
import com.example.caycanh.Frame.sqlite.CareSQLite;
import com.example.caycanh.R;
import com.example.caycanh.databinding.FragmentChamSocBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChamSoc extends Fragment {


    FragmentChamSocBinding binding;
    private SearchAdapter1 SearchAdapter1;
     List<Care> cares;
     CareSQLite careSQLite ;
     List<Search> searches;
     public  int index ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cham_soc,container,false);
//        listCare = new ListCare();
        cares = new ArrayList<>();
        careSQLite = new CareSQLite(getContext());
        setDataCare();
        setEvent();

        return binding.getRoot();
    }

    private void setEvent() {
//        // search_item
        List<Search> searchList = getDataSearch();
        setSearchAdapter1(searchList);
//        setDataCare(2);
        setContent(index);


    }
    public void setIndex(int index){
        this.index = index;
    }

    public void setContent(int hihihihi) {
        try {

            cares = careSQLite.getAllCare();
            Care care = cares.get(hihihihi);
            binding.tvNameCare.setText(care.getTitle_care());
            binding.tvContentCare.setText(care.getContent_care());
            Glide.with(getContext())
                    .load(care.getImage_care())
                    .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(12)))
                    .into(binding.imgCare);
        }catch(Exception e){
            Log.e("tan1", e.toString());
        }


    }

    private List<Search> getDataSearch() {
        List<Search> searchList = new ArrayList<Search>();
        searchList.add(new Search(1, "Ch??m s??c c??y v??n ph??ng"));
        searchList.add(new Search(2, "Ch??m s??c c??y th???y sinh"));
        searchList.add(new Search(3, "Ch??m s??c sen ???? v?? x????ng r???ng"));
        return searchList;
    }


    private void setSearchAdapter1(List<Search> searchList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        binding.searchRcv.setLayoutManager(layoutManager);
        SearchAdapter1 = new SearchAdapter1(getActivity(), searchList);
        binding.searchRcv.setAdapter(SearchAdapter1);
    }


    public void setDataCare() {

        Call<ListCare> call = RetrofitClient.getInstance().getProductAPI().getAllCareAPI();
        call.enqueue(new Callback<ListCare>() {
            @Override
            public void onResponse(Call<ListCare> call, Response<ListCare> response) {
                try{
                    ListCare listCare;
                    listCare = response.body();
                    cares = listCare.getCare();
                for (Care c:cares) {
//                    searches.add(new Search(Integer.valueOf(c.getId_care()),c.getTitle_care()));
                    if (careSQLite.getCare(c.getId_care())== null) {
                        //ktra sp co trong bang chua
                        careSQLite.InsertCare(c);

                    }
                }
//                    try {
//
//                        Care care = cares.get(position);
//                        setContent(care);
//                    }catch(Exception e){
//                        Log.e("tan1", e.toString());
//                    }
                    Log.e("hi", "cares: " + cares);
                }catch(Exception e){

                    Toast.makeText(getContext(), "ERROR "+ e.toString(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ListCare> call, Throwable t) {
                Toast.makeText(getContext(), "Call API failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setContent(index);
    }
}