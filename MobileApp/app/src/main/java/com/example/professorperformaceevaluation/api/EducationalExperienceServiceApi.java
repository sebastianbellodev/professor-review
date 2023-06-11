package com.example.professorperformaceevaluation.api;

import com.example.professorperformaceevaluation.model.EducationalExperience;
import com.example.professorperformaceevaluation.model.Faculty;
import com.example.professorperformaceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface EducationalExperienceServiceApi {

    @GET("educationalexperiences")
    Call<Response> getEducationalExperiences(@Header("Authorization") String token);

    @PATCH("educationalexperiences")
    Call<Response> patch(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);

    @POST("educationalexperiences")
    Call<Response> post(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);

    @POST("educationalexperiences/faculty")
    Call<Response> getUserByUsername(@Header("Authorization") String token, @Body Faculty faculty);

    @POST("educationalexperiences/id")
    Call<Response> postUser(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);

}