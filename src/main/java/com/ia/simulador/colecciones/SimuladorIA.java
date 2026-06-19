package com.ia.simulador.colecciones;

import com.ia.encapsulamiento.ArbolDecision;
import com.ia.encapsulamiento.ModeloIA;
import com.ia.encapsulamiento.ModeloRegresion;
import com.ia.encapsulamiento.RedNeuronal;
import com.ia.simulador.abstraction.Tokenizador;
import com.ia.simulador.abstraction.TokenizadorBasico;
import com.ia.simulador.abstraction.TokenizadorHuggingFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<ModeloIA> inventarioModelos = new ArrayList<>();

        inventarioModelos.add(new RedNeuronal("Perceptrón Multicapa", 0.70, 3));
        inventarioModelos.add(new ArbolDecision("Random Forest Node", 0.65, 8));
        inventarioModelos.add(new ModeloRegresion("ElasticNet Linear", 0.60, "Lasso"));
        inventarioModelos.add(new RedNeuronal("Deep CNN Vision", 0.55, 5));

        System.out.println("\nEjecutando Ciclo de Vida y Entrenamiento Dinámico");
        for (ModeloIA modelo : inventarioModelos) {
            System.out.println("[Estado Inicial]");
            modelo.mostrarMetricas();

            modelo.entrenar();

            System.out.println("[Estado Post-Entrenamiento]");
            modelo.mostrarMetricas();
            System.out.println();
        }

        Map<String, Tokenizador> catalogoTokenizadores = new HashMap<>();

        catalogoTokenizadores.put("BASICO", new TokenizadorBasico());
        catalogoTokenizadores.put("HUGGING_FACE", new TokenizadorHuggingFace());

        System.out.println("Pipeline de Procesamiento Indexado en Memoria");
        String textoPrueba = "Corelia optimiza la gestión del tiempo de los estudiantes.";

        String claveBuscada = "HUGGING_FACE";
        Tokenizador tokenizadorActivo = catalogoTokenizadores.get(claveBuscada);

        if (tokenizadorActivo != null) {
            System.out.println("Componente recuperado con éxito mediante la clave: " + claveBuscada);
            String[] tokens = tokenizadorActivo.dividirTexto(textoPrueba);
            imprimirTokens(tokens);
        } else {
            System.out.println("Error: El procesador con la clave solicitada no existe.");
        }

        System.out.println("\nReporte de Auditoría: Modelos de Alta Precisión");
        double umbralPrecision = 0.80;
        System.out.printf("Filtrando modelos con precisión estrictamente superior al %.0f%%%n", umbralPrecision * 100);

        filtrarModelosPorPrecision(inventarioModelos, umbralPrecision);
    }

    private static void filtrarModelosPorPrecision(List<ModeloIA> modelos, double umbral) {
        int contados = 0;
        for (ModeloIA modelo : modelos) {
            if (modelo.getPrecision() > umbral) {
                System.out.printf(" -> [APROBADO] %s (%.2f%%)%n", modelo.getNombre(), modelo.getPrecision() * 100);
                contados++;
            }
        }
        if (contados == 0) {
            System.out.println("Ningún modelo en el inventario actual supera el umbral requerido.");
        }
    }

    private static void imprimirTokens(String[] tokens) {
        System.out.print("Tokens generados: [ ");
        for (String t : tokens) {
            System.out.print("'" + t + "' ");
        }
        System.out.println("]");
    }
}