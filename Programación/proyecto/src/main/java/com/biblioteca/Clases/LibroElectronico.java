package com.biblioteca.Clases;

import java.time.LocalDate;

import com.biblioteca.Enum.Formato;
import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;

public class LibroElectronico extends Libro {
    private int idElectronico;
    private Formato formato;
    private String urlDescarga;

    public LibroElectronico(int id, String titulo, String autor, Genero genero, String isbn, LocalDate anioPublicacion,
            int copiasTotales, int copiasDisponibles, Tipo tipo, int idElectronico, Formato formato,
            String urlDescarga) {
        super(id, titulo, autor, genero, isbn, anioPublicacion, copiasTotales, copiasDisponibles, tipo);

        this.idElectronico = idElectronico;
        this.formato = formato;
        this.urlDescarga = urlDescarga;
    }

    public int getIdElectronico() {
        return idElectronico;
    }

    public Formato getFormato() {
        return formato;
    }

    public String getUrlDescarga() {
        return urlDescarga;
    }

}
