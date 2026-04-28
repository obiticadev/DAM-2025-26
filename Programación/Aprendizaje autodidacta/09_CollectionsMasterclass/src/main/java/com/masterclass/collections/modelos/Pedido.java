package com.masterclass.collections.modelos;

import com.masterclass.collections.modelos.interfaces.Identificable;
import com.masterclass.collections.modelos.interfaces.Procesable;

import java.util.Objects;

/**
 * Entidad de dominio: Pedido de un cliente.
 * Implementa Identificable y Procesable — usada en ejercicios de Queue,
 * instanceof y Boss Final.
 */
public class Pedido implements Identificable, Procesable {

    private final String id;
    private final String nombre;
    private final String clienteId;
    private double total;
    private int prioridad;
    private boolean procesado;

    public Pedido(String id, String clienteId, double total, int prioridad) {
        this.id = id;
        this.nombre = "Pedido-" + id;
        this.clienteId = clienteId;
        this.total = total;
        this.prioridad = prioridad;
        this.procesado = false;
    }

    @Override public String getId()          { return id; }
    @Override public String getNombre()      { return nombre; }
    @Override public int getPrioridad()      { return prioridad; }
    @Override public boolean isProcesado()   { return procesado; }

    @Override
    public void procesar() {
        this.procesado = true;
        System.out.println("[Procesado] " + this);
    }

    public String getClienteId() { return clienteId; }
    public double getTotal()     { return total; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido p)) return false;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pedido{id='%s', cliente='%s', total=%.2f, prioridad=%d, procesado=%b}"
                .formatted(id, clienteId, total, prioridad, procesado);
    }
}
