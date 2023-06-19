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
        binding.setLifecycleOwner(this);
        binding.setQueryStudentModelView(viewModel);
    }
}