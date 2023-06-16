package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.AcademicOfferingServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AcademicOfferingClient {

    private static AcademicOfferingClient instance = null;
    private final AcademicOfferingServiceApi apiService;

    private AcademicOfferingClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(AcademicOfferingServiceApi.class);
    }

    public static synchronized AcademicOfferingClient getInstance() {
        if (instance == null) {
            instance = new AcademicOfferingClient();
        }
        return instance;
    }

    public AcademicOfferingServiceApi getApiService() {
        return apiService;
    }

}