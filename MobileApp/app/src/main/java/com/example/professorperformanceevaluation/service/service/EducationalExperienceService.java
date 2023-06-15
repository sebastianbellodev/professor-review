package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.service.client.EducationalExperienceClient;

import retrofit2.Call;
import retrofit2.Callback;

public class EducationalExperienceService {

    private final String token;

    public EducationalExperienceService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void getEducationalExperienceById(EducationalExperience educationalExperience, EducationalExperienceServiceCallback callback) {
        Call<Response> call = EducationalExperienceClient.getInstance().getApiService().getEducationalExperienceById("Bearer " + token, educationalExperience);
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

    public void getEducationalExperienceByName(EducationalExperience educationalExperience, EducationalExperienceServiceCallback callback) {
        Call<Response> call = EducationalExperienceClient.getInstance().getApiService().getEducationalExperienceByName("Bearer " + token, educationalExperience);
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

    public void getEducationalExperiences(EducationalExperienceServiceCallback callback) {
        Call<Response> call = EducationalExperienceClient.getInstance().getApiService().getEducationalExperiences("Bearer " + token);
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

    public void getEducationalExperiencesByEducationalProgram(EducationalProgram educationalProgram, EducationalExperienceServiceCallback callback) {
        Call<Response> call = EducationalExperienceClient.getInstance().getApiService().getEducationalExperiencesByEducationalProgram("Bearer " + token, educationalProgram);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    public void getEducationalExperiencesByFaculty(Faculty faculty, EducationalExperienceServiceCallback callback) {
        Call<Response> call = EducationalExperienceClient.getInstance().getApiService().getEducationalExperiencesByFaculty("Bearer " + token, faculty);
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

    public void patch(EducationalExperience educationalExperience, EducationalExperienceServiceCallback callback) {
        Call<Response> call = EducationalExperienceClient.getInstance().getApiService().patch("Bearer " + token, educationalExperience);
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

    public void post(EducationalExperience educationalExperience, EducationalExperienceServiceCallback callback) {
        Call<Response> call = EducationalExperienceClient.getInstance().getApiService().post("Bearer " + token, educationalExperience);
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

    public interface EducationalExperienceServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}