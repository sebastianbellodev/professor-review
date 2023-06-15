package com.example.professorperformanceevaluation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AcademicOffering implements Serializable {

    @SerializedName("idAcademicOffering")
    @Expose
    private int idAcademicOffering;
    @SerializedName("idSyllabus")
    @Expose
    private int idSyllabus;
    @SerializedName("idProfessor")
    @Expose
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