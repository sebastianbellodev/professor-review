package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.activity.ProfessorPerformanceManagementMenuActivity;
import com.example.professorperformanceevaluation.model.AcademicOffering;
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.EducationalProgram;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Professor;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Review;
import com.example.professorperformanceevaluation.model.SchoolPeriod;
import com.example.professorperformanceevaluation.model.Syllabus;
import com.example.professorperformanceevaluation.service.service.EducationalExperienceService;
import com.example.professorperformanceevaluation.service.service.ProfessorService;
import com.example.professorperformanceevaluation.service.service.ReviewService;
import com.example.professorperformanceevaluation.service.service.SchoolPeriodService;
import com.example.professorperformanceevaluation.service.service.SyllabusService;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class LogReviewViewModel extends AndroidViewModel {

    private final MutableLiveData<String> comment = new MutableLiveData<>();
    private float rating;
    private final MutableLiveData<List<SchoolPeriod>> schoolPeriods = new MutableLiveData<>();
    private final MutableLiveData<List<EducationalExperience>> educationalExperiences = new MutableLiveData<>();
    private final MutableLiveData<List<Professor>> professors = new MutableLiveData<>();
    private final Context context;

    private Professor professor;
    private SchoolPeriod schoolPeriod;
    private EducationalExperience educationalExperience;

    private int numberStars;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public MutableLiveData<String> getComment() {
        return comment;
    }

    public SchoolPeriod getSchoolPeriod() {
        return schoolPeriod;
    }

    public void setSchoolPeriod(SchoolPeriod schoolPeriod) {
        this.schoolPeriod = schoolPeriod;
    }

    public EducationalExperience getEducationalExperience() {
        return educationalExperience;
    }

    public void setEducationalExperience(EducationalExperience educationalExperience) {
        this.educationalExperience = educationalExperience;
    }

    public int getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(int numberStars) {
        this.numberStars = numberStars;
    }

    public LogReviewViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.loadSchoolPeriods();
        this.loadEducationalExperiencesByEducationalProgram();

    }

    public void onAcceptClicked() {
        if (this.comment.getValue() == null) {
            Toast.makeText(context, R.string.empty_fields_label, Toast.LENGTH_SHORT).show();
        } else {
            this.postReview();
        }

    }

    public void goToMenu() {
        Intent intent = new Intent(context, ProfessorPerformanceManagementMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("student", DataManager.getInstance().getStudent());
        context.startActivity(intent);


    }

    public void postReview() {
        Review review = new Review();
        review.setIdAcademicOffering(0);
        String newComment = this.comment.getValue();
        review.setComment(newComment);
        review.setIdEducationaExperience(this.getEducationalExperience().getIdEducationalExperience());
        review.setStars(Math.round(this.rating));
        review.setIdSyllabus(0);
        review.setIdEducationalProgram(DataManager.getInstance().getStudent().getIdEducationalProgram());
        review.setIdProfessor(this.professor.getIdProfessor());
        review.setIdSchoolPeriod(this.schoolPeriod.getIdSchoolPeriod());
        review.setRegistrationNumber(DataManager.getInstance().getStudent().getRegistrationNumber());
        ReviewService reviewService = new ReviewService(context);
        reviewService.post(review, new ReviewService.ReviewServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else if (code == 456) {
                    Toast.makeText(context, R.string.review_already_post_label, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.review_post_label, Toast.LENGTH_SHORT).show();
                    goToMenu();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onEducationalExperienceSelected(int position) {
        this.professors.setValue(new ArrayList<>());
        this.loadProfessorsByEducationalExperience(this.educationalExperiences.getValue().get(position));
    }

    private void loadProfessorsByEducationalExperience(EducationalExperience educationalExperience) {
        ProfessorService professorService = new ProfessorService(context);
        professorService.getProfessorsByEducationalExperience(educationalExperience, new ProfessorService.ProfessorServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                int code = response.getCode();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.expired_session_label, Toast.LENGTH_SHORT).show();
                } else {
                    professors.setValue(response.getProfessors());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, R.string.service_not_available_label, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loadEducationalExperiencesByEducationalProgram() {
        EducationalProgram educationalProgram = new EducationalProgram();
        educationalProgram.setIdEducationalProgram(DataManager.getInstance().getStudent().getIdEducationalProgram());
        EducationalExperienceService educationalExperienceService = new EducationalExperienceService(context);
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

    private void loadSchoolPeriods() {
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

    //Getters
    public MutableLiveData<List<SchoolPeriod>> getSchoolPeriods() {
        return schoolPeriods;
    }

    public MutableLiveData<List<Professor>> getProfessors() {
        return professors;
    }

    public MutableLiveData<List<EducationalExperience>> getEducationalExperiences() {
        return educationalExperiences;
    }
}
