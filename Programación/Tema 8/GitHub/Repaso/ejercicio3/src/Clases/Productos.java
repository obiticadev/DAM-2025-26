package Clases;

import Enum.Categoria;

public class Productos {

    private String codigoProducto;
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Productos(String codigoProducto, String nombre, double precio, Categoria categoria) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoProducto == null) ? 0 : codigoProducto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Productos other = (Productos) obj;
        if (codigoProducto == null) {
            if (other.codigoProducto != null)
                return false;
        } else if (!codigoProducto.equals(other.codigoProducto))
            return false;
        return true;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

}
