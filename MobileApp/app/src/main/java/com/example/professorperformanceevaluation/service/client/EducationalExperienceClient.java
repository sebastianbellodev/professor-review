package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.EducationalExperienceServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EducationalExperienceClient {

    private final EducationalExperienceServiceApi apiService;
    private static EducationalExperienceClient instance = null;

    private EducationalExperienceClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(EducationalExperienceServiceApi.class);
    }

    public static synchronized EducationalExperienceClient getInstance() {
        if (instance == null) {
            instance = new EducationalExperienceClient();
        }
        return instance;
    }

    public EducationalExperienceServiceApi getApiService() {
        return apiService;
    }

}