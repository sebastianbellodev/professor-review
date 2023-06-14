package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface FacultyServiceApi {

    @DELETE
    Call<Response> delete(@Header("Authorization") String token, @Body Faculty faculty);

    @GET
    Call<Response> getFaculties(@Header("Authorization") String token);

    @PATCH
    Call<Response> patch(@Header("Authorization") String token, @Body Faculty faculty);

    @POST
    Call<Response> post(@Header("Authorization") String token, @Body Faculty faculty);

}