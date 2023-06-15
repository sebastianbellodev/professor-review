package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.SyllabusServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SyllabusClient {

    private final SyllabusServiceApi apiService;
    private static SyllabusClient instance = null;

    private SyllabusClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(SyllabusServiceApi.class);
    }

    public static synchronized SyllabusClient getInstance() {
        if (instance == null) {
            instance = new SyllabusClient();
        }
        return instance;
    }

    public SyllabusServiceApi getApiService() {
        return apiService;
    }

}