package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.LoginActivity;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.service.service.StudentService;
import com.example.professorperformanceevaluation.service.service.UserService;

import java.net.HttpURLConnection;

public class ActiveAccountViewModel extends AndroidViewModel {

    private final MutableLiveData<String> registrationNumber = new MutableLiveData<>();
    private final MutableLiveData<String> oneTimePassword = new MutableLiveData<>();
    private final Context context;
    private StudentService studentService;
    private UserService userService;

    public ActiveAccountViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        studentService = new StudentService(context);
        userService = new UserService(context);
        userService.signUp(new UserService.UserServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                if (response.getCode() == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    String token = response.getToken();
                    setToken(token);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setToken(String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public MutableLiveData<String> getRegistrationNumber() {
        return registrationNumber;
    }

    public MutableLiveData<String> getOneTimePassword() {
        return oneTimePassword;
    }

    public void onCancelButtonClick() {
        goToLogin();
    }

    private void goToLogin() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void onAcceptButtonClick() {
        if (registrationNumber.getValue() != null && oneTimePassword.getValue() != null) {
            String registrationNumber = getRegistrationNumber().getValue();
            String oneTimePassword = getOneTimePassword().getValue();
            Student student = new Student(registrationNumber, oneTimePassword);
            active(student);
        } else {
            Toast.makeText(context, R.string.empty_fields_label, Toast.LENGTH_SHORT).show();
        }
    }

    private void active(Student student) {
        studentService.activate(student, new StudentService.StudentServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                    Toast.makeText(context, R.string.invalid_data_label, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.modified_information_label, Toast.LENGTH_SHORT).show();
                    goToLogin();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

}