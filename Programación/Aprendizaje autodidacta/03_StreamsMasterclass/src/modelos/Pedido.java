package modelos;

import java.util.List;
import java.util.Objects;

/**
 * CLASE TERCIARIA: PEDIDO
 * 
 * POJO para ejercicios avanzados con flatMap, reduce y Optional.
 * Cada pedido contiene una lista de productos, permitiendo practicar
 * streams anidados y aplanamiento de colecciones.
 */
public class Pedido {

    private String idPedido;
    private String cliente;
    private List<Producto> productos;
    private boolean entregado;

    public Pedido(String idPedido, String cliente, List<Producto> productos, boolean entregado) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.productos = productos;
        this.entregado = entregado;
    }

    public String getIdPedido() { return idPedido; }
    public String getCliente() { return cliente; }
    public List<Producto> getProductos() { return productos; }
    public boolean isEntregado() { return entregado; }

    public void marcarEntregado() { this.entregado = true; }

    public double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public int totalArticulos() {
        return productos.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(idPedido, pedido.idPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido);
    }

    @Override
    public String toString() {
        return "Pedido " + idPedido + " [" + cliente + " | " + productos.size() + 
               " artículos | " + (entregado ? "Entregado" : "Pendiente") + "]";
    }
}
