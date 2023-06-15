package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface EducationalProgramServiceApi {

    @GET("educationalprograms")
    Call<Response> getEducationalPrograms(@Header("Authorization") String token);

    @POST("educationalprograms/faculty")
    Call<Response> getEducationalProgramsByFaculty(@Header("Authorization") String token, @Body Faculty faculty);

    @PATCH("educationalprograms")
    Call<Response> patch(@Header("Authorization") String token, @Body EducationalProgram educationalProgram);

    @POST("educationalprograms")
    Call<Response> post(@Header("Authorization") String token, @Body EducationalProgram educationalProgram);

}