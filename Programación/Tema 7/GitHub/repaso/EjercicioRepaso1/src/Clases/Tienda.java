package Clases;

import java.util.ArrayList;

public class Tienda {
    protected ArrayList<Producto> listaProductos;
    protected ArrayList<ProductoElectronico> listaElectronicos;

    public Tienda() {
        this.listaProductos = new ArrayList<>();
        this.listaElectronicos = new ArrayList<>();
    }

    public boolean añadirProducto(Producto p) {
        if (listaProductos.size() < 10 && !listaProductos.contains(p)) {
            return listaProductos.add(p);
        }
        return false;
    }

    public boolean añadirProductoElectronico(ProductoElectronico pe) {
        if (listaElectronicos.size() < 10 && !listaElectronicos.contains(pe)) {
            return listaElectronicos.add(pe);
        }
        return false;
    }

    public Double calcularDesucentoProducto(Producto p, Double descuento) {
        return p.calcularDescuento(descuento);
    }

    public Double calcularDescuentoFinalProducto(Producto p, Double descuento, Double descuentoAdicional) {
        if (p instanceof ProductoElectronico pe) {
            return pe.calcularDescuento(descuento, descuentoAdicional);
        }
        return -1.0;
    }

    public String mostrarInformacion(Double porcentaje, Double descuentoAdicional) {
        StringBuilder sb = new StringBuilder();

        sb.append("=== Lista de Productos ===\n")
                .append("== Productos ==\n");
        for (Producto p : listaProductos) {
            sb.append(" -> ").append(p.getNombre()).append(" (").append(p.getCodigo()).append(")\n")
                    .append("\tPrecio: ").append(p.getPrecio()).append("€\n")
                    .append("\tPrecio con ").append(porcentaje).append("% de descuento: ")
                    .append(p.calcularDescuento(porcentaje)).append("€\n");
        }
        sb.append("== Productos Electrónicos ==\n");
        for (ProductoElectronico pe : listaElectronicos) {
            sb.append(" -> ").append(pe.getNombre()).append(" (").append(pe.getCodigo()).append(")\n")
                    .append("\tPrecio: ").append(pe.getPrecio()).append("€\n")
                    .append("\tPrecio con ").append(porcentaje).append("% de descuento y ").append(descuentoAdicional)
                    .append("€ de descuento adicional: ")
                    .append(pe.calcularDescuento(porcentaje, descuentoAdicional)).append("€\n")
                    .append("\tGarantía: ").append(pe.getGarantia()).append(" días\n");
        }

        return sb.toString();

    }

}
