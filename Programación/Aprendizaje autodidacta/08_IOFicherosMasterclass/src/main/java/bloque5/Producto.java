package bloque5;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase modelo para los ejercicios de serializacion.
 * Representa un producto de una tienda de alimentacion.
 */
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private double precio;
    private int stock;
    private transient String codigoInterno; // no se serializa

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.codigoInterno = null;
    }

    public Producto(String nombre, double precio, int stock, String codigoInterno) {
        this(nombre, precio, stock);
        this.codigoInterno = codigoInterno;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCodigoInterno() { return codigoInterno; }
    public void setCodigoInterno(String codigoInterno) { this.codigoInterno = codigoInterno; }

    @Override
    public String toString() {
        return String.format("Producto{nombre='%s', precio=%.2f, stock=%d}", nombre, precio, stock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto p = (Producto) o;
        return Double.compare(p.precio, precio) == 0
                && stock == p.stock
                && Objects.equals(nombre, p.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, precio, stock);
    }
}
