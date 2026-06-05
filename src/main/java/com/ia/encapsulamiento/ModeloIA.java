package com.ia.encapsulamiento;

import java.util.Random;

public class ModeloIA {

    // Atributos privados
    private String nombre;
    private double precision;
    private int epocasEntrenadas;
    private double tasaAprendizaje;

    private final Random random = new Random();

    // Constructor
    public ModeloIA(String nombre, double tasaAprendizaje) {
        this.nombre = nombre;

        if (tasaAprendizaje > 0.0 && tasaAprendizaje < 1.0) {
            this.tasaAprendizaje = tasaAprendizaje;
        } else {
            System.out.println("[" + nombre + "] Tasa de aprendizaje inválida en constructor. " +
                    "Asignando valor por defecto: 0.01");
            this.tasaAprendizaje = 0.01;
        }
        this.epocasEntrenadas = 0;
        this.precision = 50.0;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecision() {
        return precision;
    }

    public int getEpocasEntrenadas() {
        return epocasEntrenadas;
    }

    public double getTasaAprendizaje() {
        return tasaAprendizaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTasaAprendizaje(double tasaAprendizaje) {
        if (tasaAprendizaje > 0.0 && tasaAprendizaje < 1.0) {
            this.tasaAprendizaje = tasaAprendizaje;
            System.out.println("[" + nombre + "] Tasa de aprendizaje actualizada exitosamente a: " + tasaAprendizaje);
        } else {
            System.out.println("[" + nombre + "]: Intento de asignar tasa inválida (" + tasaAprendizaje + ")." +
                    " Se mantiene el valor anterior: " + this.tasaAprendizaje);
        }
    }

    public void entrenar() {
        this.epocasEntrenadas++;

        double factorMaximo = (100.0 - this.precision) * this.tasaAprendizaje;
        double incremento = (random.nextDouble() * factorMaximo) + 0.1;

        this.precision += incremento;

        if (this.precision > 100.0) {
            this.precision = 100.0;
        }
    }

    public void mostrarMetricas() {
        System.out.printf("Modelo IA: %-15s", this.nombre);
        System.out.printf("Precisión: %6.2f%%", this.precision);
        System.out.printf("Épocas: %d", this.epocasEntrenadas);
        System.out.printf("Tasa Learning: %.4f", this.tasaAprendizaje);
    }

}
