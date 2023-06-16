package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.professorperformanceevaluation.activity.EducationalProgramAdministrationMenuActivity;
import com.example.professorperformanceevaluation.activity.LoginActivity;
import com.example.professorperformanceevaluation.activity.ProfessorPerformanceManagementMenuActivity;
import com.example.professorperformanceevaluation.activity.StudentManagementMenuActivity;
import com.example.professorperformanceevaluation.model.Student;

public class MainMenuViewModel extends AndroidViewModel {

    private final Context context;
    private Student student;

    public MainMenuViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void onProfessorPerformanceEvaluationsManagementButtonClicked() {
        Intent intent = new Intent(context, ProfessorPerformanceManagementMenuActivity.class);
        intent.putExtra("student", student);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void onEducationalProgramManagementButtonClicked() {
        Intent intent = new Intent(context, EducationalProgramAdministrationMenuActivity.class);
        intent.putExtra("student", student);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void onStudentManagementButtonClicked() {
        Intent intent = new Intent(context, StudentManagementMenuActivity.class);
        intent.putExtra("student", student);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void onLogOutButtonClicked() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

}