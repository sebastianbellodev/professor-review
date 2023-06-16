package com.example.professorperformanceevaluation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityModifyStudentBinding;
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.viewmodel.ModifyStudentViewModel;

public class ModifyStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityModifyStudentBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_modify_student);
        ModifyStudentViewModel viewModel = new ViewModelProvider(this).get(ModifyStudentViewModel.class);
        viewModel.setStudent(DataManager.getInstance().getStudent());
        viewModel.setUser(DataManager.getInstance().getUser());
        binding.setLifecycleOwner(this);
        binding.setModifyStudentViewModel(viewModel);
    }
}