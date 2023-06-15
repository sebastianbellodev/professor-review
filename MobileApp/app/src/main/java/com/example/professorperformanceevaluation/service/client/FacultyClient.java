package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.FacultyServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FacultyClient {

    private final FacultyServiceApi apiService;
    private static FacultyClient instance = null;

    private FacultyClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(FacultyServiceApi.class);
    }

    public static synchronized FacultyClient getInstance() {
        if (instance == null) {
            instance = new FacultyClient();
        }
        return instance;
    }

    public FacultyServiceApi getApiService() {
        return apiService;
    }

}