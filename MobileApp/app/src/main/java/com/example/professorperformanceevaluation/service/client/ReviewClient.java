package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.ReviewServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewClient {

    private final ReviewServiceApi apiService;
    private static ReviewClient instance = null;

    private ReviewClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ReviewServiceApi.class);
    }

    public static synchronized ReviewClient getInstance() {
        if (instance == null) {
            instance = new ReviewClient();
        }
        return instance;
    }

    public ReviewServiceApi getApiService() {
        return apiService;
    }

}