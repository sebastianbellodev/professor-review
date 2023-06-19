package com.example.professorperformanceevaluation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.adapter.ReviewAdapter;
import com.example.professorperformanceevaluation.databinding.ActivityModifyStudentBinding;
import com.example.professorperformanceevaluation.databinding.ActivityQueryEducationalExperienceBinding;
import com.example.professorperformanceevaluation.databinding.ActivityQueryProfessorBinding;
import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.Professor;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.viewmodel.QueryEducationalExperienceViewModel;
import com.example.professorperformanceevaluation.viewmodel.QueryProfessorViewModel;

public class QueryProfessorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityQueryProfessorBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_query_professor);
        QueryProfessorViewModel viewModel = new ViewModelProvider(this).get(QueryProfessorViewModel.class);
        ReviewAdapter reviewAdapter = new ReviewAdapter();
        viewModel.reviews.observe(this, reviews -> {
            reviewAdapter.setReviews(reviews);
            reviewAdapter.notifyDataSetChanged();
        });
        Student student = (Student) getIntent().getSerializableExtra("student");
        viewModel.setStudent(student);
        binding.setLifecycleOwner(this);
        binding.setQueryProfessorViewModel(viewModel);
        Spinner professorSpinner = findViewById(R.id.professor_spinner);
        professorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Professor professor = (Professor) professorSpinner.getItemAtPosition(position);
                viewModel.setProfessor(professor);
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