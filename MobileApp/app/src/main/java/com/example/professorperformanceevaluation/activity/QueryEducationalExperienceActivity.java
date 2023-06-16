package com.example.professorperformanceevaluation.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.adapter.ReviewAdapter;
import com.example.professorperformanceevaluation.databinding.ActivityQueryEducationalExperienceBinding;
import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.viewmodel.QueryEducationalExperienceViewModel;

public class QueryEducationalExperienceActivity extends AppCompatActivity {

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityQueryEducationalExperienceBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_query_educational_experience);
        QueryEducationalExperienceViewModel viewModel = new ViewModelProvider(this).get(QueryEducationalExperienceViewModel.class);
        ReviewAdapter reviewAdapter = new ReviewAdapter();
        viewModel.setReviewAdapter(reviewAdapter);
        viewModel.reviews.observe(this, reviews -> {
            reviewAdapter.setReviews(reviews);
            reviewAdapter.notifyDataSetChanged();
        });
        Student student = (Student) getIntent().getSerializableExtra("student");
        viewModel.setStudent(student);
        binding.setLifecycleOwner(this);
        binding.setQueryEducationalExperienceViewModel(viewModel);
        Spinner educationalExperienceSpinner = findViewById(R.id.educational_experience_spinner);
        educationalExperienceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EducationalExperience educationalExperience = (EducationalExperience) educationalExperienceSpinner.getItemAtPosition(position);
                viewModel.setEducationalExperience(educationalExperience);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        RecyclerView reviewRecyclerView = findViewById(R.id.review_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        reviewRecyclerView.setLayoutManager(linearLayoutManager);
        reviewRecyclerView.setAdapter(reviewAdapter);
    }

}