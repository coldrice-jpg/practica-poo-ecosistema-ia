package com.ia.encapsulamiento;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ModeloIA> ecosistema = new ArrayList<>();

        ecosistema.add(new RedNeuronal("Perceptrón Multicapa", 0.01, 5));
        ecosistema.add(new ArbolDecision("Clasificador de Iris", 0.05, 15));
        ecosistema.add(new ModeloRegresion("Regresión Ridge", 0.001, 0.01));


        for (ModeloIA modelo : ecosistema) {
            modelo.mostrarMetricas();
            System.out.println();
        }
    }
}