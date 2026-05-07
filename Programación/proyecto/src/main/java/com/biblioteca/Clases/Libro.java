package com.biblioteca.Clases;

import java.io.Serializable;

import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;

public abstract class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    protected int id;
    protected String titulo;
    protected String autor;
    protected Genero genero;
    protected String isbn;
    protected int anioPublicacion;
    protected int copiasTotales;
    protected int copiasDisponibles;
    protected Tipo tipo;

    public Libro(int id, String titulo, String autor, Genero genero, String isbn, int anioPublicacion,
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

    public int getAnioPublicacion() {
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

    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero + ", isbn=" + isbn
                + ", anioPublicacion=" + anioPublicacion + ", copiasTotales=" + copiasTotales + ", copiasDisponibles="
                + copiasDisponibles + ",";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
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
        Libro other = (Libro) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        return true;
    }

}
