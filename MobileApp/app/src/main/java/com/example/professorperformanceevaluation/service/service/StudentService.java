package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.service.client.StudentClient;

import retrofit2.Call;
import retrofit2.Callback;

public class StudentService {

    private final String token;

    public StudentService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void delete(Student student, StudentServiceCallback callback) {
        Call<Response> call = StudentClient.getInstance().getApiService().delete("Bearer " + token, student);
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

    public void getStudentByEmailAddress(Student student, StudentServiceCallback callback) {
        Call<Response> call = StudentClient.getInstance().getApiService().getStudentByEmailAddress("Bearer " + token, student);
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

    public void getStudentByPhoneNumber(Student student, StudentServiceCallback callback) {
        Call<Response> call = StudentClient.getInstance().getApiService().getStudentByPhoneNumber("Bearer " + token, student);
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

    public void getStudentByRegistrationNumber(Student student, StudentServiceCallback callback) {
        Call<Response> call = StudentClient.getInstance().getApiService().getStudentByRegistrationNumber("Bearer " + token, student);
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

    public void getStudents(StudentServiceCallback callback) {
        Call<Response> call = StudentClient.getInstance().getApiService().getStudents("Bearer " + token);
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

    public void getStudentsByFaculty(Faculty faculty, StudentServiceCallback callback) {
        Call<Response> call = StudentClient.getInstance().getApiService().getStudentsByFaculty("Bearer " + token, faculty);
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

    public void patch(Student student, StudentServiceCallback callback) {
        Call<Response> call = StudentClient.getInstance().getApiService().patch("Bearer " + token, student);
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

    public void post(Student student, StudentServiceCallback callback) {
        Call<Response> call = StudentClient.getInstance().getApiService().post("Bearer " + token, student);
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

    public interface StudentServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}