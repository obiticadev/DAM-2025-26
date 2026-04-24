package Clases;

import java.time.LocalDate;

public abstract class Prestamo implements Comparable<Prestamo> {
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String idSocio;

    public Prestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion, String idSocio) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.idSocio = idSocio;
    }

    public abstract String mostrarDetalles();

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    @Override
    public int compareTo(Prestamo o) {
        return o.fechaPrestamo.compareTo(this.fechaPrestamo);
    }

    public String getIdSocio() {
        return idSocio;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

}
