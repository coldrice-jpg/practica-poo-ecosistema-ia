package com.ia.simulador.polimorfismo;

import com.ia.encapsulamiento.ModeloIA;

public class RedNeuronal extends ModeloIA implements Entrenable {
    private int capasOcultas;

    public RedNeuronal(String nombre, double tasaAprendizaje, int capasOcultas) {
        super(nombre, tasaAprendizaje);
        this.capasOcultas = capasOcultas;
    }

    @Override
    public void ajustarPesos(double tasa) {

        double incremento = tasa * (1.5 / (capasOcultas + 1));
        double nuevaPrecision = Math.min(getPrecision() + incremento + 0.65, 0.99);
        setPrecision(nuevaPrecision);
        setEpocas(getEpocas() + 50);
        System.out.println("[" + getNombre() + "] Backpropagation ejecutado en " + capasOcultas + " capas. Precisión sube a: " + String.format("%.2f", nuevaPrecision * 100) + "%");
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Capas Ocultas: " + capasOcultas);
    }
}