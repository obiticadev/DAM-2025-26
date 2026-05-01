package com.biblioteca.Clases;

import java.time.LocalDate;

import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;

public class LibroEnPapel extends Libro {
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

}
