package com.ia.simulador.polimorfismo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimuladorIA {
    public static void main(String[] args) {

        List<Entrenable> pipelineEntrenamiento = new ArrayList<>();
        pipelineEntrenamiento.add(new RedNeuronal("Perceptrón Multicapa", 0.01, 5));
        pipelineEntrenamiento.add(new ArbolDecision("Clasificador de Iris", 0.05, 15));
        pipelineEntrenamiento.add(new ModeloRegresion("Regresión Ridge", 0.001, 0.01));

        System.out.println("Ejecutando Optimización Genérica en el Pipeline");
        for (Entrenable modelo : pipelineEntrenamiento) {
            modelo.ajustarPesos(0.02);
        }
        System.out.println();

        String textoPrueba = "La inteligencia artificial y el aprendizaje neuronal cambian el software.";
        System.out.println("Texto original: \"" + textoPrueba + "\"\n");

        Tokenizador miTokenizador = new TokenizadorBasico();
        System.out.println("[Configuración: TokenizadorBasico]");
        System.out.println("Tokens resultantes: " + Arrays.toString(miTokenizador.dividirTexto(textoPrueba)));
        System.out.println();

        miTokenizador = new TokenizadorHuggingFace();
        System.out.println("[Configuración: TokenizadorHuggingFace]");
        System.out.println("Tokens resultantes: " + Arrays.toString(miTokenizador.dividirTexto(textoPrueba)));

    }
}