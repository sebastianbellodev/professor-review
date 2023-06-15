package com.example.professorperformanceevaluation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Syllabus implements Serializable {

    @SerializedName("idSyllabus")
    @Expose
    private int idSyllabus;
    @SerializedName("idEducationalProgram")
    @Expose
    private int idEducationalProgram;
    @SerializedName("idEducationalExperience")
    @Expose
    private int idEducationalExperience;

    public Syllabus() {
    }

    public int getIdSyllabus() {
        return idSyllabus;
    }

    public void setIdSyllabus(int idSyllabus) {
        this.idSyllabus = idSyllabus;
    }

    public int getIdEducationalProgram() {
        return idEducationalProgram;
    }

    public void setIdEducationalProgram(int idEducationalProgram) {
        this.idEducationalProgram = idEducationalProgram;
    }

    public int getIdEducationalExperience() {
        return idEducationalExperience;
    }

    public void setIdEducationalExperience(int idEducationalExperience) {
        this.idEducationalExperience = idEducationalExperience;
    }

}