package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.professorperformanceevaluation.activity.MainMenuActivity;
import com.example.professorperformanceevaluation.activity.ModifyStudentActivity;
import com.example.professorperformanceevaluation.activity.QueryStudentProfileActivity;
import com.example.professorperformanceevaluation.model.Student;

public class StudentManagementViewModel extends AndroidViewModel {

    private final Context context;
    private Student student;

    public StudentManagementViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void onQueryStudentButtonClicked() {
        Intent intent = new Intent(context, QueryStudentProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }

    public void onModifyStudentButtonClicked() {
        Intent intent = new Intent(context, ModifyStudentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }

    public void onReturnButtonClicked() {
        Intent intent = new Intent(context, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }

}