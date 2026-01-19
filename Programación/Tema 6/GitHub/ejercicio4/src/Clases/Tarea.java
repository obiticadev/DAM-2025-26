package Clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tarea {
    private String titulo;
    private Categoria categoria;
    private Estado estado;
    private LocalDate fecha;
    private LocalTime hora;

    public Tarea(String titulo, Categoria categoria, Estado estado) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.estado = estado;
        this.fecha = fecha.now();
        this.hora = hora.now();
    }

    public boolean validarCategoria(String nombre) {

        Categoria categoriaSeleccionada = null;

        for (Categoria busqueda : this.categoria.values()) {
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

    @Override
    public String toString() {
        return "Tarea [titulo=" + titulo + ", categoria=" + categoria + ", estado=" + estado + ", fecha=" + fecha
                + ", hora=" + hora + "]";
    }

}
