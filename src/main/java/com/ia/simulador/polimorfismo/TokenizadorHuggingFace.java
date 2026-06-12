package com.ia.simulador.polimorfismo;

import java.util.ArrayList;
import java.util.List;

public class TokenizadorHuggingFace implements Tokenizador {

    @Override
    public String[] dividirTexto(String parrafo) {
        if (parrafo == null || parrafo.trim().isEmpty()) {
            return new String[0];
        }

        String[] palabras = parrafo.split("\\s+");
        List<String> subpalabras = new ArrayList<>();

        for (String palabra : palabras) {
            String palabraMinuscula = palabra.toLowerCase().replaceAll("[.,¡!¿?]", "");

            if (palabraMinuscula.contains("inteligencia")) {
                subpalabras.add("intel");
                subpalabras.add("##igencia");
            } else if (palabraMinuscula.contains("aprendizaje")) {
                subpalabras.add("apren");
                subpalabras.add("##dizaje");
            } else if (palabraMinuscula.contains("neuronal")) {
                subpalabras.add("neuro");
                subpalabras.add("##nal");
            } else {
                subpalabras.add(palabra);
            }
        }

        return subpalabras.toArray(new String[0]);
    }
}