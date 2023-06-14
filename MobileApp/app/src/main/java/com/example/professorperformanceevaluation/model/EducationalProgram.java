package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EducationalProgram {

    @JsonProperty("idEducationalProgram")
    public int idEducationalProgram;
    @JsonProperty("name")
    public String name;
    @JsonProperty("idFaculty")
    public int idFaculty;

    public EducationalProgram() {
    }

    public int getIdEducationalProgram() {
        return idEducationalProgram;
    }

    public void setIdEducationalProgram(int idEducationalProgram) {
        this.idEducationalProgram = idEducationalProgram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

}