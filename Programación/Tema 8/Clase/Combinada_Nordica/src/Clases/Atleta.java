package Clases;

import java.time.LocalTime;

public class Atleta implements Comparable<Atleta> {
    private String nombre;
    private Double puntuacionSalto;
    private LocalTime horaComienzo;

    public Atleta(String nombre, Double puntuacionSalto) {
        this.nombre = nombre;
        this.puntuacionSalto = puntuacionSalto;

    }

    public LocalTime Gundersen(Double puntuacionMaxima, LocalTime horaPartida) {
        Double diferencia = puntuacionMaxima - puntuacionSalto;
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

    public Double getPuntuacionSalto() {
        return puntuacionSalto;
    }

    public LocalTime getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(LocalTime horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    @Override
    public int compareTo(Atleta o) {

        return o.puntuacionSalto.compareTo(this.puntuacionSalto);

    }

}
