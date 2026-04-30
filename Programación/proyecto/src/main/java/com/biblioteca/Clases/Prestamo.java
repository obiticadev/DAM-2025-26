package com.biblioteca.Clases;

import java.time.LocalDate;

import com.biblioteca.Enum.Estado;

public class Prestamo {
    private int id;
    private int idUsuario;
    private int idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;
    private Estado estado;
}
