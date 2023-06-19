package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.LogStudentActivity;
import com.example.professorperformanceevaluation.activity.LogStudentEmailAddressPhoneNumberActivity;
import com.example.professorperformanceevaluation.activity.MainMenuActivity;
import com.example.professorperformanceevaluation.activity.StudentManagementMenuActivity;
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.service.StudentService;
import com.example.professorperformanceevaluation.service.service.UserService;

import java.net.HttpURLConnection;

public class ModifyStudentViewModel extends AndroidViewModel {
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> biography = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> lastName = new MutableLiveData<>();
    private MutableLiveData<String> phoneNumber = new MutableLiveData<>();
    private final Context context;
    
    private final StudentService studentService;

    private Student student;
    private Student oldStudent;

    private User user;
    private User oldUser;
    
    
    public ModifyStudentViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        studentService = new StudentService(context);
        oldStudent = DataManager.getInstance().getStudent();
        student = new Student();
        oldUser = DataManager.getInstance().getUser();
        user = new User();
        username.setValue(oldUser.getUsername());
        biography.setValue(oldStudent.getBiography());
        if (oldStudent.getBiography() == null) {
            oldStudent.setBiography("");
        }
        name.setValue(oldStudent.getName());
        lastName.setValue(oldStudent.getLastName());
        phoneNumber.setValue(oldStudent.getPhoneNumber());
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public void setUsername(MutableLiveData<String> username) {
        this.username = username;
    }

    public MutableLiveData<String> getBiography() {
        return biography;
    }

    public void setBiography(MutableLiveData<String> biography) {
        this.biography = biography;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public MutableLiveData<String> getLastName() {
        return lastName;
    }

    public void setLastName(MutableLiveData<String> lastName) {
        this.lastName = lastName;
    }

    public MutableLiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(MutableLiveData<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void onAcceptButtonClicked() {
        System.out.println(phoneNumber.getValue().length());
    }

    public boolean validateClearField() {
        return (username.getValue().isEmpty())
                && (name.getValue().isEmpty())
                && (lastName.getValue().isEmpty())
                && (phoneNumber.getValue().isEmpty());
    }

    public boolean validateOverflowedField() {
        return phoneNumber.getValue().length() != 10;
    }

    private void patch(Student student, User user) {
        student.setUser(user);
        studentService.patch(student, new StudentService.StudentServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else if (code == HttpURLConnection.HTTP_NOT_FOUND || code == HttpURLConnection.HTTP_BAD_REQUEST) {
                    Toast.makeText(context, R.string.invalid_data_label, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.information_put_label, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCancelButtonClicked() {
        Intent intent = new Intent(context, StudentManagementMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }
}
