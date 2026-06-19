package com.masterclass.api.b31_oodb;

/**
 * Objeto de dominio simple (POJO) que se persistirá "a mano" en una fila de la tabla EMP.
 *
 * <p>Encarna el <b>desfase objeto-relacional</b> del bloque: en memoria es un objeto con
 * identidad y campos; en la BD es una fila. Mapear de uno a otro es lo que automatizan JPA
 * (b12) y, en el mundo OO puro, una base de datos de objetos.
 */
public class Empleado {

    private int id;
    private final String nombre;
    private final int salario;

    public Empleado(int id, String nombre, int salario) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
    }

    public Empleado(String nombre, int salario) {
        this(0, nombre, salario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Empleado{id=" + id + ", nombre='" + nombre + "', salario=" + salario + '}';
    }
}
