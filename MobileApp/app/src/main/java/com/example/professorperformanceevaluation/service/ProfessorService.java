package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.api.ProfessorServiceApi;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Professor;
import com.example.professorperformanceevaluation.model.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfessorService {

    private static final String URL = "http://professorperformanceevaluation-production-7405.up.railway.app/api/professors/";
    private static String token;
    private static ProfessorServiceApi apiService;

    public ProfessorService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ProfessorServiceApi.class);
    }

    public static void delete(Professor professor, ProfessorServiceCallback callback) {
        Call<Response> call = apiService.delete("Bearer " + token, professor);
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

    public static void getProfessorById(Professor professor, ProfessorServiceCallback callback) {
        Call<Response> call = apiService.getProfessorById("Bearer " + token, professor);
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

    public static void getProfessors(ProfessorServiceCallback callback) {
        Call<Response> call = apiService.getProfessors("Bearer " + token);
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

    public static void getProfessorsByEducationalProgram(EducationalProgram educationalProgram, ProfessorServiceCallback callback) {
        Call<Response> call = apiService.getProfessorsByEducationalProgram("Bearer " + token, educationalProgram);
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

    public static void getProfessorsByFaculty(Faculty faculty, ProfessorServiceCallback callback) {
        Call<Response> call = apiService.getProfessorsByFaculty("Bearer " + token, faculty);
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

    public static void patch(Professor professor, ProfessorServiceCallback callback) {
        Call<Response> call = apiService.patch("Bearer " + token, professor);
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

    public static void post(Professor professor, ProfessorServiceCallback callback) {
        Call<Response> call = apiService.post("Bearer " + token, professor);
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

    public interface ProfessorServiceCallback {
        void onSuccess(Response response);

        void onFailure(Throwable throwable);
    }

}