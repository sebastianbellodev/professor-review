package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.service.client.EducationalProgramClient;

import retrofit2.Call;
import retrofit2.Callback;

public class EducationalProgramService {

    private final String token;

    public EducationalProgramService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void getEducationalPrograms(EducationalProgramServiceCallback callback) {
        Call<Response> call = EducationalProgramClient.getInstance().getApiService().getEducationalPrograms("Bearer " + token);
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

    public void getEducationalProgramsByFaculty(Faculty faculty, EducationalProgramServiceCallback callback) {
        Call<Response> call = EducationalProgramClient.getInstance().getApiService().getEducationalProgramsByFaculty("Bearer " + token, faculty);
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

    public void patch(EducationalProgram educationalProgram, EducationalProgramServiceCallback callback) {
        Call<Response> call = EducationalProgramClient.getInstance().getApiService().patch("Bearer " + token, educationalProgram);
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

    public void post(EducationalProgram educationalProgram, EducationalProgramServiceCallback callback) {
        Call<Response> call = EducationalProgramClient.getInstance().getApiService().post("Bearer " + token, educationalProgram);
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

    public interface EducationalProgramServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}