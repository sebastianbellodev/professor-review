package com.example.professorperformaceevaluation.api;

import com.example.professorperformaceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface FacultyServiceApi {

    @GET("faculties")
    Call<Response> getFaculties(@Header("Authorization") String token);

}