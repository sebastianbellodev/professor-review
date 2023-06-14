package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.SchoolPeriod;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SchoolPeriodServiceApi {

    @POST("id")
    Call<Response> getSchoolPeriodById(@Header("Authorization") String token, @Body SchoolPeriod schoolPeriod);

    @GET
    Call<Response> getSchoolPeriods(@Header("Authorization") String token);

}