package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.UpdateReviewActivity;
import com.example.professorperformanceevaluation.adapter.ReviewAdapter;
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Review;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.service.service.ReviewService;

import java.net.HttpURLConnection;
import java.util.List;

public class MyReviewListViewModel extends AndroidViewModel {
    public MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private final Context context;
    private ReviewAdapter reviewAdapter;
    private Student student;

    public Review reviewUpdate;

    public MutableLiveData<List<Review>> getReviews() {
        return reviews;
    }

    public void setReviews(MutableLiveData<List<Review>> reviews) {
        this.reviews = reviews;
    }

    public ReviewAdapter getReviewAdapter() {
        return reviewAdapter;
    }

    public void setReviewAdapter(ReviewAdapter reviewAdapter) {
        this.reviewAdapter = reviewAdapter;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public MyReviewListViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.student = DataManager.getInstance().getStudent();
        this.loadReviews();
    }
    public void setReviewUpdate(int i){
        reviewUpdate = this.reviews.getValue().get(i);
    }
    public void goEditReview(){
        if(reviewUpdate!=null) {
            Review review = this.reviewUpdate;
            Intent intent = new Intent(context, UpdateReviewActivity.class);
            intent.putExtra("review", review);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }else {
            Toast.makeText(context, "Seleccione una review para poder editarla", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadReviews(){
        ReviewService reviewService = new ReviewService(context);
        reviewService.getReviewsofStudent(student, new ReviewService.ReviewServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    reviews.setValue(response.getReviews());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
