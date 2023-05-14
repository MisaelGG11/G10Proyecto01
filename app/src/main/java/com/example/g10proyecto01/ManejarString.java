package com.example.g10proyecto01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManejarString {
    public static int extraerNumero(String cadena) {
        String regex = "(\\d+):.*"; // Expresión regular para buscar un número seguido de dos puntos y cualquier otro texto
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cadena);

        if (matcher.find()) {
            String numeroString = matcher.group(1);
            int numero = Integer.parseInt(numeroString);
            return numero;
        } else {
            // Manejar el caso en que no se encuentra un número antes de los dos puntos
            return -1; // por ejemplo
        }
    }

}
