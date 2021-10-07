package com.example.caycanh.Frame.API;

import com.example.caycanh.Frame.OOP.Care;
import com.example.caycanh.Frame.OOP.ListCare;
import com.example.caycanh.Frame.OOP.ListNews;
import com.example.caycanh.Frame.OOP.Product;
import com.example.caycanh.Frame.OOP.Tree;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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
