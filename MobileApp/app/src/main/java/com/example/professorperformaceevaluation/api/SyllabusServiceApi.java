package com.example.professorperformaceevaluation.api;

import com.example.professorperformaceevaluation.model.Response;
import com.example.professorperformaceevaluation.model.Syllabus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SyllabusServiceApi {

    @DELETE("syllabuses")
    Call<Response> delete(@Header("Authorization") String token, @Body Syllabus syllabus);

    @POST("syllabuses")
    Call<Response> post(@Header("Authorization") String token, @Body Syllabus syllabus);

    @POST("syllabuses/id")
    Call<Response> getSyllabusById(@Header("Authorization") String token, @Body Syllabus syllabus);

}