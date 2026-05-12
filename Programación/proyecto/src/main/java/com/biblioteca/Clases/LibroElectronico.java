package com.biblioteca.Clases;

import com.biblioteca.Enum.Formato;
import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;

public class LibroElectronico extends Libro {

    private static final long serialVersionUID = 1L;

    private int idElectronico;
    private Formato formato;
    private String urlDescarga;

    // CONSTRUCTOR ----------------------------

    public LibroElectronico(int id, String titulo, String autor, Genero genero, String isbn, int anioPublicacion,
            int copiasTotales, int copiasDisponibles, Tipo tipo, int idElectronico, Formato formato,
            String urlDescarga) {
        super(id, titulo, autor, genero, isbn, anioPublicacion, copiasTotales, copiasDisponibles, tipo);

        this.idElectronico = idElectronico;
        this.formato = formato;
        this.urlDescarga = urlDescarga;
    }

    // GETTERS ----------------------------

    public int getIdElectronico() {
        return idElectronico;
    }

    public Formato getFormato() {
        return formato;
    }

    public String getUrlDescarga() {
        return urlDescarga;
    }

    // MÉTODOS DE OBJETO ----------------------------

    @Override
    public String toString() {
        return super.toString() + " LibroElectronico [idElectronico=" + idElectronico +
                ", formato=" + formato + ", urlDescarga=" + urlDescarga + "]]";
    }

}
