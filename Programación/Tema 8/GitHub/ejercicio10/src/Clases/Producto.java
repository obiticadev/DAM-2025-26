package Clases;

import Excepciones.PrecioBajoExcepcion;

public class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) throws Exception {
        if (nombre.isBlank()) {
            throw new Exception("Nombre vacío");
        }
        if (precio <= 0) {
            throw new Exception("Precio inferior o igual a cero");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(Producto p) {
        this.nombre = p.nombre;
        this.precio = p.precio;
    }

    public void aplicarDescuento(double descuento) throws PrecioBajoExcepcion {
        double precioDescontado = this.precio * (100 - descuento) / 100;
        this.precio = precioDescontado;
        if (precioDescontado < 5) {
            throw new PrecioBajoExcepcion("Precio inferior a 5", new Producto(this));
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        long temp;
        temp = Double.doubleToLongBits(precio);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Producto other = (Producto) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
            return false;
        return true;
    }

}
