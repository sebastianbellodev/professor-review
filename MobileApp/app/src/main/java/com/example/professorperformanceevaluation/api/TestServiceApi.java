package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TestServiceApi {
    @POST("pings")
    Call<Response> ping();
}
