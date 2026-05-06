package com.biblioteca.Clases;

import java.io.Serializable;
import java.time.LocalDate;

import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;

public abstract class Libro implements Serializable {

    // TODO [CÓDIGO FALTANTE] Añadir serialVersionUID para garantizar compatibilidad en serialización.
    //  → private static final long serialVersionUID = 1L;
    //  Sin este campo, cualquier cambio en la clase romperá la deserialización de objetos guardados.

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

    // TODO [CÓDIGO FALTANTE] Añadir setter para copiasDisponibles.
    //  → Se necesita para actualizar las copias cuando se crea/devuelve un préstamo.
    //  → public void setCopiasDisponibles(int copiasDisponibles) { this.copiasDisponibles = copiasDisponibles; }

    // TODO [CÓDIGO FALTANTE] Implementar toString() en esta clase abstracta.
    //  → Debe mostrar los campos comunes: id, titulo, autor, genero, isbn, tipo.
    //  → Las subclases (LibroEnPapel, LibroElectronico) deben llamar a super.toString() y añadir sus campos propios.
    //  → Ejemplo: @Override public String toString() { return "Libro{id=" + id + ", titulo='" + titulo + "', ...}"; }

    // TODO [RECOMENDACIÓN] Implementar equals() y hashCode() basándose en el campo isbn (que es UNIQUE en BD).
    //  → Esto permite comparar libros correctamente en colecciones (ej: HashSet, HashMap).
    //  → equals: comparar isbn. hashCode: Objects.hash(isbn).

}
