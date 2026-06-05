package com.ia.encapsulamiento;

public class SimuladorIA {
    public static void main(String[] args) {
        System.out.println("INICIANDO SIMULACIÓN DE MODELOS DE IA\n");

        ModeloIA redNeuronal = new ModeloIA("RedNeuronal", 0.1);
        ModeloIA arbolDecision = new ModeloIA("ArbolDecision", 0.05);

        System.out.println("ESTADO INICIAL");
        redNeuronal.mostrarMetricas();
        arbolDecision.mostrarMetricas();

        System.out.println("\nPRUEBA DE ENCAPSULACIÓN (Valores Inválidos)");

        redNeuronal.setTasaAprendizaje(-0.5);

        arbolDecision.setTasaAprendizaje(1.2);

        System.out.println("\nVerificando que los objetos mantengan su integridad:");
        System.out.println("Tasa " + redNeuronal.getNombre() + ": " + redNeuronal.getTasaAprendizaje());
        System.out.println("Tasa " + arbolDecision.getNombre() + ": " + arbolDecision.getTasaAprendizaje());

        System.out.println("\nINICIANDO CICLO DE ENTRENAMIENTO");

        System.out.println("\n>> Entrenando: " + redNeuronal.getNombre());
        for (int i = 0; i < 3; i++) {
            redNeuronal.entrenar();
            redNeuronal.mostrarMetricas();
        }

        System.out.println("\n>> Entrenando: " + arbolDecision.getNombre());
        for (int i = 0; i < 3; i++) {
            arbolDecision.entrenar();
            arbolDecision.mostrarMetricas();
        }

    }
}