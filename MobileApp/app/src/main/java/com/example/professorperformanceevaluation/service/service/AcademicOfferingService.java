package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.model.AcademicOffering;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.service.client.AcademicOfferingClient;

import retrofit2.Call;
import retrofit2.Callback;

public class AcademicOfferingService {

    private final String token;

    public AcademicOfferingService(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void getAcademicOfferingById(AcademicOffering academicOffering, AcademicOfferingService.AcademicOfferingServiceCallback callback) {
        Call<Response> call = AcademicOfferingClient.getInstance().getApiService().getAcademicOfferingById("Bearer " + token, academicOffering);
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

    public interface AcademicOfferingServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}