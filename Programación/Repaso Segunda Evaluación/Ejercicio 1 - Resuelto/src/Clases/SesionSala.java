package Clases;

import Excepciones.ErrorEnCantidadAsientos;

public class SesionSala {
    private Asiento[][] asientos;
    private double precioEntrada;
    private int numSesion;

    public SesionSala(int filas, int columnas, double precioEntrada, int numSesion) throws ErrorEnCantidadAsientos {
        if (filas <= 0) {
            throw new ErrorEnCantidadAsientos("El número de filas no puede ser negativo o cero: " + filas);
        }
        if (columnas <= 0) {
            throw new ErrorEnCantidadAsientos("El número de asientos no puede ser negativo o cero: " + columnas);
        }
        if (filas > 15) {
            throw new ErrorEnCantidadAsientos("La sala no puede tener " + filas + " filas");
        }
        if (columnas > 20) {
            throw new ErrorEnCantidadAsientos("La sala no puede tener " + columnas + " asientos");
        }

        this.asientos = new Asiento[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asientos[i][j] = new Asiento();
            }
        }
        this.precioEntrada = precioEntrada;
        this.numSesion = numSesion;
    }

    public String mostrarSala() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sesión ").append(numSesion).append(" (").append(asientos.length).append("x").append(asientos[0].length).append(")\n");
        sb.append("   ");
        for (int j = 0; j < asientos[0].length; j++) {
            sb.append(String.format("%3d", j + 1));
        }
        sb.append("\n");

        for (int i = 0; i < asientos.length; i++) {
            sb.append(String.format("%2d ", i + 1));
            for (int j = 0; j < asientos[i].length; j++) {
                sb.append("  ").append(asientos[i][j].getDisponibilidad());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean reservarAsiento(int fila, int columna) {
        if (fila < 0 || fila > asientos.length - 1 || columna < 0 || columna > asientos[0].length - 1) {
            return false;
        }
        if (asientos[fila][columna].getDisponibilidad() == 'L') {
            asientos[fila][columna].reservarAsiento();
            return true;
        }
        return false;
    }

    public boolean liberarAsiento(int idReserva) {
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                if (asientos[i][j].getIdReserva() == idReserva) {
                    asientos[i][j].liberarAsiento();
                    return true;
                }
            }
        }
        return false;
    }

    public double mostrarRecaudacion() {
        int ocupados = 0;
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                if (asientos[i][j].getDisponibilidad() == 'O') {
                    ocupados++;
                }
            }
        }
        return ocupados * precioEntrada;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public int getNumSesion() {
        return numSesion;
    }

}
