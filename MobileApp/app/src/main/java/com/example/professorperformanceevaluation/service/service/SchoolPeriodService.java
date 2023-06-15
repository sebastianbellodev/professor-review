package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.SchoolPeriod;
import com.example.professorperformanceevaluation.service.client.SchoolPeriodClient;

import retrofit2.Call;
import retrofit2.Callback;

public class SchoolPeriodService {

    private final String token;

    public SchoolPeriodService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void getSchoolPeriodById(SchoolPeriod schoolPeriod, SchoolPeriodServiceCallback callback) {
        Call<Response> call = SchoolPeriodClient.getInstance().getApiService().getSchoolPeriodById("Bearer " + token, schoolPeriod);
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

    public void getSchoolPeriods(SchoolPeriodServiceCallback callback) {
        Call<Response> call = SchoolPeriodClient.getInstance().getApiService().getSchoolPeriods("Bearer " + token);
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