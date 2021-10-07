package com.example.caycanh.Frame.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private ProductAPI productAPI;

    private RetrofitClient() {

        Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(productAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        productAPI = retrofit.create(ProductAPI.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ProductAPI getProductAPI() {
        return productAPI;
    }
}
