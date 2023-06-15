package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.professorperformanceevaluation.activity.MainMenuActivity;
import com.example.professorperformanceevaluation.model.Student;

public class ProfessorPerformanceEvaluationsManagementMenuViewModel extends AndroidViewModel {

    private final Context context;
    private Student student;

    public ProfessorPerformanceEvaluationsManagementMenuViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void onLogProfessorPerformanceEvaluationButtonClicked() {
    }

    public void onModifyProfessorPerformanceEvaluationButtonClicked() {
    }

    public void onReturnButtonClicked() {
        Intent intent = new Intent(context, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }

}