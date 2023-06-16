package com.example.professorperformanceevaluation.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityProfessorPerformanceManagementMenuBinding;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.viewmodel.ProfessorPerformanceEvaluationsManagementMenuViewModel;

public class ProfessorPerformanceManagementMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfessorPerformanceManagementMenuBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_professor_performance_management_menu);
        ProfessorPerformanceEvaluationsManagementMenuViewModel viewModel = new ViewModelProvider(this).get(ProfessorPerformanceEvaluationsManagementMenuViewModel.class);
        Student student = (Student) getIntent().getSerializableExtra("student");
        viewModel.setStudent(student);
        binding.setLifecycleOwner(this);
        binding.setProfessorPerformanceManagementMenuViewModel(viewModel);
    }

}