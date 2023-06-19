package com.example.professorperformanceevaluation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.databinding.ActivityLogReviewBinding;
import com.example.professorperformanceevaluation.databinding.ActivityUpdateReviewBinding;
import com.example.professorperformanceevaluation.model.Review;
import com.example.professorperformanceevaluation.model.SchoolPeriod;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.viewmodel.UpdateReviewViewModel;

public class UpdateReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUpdateReviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_update_review);
        UpdateReviewViewModel viewModel =
                new ViewModelProvider(this).get(UpdateReviewViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setUpdateReviewViewModel(viewModel);
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
        //Intent review
        Review review = (Review)getIntent().getSerializableExtra("review");
        viewModel.setReview(review);
        TextView textViewProfessor = findViewById(R.id.professor_text_view);
        textViewProfessor.setText(review.getProfessor());
        TextView textViewEducationalExperience = findViewById(R.id.educational_experience_text_view);
        textViewEducationalExperience.setText(review.getEducationalExperience());
    }
}