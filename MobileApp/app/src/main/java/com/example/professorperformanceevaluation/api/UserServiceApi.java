package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface UserServiceApi {

    @DELETE("users")
    Call<Response> delete(@Header("Authorization") String token, @Body User user);

    @POST("users/username")
    Call<Response> getUserByUsername(@Header("Authorization") String token, @Body User user);

    @GET("users")
    Call<Response> getUsers(@Header("Authorization") String token);

    @POST("users/login")
    Call<Response> login(@Header("Authorization") String credentials, @Body User user);

    @PATCH("users")
    Call<Response> patch(@Header("Authorization") String token, @Body User user);

    @POST("users")
    Call<Response> post(@Header("Authorization") String token, @Body User user);

    @GET("users/signup")
    Call<Response> signUp(@Header("Authorization") String credentials);

}