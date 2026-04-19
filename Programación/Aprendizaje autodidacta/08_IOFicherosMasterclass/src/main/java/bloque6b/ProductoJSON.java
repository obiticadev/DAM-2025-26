package bloque6b;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase modelo para los ejercicios de JSON/XML.
 * Representa un producto con nombre y precio.
 */
public class ProductoJSON {

    public String nombre;
    public double precio;

    public ProductoJSON() {} // Constructor vacio obligatorio para Jackson

    public ProductoJSON(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("ProductoJSON{nombre='%s', precio=%.2f}", nombre, precio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoJSON that = (ProductoJSON) o;
        return Double.compare(that.precio, precio) == 0 &&
               java.util.Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nombre, precio);
    }
}
