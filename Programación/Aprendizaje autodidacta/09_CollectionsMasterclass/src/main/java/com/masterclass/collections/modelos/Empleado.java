package com.masterclass.collections.modelos;

import com.masterclass.collections.modelos.interfaces.Auditable;
import com.masterclass.collections.modelos.interfaces.Identificable;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad de dominio: Empleado de una empresa.
 * Implementa Identificable y Auditable — usada principalmente en los
 * bloques de HashMap e instanceof.
 */
public class Empleado implements Identificable, Auditable {

    private final String id;
    private String nombre;
    private String departamento;
    private double salario;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private final String creadoPor;

    public Empleado(String id, String nombre, String departamento, double salario, String creadoPor) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.creadoPor = creadoPor;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = this.fechaCreacion;
    }

    @Override public String getId()                     { return id; }
    @Override public String getNombre()                 { return nombre; }
    @Override public LocalDateTime getFechaCreacion()   { return fechaCreacion; }
    @Override public LocalDateTime getUltimaModificacion() { return ultimaModificacion; }
    @Override public String getCreadoPor()              { return creadoPor; }

    public String getDepartamento() { return departamento; }
    public double getSalario()      { return salario; }

    public void setSalario(double salario) {
        this.salario = salario;
        this.ultimaModificacion = LocalDateTime.now();
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
        this.ultimaModificacion = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado e)) return false;
        return Objects.equals(id, e.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Empleado{id='%s', nombre='%s', departamento='%s', salario=%.2f}"
                .formatted(id, nombre, departamento, salario);
    }
}
