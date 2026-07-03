package com.ia.encapsulamiento;

import com.ia.simulador.abstraction.Tokenizador;
import com.ia.simulador.abstraction.TokenizadorBasico;
import com.ia.simulador.abstraction.TokenizadorHuggingFace;

public class SimuladorIA {
    public static void main(String[] args) {
        System.out.println("PRUEBA DE ABSTRACCIÓN Y CONTRATOS");

        System.out.println("[OK] La superclase ModeloIA está protegida contra instanciaciones directas.\n");

        ModeloIA[] modelos = new ModeloIA[3];
        modelos[0] = new RedNeuronal("Perceptrón Multicapa", 0.70, 3);
        modelos[1] = new ArbolDecision("Random Forest Node", 0.65, 8);
        modelos[2] = new ModeloRegresion("ElasticNet Linear", 0.60, "Lasso");

        System.out.println("Ejecutando Entrenamiento Polimórfico");
        for (ModeloIA modelo : modelos) {
            modelo.mostrarMetricas();
            modelo.train();
            modelo.mostrarMetricas();
            System.out.println();
        }

        System.out.println("Pipeline de Procesamiento de Texto Abstracto");
        String textoPrueba = "Corelia automatiza los procesos académicos universitarios.";

        // Uso de Tokenizador Básico
        Tokenizador tokenizador = new TokenizadorBasico();
        System.out.println("Usando: TokenizadorBasico");
        imprimirTokens(tokenizador.dividirTexto(textoPrueba));

        // Intercambio dinámico a HuggingFace
        tokenizador = new TokenizadorHuggingFace();
        System.out.println("\nUsando: TokenizadorHuggingFace (Subwords/Signos)");
        imprimirTokens(tokenizador.dividirTexto(textoPrueba));
    }

    private static void imprimirTokens(String[] tokens) {
        System.out.print("Tokens generados: [ ");
        for (String t : tokens) {
            System.out.print("'" + t + "' ");
        }
        System.out.println("]");
    }
}