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

public class LogStudentEmailAddressPhoneNumberViewModel extends AndroidViewModel {

    private final MutableLiveData<String> emailAddress = new MutableLiveData<>();
    private final MutableLiveData<String> phoneNumber = new MutableLiveData<>();

    private final Context context;

    private final StudentService studentService;
    private final UserService userService;

    public LogStudentEmailAddressPhoneNumberViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        studentService = new StudentService(context);
        userService = new UserService(context);
    }

    public MutableLiveData<String> getEmailAddress() {
        return emailAddress;
    }

    public MutableLiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void onCancelClicked() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void onAcceptClicked() {
        userService.signUp(new UserService.UserServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                if (response.getCode() == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    String token = response.getToken();
                    setToken(token);
                    signUp();
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

    private void signUp() {
        if (emailAddress.getValue() != null & phoneNumber.getValue() != null) {
            String emailAddress = this.emailAddress.getValue();
            String phoneNumber = this.phoneNumber.getValue();
            Student student = new Student(emailAddress, phoneNumber);
            checkStudentByEmailAddressExistence(student);
        } else {
            Toast.makeText(context, R.string.empty_fields_label, Toast.LENGTH_SHORT).show();
        }
    }

    public void checkStudentByEmailAddressExistence(Student student) {
        studentService.getStudentByEmailAddress(student, new StudentService.StudentServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                if (response.getCode() == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else if (response.getCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                    checkStudentByPhoneNumberExistence(student);
                } else {
                    Toast.makeText(context, R.string.student_already_registered_label, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkStudentByPhoneNumberExistence(Student student) {
        studentService.getStudentByPhoneNumber(student, new StudentService.StudentServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                if (response.getCode() == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else if (response.getCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                    goToLogStudentEducationalProgram();
                } else {
                    Toast.makeText(context, R.string.student_already_registered_label, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToLogStudentEducationalProgram() {

    }

}