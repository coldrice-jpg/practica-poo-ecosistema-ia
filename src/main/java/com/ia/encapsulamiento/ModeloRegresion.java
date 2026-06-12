package com.ia.encapsulamiento;

public class ModeloRegresion extends ModeloIA {

    private double coeficienteRegularizacion;

    // Constructor
    public ModeloRegresion(String nombre, double tasaAprendizaje, double coeficienteRegularizacion) {
        super(nombre, tasaAprendizaje);
        this.coeficienteRegularizacion = coeficienteRegularizacion;
    }


    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Coeficiente de Regularización: " + coeficienteRegularizacion);
    }

    public double getCoeficienteRegularizacion() {
        return coeficienteRegularizacion;
    }
}