package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.LogStudentEducationalProgramActivity;
import com.example.professorperformanceevaluation.activity.LoginActivity;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.service.StudentService;
import com.example.professorperformanceevaluation.service.service.UserService;
import com.example.professorperformanceevaluation.utilities.Utilities;

import java.net.HttpURLConnection;
import java.util.Objects;

public class LogStudentViewModel extends AndroidViewModel {

    private final MutableLiveData<String> username = new MutableLiveData<>();
    private final MutableLiveData<String> name = new MutableLiveData<>();
    private final MutableLiveData<String> lastName = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();
    private final MutableLiveData<String> passwordConfirmation = new MutableLiveData<>();
    private final StudentService studentService;
    private final UserService userService;
    private final Context context;
    private Student student;
    private User user;

    public LogStudentViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        studentService = new StudentService(context);
        userService = new UserService(context);
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getLastName() {
        return lastName;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<String> getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void onCancelButtonClick() {
        Intent intent = new Intent(context, LogStudentEducationalProgramActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void onAcceptButtonClick() {
        if (checkEmptyFields()) {
            String password = getPassword().getValue();
            String passwordConfirmation = getPasswordConfirmation().getValue();
            if (Objects.equals(password, passwordConfirmation)) {
                String username = getUsername().getValue();
                password = Utilities.computeSHA256Hash(password);
                String registrationNumber = student.getRegistrationNumber();
                user = new User(username, password, registrationNumber);
                String name = getName().getValue();
                String lastName = getLastName().getValue();
                student.setName(name);
                student.setLastName(lastName);
                logStudent();
            } else {
                Toast.makeText(context, R.string.invalid_data_label, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, R.string.empty_fields_label, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkEmptyFields() {
        return getUsername().getValue() == null ||
                getName().getValue() == null ||
                getLastName().getValue() == null ||
                getPassword().getValue() == null ||
                getPasswordConfirmation().getValue() != null;
    }

    private void logStudent() {
        studentService.post(student, new StudentService.StudentServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    logUser();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logUser() {
        userService.post(user, new UserService.UserServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_BAD_REQUEST) {
                    Toast.makeText(context, R.string.user_already_registered, Toast.LENGTH_SHORT).show();
                } else if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.registered_information_label, Toast.LENGTH_SHORT).show();
                    goToLogin();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToLogin() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

}