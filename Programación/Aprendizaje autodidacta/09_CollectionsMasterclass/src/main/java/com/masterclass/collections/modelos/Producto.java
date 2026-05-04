package com.masterclass.collections.modelos;

import com.masterclass.collections.modelos.interfaces.Clasificable;
import com.masterclass.collections.modelos.interfaces.Identificable;

import java.util.Objects;

/**
 * Entidad de dominio: Producto de un catálogo.
 * Implementa Identificable y Clasificable — usada en ejercicios de ArrayList,
 * HashMap y en los bloques de instanceof.
 */
public class Producto implements Identificable, Clasificable {

    private final String id;
    private String nombre;
    private double precio;
    private String categoria;
    private String tipo;
    private int stock;

    public Producto(String id, String nombre, double precio, String categoria, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.tipo = tipo;
        this.stock = 0;
    }

    public Producto(String id, String nombre, double precio, String categoria, String tipo, int stock) {
        this(id, nombre, precio, categoria, tipo);
        this.stock = stock;
    }

    @Override public String getId()        { return id; }
    @Override public String getNombre()    { return nombre; }
    @Override public String getCategoria() { return categoria; }
    @Override public String getTipo()      { return tipo; }

    public double getPrecio() { return precio; }
    public int getStock()     { return stock; }

    public void setPrecio(double precio)   { this.precio = precio; }
    public void setNombre(String nombre)   { this.nombre = nombre; }
    public void setStock(int stock)        { this.stock = stock; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto p)) return false;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Producto{id='%s', nombre='%s', precio=%.2f, categoria='%s'}"
                .formatted(id, nombre, precio, categoria);
    }
}
