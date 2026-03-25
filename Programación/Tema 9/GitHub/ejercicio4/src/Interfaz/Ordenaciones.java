package Interfaz;

import java.util.ArrayList;

import Clases.Producto;

public interface Ordenaciones {

    public ArrayList<Producto> ordenarAltoProducto(ArrayList<Producto> coleccion);

    public ArrayList<Producto> ordenarAnchoProducto(ArrayList<Producto> coleccion);

    public ArrayList<Producto> ordenarLargoProducto(ArrayList<Producto> coleccion);
}
