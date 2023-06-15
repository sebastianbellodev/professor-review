package com.example.professorperformanceevaluation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityLogStudentEducationalProgramBinding;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.viewmodel.LogStudentEducationalProgramViewModel;

public class LogStudentEducationalProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLogStudentEducationalProgramBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_log_student_educational_program);
        LogStudentEducationalProgramViewModel viewModel = new ViewModelProvider(this).get(LogStudentEducationalProgramViewModel.class);
        Student student = (Student) getIntent().getSerializableExtra("student");
        viewModel.setStudent(student);
        binding.setLifecycleOwner(this);
        binding.setLogStudentEducationalProgramViewModel(viewModel);
        Spinner spinner = findViewById(R.id.faculty_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Faculty faculty = (Faculty) adapterView.getItemAtPosition(position);
                viewModel.setFaculty(faculty);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}