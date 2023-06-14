package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {

    @JsonProperty("idReview")
    private int idReview;
    @JsonProperty("stars")
    private int stars;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("idSchoolPeriod")
    private int idSchoolPeriod;
    @JsonProperty("idAcademicOffering")
    private int idAcademicOffering;
    @JsonProperty("registrationNumber")
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