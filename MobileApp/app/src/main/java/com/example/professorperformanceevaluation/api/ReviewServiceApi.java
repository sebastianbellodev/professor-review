package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Review;
import com.example.professorperformanceevaluation.model.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ReviewServiceApi {

    @DELETE("reviews")
    Call<Response> delete(@Header("Authorization") String token, @Body Review review);

    @POST("reviews/educationalexperience")
    Call<Response> getReviewsByEducationalExperience(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);

    @POST("reviews/student")
    Call<Response> getReviewsOfStudent(@Header("Authorization") String token, @Body Student student);

    @PATCH("reviews/update")
    Call<Response> patch(@Header("Authorization") String token, @Body Review review);

    @POST("reviews")
    Call<Response> post(@Header("Authorization") String token, @Body Review review);

}