package com.example.professorperformanceevaluation.service.service;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.service.client.TestClient;

import retrofit2.Call;
import retrofit2.Callback;

public class TestService {

    public TestService() {
    }

    public void ping(TestService.TestServiceCallback callback) {
        Call<Response> call = TestClient.getInstance().getApiService().ping();
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

    public interface TestServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}