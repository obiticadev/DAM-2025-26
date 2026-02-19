package Clases;

import java.time.LocalTime;

public class Atleta {
    private String nombre;
    private double puntuacionSalto;
    private LocalTime horaComienzo;

    public Atleta(String nombre, double puntuacionSalto) {
        this.nombre = nombre;
        this.puntuacionSalto = puntuacionSalto;

    }

    public LocalTime Gundersen(double puntuacionMaxima, LocalTime horaPartida) {
        double diferencia = puntuacionMaxima - puntuacionSalto;
        Long retraso = Math.round(diferencia * 1.5);
        horaComienzo = horaPartida.plusSeconds(retraso);
        return horaComienzo;
    }

    @Override
    public String toString() {
        return "Atleta [nombre=" + nombre + ", puntuacionSalto=" + puntuacionSalto + ", horaComienzo=" + horaComienzo
                + "]";
    }

    public String getNombre() {
        return nombre;
    }

    public double getPuntuacionSalto() {
        return puntuacionSalto;
    }

    public LocalTime getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(LocalTime horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

}
