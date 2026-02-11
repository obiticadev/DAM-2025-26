package Clases;

import java.util.ArrayList;

public class Tienda {
    protected ArrayList<Producto> listaProductos;
    protected ArrayList<ProductoElectronico> listaElectronicos;

    public Tienda() {
        this.listaProductos = new ArrayList<>();
        this.listaElectronicos = new ArrayList<>();
    }


    public boolean añadirProducto(Producto p){
        if (listaProductos.size() < 10 && !listaProductos.contains(p)) {
            return listaProductos.add(p);
        }
        return false;
    }

    public boolean añadirProductoElectronico(ProductoElectronico pe){
        if (listaElectronicos.size() < 10 && !listaElectronicos.contains(pe)) {
            return listaElectronicos.add(pe);
        }
        return false;
    }

    public Double calcularDesucentoProducto(Producto p, Double descuento){
        return p.calcularDescuento(descuento);
    }

    

    
}
