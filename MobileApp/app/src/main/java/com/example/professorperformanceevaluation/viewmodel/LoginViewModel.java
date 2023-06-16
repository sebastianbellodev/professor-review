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
import com.example.professorperformanceevaluation.activity.ActiveAccountActivity;
import com.example.professorperformanceevaluation.activity.LogStudentEmailAddressPhoneNumberActivity;
import com.example.professorperformanceevaluation.activity.MainMenuActivity;
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.service.StudentService;
import com.example.professorperformanceevaluation.service.service.UserService;
import com.example.professorperformanceevaluation.utilities.Utilities;

import java.net.HttpURLConnection;

public class LoginViewModel extends AndroidViewModel {

    private final MutableLiveData<String> username = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();
    private final Context context;
    private final StudentService studentService;
    private final UserService userService;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        studentService = new StudentService(context);
        userService = new UserService(context);
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void onLoginButtonClicked() {
        if (username.getValue() != null & password.getValue() != null) {
            String username = this.username.getValue();
            String password = this.password.getValue();
            password = Utilities.computeSHA256Hash(password);
            User user = new User(username, password);
            login(user);
        } else {
            Toast.makeText(context, R.string.empty_fields_label, Toast.LENGTH_SHORT).show();
        }
    }

    private void login(User user) {
        userService.login(user, new UserService.UserServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                    Toast.makeText(context, R.string.invalid_data_label, Toast.LENGTH_SHORT).show();
                } else {
                    String token = response.getToken();
                    setToken(token);
                    getStudent(user);
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

    private void getStudent(User user) {
        userService.getUserByUsername(user, new UserService.UserServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    String registrationNumber = response.getUsers().get(0).getRegistrationNumber();
                    Student student = new Student(registrationNumber);
                    setStudent(student);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setStudent(Student student) {
        studentService.getStudentByRegistrationNumber(student, new StudentService.StudentServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    DataManager.getInstance().setStudent(response.getStudents().get(0));
                    goToMainMenu(response.getStudents().get(0));
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void goToMainMenu(Student student) {
        Intent intent = new Intent(context, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }

    public void onSignUpButtonClicked() {
        Intent intent = new Intent(context, LogStudentEmailAddressPhoneNumberActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void onActiveAccountButtonClicked() {
        Intent intent = new Intent(context, ActiveAccountActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

}