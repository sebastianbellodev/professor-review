package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Student {
    @SerializedName("registrationNumber")
    @Expose
    private String registrationNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("activationDate")
    @Expose
    private Date activationDate;
    @SerializedName("oneTimePassword")
    @Expose
    private String oneTimePassword;
    @SerializedName("idEducationalProgram")
    @Expose
    private int idEducationalProgram;

    public Student() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public int getIdEducationalProgram() {
        return idEducationalProgram;
    }

    public void setIdEducationalProgram(int idEducationalProgram) {
        this.idEducationalProgram = idEducationalProgram;
    }

}