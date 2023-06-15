package com.example.professorperformanceevaluation.service.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.client.UserClient;

import retrofit2.Call;
import retrofit2.Callback;

public class UserService {

    private final Context context;
    private final String token;

    public UserService(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    public void delete(User user, UserServiceCallback callback) {
        Call<Response> call = UserClient.getInstance().getApiService().delete("Bearer " + token, user);
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

    public void getUserByUsername(User user, UserServiceCallback callback) {
        Call<Response> call = UserClient.getInstance().getApiService().getUserByUsername("Bearer " + token, user);
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

    public void getUsers(UserServiceCallback callback) {
        Call<Response> call = UserClient.getInstance().getApiService().getUsers("Bearer " + token);
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

    public void login(User user, UserServiceCallback callback) {
        String credentials = getCredentials();
        Call<Response> call = UserClient.getInstance().getApiService().login("Basic " + credentials, user);
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

    private String getCredentials() {
        String username = context.getString(R.string.username);
        String password = context.getString(R.string.password);
        String credentials = username + ":" + password;
        return Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }

    public void patch(User user, UserServiceCallback callback) {
        Call<Response> call = UserClient.getInstance().getApiService().patch("Bearer " + token, user);
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

    public void post(User user, UserServiceCallback callback) {
        Call<Response> call = UserClient.getInstance().getApiService().post("Bearer " + token, user);
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

    public void signUp(UserServiceCallback callback) {
        String credentials = getCredentials();
        Call<Response> call = UserClient.getInstance().getApiService().signUp("Basic " + credentials);
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

    public interface UserServiceCallback {

        void onSuccess(Response response);

        void onFailure(Throwable throwable);

    }

}