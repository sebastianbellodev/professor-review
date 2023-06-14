package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Syllabus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SyllabusServiceApi {

    @DELETE
    Call<Response> delete(@Header("Authorization") String token, @Body Syllabus syllabus);

    @POST("id")
    Call<Response> getSyllabusById(@Header("Authorization") String token, @Body Syllabus syllabus);

    @POST
    Call<Response> post(@Header("Authorization") String token, @Body Syllabus syllabus);

}