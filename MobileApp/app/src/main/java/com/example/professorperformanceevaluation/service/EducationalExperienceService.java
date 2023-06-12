package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.api.EducationalExperienceServiceApi;
import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EducationalExperienceService {

    private static final String URL = String.valueOf(R.string.base_url);

    private static String token;
    private static Retrofit retrofit;
    private static EducationalExperienceServiceApi apiService;

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
        apiService = retrofit.create(EducationalExperienceServiceApi.class);
    }

    public static Response getEducationalExperienceById(EducationalExperience educationalExperience) {
        Call<Response> call = apiService.getEducationalExperienceById("Bearer " + token, educationalExperience);
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

    public static Response getEducationalExperienceByName(EducationalExperience educationalExperience) {
        Call<Response> call = apiService.getEducationalExperienceByName("Bearer " + token, educationalExperience);
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

    public static Response getEducationalExperiences() {
        Call<Response> call = apiService.getEducationalExperiences("Bearer " + token);
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

    public static Response getEducationalExperiencesByEducationalProgram(EducationalProgram educationalProgram) {
        Call<Response> call = apiService.getEducationalExperiencesByEducationalProgram("Bearer " + token, educationalProgram);
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

    public static Response getEducationalExperiencesByFaculty(Faculty faculty) {
        Call<Response> call = apiService.getEducationalExperiencesByFaculty("Bearer " + token, faculty);
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

    public static Response patch(EducationalExperience educationalExperience) {
        Call<Response> call = apiService.patch("Bearer " + token, educationalExperience);
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

    public static Response post(EducationalExperience educationalExperience) {
        Call<Response> call = apiService.post("Bearer " + token, educationalExperience);
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