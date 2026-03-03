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
        if (precioDescontado < 5) {
            throw new PrecioBajoExcepcion("Precio inferior a 5", this);
        }
        this.precio = precioDescontado;
    }

}
