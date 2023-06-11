package com.example.professorperformaceevaluation.api;

import com.example.professorperformaceevaluation.model.Faculty;
import com.example.professorperformaceevaluation.model.Response;
import com.example.professorperformaceevaluation.model.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface StudentServiceApi {

    @DELETE("students")
    Call<Response> delete(@Header("Authorization") String token, @Body Student student);

    @GET("students")
    Call<Response> getStudents(@Header("Authorization") String token);

    @PATCH("students")
    Call<Response> patch(@Header("Authorization") String token, @Body Student student);

    @POST("students")
    Call<Response> post(@Header("Authorization") String token, @Body Student student);

    @POST("students/emailaddress")
    Call<Response> getStudentByEmailAddress(@Header("Authorization") String token, @Body Student student);

    @POST("students/phonenumber")
    Call<Response> getStudentByPhoneNumber(@Header("Authorization") String token, @Body Student student);

    @POST("students/registrationnumber")
    Call<Response> getStudentByRegistrationNumber(@Header("Authorization") String token, @Body Student student);

    @POST("students/faculty")
    Call<Response> getStudentsByFaculty(@Header("Authorization") String token, @Body Faculty faculty);

}