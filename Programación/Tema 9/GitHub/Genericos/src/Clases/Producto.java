package Clases;

import java.util.List;

import Interfaz.Dao;

public class Producto {
    private String nombreProducto;

    public Producto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Override
    public String toString() {
        return "Producto [nombreProducto=" + nombreProducto + "]";
    }

}
