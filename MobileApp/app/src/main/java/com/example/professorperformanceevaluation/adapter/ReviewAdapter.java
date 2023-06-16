package com.example.professorperformanceevaluation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.model.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviews;

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return reviews != null ? reviews.size() : 0;
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        private TextView starsTextView;
        private TextView commentTextView;
        private TextView schoolPeriodTextView;
        private TextView educationalExperienceTextView;
        private TextView professorTextView;
        private TextView studentTextView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            starsTextView = itemView.findViewById(R.id.stars_text_view);
            commentTextView = itemView.findViewById(R.id.comment_text_view);
            schoolPeriodTextView = itemView.findViewById(R.id.school_period_text_view);
            educationalExperienceTextView= itemView.findViewById(R.id.educational_experience_text_view);
            professorTextView = itemView.findViewById(R.id.professor_text_view);
            studentTextView = itemView.findViewById(R.id.student_text_view);
        }

        public void bind(Review review) {
            starsTextView.setText(String.valueOf(review.getStars()));
            commentTextView.setText(review.getComment());
            educationalExperienceTextView.setText(review.getEducationalExperience());
            professorTextView.setText(review.getProfessor());
            studentTextView.setText(review.getStudent());
            schoolPeriodTextView.setText(review.getSchoolPeriod());
        }

    }
}