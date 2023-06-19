package com.example.professorperformanceevaluation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityQueryStudentProfileBinding;
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.viewmodel.QueryStudentProfileViewModel;

public class QueryStudentProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityQueryStudentProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_query_student_profile);
        QueryStudentProfileViewModel  viewModel = new ViewModelProvider(this).get(QueryStudentProfileViewModel.class);
        Student student = (Student) getIntent().getSerializableExtra("student");
        viewModel.setStudent(student);
        binding.setLifecycleOwner(this);
        binding.setQueryStudentModelView(viewModel);
        TextView textViewName = findViewById(R.id.username_text_view);
        TextView textViewLastName = findViewById(R.id.last_name_text_view);
        TextView textViewEmail = findViewById(R.id.email_adress_text_view);
        TextView textViewPhoneNumber = findViewById(R.id.phone_number_text_view);
        TextView textViewBiography = findViewById(R.id.student_biography_text_view);
        Student usableStudent = DataManager.getInstance().getStudent();
        textViewName.setText(usableStudent.getName());
        textViewLastName.setText(usableStudent.getLastName());
        textViewEmail.setText(usableStudent.getEmailAddress());
        textViewPhoneNumber.setText(usableStudent.getPhoneNumber());
        textViewBiography.setText(usableStudent.getBiography());
    }
}