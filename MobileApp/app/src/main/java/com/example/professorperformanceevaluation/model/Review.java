package com.example.professorperformanceevaluation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {
    @SerializedName("idEducationalExperience")
    @Expose
    private int idEducationaExperience;
    @SerializedName("idEducationalProgram")
    @Expose
    private int idEducationalProgram;
    @SerializedName("idProfessor")
    @Expose
    private int idProfessor;
    @SerializedName("idSyllabus")
    @Expose
    private int idSyllabus;
    @SerializedName("idReview")
    @Expose
    private int idReview;
    @SerializedName("stars")
    @Expose
    private int stars;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("idSchoolPeriod")
    @Expose
    private int idSchoolPeriod;
    private String schoolPeriod;
    @SerializedName("idAcademicOffering")
    @Expose
    private int idAcademicOffering;
    private String educationalExperience;
    private String professor;
    @SerializedName("registrationNumber")
    @Expose
    private String registrationNumber;
    private String student;

    public Review() {
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public int getIdSyllabus() {
        return idSyllabus;
    }

    public void setIdSyllabus(int idSyllabus) {
        this.idSyllabus = idSyllabus;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdEducationaExperience() {
        return idEducationaExperience;
    }

    public void setIdEducationaExperience(int idEducationaExperience) {
        this.idEducationaExperience = idEducationaExperience;
    }

    public int getIdEducationalProgram() {
        return idEducationalProgram;
    }

    public void setIdEducationalProgram(int idEducationalProgram) {
        this.idEducationalProgram = idEducationalProgram;
    }

    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdSchoolPeriod() {
        return idSchoolPeriod;
    }

    public void setIdSchoolPeriod(int idSchoolPeriod) {
        this.idSchoolPeriod = idSchoolPeriod;
    }

    public String getSchoolPeriod() {
        return schoolPeriod;
    }

    public void setSchoolPeriod(String schoolPeriod) {
        this.schoolPeriod = schoolPeriod;
    }

    public int getIdAcademicOffering() {
        return idAcademicOffering;
    }

    public void setIdAcademicOffering(int idAcademicOffering) {
        this.idAcademicOffering = idAcademicOffering;
    }

    public String getEducationalExperience() {
        return educationalExperience;
    }

    public void setEducationalExperience(String educationalExperience) {
        this.educationalExperience = educationalExperience;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

}