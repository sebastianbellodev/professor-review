package com.example.professorperformanceevaluation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.professorperformanceevaluation.R;
import com.example.professorperformanceevaluation.model.DataManager;
import com.example.professorperformanceevaluation.model.EducationalExperience;
import com.example.professorperformanceevaluation.model.Faculty;
import com.example.professorperformanceevaluation.model.Professor;
import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.SchoolPeriod;
import com.example.professorperformanceevaluation.service.service.EducationalExperienceService;
import com.example.professorperformanceevaluation.service.service.ProfessorService;
import com.example.professorperformanceevaluation.service.service.SchoolPeriodService;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class LogReviewViewModel extends AndroidViewModel {
    private final MutableLiveData<List<SchoolPeriod>> schoolPeriods = new MutableLiveData<>();
    private final MutableLiveData<List<EducationalExperience>> educationalExperiences = new MutableLiveData<>();
    private final MutableLiveData<List<Professor>> professors = new MutableLiveData<>();
    private final Context context;

    public LogReviewViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.loadSchoolPeriods();
        this.loadEducationalExperiencesByFaculty();

    }

    public void onEducationalExperienceSelected(int position){
        this.professors.setValue(new ArrayList<>());
        this.loadProfesssorsByEducationalExperience(this.educationalExperiences.getValue().get(position));
    }
    private void loadProfesssorsByEducationalExperience(EducationalExperience educationalExperience){
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
    private void loadEducationalExperiencesByFaculty(){
        Faculty faculty = DataManager.getInstance().getFaculty();
        EducationalExperienceService educationalExperienceService = new EducationalExperienceService(context);
        educationalExperienceService.getEducationalExperiencesByFaculty(faculty, new EducationalExperienceService.EducationalExperienceServiceCallback() {
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
