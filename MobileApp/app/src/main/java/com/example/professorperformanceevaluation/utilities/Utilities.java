package com.example.professorperformanceevaluation.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    public static String computeSHA256Hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte item : bytes) {
                String hex = String.format("%02x", item);
                stringBuilder.append(hex);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String formatterDate(Date fecha) {
        DateFormat formatoFechaEntrada = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatoFechaSalida = new SimpleDateFormat("dd/MM/yyyy");

            return formatoFechaSalida.format(fecha);
    }

}