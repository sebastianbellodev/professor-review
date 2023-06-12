package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.api.StudentServiceApi;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentService {

    private static final String URL = String.valueOf(R.string.base_url);

    private static String token;
    private static Retrofit retrofit;
    private static StudentServiceApi apiService;

    public static void initialize(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(StudentServiceApi.class);
    }

    public static Response delete(Student student) {
        Call<Response> call = apiService.delete("Bearer " + token, student);
        try {
            retrofit2.Response<Response> response = call.execute();
            return response.body();
        } catch (Exception exception) {
            Response response = new Response();
            response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public static Response getStudentByEmailAddress(Student student) {
        Call<Response> call = apiService.getStudentByEmailAddress("Bearer " + token, student);
        try {
            retrofit2.Response<Response> response = call.execute();
            return response.body();
        } catch (Exception exception) {
            Response response = new Response();
            response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public static Response getStudentByPhoneNumber(Student student) {
        Call<Response> call = apiService.getStudentByPhoneNumber("Bearer " + token, student);
        try {
            retrofit2.Response<Response> response = call.execute();
            return response.body();
        } catch (Exception exception) {
            Response response = new Response();
            response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public static Response getStudentByRegistrationNumber(Student student) {
        Call<Response> call = apiService.getStudentByRegistrationNumber("Bearer " + token, student);
        try {
            retrofit2.Response<Response> response = call.execute();
            return response.body();
        } catch (Exception exception) {
            Response response = new Response();
            response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public static Response getStudents() {
        Call<Response> call = apiService.getStudents("Bearer " + token);
        try {
            retrofit2.Response<Response> response = call.execute();
            return response.body();
        } catch (Exception exception) {
            Response response = new Response();
            response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public static Response getStudentsByFaculty(Faculty faculty) {
        Call<Response> call = apiService.getStudentsByFaculty("Bearer " + token, faculty);
        try {
            retrofit2.Response<Response> response = call.execute();
            return response.body();
        } catch (Exception exception) {
            Response response = new Response();
            response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public static Response patch(Student student) {
        Call<Response> call = apiService.patch("Bearer " + token, student);
        try {
            retrofit2.Response<Response> response = call.execute();
            return response.body();
        } catch (Exception exception) {
            Response response = new Response();
            response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public static Response post(Student student) {
        Call<Response> call = apiService.post("Bearer " + token, student);
        try {
            retrofit2.Response<Response> response = call.execute();
            return response.body();
        } catch (Exception exception) {
            Response response = new Response();
            response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage(exception.getMessage());
            return response;
        }
    }

}