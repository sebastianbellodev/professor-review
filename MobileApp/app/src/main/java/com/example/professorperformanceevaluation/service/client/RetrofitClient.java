package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.UserServiceApi;
import com.example.professorperformanceevaluation.api.server.ServiceApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient<T> {
    private static RetrofitClient instance = null;

    private UserServiceApi myApi;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ServiceApi.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(UserServiceApi.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public UserServiceApi getMyApi() {
        return myApi;
    }
}
