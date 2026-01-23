package Clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tarea {
    protected String titulo;
    protected Categoria categoria;
    protected Estado estado;
    protected LocalDate fecha;
    protected LocalTime hora;
    protected LocalDateTime fechaCompleta;

    public Tarea(String titulo, Categoria categoria, Estado estado, LocalDate fecha, LocalTime hora) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaCompleta = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), hora.getHour(),
                hora.getMinute(), hora.getSecond());
    }

    public LocalDateTime getFechaCompleta() {
        return fechaCompleta;
    }

    public boolean validarCategoria(String nombre) {

        Categoria categoriaSeleccionada = null;

        for (Categoria busqueda : Categoria.values()) {
            if (busqueda.name().equals(nombre)) {
                categoriaSeleccionada = busqueda;
                break;
            }
        }

        if (categoriaSeleccionada != null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean tareaVencida() {
        LocalDateTime fechaCompleta = LocalDateTime.of(this.fecha.getYear(), this.fecha.getMonth(),
                this.fecha.getDayOfMonth(), this.hora.getHour(), this.hora.getMinute(), this.hora.getSecond());
        if (LocalDateTime.now().isAfter(fechaCompleta)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tarea other = (Tarea) obj;
        if (categoria != other.categoria)
            return false;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "titulo: " + titulo + "\ncategoria: " + categoria + "\nestado: " + estado + "\nfecha: " + fecha
                + "\nhora: " + hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setFechaCompleta(LocalDateTime fechaCompleta) {
        this.fechaCompleta = fechaCompleta;
    }

}
