package Clases;

import java.util.Arrays;

import Excepciones.ErrorEnCantidadAsientos;

public class SesionSala {
    private Asiento[][] asientos;
    private double precioEntrada;
    private int numSesion;
    private char disponibilidad;

    public SesionSala(int filas, int columnas, double precioEntrada, int numSesion) {
        if (filas < 0) {
            throw new IllegalArgumentException("No se puede insertar " + filas + " filas");
        } else if (columnas < 0) {
            throw new IllegalArgumentException("No se puede insertar " + columnas + " columnas");
        } else if (filas > 15) {
            throw new ErrorEnCantidadAsientos("La sala no puede tener " + filas + " filas");
        } else if (columnas > 20) {
            throw new ErrorEnCantidadAsientos("La sala no puede tener " + columnas + " asientos");
        }
        asientos = new Asiento[filas][columnas];
        for (Asiento[] a : asientos) {
            Arrays.fill(a, new Asiento());
        }
        this.precioEntrada = precioEntrada;
        this.numSesion = numSesion;
    }

    public String mostrarSala() {
        StringBuilder sb = new StringBuilder();
        for (Asiento[] filaAsientos : asientos) {
            for (Asiento asiento : filaAsientos) {
                sb.append(asiento.getDisponibilidad()).append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    public boolean reservarAsiento(int fila, int columna) {
        if (fila < 0 || fila > asientos.length - 1 || columna < 0 || columna < asientos[0].length - 1) {
            return false;
        }
        if (asientos[fila][columna].getDisponibilidad() == 'L') {
            asientos[fila][columna].reservarAsiento();
            return true;
        } else {
            return false;
        }
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

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public int getNumSesion() {
        return numSesion;
    }

    public char getDisponibilidad() {
        return disponibilidad;
    }

}
