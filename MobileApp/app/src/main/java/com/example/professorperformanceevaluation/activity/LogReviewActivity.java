package com.example.professorperformanceevaluation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityActiveAccountBinding;
import com.example.professorperformanceevaluation.databinding.ActivityLogReviewBinding;
import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Professor;
import com.example.professorperformanceevaluation.model.SchoolPeriod;
import com.example.professorperformanceevaluation.viewmodel.LogReviewViewModel;

public class LogReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLogReviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_log_review);
        LogReviewViewModel viewModel = new ViewModelProvider(this).get(LogReviewViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setLogReviewViewModel(viewModel);
        //SchoolPeriod
        Spinner periodsSpinner = findViewById(R.id.spinnerPeriods);
        periodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SchoolPeriod schoolPeriod = (SchoolPeriod) adapterView.getItemAtPosition(position);
                viewModel.setSchoolPeriod(schoolPeriod);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //EducationalExperience
        Spinner educationalSpinner = findViewById(R.id.spinnerEducationalExperience);
        educationalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                EducationalExperience educationalExperience = (EducationalExperience) adapterView.getItemAtPosition(position);
                viewModel.setEducationalExperience(educationalExperience);
                viewModel.onEducationalExperienceSelected(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //Professor
        Spinner professorSpinner = findViewById(R.id.spinnerProfessor);
        professorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Professor professor = (Professor) adapterView.getItemAtPosition(position);
                viewModel.setProfessor(professor);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}