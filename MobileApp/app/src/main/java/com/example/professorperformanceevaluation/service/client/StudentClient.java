package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.StudentServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentClient {

    private final StudentServiceApi apiService;
    private static StudentClient instance = null;

    private StudentClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(StudentServiceApi.class);
    }

    public static synchronized StudentClient getInstance() {
        if (instance == null) {
            instance = new StudentClient();
        }
        return instance;
    }

    public StudentServiceApi getApiService() {
        return apiService;
    }

}