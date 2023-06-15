package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.professorperformanceevaluation.service.service.EducationalProgramService;
import com.example.professorperformanceevaluation.service.service.FacultyService;

public class LogStudentEducationalProgramViewModel extends AndroidViewModel {

    private final Context context;

    private final FacultyService facultyService;
    private final EducationalProgramService educationalProgramService;

    public LogStudentEducationalProgramViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        facultyService = new FacultyService(context);
        educationalProgramService = new EducationalProgramService(context);
    }

}