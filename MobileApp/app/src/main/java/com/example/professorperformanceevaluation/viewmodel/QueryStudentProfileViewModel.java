package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.MainMenuActivity;
import com.example.professorperformanceevaluation.activity.StudentManagementMenuActivity;
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.service.StudentService;

import java.net.HttpURLConnection;

public class QueryStudentProfileViewModel extends AndroidViewModel {

    private final MutableLiveData<String> username = new MutableLiveData<>();
    private final MutableLiveData<String> lastName = new MutableLiveData<>();
    private final MutableLiveData<String> name = new MutableLiveData<>();
    private final MutableLiveData<String> phoneNumber = new MutableLiveData<>();
    private final MutableLiveData<String> emailAddress = new MutableLiveData<>();
    private final MutableLiveData<String> biography = new MutableLiveData<>();
    private final Context context;
    private Student student;
    private User user;
    private Student actualStudent;
    private final StudentService studentService;


    public QueryStudentProfileViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        studentService = new StudentService(context);
        student = DataManager.getInstance().getStudent();
        user = DataManager.getInstance().getUser();
        username.setValue(user.getUsername());
        biography.setValue(student.getBiography());
        name.setValue(student.getName());
        lastName.setValue(student.getLastName());
        phoneNumber.setValue(student.getPhoneNumber());
        emailAddress.setValue(student.getEmailAddress());
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getLastName() {
        return lastName;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public MutableLiveData<String> getEmailAddress() {
        return emailAddress;
    }

    public MutableLiveData<String> getBiography() {
        return biography;
    }


    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return actualStudent;
    }

    public void onReturnButtonClicked() {
        Intent intent = new Intent(context, StudentManagementMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }
}
