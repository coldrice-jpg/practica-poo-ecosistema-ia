package com.ia.encapsulamiento;

public abstract class ModeloIA {
    private String nombre;
    private double tasaAprendizaje;
    private double precision;
    private int epocas;

    // Constructor
    protected ModeloIA(String nombre, double tasaAprendizaje) {
        this.nombre = nombre;
        this.tasaAprendizaje = tasaAprendizaje;
        this.precision = 0.0;
        this.epocas = 0;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public double getTasaAprendizaje() {
        return tasaAprendizaje;
    }

    public double getPrecision() {
        return precision;
    }

    protected void setPrecision(double precision) {
        this.precision = precision;
    }

    public int getEpocas() {
        return epocas;
    }

    protected void setEpocas(int epocas) {
        this.epocas = epocas;
    }

    public void mostrarMetricas() {
        System.out.println("--- Métricas del Modelo: " + nombre + " ---");
        System.out.println("Tasa de Aprendizaje: " + tasaAprendizaje);
        System.out.println("Precisión Actual: " + (precision * 100) + "%");
        System.out.println("Épocas de Entrenamiento: " + epocas);
    }
}