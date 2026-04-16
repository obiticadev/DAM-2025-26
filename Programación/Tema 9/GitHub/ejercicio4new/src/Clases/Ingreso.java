package Clases;

import Interfaces.Transaccion;

public class Ingreso implements Transaccion {
    private double importeIngreso;
    private String descripcion;

    public Ingreso(double importeIngreso, String descripcion) {
        this.importeIngreso = importeIngreso;
        this.descripcion = descripcion;
    }

    @Override
    public double getImport() {
        return this.importeIngreso;
    }
}
