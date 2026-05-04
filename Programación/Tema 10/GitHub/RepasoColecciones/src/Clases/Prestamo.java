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
        if (o == null || o.fechaPrestamo == null || this.fechaPrestamo == null) {
            return 0;
        }
        return o.fechaPrestamo.compareTo(this.fechaPrestamo);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fechaPrestamo == null) ? 0 : fechaPrestamo.hashCode());
        result = prime * result + ((fechaDevolucion == null) ? 0 : fechaDevolucion.hashCode());
        result = prime * result + ((idSocio == null) ? 0 : idSocio.hashCode());
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
        Prestamo other = (Prestamo) obj;
        if (fechaPrestamo == null) {
            if (other.fechaPrestamo != null)
                return false;
        } else if (!fechaPrestamo.equals(other.fechaPrestamo))
            return false;
        if (fechaDevolucion == null) {
            if (other.fechaDevolucion != null)
                return false;
        } else if (!fechaDevolucion.equals(other.fechaDevolucion))
            return false;
        if (idSocio == null) {
            if (other.idSocio != null)
                return false;
        } else if (!idSocio.equals(other.idSocio))
            return false;
        return true;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

}
