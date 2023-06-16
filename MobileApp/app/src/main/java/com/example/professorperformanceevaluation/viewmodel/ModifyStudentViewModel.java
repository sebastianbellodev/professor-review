package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.LogStudentActivity;
import com.example.professorperformanceevaluation.activity.LogStudentEmailAddressPhoneNumberActivity;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.service.StudentService;

public class ModifyStudentViewModel extends AndroidViewModel {
    private final Context context;
    
    private final StudentService studentService;

    private Student student;

    private User user;
    
    
    public ModifyStudentViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        studentService = new StudentService(context);
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

    }

    public void goToLogStudent(Student student) {

    }

    public void onCancelButtonClicked() {

    }
}
