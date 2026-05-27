package com.bootcamp.finale.modelo;

import java.util.Objects;

public class Usuario {

    private final long id;
    private final String nombre;
    private String email;
    private final int edad;

    public Usuario(long id, String nombre, String email, int edad) {
        if (id <= 0) {
            throw new IllegalArgumentException("id debe ser positivo");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("nombre obligatorio");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email inválido");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("edad no negativa");
        }
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }

    public long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public int getEdad() { return edad; }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email inválido");
        }
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario u = (Usuario) o;
        return id == u.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre='" + nombre
             + "', email='" + email + "', edad=" + edad + "}";
    }
}
