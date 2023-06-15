package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.EducationalProgramServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EducationalProgramClient {

    private final EducationalProgramServiceApi apiService;
    private static EducationalProgramClient instance = null;

    private EducationalProgramClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(EducationalProgramServiceApi.class);
    }

    public static synchronized EducationalProgramClient getInstance() {
        if (instance == null) {
            instance = new EducationalProgramClient();
        }
        return instance;
    }

    public EducationalProgramServiceApi getApiService() {
        return apiService;
    }

}