package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Review;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ReviewServiceApi {

    @POST("reviews/educationalexperience")
    Call<Response> getReviewsByEducationalExperience(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);

    @PATCH("reviews")
    Call<Response> patch(@Header("Authorization") String token, @Body Review review);

    @POST("reviews")
    Call<Response> post(@Header("Authorization") String token, @Body Review review);

}