package com.example.professorperformanceevaluation.model;

import com.google.gson.annotations.SerializedName;

public class Test {
    @SerializedName("result")
    private Integer result;

    public Test(Integer result) {
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
