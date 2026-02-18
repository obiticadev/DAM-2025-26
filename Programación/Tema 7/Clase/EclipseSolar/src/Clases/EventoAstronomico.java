package Clases;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import Interfaces.JsonExportable;

public abstract class EventoAstronomico implements Comparable<EventoAstronomico>, JsonExportable {
    private LocalDate fecha;
    private LocalTime horaInicio, horaFin;
    private String descripcion;

    public EventoAstronomico(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String descripcion) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
    }

    public Duration getDuracion() {
        return Duration.between(this.horaInicio, this.horaFin);
    }

    // Orden natural: por fecha, luego por horaInicio
    @Override
    public int compareTo(EventoAstronomico otro) {
        int compFecha = this.fecha.compareTo(otro.fecha);
        if (compFecha != 0)
            return compFecha;
        return this.horaInicio.compareTo(otro.horaInicio);
    }

    // Getters
    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }
}