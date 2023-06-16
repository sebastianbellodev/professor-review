package com.example.professorperformanceevaluation.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.QueryStudentBinding;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.viewmodel.QueryStudentProfileViewModel;

public class QueryStudentProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QueryStudentBinding binding = DataBindingUtil.setContentView(this, R.layout.query_student);
        QueryStudentProfileViewModel  viewModel = new ViewModelProvider(this).get(QueryStudentProfileViewModel.class);
        Student student = (Student) getIntent().getSerializableExtra("student");
        viewModel.setStudent(student);
        binding.setLifecycleOwner(this);
        binding.setQueryStudentProfileViewModel(viewModel);
        TextView textViewName = findViewById(R.id.student_name_text_view);
        TextView textViewLastName = findViewById(R.id.student_last_name_text_view);
        TextView textViewEmail = findViewById(R.id.student_email_text_view);
        TextView textViewPhoneNumber = findViewById(R.id.student_phone_number_text_view);
        TextView textViewBiography = findViewById(R.id.student_biography_text_view);

        Student usableStudent = viewModel.getStudent();
        textViewName.setText(usableStudent.getName());
        textViewLastName.setText(usableStudent.getLastName());
        textViewEmail.setText(usableStudent.getEmailAddress());
        textViewPhoneNumber.setText(usableStudent.getPhoneNumber());
        textViewBiography.setText(usableStudent.getBiography());
    }
}
