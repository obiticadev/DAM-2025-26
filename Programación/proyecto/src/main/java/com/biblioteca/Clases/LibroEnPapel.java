package com.biblioteca.Clases;

import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;

public class LibroEnPapel extends Libro {

    private static final long serialVersionUID = 1L;

    private int idPapel;
    private String ubicacion;

    public LibroEnPapel(int id, String titulo, String autor, Genero genero, String isbn, int anioPublicacion,
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

    @Override
    public String toString() {
        return super.toString() + " LibroEnPapel [idPapel=" + idPapel + ", ubicacion=" + ubicacion + "]]";
    }

}
