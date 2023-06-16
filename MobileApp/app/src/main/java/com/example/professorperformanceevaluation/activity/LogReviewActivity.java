package com.example.professorperformanceevaluation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityActiveAccountBinding;
import com.example.professorperformanceevaluation.databinding.ActivityLogReviewBinding;
import com.example.professorperformanceevaluation.viewmodel.LogReviewViewModel;

public class LogReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLogReviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_log_review);
        LogReviewViewModel viewModel = new ViewModelProvider(this).get(LogReviewViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setLogReviewViewModel(viewModel);
    }
}