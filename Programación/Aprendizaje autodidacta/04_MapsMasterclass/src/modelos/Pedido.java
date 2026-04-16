package modelos;

import java.time.LocalDate;

public class Pedido {
    private String idPedido;
    private String cliente;
    private LocalDate fecha;
    private double total;
    private String estado; // "PENDIENTE", "ENVIADO", "ENTREGADO", "CANCELADO"

    public Pedido(String idPedido, String cliente, LocalDate fecha, double total, String estado) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public String getIdPedido() { return idPedido; }
    public String getCliente() { return cliente; }
    public LocalDate getFecha() { return fecha; }
    public double getTotal() { return total; }
    public String getEstado() { return estado; }

    @Override
    public String toString() {
        return String.format("Pedido{id='%s', cliente='%s', total=%.2f, estado='%s'}", idPedido, cliente, total, estado);
    }
}
