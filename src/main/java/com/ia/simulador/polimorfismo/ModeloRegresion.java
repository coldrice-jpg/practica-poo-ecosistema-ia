package com.ia.simulador.polimorfismo;

import com.ia.encapsulamiento.ModeloIA;

public class ModeloRegresion extends ModeloIA implements Entrenable {
    private double coeficienteRegularizacion;

    public ModeloRegresion(String nombre, double tasaAprendizaje, double coeficienteRegularizacion) {
        super(nombre, tasaAprendizaje);
        this.coeficienteRegularizacion = coeficienteRegularizacion;
    }

    @Override
    public void ajustarPesos(double tasa) {

        double penalizacion = coeficienteRegularizacion * 0.1;
        double nuevaPrecision = Math.min(getPrecision() + 0.60 + (tasa - penalizacion), 0.88);
        setPrecision(nuevaPrecision);
        setEpocas(getEpocas() + 10);
        System.out.println("[" + getNombre() + "] Gradiente descendente aplicado con penalización " + coeficienteRegularizacion + ". Precisión: " + String.format("%.2f", nuevaPrecision * 100) + "%");
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Coeficiente de Regularización: " + coeficienteRegularizacion);
    }
}