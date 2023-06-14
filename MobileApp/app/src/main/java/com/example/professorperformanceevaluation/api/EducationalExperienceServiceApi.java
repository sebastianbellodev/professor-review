package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface EducationalExperienceServiceApi {

    @POST("id")
    Call<Response> getEducationalExperienceById(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);

    @POST("name")
    Call<Response> getEducationalExperienceByName(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);

    @GET
    Call<Response> getEducationalExperiences(@Header("Authorization") String token);

    @POST("educationalprogram")
    Call<Response> getEducationalExperiencesByEducationalProgram(@Header("Authorization") String token, @Body EducationalProgram educationalProgram);

    @POST("faculty")
    Call<Response> getEducationalExperiencesByFaculty(@Header("Authorization") String token, @Body Faculty faculty);

    @PATCH
    Call<Response> patch(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);

    @POST
    Call<Response> post(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);

}