package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.api.FacultyServiceApi;
import com.example.professorperformanceevaluation.model.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FacultyService {

    private static final String URL = R.string.base_url + "faculties/";

    private static String token;
    private static Retrofit retrofit;
    private static FacultyServiceApi apiService;

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
        apiService = retrofit.create(FacultyServiceApi.class);
    }

    public static Response getFaculties() {
        Call<Response> call = apiService.getFaculties("Bearer " + token);
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