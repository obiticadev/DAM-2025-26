package Excepciones;

import Clases.Producto;

public class PrecioBajoExcepcion extends Exception {
    private Producto productoCopia;

    public PrecioBajoExcepcion(String mensaje, Producto p) {
        super(mensaje);
        this.productoCopia = p;
    }

    public Producto getProductoCopiado() {
        return productoCopia;
    }
}
