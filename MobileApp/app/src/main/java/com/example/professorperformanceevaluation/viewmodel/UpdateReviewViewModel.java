package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.ProfessorPerformanceManagementMenuActivity;
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Review;
import com.example.professorperformanceevaluation.model.SchoolPeriod;
import com.example.professorperformanceevaluation.service.service.ReviewService;
import com.example.professorperformanceevaluation.service.service.SchoolPeriodService;

import java.net.HttpURLConnection;
import java.util.List;

public class UpdateReviewViewModel extends AndroidViewModel {
    private final MutableLiveData<List<SchoolPeriod>> schoolPeriods = new MutableLiveData<>();
    private SchoolPeriod schoolPeriod;
    private Review review;
    private final MutableLiveData<String> comment = new MutableLiveData<>();
    private final MutableLiveData<String> educationalExperience = new MutableLiveData<>();
    private final MutableLiveData<String> professor = new MutableLiveData<>();
    private float rating;
    private final Context context;


    public UpdateReviewViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.loadSchoolPeriods();
    }

    private void loadSchoolPeriods(){
        SchoolPeriodService schoolPeriodService = new SchoolPeriodService(context);
        schoolPeriodService.getSchoolPeriods(new SchoolPeriodService.SchoolPeriodServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    schoolPeriods.setValue(response.getSchoolPeriods());
                }
            }
            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public MutableLiveData<List<SchoolPeriod>> getSchoolPeriods() {
        return schoolPeriods;
    }

    public SchoolPeriod getSchoolPeriod() {
        return schoolPeriod;
    }

    public MutableLiveData<String> getEducationalExperience() {
        return educationalExperience;
    }

    public MutableLiveData<String> getProfessor() {
        return professor;
    }

    public void setSchoolPeriod(SchoolPeriod schoolPeriod) {
        this.schoolPeriod = schoolPeriod;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public MutableLiveData<String> getComment() {
        return comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void onAceptClicked(){
        if(this.comment.getValue()==null){
            Toast.makeText(context, "No se pueden dejar campos vacios", Toast.LENGTH_SHORT).show();
        }else{
            this.updateReview();
        }

    }

    public void updateReview(){
        String newComment = this.comment.getValue();
        review.setComment(newComment);
        review.setStars(Math.round(this.rating));
        review.setIdSchoolPeriod(this.schoolPeriod.getIdSchoolPeriod());
        ReviewService reviewService = new ReviewService(context);
        reviewService.patch(review, new ReviewService.ReviewServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else if(code == 456){
                    Toast.makeText(context, "Ya se encuentra una reseña registrada con la misma información", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Se ha actualizado correctamente en el sistema", Toast.LENGTH_SHORT).show();
                    goToMenu();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void goToMenu(){
        Intent intent = new Intent(context, ProfessorPerformanceManagementMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", DataManager.getInstance().getStudent());
        context.startActivity(intent);
    }
}
