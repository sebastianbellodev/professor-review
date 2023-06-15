package com.example.professorperformanceevaluation.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityLogStudentEducationalProgramBinding;
import com.example.professorperformanceevaluation.viewmodel.LogStudentEducationalProgramViewModel;

public class LogStudentEducationalProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLogStudentEducationalProgramBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_log_student_educational_program);
        LogStudentEducationalProgramViewModel viewModel = new ViewModelProvider(this).get(LogStudentEducationalProgramViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setLogStudentEducationalProgramViewModel(viewModel);
    }

}