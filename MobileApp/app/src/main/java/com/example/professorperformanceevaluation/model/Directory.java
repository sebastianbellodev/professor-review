package com.example.professorperformanceevaluation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Directory implements Serializable {

    @SerializedName("idDirectory")
    @Expose
    private int idDirectory;
    @SerializedName("idFaculty")
    @Expose
    private int idFaculty;
    @SerializedName("idProfessor")
    @Expose
    private int idProfessor;

    public Directory() {
    }

    public int getIdDirectory() {
        return idDirectory;
    }

    public void setIdDirectory(int idDirectory) {
        this.idDirectory = idDirectory;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

}