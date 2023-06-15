package com.example.professorperformanceevaluation.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityEducationalProgramAdministrationMenuBinding;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.viewmodel.EducationalProgramAdministrationMenuViewModel;

public class EducationalProgramAdministrationMenuActivity extends AppCompatActivity {

    private ActivityEducationalProgramAdministrationMenuBinding binding;
    private EducationalProgramAdministrationMenuViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_educational_program_administration_menu);
        viewModel = new ViewModelProvider(this).get(EducationalProgramAdministrationMenuViewModel.class);
        Student student = (Student) getIntent().getSerializableExtra("student");
        viewModel.setStudent(student);
        binding.setLifecycleOwner(this);
        binding.setEducationalProgramAdministrationMenuViewModel(viewModel);
    }

}