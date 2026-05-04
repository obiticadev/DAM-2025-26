package com.masterclass.collections.modelos;

import com.masterclass.collections.modelos.interfaces.Auditable;
import com.masterclass.collections.modelos.interfaces.Procesable;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad de dominio: Evento del sistema.
 * Implementa Procesable y Auditable — usada principalmente en los bloques
 * de instanceof y Boss Final.
 */
public class Evento implements Procesable, Auditable {

    private final String id;
    private final String tipo;
    private final String descripcion;
    private int prioridad;
    private boolean procesado;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private final String creadoPor;

    public Evento(String id, String tipo, String descripcion, int prioridad, String creadoPor) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.procesado = false;
        this.creadoPor = creadoPor;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = this.fechaCreacion;
    }

    @Override public int getPrioridad()                    { return prioridad; }
    @Override public boolean isProcesado()                 { return procesado; }
    @Override public LocalDateTime getFechaCreacion()      { return fechaCreacion; }
    @Override public LocalDateTime getUltimaModificacion() { return ultimaModificacion; }
    @Override public String getCreadoPor()                 { return creadoPor; }

    @Override
    public void procesar() {
        this.procesado = true;
        this.ultimaModificacion = LocalDateTime.now();
        System.out.println("[Evento procesado] " + this);
    }

    public String getId()          { return id; }
    public String getTipo()        { return tipo; }
    public String getDescripcion() { return descripcion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento e)) return false;
        return Objects.equals(id, e.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Evento{id='%s', tipo='%s', prioridad=%d, procesado=%b}"
                .formatted(id, tipo, prioridad, procesado);
    }
}
