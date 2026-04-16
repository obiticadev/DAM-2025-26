package modelos;

import java.util.Objects;

/**
 * CLASE SECUNDARIA: PRODUCTO
 * 
 * POJO para practicar Streams con catálogos de productos, precios y categorías.
 * Útil para ejercicios de reducción, estadísticas y agrupación.
 */
public class Producto {

    private String nombre;
    private String categoria; // Ej: "Electrónica", "Ropa", "Alimentación", "Software"
    private double precio;
    private int stock;
    private boolean disponible;

    public Producto(String nombre, String categoria, double precio, int stock, boolean disponible) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.disponible = disponible;
    }

    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public boolean isDisponible() { return disponible; }

    public void setPrecio(double precio) { this.precio = precio; }
    public void reducirStock(int cantidad) { this.stock -= cantidad; }

    public boolean esOferta() { return this.precio < 20.0 && this.disponible; }
    public boolean esPremium() { return this.precio > 100.0; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(nombre, producto.nombre) &&
               Objects.equals(categoria, producto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, categoria);
    }

    @Override
    public String toString() {
        return nombre + " [" + categoria + " | " + precio + "€ | Stock: " + stock +
               " | " + (disponible ? "Disponible" : "Agotado") + "]";
    }
}
