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
    private MutableLiveData<String> emailAddress = new MutableLiveData<>();
    private final Context context;
    
    private final StudentService studentService;

    private Student student;

    private User user;
    
    
    public ModifyStudentViewModel(@NonNull Application application) {
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

    public MutableLiveData<String> getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(MutableLiveData<String> emailAddress) {
        this.emailAddress = emailAddress;
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

    private void patch(User user) {

    }

    public void goToLogStudent(Student student) {

    }

    public void onCancelButtonClicked() {
        Intent intent = new Intent(context, StudentManagementMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }
}
