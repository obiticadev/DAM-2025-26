package com.biblioteca.Clases;

import java.io.Serializable;
import java.time.LocalDate;

public class Usuario implements Serializable {

    // TODO [CÓDIGO FALTANTE] Añadir serialVersionUID.
    //  → private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDate fechaRegistro;

    public Usuario(int id, String nombre, String apellido, String email, String telefono, LocalDate fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    // TODO [CÓDIGO FALTANTE] Implementar toString().
    //  → Mostrar: id, nombre completo (nombre + apellido), email, teléfono, fechaRegistro.
    //  → Se usa en listarUsuarios() de App.java para imprimir datos de cada usuario.

    // TODO [RECOMENDACIÓN] Implementar equals() y hashCode() basándose en email (UNIQUE en BD).
    //  → Permitirá usar correctamente Usuario en colecciones tipo Set o como clave de Map.

}
