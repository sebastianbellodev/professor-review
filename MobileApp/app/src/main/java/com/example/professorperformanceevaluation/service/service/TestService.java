package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.client.TestClient;
import com.example.professorperformanceevaluation.service.client.UserClient;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;

public class TestService {
    private final Context context;
    private final Gson gson;
    private final String token;

    public TestService(Context context) {
        this.context = context;
        gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void ping(TestService.TestServiceCallback callback) {
        Call<Response> call = TestClient.getInstance().getApiService().ping();
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

    public interface TestServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}
