package com.example.professorperformanceevaluation.api.server;

public abstract class ApiService {

    private static final String BASE_URL = "http://10.0.2.2:3000/api/";

    public static String getBaseUrl() {
        return BASE_URL;
    }

}