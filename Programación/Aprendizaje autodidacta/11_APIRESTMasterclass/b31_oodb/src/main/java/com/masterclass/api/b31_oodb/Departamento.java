package com.masterclass.api.b31_oodb;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de dominio <b>estructurado</b>: un departamento con un grafo de empleados.
 *
 * <p>Persistirlo (Ej252) obliga a "aplanar" el grafo a dos tablas (DEPT y EMP con clave
 * foránea) y, al cargarlo, a "rehidratarlo" reconstruyendo la lista. Eso es exactamente lo
 * que una BD objeto-relacional/OO promete hacer por ti, y lo que JPA hace en b12.
 */
public class Departamento {

    private int id;
    private final String nombre;
    private final List<Empleado> empleados;

    public Departamento(int id, String nombre, List<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.empleados = empleados != null ? new ArrayList<>(empleados) : new ArrayList<>();
    }

    public Departamento(String nombre) {
        this(0, nombre, new ArrayList<>());
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

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public Departamento addEmpleado(Empleado e) {
        empleados.add(e);
        return this;
    }

    @Override
    public String toString() {
        return "Departamento{id=" + id + ", nombre='" + nombre + "', empleados=" + empleados + '}';
    }
}
