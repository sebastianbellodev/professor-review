package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcademicOffering {

    @JsonProperty("idAcademicOffering")
    private int idAcademicOffering;
    @JsonProperty("idSyllabus")
    private int idSyllabus;
    @JsonProperty("idProfessor")
    private int idProfessor;

    public AcademicOffering() {
    }

    public int getIdAcademicOffering() {
        return idAcademicOffering;
    }

    public void setIdAcademicOffering(int idAcademicOffering) {
        this.idAcademicOffering = idAcademicOffering;
    }

    public int getIdSyllabus() {
        return idSyllabus;
    }

    public void setIdSyllabus(int idSyllabus) {
        this.idSyllabus = idSyllabus;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

}