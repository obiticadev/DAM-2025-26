package com.biblioteca.Clases;

import java.io.Serializable;
import java.time.LocalDate;

import com.biblioteca.Enum.Estado;

public class Prestamo implements Serializable {

    // TODO [CÓDIGO FALTANTE] Añadir serialVersionUID.
    //  → private static final long serialVersionUID = 1L;

    private int id;
    private int idUsuario;
    private int idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;
    private Estado estado;

    public Prestamo(int id, int idUsuario, int idLibro, LocalDate fechaPrestamo, LocalDate fechaDevolucionPrevista,
            LocalDate fechaDevolucionReal, Estado estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public Estado getEstado() {
        return estado;
    }

    // TODO [CÓDIGO FALTANTE] Añadir setter para estado.
    //  → Se necesita para cambiar el estado del préstamo (ACTIVO → DEVUELTO / RETRASADO).
    //  → public void setEstado(Estado estado) { this.estado = estado; }

    // TODO [CÓDIGO FALTANTE] Añadir setter para fechaDevolucionReal.
    //  → Se necesita cuando el usuario devuelve el libro: setFechaDevolucionReal(LocalDate.now()).
    //  → public void setFechaDevolucionReal(LocalDate fecha) { this.fechaDevolucionReal = fecha; }

    // TODO [CÓDIGO FALTANTE] Implementar toString().
    //  → Mostrar: id, idUsuario, idLibro, fechaPrestamo, fechaDevolucionPrevista, fechaDevolucionReal, estado.
    //  → Ojo: fechaDevolucionReal puede ser null, manejar con operador ternario.

}
