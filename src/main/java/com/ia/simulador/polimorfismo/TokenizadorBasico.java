package com.ia.simulador.polimorfismo;

public class TokenizadorBasico implements Tokenizador {

    @Override
    public String[] dividirTexto(String parrafo) {
        if (parrafo == null || parrafo.trim().isEmpty()) {
            return new String[0];
        }

        return parrafo.split("\\s+");
    }

}
