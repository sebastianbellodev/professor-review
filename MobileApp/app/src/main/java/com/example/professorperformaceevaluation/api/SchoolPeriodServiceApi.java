package com.example.professorperformaceevaluation.api;

import com.example.professorperformaceevaluation.model.Response;
import com.example.professorperformaceevaluation.model.SchoolPeriod;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SchoolPeriodServiceApi {

    @GET("schoolperiods")
    Call<Response> getSchoolPeriods(@Header("Authorization") String token);

    @POST("schoolperiods/id")
    Call<Response> getSchoolPeriodById(@Header("Authorization") String token, @Body SchoolPeriod schoolPeriod);

}