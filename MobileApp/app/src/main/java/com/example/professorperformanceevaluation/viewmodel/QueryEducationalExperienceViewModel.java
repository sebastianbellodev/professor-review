package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.EducationalProgramAdministrationMenuActivity;
import com.example.professorperformanceevaluation.adapter.ReviewAdapter;
import com.example.professorperformanceevaluation.model.AcademicOffering;
import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Professor;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Review;
import com.example.professorperformanceevaluation.model.SchoolPeriod;
import com.example.professorperformanceevaluation.model.Student;
import com.example.professorperformanceevaluation.service.service.AcademicOfferingService;
import com.example.professorperformanceevaluation.service.service.EducationalExperienceService;
import com.example.professorperformanceevaluation.service.service.ProfessorService;
import com.example.professorperformanceevaluation.service.service.ReviewService;
import com.example.professorperformanceevaluation.service.service.SchoolPeriodService;
import com.example.professorperformanceevaluation.service.service.StudentService;

import java.net.HttpURLConnection;
import java.util.List;

public class QueryEducationalExperienceViewModel extends AndroidViewModel {

    public MutableLiveData<List<EducationalExperience>> educationalExperiences = new MutableLiveData<>();
    public MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private final Context context;
    private ReviewAdapter reviewAdapter;
    private Student student;
    private EducationalExperience educationalExperience;
    private final EducationalExperienceService educationalExperienceService;
    private final ReviewService reviewService;

    public QueryEducationalExperienceViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        educationalExperienceService = new EducationalExperienceService(context);
        reviewService = new ReviewService(context);
    }

    public void setReviewAdapter(ReviewAdapter reviewAdapter) {
        this.reviewAdapter = reviewAdapter;
    }

    public void setStudent(Student student) {
        this.student = student;
        int idEducationalProgram = student.getIdEducationalProgram();
        EducationalProgram educationalProgram = new EducationalProgram(idEducationalProgram);
        educationalExperienceService.getEducationalExperiencesByEducationalProgram(educationalProgram, new EducationalExperienceService.EducationalExperienceServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    educationalExperiences.setValue(response.getEducationalExperiences());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setEducationalExperience(EducationalExperience educationalExperience) {
        this.educationalExperience = educationalExperience;
        getReviewsByEducationalExperience(educationalExperience);
    }

    private void getReviewsByEducationalExperience(EducationalExperience educationalExperience) {
        reviewService.getReviewsByEducationalExperience(educationalExperience, new ReviewService.ReviewServiceCallback() {
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

    public void onReturnButtonClicked() {
        Intent intent = new Intent(context, EducationalProgramAdministrationMenuActivity.class);
        intent.putExtra("student", student);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

}