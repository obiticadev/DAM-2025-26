package com.masterclass.api.b13_rel;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 119 · Cascade y orphanRemoval.
 *
 * <p>Teoría: {@code teoria/13_Relaciones_JPA.md} (sección 13.2).
 */
public final class Ej119CascadeTypes {

    private Ej119CascadeTypes() {
    }

    /**
     * Guarda la factura con sus conceptos en UNA operación (cascade).
     *
     * @param em EntityManager
     * @param f  factura con conceptos
     * @return nº de conceptos persistidos (recargando)
     */
    public static int guardarEnCascada(EntityManager em, Factura119 f) {
        // TODO 1: begin tx, persist(f) SOLO (no persistas conceptos a mano), commit.
        // TODO 2: gracias a cascade=ALL, los conceptos se guardan con la factura.
        // TODO 3: em.clear(), recarga la factura, devuelve su nº de conceptos.
        return -1;
    }

    /**
     * Elimina un concepto de la factura: orphanRemoval debe borrarlo de la BD.
     *
     * @param em EntityManager
     * @param facturaId id de la factura
     * @return nº de conceptos restantes tras quitar el primero
     */
    public static int quitarConceptoYContar(EntityManager em, Long facturaId) {
        // TODO 4: begin tx, recupera la factura.
        // TODO 5: quita el primer concepto de la lista.
        // TODO 6: con orphanRemoval=true, al quitarlo de la colección se BORRA en BD.
        // TODO 7: commit, em.clear(), recarga y devuelve el nº de conceptos.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@Entity
class Concepto119 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura119 factura;

    protected Concepto119() {
    }

    public Concepto119(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFactura(Factura119 f) {
        this.factura = f;
    }
}

@Entity
class Factura119 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO 8: anota 'conceptos' con @OneToMany(mappedBy="factura").
    // TODO 9: añade cascade = CascadeType.ALL.
    // TODO 10: añade orphanRemoval = true (quitar de la lista => DELETE).
    private List<Concepto119> conceptos = new ArrayList<>();

    public Factura119() {
    }

    public void add(Concepto119 c) {
        conceptos.add(c);
        c.setFactura(this);
    }

    public Long getId() {
        return id;
    }

    public List<Concepto119> getConceptos() {
        return conceptos;
    }
}
