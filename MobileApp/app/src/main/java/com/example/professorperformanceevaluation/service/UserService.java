package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.client.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;

public class UserService {
    private static Context context;
    private static String token;

    public UserService(Context context) {
        UserService.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    private static String getCredentials() {
        String username = context.getString(R.string.username);
        String password = context.getString(R.string.password);
        String credentials = username + ":" + password;
        return Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }

    public static void delete(User user, UserServiceCallback callback) {
        Call<Response> call = RetrofitClient.getInstance().getMyApi().delete("Bearer " + token, user);
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

    public static void getUserByUsername(User user, UserServiceCallback callback) {
        Call<Response> call = RetrofitClient.getInstance().getMyApi().getUserByUsername("Bearer " + token, user);
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

    public static void getUsers(UserServiceCallback callback) {
        Call<Response> call = RetrofitClient.getInstance().getMyApi().getUsers("Bearer " + token);
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

    public static void login(User user, UserServiceCallback callback) {
        String credentials = getCredentials();
        Call<Response> call = RetrofitClient.getInstance().getMyApi().login("Basic " + credentials, user);
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

    public static void patch(User user, UserServiceCallback callback) {
        Call<Response> call = RetrofitClient.getInstance().getMyApi().patch("Bearer " + token, user);
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

    public static void post(User user, UserServiceCallback callback) {
        Call<Response> call = RetrofitClient.getInstance().getMyApi().post("Bearer " + token, user);
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

    public static void signUp(UserServiceCallback callback) {
        String credentials = getCredentials();
        Call<Response> call = RetrofitClient.getInstance().getMyApi().signUp("Basic " + credentials);
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