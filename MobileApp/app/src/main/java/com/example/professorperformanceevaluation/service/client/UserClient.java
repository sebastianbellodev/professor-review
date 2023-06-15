package com.example.professorperformanceevaluation.service.client;

import com.example.professorperformanceevaluation.api.UserServiceApi;
import com.example.professorperformanceevaluation.api.server.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserClient {

    private final UserServiceApi apiService;
    private static UserClient instance = null;

    private UserClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(UserServiceApi.class);
    }

    public static synchronized UserClient getInstance() {
        if (instance == null) {
            instance = new UserClient();
        }
        return instance;
    }

    public UserServiceApi getApiService() {
        return apiService;
    }

}