package modelos;

import java.util.Objects;

/**
 * Clase Producto para demostrar el comportamiento en colecciones como HashSet y
 * HashMap.
 * Es crucial sobreescribir equals y hashCode cuando usamos objetos en
 * colecciones
 * para garantizar que la colección pueda identificar duplicados correctamente.
 */
public class Producto {
    private int id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }

    // ==========================================
    // MÉTODOS CLAVE PARA HASHSET / HASHMAP
    // ==========================================

    // Si no sobreescribimos equals(), Java usará la implementación por defecto de
    // Object,
    // que simplemente comprueba si las referencias de memoria son iguales (a == b).
    // Nosotros queremos que dos productos sean "iguales" si tienen el mismo ID.
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // Si es el mismo objeto en memoria, es true
        if (o == null || getClass() != o.getClass())
            return false; // Si son de distinta clase, es false
        Producto producto = (Producto) o;
        return id == producto.id; // Son iguales si el ID es el mismo
    }

    // Si sobreescribes equals(), DEBES sobreescribir hashCode().
    // Las colecciones Hash primero agrupan objetos que tienen el mismo HashCode,
    // y luego comprueban con equals() dentro de ese grupo.
    // Si dos objetos son iguales según equals(), DEBEN tener el mismo hashCode().
    @Override
    public int hashCode() {
        return Objects.hash(id); // Generamos el hash basado únicamente en el ID
    }
}
