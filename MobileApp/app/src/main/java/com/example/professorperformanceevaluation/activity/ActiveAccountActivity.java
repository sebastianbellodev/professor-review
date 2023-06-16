package com.example.professorperformanceevaluation.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityActiveAccountBinding;
import com.example.professorperformanceevaluation.viewmodel.ActiveAccountViewModel;

public class ActiveAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityActiveAccountBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_active_account);
        ActiveAccountViewModel viewModel = new ViewModelProvider(this).get(ActiveAccountViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setActiveAccountViewModel(viewModel);
    }

}