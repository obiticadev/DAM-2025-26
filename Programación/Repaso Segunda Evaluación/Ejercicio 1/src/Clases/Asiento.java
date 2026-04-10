package Clases;

public class Asiento {
    private static int contadorReservas = 0;
    private char disponibilidad;
    private int idReserva;

    public Asiento() {
        this.disponibilidad = 'L';
    }

    public void reservarAsiento() {
        this.disponibilidad = 'O';
        this.idReserva = ++contadorReservas;
    }

    public void liberarAsiento() {
        this.disponibilidad = 'L';
        this.idReserva = (Integer) null;
    }

    public char getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(char disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public static int getContadorReservas() {
        return contadorReservas;
    }

    public int getIdReserva() {
        return idReserva;
    }

}
