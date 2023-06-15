package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.SchoolPeriodServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchoolPeriodClient {

    private final SchoolPeriodServiceApi apiService;
    private static SchoolPeriodClient instance = null;

    private SchoolPeriodClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(SchoolPeriodServiceApi.class);
    }

    public static synchronized SchoolPeriodClient getInstance() {
        if (instance == null) {
            instance = new SchoolPeriodClient();
        }
        return instance;
    }

    public SchoolPeriodServiceApi getApiService() {
        return apiService;
    }

}