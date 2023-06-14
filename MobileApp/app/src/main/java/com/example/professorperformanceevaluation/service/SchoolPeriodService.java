package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.api.SchoolPeriodServiceApi;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.SchoolPeriod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchoolPeriodService {

    private static final String URL = String.valueOf(R.string.base_url);

    private static String token;
    private static Retrofit retrofit;
    private static SchoolPeriodServiceApi apiService;

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
        apiService = retrofit.create(SchoolPeriodServiceApi.class);
    }

    public static Response getSchoolPeriodById(SchoolPeriod schoolPeriod) {
        Call<Response> call = apiService.getSchoolPeriodById("Bearer " + token, schoolPeriod);
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

    public static Response getSchoolPeriods() {
        Call<Response> call = apiService.getSchoolPeriods("Bearer " + token);
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