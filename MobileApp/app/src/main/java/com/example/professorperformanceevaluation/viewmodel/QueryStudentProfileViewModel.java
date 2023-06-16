package com.example.professorperformanceevaluation.viewmodel;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.professorperformanceevaluation.service.service.StudentService;

public class QueryStudentProfileViewModel extends  AndroidViewModel{

    private  final Context context;

    private final StudentService studentService;

    public QueryStudentProfileViewModel(@NonNull Application application){
        super(application);
        context = application.getApplicationContext();
        studentService = new StudentService(context);

    }
}
