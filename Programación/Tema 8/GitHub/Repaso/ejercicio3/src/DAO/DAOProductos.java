package DAO;

import java.util.ArrayList;
import java.util.List;

import Clases.Productos;
import Enum.Categoria;

public class DAOProductos {
    private List<Productos> listaProductos;

    public DAOProductos() {
        this.listaProductos = new ArrayList<>();
    }

    private void cargarDatos() {
        Productos p1 = new Productos("12345", "Pulsera", 25, Categoria.COMPLEMENTOS);
        Productos p2 = new Productos("874395", "Reloj", 150, Categoria.COMPLEMENTOS);
        listaProductos.add(p1)
    }

}
