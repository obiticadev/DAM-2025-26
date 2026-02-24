package Clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Enum.Estado;

public class Pedidos {

    private static int contadorPedido = 0;
    private int codigoPedido;
    private List<Productos> listaProductos;
    private LocalDate fecha;
    private Estado estado;

    public Pedidos(Estado estado) {
        this.codigoPedido = contadorPedido++;
        this.listaProductos = new ArrayList<>();
        this.fecha = LocalDate.now();
        this.estado = estado;
    }

    public void agregarProducto(Productos p) {
        this.listaProductos.add(p);
    }

    public double totalPedido() {
        double total = 0;
        for (Productos p : listaProductos) {
            total += p.getPrecio();
        }
        return total;
    }

    public String listarProductos() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaProductos.size(); i++) {
            sb.append("Pedido ").append(this.codigoPedido).append(", ").append(this.fecha).append("\n")
                    .append("\tEstado: ").append(estado.toString()).append("\n")
                    .append("\t").append(i + 1).append(". ")
                    .append(listaProductos.get(i).getNombre()).append(", ")
                    .append(listaProductos.get(i).getPrecio()).append(" [")
                    .append(listaProductos.get(i).getCodigoProducto()).append("] ");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigoPedido;
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
        Pedidos other = (Pedidos) obj;
        if (codigoPedido != other.codigoPedido)
            return false;
        return true;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public Estado getEstado() {
        return estado;
    }

}
