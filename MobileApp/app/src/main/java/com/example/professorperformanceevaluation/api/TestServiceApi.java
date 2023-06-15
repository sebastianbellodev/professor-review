package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
<<<<<<< HEAD
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TestServiceApi {
    @POST("pings")
=======

public interface TestServiceApi {
    @GET("pings")
>>>>>>> dd91e9286aaa31599cb923c0cb56dc00948cac15
    Call<Response> ping();
}
