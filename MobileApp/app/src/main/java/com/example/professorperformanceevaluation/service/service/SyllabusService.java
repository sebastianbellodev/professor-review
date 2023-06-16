package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Syllabus;
import com.example.professorperformanceevaluation.service.client.SyllabusClient;

import retrofit2.Call;
import retrofit2.Callback;

public class SyllabusService {

    private final String token;

    public SyllabusService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void delete(Syllabus syllabus, SyllabusServiceCallback callback) {
        Call<Response> call = SyllabusClient.getInstance().getApiService().delete("Bearer " + token, syllabus);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onSuccess(new Response(response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    public void getSyllabusById(Syllabus syllabus, SyllabusServiceCallback callback) {
        Call<Response> call = SyllabusClient.getInstance().getApiService().getSyllabusById("Bearer " + token, syllabus);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onSuccess(new Response(response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    public void getSyllabusByEducationalExperience(EducationalExperience educationalExperience, SyllabusServiceCallback callback) {
        Call<Response> call = SyllabusClient.getInstance().getApiService().getSyllabusByEducationalExperience("Bearer " + token, educationalExperience);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onSuccess(new Response(response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    public void post(Syllabus syllabus, SyllabusServiceCallback callback) {
        Call<Response> call = SyllabusClient.getInstance().getApiService().post("Bearer " + token, syllabus);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onSuccess(new Response(response.code()));
                }
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