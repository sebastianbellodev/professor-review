package com.example.professorperformanceevaluation.api.server;

public abstract class ApiService {

    private static final String BASE_URL = "http://professorperformanceevaluation-production-7405.up.railway.app/api/";

    public static String getBaseUrl() {
        return BASE_URL;
    }

}