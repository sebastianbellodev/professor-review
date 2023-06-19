package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Professor;
import com.example.professorperformanceevaluation.model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ProfessorServiceApi {

    @DELETE("professors")
    Call<Response> delete(@Header("Authorization") String token, @Body Professor professor);

    @POST("professors/id")
    Call<Response> getProfessorById(@Header("Authorization") String token, @Body Professor professor);

    @GET("professors")
    Call<Response> getProfessors(@Header("Authorization") String token);

    @POST("professors/educationalexperience")
    Call<Response> getProfessorsByEducationalExperience(@Header("Authorization") String token, @Body EducationalExperience educationalExperience);
    @POST("professors/faculty")
    Call<Response> getProfessorsByFaculty(@Header("Authorization") String token, @Body Faculty faculty);

    @PATCH("professors")
    Call<Response> patch(@Header("Authorization") String token, @Body Professor professor);

    @POST("professors")
    Call<Response> post(@Header("Authorization") String token, @Body Professor professor);

}