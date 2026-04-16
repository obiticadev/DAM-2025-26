package Clases;

import java.util.ArrayList;

public class Tienda {
    private ArrayList<Producto> listaProductos;

    public Tienda() {
        this.listaProductos = new ArrayList<>();
    }

    public boolean añadirProducto(Producto p) {
        if (!this.listaProductos.contains(p)) {
            return this.listaProductos.add(p);
        } else {
            return false;
        }
    }

    public boolean eliminarProducto(Producto p) {
        if (this.listaProductos.contains(p)) {
            return this.listaProductos.remove(p);
        } else {
            return false;
        }
    }

    public boolean aplicarDescuento(Producto p) {
        if (this.listaProductos.contains(p)) {
            this.listaProductos.iterator()
            
        }
    }
}
