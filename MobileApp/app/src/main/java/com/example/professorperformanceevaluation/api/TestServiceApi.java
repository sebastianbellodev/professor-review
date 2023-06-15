package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestServiceApi {

    @GET("pings")
    Call<Response> ping();

}