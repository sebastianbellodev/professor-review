package com.example.professorperformanceevaluation.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityLogStudentEmailAddressPhoneNumberBinding;
import com.example.professorperformanceevaluation.viewmodel.LogStudentEmailAddressPhoneNumberViewModel;

public class LogStudentEmailAddressPhoneNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLogStudentEmailAddressPhoneNumberBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_log_student_email_address_phone_number);
        LogStudentEmailAddressPhoneNumberViewModel viewModel = new ViewModelProvider(this).get(LogStudentEmailAddressPhoneNumberViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setLogStudentEmailAddressPhoneNumberViewModel(viewModel);
    }

}