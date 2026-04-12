package Clases;

import Excepciones.ErrorEnCantidadAsientos;

public class SesionSala {
    private Asiento[][] asientos;
    private double precioEntrada;
    private int numSesion;

    public SesionSala(int filas, int columnas, double precioEntrada, int numSesion) throws ErrorEnCantidadAsientos {
        if (filas <= 0) {
            throw new ErrorEnCantidadAsientos("Error: El número de filas no puede ser negativo o cero", filas);
        }
        if (columnas <= 0) {
            throw new ErrorEnCantidadAsientos("Error: El número de columnas no puede ser negativo o cero", columnas);
        }
        if (filas > 15) {
            throw new ErrorEnCantidadAsientos("La sala no puede tener " + filas + " filas", filas);
        }
        if (columnas > 20) {
            throw new ErrorEnCantidadAsientos("La sala no puede tener " + columnas + " columnas", columnas);
        }
        this.asientos = new Asiento[filas][columnas];
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[0].length; j++) {
                asientos[i][j] = new Asiento();
            }
        }
        this.precioEntrada = precioEntrada;
        this.numSesion = numSesion;
    }

    public String mostrarAsiento() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sesión: ").append(numSesion).append("\n")
                .append("Precio de entrada: ").append(this.precioEntrada).append("\n")
                .append("Asientos: \n");
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[0].length; j++) {
                sb.append(asientos[i][j].getDisponibilidad() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean reservarAsiento(int fila, int columnas) {
        if (fila < 0 || fila > asientos.length - 1 || columnas < 0 || columnas > asientos[0].length - 1) {
            return false;
        }
        if (asientos[fila][columnas].getDisponibilidad() == 'L') {
            asientos[fila][columnas].reservarAsiento();
            return true;
        }
        return false;
    }

    public boolean liberarAsiento(int idReserva) {
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[0].length; j++) {
                if (asientos[i][j].getIdReserva() == idReserva) {
                    asientos[i][j].liberarAsiento();
                    return true;
                }
            }
        }
        return false;
    }

    public double mostrarRecaudacion() {
        int contadorReservas = 0;
        double recaudacion;
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[0].length; j++) {
                if (asientos[i][j].getDisponibilidad() == 'O') {
                    contadorReservas++;
                }
            }
        }
        recaudacion = precioEntrada * contadorReservas;
        return recaudacion;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public int getNumSesion() {
        return numSesion;
    }

    public Asiento[][] getAsientos() {
        return asientos;
    }
}
