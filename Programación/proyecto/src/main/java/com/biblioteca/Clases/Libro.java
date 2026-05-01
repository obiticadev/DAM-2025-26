package com.biblioteca.Clases;

import java.time.LocalDate;

import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;

public abstract class Libro {
    protected int id;
    protected String titulo;
    protected String autor;
    protected Genero genero;
    protected String isbn;
    protected LocalDate anioPublicacion;
    protected int copiasTotales;
    protected int copiasDisponibles;
    protected Tipo tipo;

    public Libro(int id, String titulo, String autor, Genero genero, String isbn, LocalDate anioPublicacion,
            int copiasTotales, int copiasDisponibles, Tipo tipo) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.copiasTotales = copiasTotales;
        this.copiasDisponibles = copiasDisponibles;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getAnioPublicacion() {
        return anioPublicacion;
    }

    public int getCopiasTotales() {
        return copiasTotales;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public Tipo getTipo() {
        return tipo;
    }

}
