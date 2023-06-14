package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.api.EducationalExperienceServiceApi;
import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EducationalExperienceService {

    private static final String URL = "http://professorperformanceevaluation-production-7405.up.railway.app/api/educationalexperiences/";
    private static String token;
    private static EducationalExperienceServiceApi apiService;

    public EducationalExperienceService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(EducationalExperienceServiceApi.class);
    }

    public static void getEducationalExperienceById(EducationalExperience educationalExperience, EducationalExperienceServiceCallback callback) {
        Call<Response> call = apiService.getEducationalExperienceById("Bearer " + token, educationalExperience);
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

    public static void getEducationalExperienceByName(EducationalExperience educationalExperience, EducationalExperienceServiceCallback callback) {
        Call<Response> call = apiService.getEducationalExperienceByName("Bearer " + token, educationalExperience);
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

    public static void getEducationalExperiences(EducationalExperienceServiceCallback callback) {
        Call<Response> call = apiService.getEducationalExperiences("Bearer " + token);
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

    public static void getEducationalExperiencesByEducationalProgram(EducationalProgram educationalProgram, EducationalExperienceServiceCallback callback) {
        Call<Response> call = apiService.getEducationalExperiencesByEducationalProgram("Bearer " + token, educationalProgram);
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

    public static void getEducationalExperiencesByFaculty(Faculty faculty, EducationalExperienceServiceCallback callback) {
        Call<Response> call = apiService.getEducationalExperiencesByFaculty("Bearer " + token, faculty);
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

    public static void patch(EducationalExperience educationalExperience, EducationalExperienceServiceCallback callback) {
        Call<Response> call = apiService.patch("Bearer " + token, educationalExperience);
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

    public static void post(EducationalExperience educationalExperience, EducationalExperienceServiceCallback callback) {
        Call<Response> call = apiService.post("Bearer " + token, educationalExperience);
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