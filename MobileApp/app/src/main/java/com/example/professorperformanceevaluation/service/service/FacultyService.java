package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.service.client.FacultyClient;

import retrofit2.Call;
import retrofit2.Callback;

public class FacultyService {

    private String token;

    public FacultyService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void delete(Faculty faculty, FacultyServiceCallback callback) {
        Call<Response> call = FacultyClient.getInstance().getApiService().delete("Bearer " + token, faculty);
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

    public void getFaculties(FacultyServiceCallback callback) {
        Call<Response> call = FacultyClient.getInstance().getApiService().getFaculties("Bearer " + token);
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

    public void patch(Faculty faculty, FacultyServiceCallback callback) {
        Call<Response> call = FacultyClient.getInstance().getApiService().patch("Bearer " + token, faculty);
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

    public void post(Faculty faculty, FacultyServiceCallback callback) {
        Call<Response> call = FacultyClient.getInstance().getApiService().post("Bearer " + token, faculty);
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

    public interface FacultyServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}