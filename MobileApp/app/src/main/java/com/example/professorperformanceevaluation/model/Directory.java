package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Directory {

    @JsonProperty("idDirectory")
    private int idDirectory;
    @JsonProperty("idFaculty")
    private int idFaculty;
    @JsonProperty("idProfessor")
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