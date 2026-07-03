package com.ia.encapsulamiento;


public class ArbolDecision extends ModeloIA {
    private int profundidadMaxima;

    // Constructor
    public ArbolDecision(String nombre, double tasaAprendizaje, int profundidadMaxima) {
        super(nombre, tasaAprendizaje);
        this.profundidadMaxima = profundidadMaxima;
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Profundidad Máxima: " + profundidadMaxima);
    }

    public int getProfundidadMaxima() {
        return profundidadMaxima;
    }
}
