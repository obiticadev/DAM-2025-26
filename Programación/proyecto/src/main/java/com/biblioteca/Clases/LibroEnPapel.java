package com.biblioteca.Clases;

import java.time.LocalDate;

import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;

public class LibroEnPapel extends Libro {

    // TODO [CÓDIGO FALTANTE] Añadir serialVersionUID.
    //  → private static final long serialVersionUID = 1L;

    private int idPapel;
    private String ubicacion;

    public LibroEnPapel(int id, String titulo, String autor, Genero genero, String isbn, LocalDate anioPublicacion,
            int copiasTotales, int copiasDisponibles, Tipo tipo, int idPapel, String ubicacion) {
        super(id, titulo, autor, genero, isbn, anioPublicacion, copiasTotales, copiasDisponibles, tipo);
        this.idPapel = idPapel;
        this.ubicacion = ubicacion;
    }

    public int getIdPapel() {
        return idPapel;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    // TODO [CÓDIGO FALTANTE] Implementar toString().
    //  → Llamar a super.toString() (una vez implementado en Libro) y concatenar los campos propios: idPapel, ubicacion.
    //  → Ejemplo: @Override public String toString() { return super.toString() + ", ubicacion='" + ubicacion + "'"; }

}
