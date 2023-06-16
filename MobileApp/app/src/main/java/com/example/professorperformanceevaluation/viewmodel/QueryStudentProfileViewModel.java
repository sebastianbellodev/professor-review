package com.example.professorperformanceevaluation.viewmodel;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.MainMenuActivity;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.service.service.StudentService;

import java.net.HttpURLConnection;

public class QueryStudentProfileViewModel extends  AndroidViewModel{

    private  final Context context;
    private Student student;
    private TextView name;
    private final StudentService studentService;

    public QueryStudentProfileViewModel(@NonNull Application application){
        super(application);
        context = application.getApplicationContext();
        studentService = new StudentService(context);
        loadStudent();
    }

    public void setStudent(Student student) {this.student = student;}

    private void loadStudent(){
        studentService.getStudentByRegistrationNumber(student, new StudentService.StudentServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                    Toast.makeText(context, R.string.invalid_data_label, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.welcome_label, Toast.LENGTH_SHORT).show();
                    loadInformation(response.getStudents().get(0));
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadInformation(Student student){

    }

    public void onReturnButtonClicked() {
        Intent intent = new Intent(context, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }
}
