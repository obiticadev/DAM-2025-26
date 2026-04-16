package Clases;

public class Asiento {
    private static int contadorReservas = 0;
    private char disponibilidad;
    private int idReserva;

    public Asiento() {
        this.disponibilidad = 'L';
        this.idReserva = 0;
    }

    public void reservarAsiento() {
        this.disponibilidad = 'O';
        this.idReserva = ++contadorReservas;
    }

    public void liberarAsiento() {
        this.disponibilidad = 'L';
        this.idReserva = 0;
    }

    public static int getContadorReservas() {
        return contadorReservas;
    }

    public char getDisponibilidad() {
        return disponibilidad;
    }

    public int getIdReserva() {
        return idReserva;
    }

}
