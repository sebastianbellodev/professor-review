package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.AcademicOffering;
import com.example.professorperformanceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AcademicOfferingServiceApi {

    @POST("academicofferings/id")
    Call<Response> getAcademicOfferingById(@Header("Authorization") String token, @Body AcademicOffering academicOffering);

}