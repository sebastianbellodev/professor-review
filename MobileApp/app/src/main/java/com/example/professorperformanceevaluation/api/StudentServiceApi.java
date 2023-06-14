package com.example.professorperformanceevaluation.api;

import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface StudentServiceApi {

    @DELETE
    Call<Response> delete(@Header("Authorization") String token, @Body Student student);

    @POST("emailaddress")
    Call<Response> getStudentByEmailAddress(@Header("Authorization") String token, @Body Student student);

    @POST("phonenumber")
    Call<Response> getStudentByPhoneNumber(@Header("Authorization") String token, @Body Student student);

    @POST("registrationnumber")
    Call<Response> getStudentByRegistrationNumber(@Header("Authorization") String token, @Body Student student);

    @GET
    Call<Response> getStudents(@Header("Authorization") String token);

    @POST("faculty")
    Call<Response> getStudentsByFaculty(@Header("Authorization") String token, @Body Faculty faculty);

    @PATCH
    Call<Response> patch(@Header("Authorization") String token, @Body Student student);

    @POST
    Call<Response> post(@Header("Authorization") String token, @Body Student student);

}