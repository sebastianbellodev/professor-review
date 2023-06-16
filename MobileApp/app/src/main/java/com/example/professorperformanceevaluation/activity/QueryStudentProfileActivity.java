package com.example.professorperformanceevaluation.activity;

import android.os.Bundle;

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
    }
}
