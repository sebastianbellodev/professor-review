package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Professor;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.service.client.ProfessorClient;

import retrofit2.Call;
import retrofit2.Callback;

public class ProfessorService {

    private final String token;

    public ProfessorService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void delete(Professor professor, ProfessorServiceCallback callback) {
        Call<Response> call = ProfessorClient.getInstance().getApiService().delete("Bearer " + token, professor);
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

    public void getProfessorById(Professor professor, ProfessorServiceCallback callback) {
        Call<Response> call = ProfessorClient.getInstance().getApiService().getProfessorById("Bearer " + token, professor);
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

    public void getProfessors(ProfessorServiceCallback callback) {
        Call<Response> call = ProfessorClient.getInstance().getApiService().getProfessors("Bearer " + token);
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

    public void getProfessorsByFaculty(Faculty faculty, ProfessorServiceCallback callback) {
        Call<Response> call = ProfessorClient.getInstance().getApiService().getProfessorsByFaculty("Bearer " + token, faculty);
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

    public void getProfessorsByEducationalExperience(EducationalExperience educationalExperience, ProfessorServiceCallback callback) {
        Call<Response> call = ProfessorClient.getInstance().getApiService().getProfessorsByEducationalExperience("Bearer " + token, educationalExperience);
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

    public void patch(Professor professor, ProfessorServiceCallback callback) {
        Call<Response> call = ProfessorClient.getInstance().getApiService().patch("Bearer " + token, professor);
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

    public void post(Professor professor, ProfessorServiceCallback callback) {
        Call<Response> call = ProfessorClient.getInstance().getApiService().post("Bearer " + token, professor);
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

    public interface ProfessorServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}