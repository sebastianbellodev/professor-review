package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.TestServiceApi;
import com.example.professorperformanceevaluation.api.UserServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestClient {
    private final TestServiceApi apiService;
    private static TestClient instance = null;

    private TestClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(TestServiceApi.class);
    }

    public static synchronized TestClient getInstance() {
        if (instance == null) {
            instance = new TestClient();
        }
        return instance;
    }

    public TestServiceApi getApiService() {
        return apiService;
    }
}
