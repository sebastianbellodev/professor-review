package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Faculty {

    @JsonProperty("idFaculty")
    private int idFaculty;
    @JsonProperty("name")
    private String name;

    public Faculty() {
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}