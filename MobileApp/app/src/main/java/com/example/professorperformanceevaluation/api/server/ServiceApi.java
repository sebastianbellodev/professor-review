package com.example.professorperformanceevaluation.api.server;

public abstract class ServiceApi {
    private static final String BASE_URL = "http://professorperformanceevaluation-production-7405.up.railway.app/";

    public static String getBASE_URL() {
        return BASE_URL;
    }
}
