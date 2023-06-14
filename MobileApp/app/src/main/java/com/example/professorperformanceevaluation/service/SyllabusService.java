package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.api.SyllabusServiceApi;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Syllabus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SyllabusService {

    private static final String URL = "http://professorperformanceevaluation-production-7405.up.railway.app/api/syllabuses/";
    private static String token;
    private static SyllabusServiceApi apiService;

    public SyllabusService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(SyllabusServiceApi.class);
    }

    public static void delete(Syllabus syllabus, SyllabusServiceCallback callback) {
        Call<Response> call = apiService.delete("Bearer " + token, syllabus);
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

    public static void getSyllabusById(Syllabus syllabus, SyllabusServiceCallback callback) {
        Call<Response> call = apiService.getSyllabusById("Bearer " + token, syllabus);
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

    public static void post(Syllabus syllabus, SyllabusServiceCallback callback) {
        Call<Response> call = apiService.post("Bearer " + token, syllabus);
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

    public interface SyllabusServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}