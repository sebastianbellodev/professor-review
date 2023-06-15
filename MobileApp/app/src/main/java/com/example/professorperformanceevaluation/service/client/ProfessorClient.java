package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.ProfessorServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfessorClient {

    private final ProfessorServiceApi apiService;
    private static ProfessorClient instance = null;

    private ProfessorClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ProfessorServiceApi.class);
    }

    public static synchronized ProfessorClient getInstance() {
        if (instance == null) {
            instance = new ProfessorClient();
        }
        return instance;
    }

    public ProfessorServiceApi getApiService() {
        return apiService;
    }

}