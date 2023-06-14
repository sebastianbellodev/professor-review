package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Syllabus {

    @JsonProperty("idSyllabus")
    private int idSyllabus;
    @JsonProperty("idEducationalProgram")
    private int idEducationalProgram;
    @JsonProperty("idEducationalExperience")
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