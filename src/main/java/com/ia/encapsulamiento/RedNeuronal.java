package com.ia.encapsulamiento;

public class RedNeuronal extends ModeloIA {

    private int capasOcultas;

    // Constructor
    public RedNeuronal(String nombre, double tasaAprendizaje, int capasOcultas) {
        super(nombre, tasaAprendizaje);
        this.capasOcultas = capasOcultas;
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Capas Ocultas: " + capasOcultas);
    }

    // Getter publico
    public int getCapasOcultas() {
        return capasOcultas;
    }
}