package Clases;

import java.time.LocalDate;

import Enum.Estado;

public class Reservas {
    private static int contadorReservas = 0;

    private int numReserva;

    private String nombreReserva;
    private LocalDate fechaReserva;
    private Libros codigoLibro;
    private Estado estado;

    public Reservas(String nombreReserva, LocalDate fechaReserva, Libros codigoLibro) {
        this.nombreReserva = nombreReserva;
        this.fechaReserva = fechaReserva;
        this.codigoLibro = codigoLibro;
        Reservas.contadorReservas++;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numReserva;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reservas other = (Reservas) obj;
        if (numReserva != other.numReserva)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reservas [numReserva=" + numReserva + ", nombreReserva=" + nombreReserva + ", fechaReserva="
                + fechaReserva + ", codigoLibro=" + codigoLibro + ", estado=" + estado + "]";
    }

    public int getNumReserva() {
        return numReserva;
    }

    public static int getContadorReservas() {
        return contadorReservas;
    }

    public Libros getCodigoLibro() {
        return codigoLibro;
    }

}
