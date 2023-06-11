package com.example.professorperformaceevaluation.api;

import com.example.professorperformaceevaluation.model.EducationalProgram;
import com.example.professorperformaceevaluation.model.Faculty;
import com.example.professorperformaceevaluation.model.Professor;
import com.example.professorperformaceevaluation.model.Response;

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

    @GET("professors")
    Call<Response> getProfessors(@Header("Authorization") String token);

    @PATCH("professors")
    Call<Response> patch(@Header("Authorization") String token, @Body Professor professor);

    @POST("professors")
    Call<Response> post(@Header("Authorization") String token, @Body Professor professor);

    @POST("professors/id")
    Call<Response> getProfessorById(@Header("Authorization") String token, @Body Professor professor);

    @POST("professors/educationalprogram")
    Call<Response> getProfessorsByEducationalProgram(@Header("Authorization") String token, @Body EducationalProgram educationalProgram);

    @POST("professors/faculty")
    Call<Response> getProfessorsByFaculty(@Header("Authorization") String token, @Body Faculty faculty);

}