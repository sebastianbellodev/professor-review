package com.example.professorperformanceevaluation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.adapter.ReviewAdapter;
import com.example.professorperformanceevaluation.databinding.ActivityMyReviewListBinding;
import com.example.professorperformanceevaluation.viewmodel.MyReviewListViewModel;

public class MyReviewListActivity extends AppCompatActivity{
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMyReviewListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_my_review_list);
        MyReviewListViewModel viewModel = new ViewModelProvider(this).get(MyReviewListViewModel.class);
        ReviewAdapter reviewAdapter = new ReviewAdapter();
        RecyclerView reviewRecyclerView = findViewById(R.id.review_recycler_view);
        viewModel.setReviewAdapter(reviewAdapter);
        viewModel.reviews.observe(this,reviews -> {
            reviewAdapter.setReviews(reviews);
            reviewAdapter.notifyDataSetChanged();
        });
        reviewAdapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                viewModel.setReviewUpdate(position);
                Toast.makeText(MyReviewListActivity.this, "Se selecciono " + position, Toast.LENGTH_SHORT).show();
            }
        });
        /*
        this.gestureDetector = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(MyReviewListActivity.this, "Doble toque detectado", Toast.LENGTH_SHORT).show();
                View childView = reviewRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    int position = reviewRecyclerView.getChildAdapterPosition(childView);
                    if (position != RecyclerView.NO_POSITION) {
                        reviewAdapter.setSelectedItem(position);
                        return true;
                    }
                }
                return false;
            }

        });

        reviewAdapter.setGestureDetector(gestureDetector);*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        reviewRecyclerView.setLayoutManager(linearLayoutManager);
        reviewRecyclerView.setAdapter(reviewAdapter);

        binding.setLifecycleOwner(this);
        binding.setMyReviewListViewModel(viewModel);
    }

}