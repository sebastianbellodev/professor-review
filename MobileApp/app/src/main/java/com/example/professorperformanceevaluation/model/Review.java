package com.example.professorperformanceevaluation.model;

public class Review {

    private int idReview;
    private int stars;
    private String comment;
    private int idSchoolPeriod;
    private int idAcademicOffering;
    private String registrationNumber;

    public Review() {
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

    public int getIdAcademicOffering() {
        return idAcademicOffering;
    }

    public void setIdAcademicOffering(int idAcademicOffering) {
        this.idAcademicOffering = idAcademicOffering;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

}