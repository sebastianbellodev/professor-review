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
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.service.service.EducationalProgramService;
import com.example.professorperformanceevaluation.service.service.FacultyService;

import java.net.HttpURLConnection;
import java.util.List;

public class LogStudentEducationalProgramViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Faculty>> faculties = new MutableLiveData<>();
    private final MutableLiveData<List<EducationalProgram>> educationalPrograms = new MutableLiveData<>();
    private final Context context;
    private final EducationalProgramService educationalProgramService;
    private Student student;
    private Faculty faculty;
    private EducationalProgram educationalProgram;

    public LogStudentEducationalProgramViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        FacultyService facultyService = new FacultyService(context);
        educationalProgramService = new EducationalProgramService(context);
        facultyService.getFaculties(new FacultyService.FacultyServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    faculties.setValue(response.getFaculties());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MutableLiveData<List<Faculty>> getFaculties() {
        return faculties;
    }

    public MutableLiveData<List<EducationalProgram>> getEducationalPrograms() {
        return educationalPrograms;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
        getEducationalProgramsByFaculty();
    }

    public void getEducationalProgramsByFaculty() {
        educationalProgramService.getEducationalProgramsByFaculty(faculty, new EducationalProgramService.EducationalProgramServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    educationalPrograms.setValue(response.getEducationalPrograms());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setEducationalProgram(EducationalProgram educationalProgram) {
        this.educationalProgram = educationalProgram;
    }

    public void onAcceptButtonClicked() {
        if (educationalProgram != null) {
            int idEducationalProgram = educationalProgram.getIdEducationalProgram();
            student.setIdEducationalProgram(idEducationalProgram);
            DataManager.getInstance().setFaculty(faculty);
            goToLogStudent(student);
        } else {
            Toast.makeText(context, R.string.empty_fields_label, Toast.LENGTH_SHORT).show();
        }
    }

    public void goToLogStudent(Student student) {
        Intent intent = new Intent(context, LogStudentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }

    public void onCancelButtonClicked() {
        Intent intent = new Intent(context, LogStudentEmailAddressPhoneNumberActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }

}