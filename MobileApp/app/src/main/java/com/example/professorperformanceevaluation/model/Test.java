package com.example.professorperformanceevaluation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test {
    @SerializedName("result")
    @Expose
<<<<<<< HEAD
    private Integer result;

    public Test(Integer result) {
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
=======
    private int result;

    public Test(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
>>>>>>> dd91e9286aaa31599cb923c0cb56dc00948cac15
        this.result = result;
    }
}
