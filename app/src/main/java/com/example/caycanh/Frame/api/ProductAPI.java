package com.example.caycanh.Frame.api;

import com.example.caycanh.Frame.model.ListCare;
import com.example.caycanh.Frame.model.ListNews;
import com.example.caycanh.Frame.model.Tree;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductAPI {

    // link api: http://demo6642047.mockable.io/tana

    String BASE_URL = "http://demo5048162.mockable.io/";
    @GET("tree")
    Call<Tree> getAllTreeAPI();
    @GET("news")
    Call<ListNews> getAllNewsAPI();
    @GET("care")
    Call<ListCare> getAllCareAPI();
}
