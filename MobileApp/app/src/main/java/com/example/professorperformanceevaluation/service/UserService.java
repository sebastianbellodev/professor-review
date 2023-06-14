package com.example.professorperformanceevaluation.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.api.UserServiceApi;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserService {

    private static final String URL = "http://professorperformanceevaluation-production-7405.up.railway.app/api/users/";
    private static Context context;
    private static String token;
    private static UserServiceApi apiService;

    public UserService(Context context) {
        UserService.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(UserServiceApi.class);
    }

    public static void delete(User user, UserServiceCallback callback) {
        Call<Response> call = apiService.delete("Bearer " + token, user);
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
        Call<Response> call = apiService.getUserByUsername("Bearer " + token, user);
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
        Call<Response> call = apiService.getUsers("Bearer " + token);
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
        Call<Response> call = apiService.login("Basic " + credentials, user);
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

    private static String getCredentials() {
        String username = context.getString(R.string.username);
        String password = context.getString(R.string.password);
        String credentials = username + ":" + password;
        return Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }

    public static void patch(User user, UserServiceCallback callback) {
        Call<Response> call = apiService.patch("Bearer " + token, user);
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
        Call<Response> call = apiService.post("Bearer " + token, user);
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
        Call<Response> call = apiService.signUp("Basic " + credentials);
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