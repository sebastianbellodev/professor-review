package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EducationalExperience {

    @JsonProperty("idEducationalExperience")
    private int idEducationalExperience;
    @JsonProperty("name")
    private String name;

    public EducationalExperience() {
    }

    public int getIdEducationalExperience() {
        return idEducationalExperience;
    }

    public void setIdEducationalExperience(int idEducationalExperience) {
        this.idEducationalExperience = idEducationalExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}