package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.api.SchoolPeriodServiceApi;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.SchoolPeriod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchoolPeriodService {

    private static final String URL = "http://professorperformanceevaluation-production-7405.up.railway.app/api/schoolperiods/";
    private static String token;
    private static SchoolPeriodServiceApi apiService;

    public SchoolPeriodService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(SchoolPeriodServiceApi.class);
    }

    public static void getSchoolPeriodById(SchoolPeriod schoolPeriod, SchoolPeriodServiceCallback callback) {
        Call<Response> call = apiService.getSchoolPeriodById("Bearer " + token, schoolPeriod);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    public static void getSchoolPeriods(SchoolPeriodServiceCallback callback) {
        Call<Response> call = apiService.getSchoolPeriods("Bearer " + token);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    public interface SchoolPeriodServiceCallback {
        void onSuccess(Response response);

        void onFailure(Throwable throwable);
    }

}