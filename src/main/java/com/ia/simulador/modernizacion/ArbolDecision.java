package com.ia.simulador.polimorfismo;

import com.ia.encapsulamiento.ModeloIA;

public class ArbolDecision extends ModeloIA implements Entrenable {
    private int profundidadMaxima;

    public ArbolDecision(String nombre, double tasaAprendizaje, int profundidadMaxima) {
        super(nombre, tasaAprendizaje);
        this.profundidadMaxima = profundidadMaxima;
    }

    @Override
    public void ajustarPesos(double tasa) {
        double nuevaPrecision = Math.min(getPrecision() + 0.70 + (profundidadMaxima * 0.005), 0.95);
        setPrecision(nuevaPrecision);
        setPrecision(getEpocas() + 1);
        System.out.println("[" + getNombre() + "] División de nodos calculada (Profundidad Máx: " + profundidadMaxima + "). Precisión: " + String.format("%.2f", nuevaPrecision * 100) + "%");
    }

    private double getEpocas() {
        return 0;
    }

    @Override
    public void entrenar() {

    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Profundidad Máxima: " + profundidadMaxima);
    }
}