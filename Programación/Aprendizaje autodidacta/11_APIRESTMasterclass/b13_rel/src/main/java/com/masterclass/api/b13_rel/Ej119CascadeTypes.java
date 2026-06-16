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

    /**
     * Reto Extra 1: Cuenta los conceptos de una factura.
     */
    public static int contarConceptos(Factura119 f) {
        // GUÍA: teoría 13.5. La colección de conceptos es una List normal.
        // PISTA: return f == null ? 0 : f.getConceptos().size();
        // OJO: Factura119.add ya está implementado (no es TODO), así que el test cambia a 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarConceptos");
    }

    /**
     * Reto Extra 2: Comprueba si la factura tiene conceptos.
     */
    public static boolean tieneConceptos(Factura119 f) {
        // GUÍA: teoría 13.5. Reutiliza contarConceptos.
        // PISTA: return contarConceptos(f) > 0;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneConceptos");
    }

    /**
     * Reto Extra 3: Comprueba si un concepto esta en la factura.
     */
    public static boolean contieneConcepto(Factura119 f, Concepto119 c) {
        // GUÍA: teoría 13.5. La List sabe contains (por identidad: Concepto119 no define equals).
        // PISTA: return f != null && c != null && f.getConceptos().contains(c);
        // OJO: el test añade y consulta la MISMA instancia → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneConcepto");
    }

    /**
     * Reto Extra 4: Comprueba si algun concepto tiene una descripcion.
     */
    public static boolean tieneDescripcion(Factura119 f, String desc) {
        // GUÍA: teoría 13.5 + streams. Concepto119 ya tiene getDescripcion().
        // PISTA: f.getConceptos().stream().anyMatch(c -> desc.equals(c.getDescripcion()));
        // OJO: "Premium" true, "Normal" false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneDescripcion");
    }

    /**
     * Reto Extra 5: Cuenta descripciones largas.
     */
    public static int contarConceptosDescLarga(Factura119 f, int len) {
        // GUÍA: teoría 13.5 + filter+count.
        // PISTA: (int) f.getConceptos().stream()
        //            .filter(c -> c.getDescripcion() != null && c.getDescripcion().length() > len).count();
        // OJO: "Mantenimiento" mide 13; con len=8 → 1. Estrictamente mayor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarConceptosDescLarga");
    }

    /**
     * Reto Extra 6: Crea un concepto.
     */
    public static Concepto119 crearConcepto(String descripcion) {
        // GUÍA: una línea — factory simple.
        // PISTA: return new Concepto119(descripcion);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearConcepto");
    }

    /**
     * Reto Extra 7: Remueve el primer concepto.
     */
    public static boolean removerPrimerConcepto(Factura119 f) {
        // GUÍA: teoría 13.5. En memoria es un remove de lista; en una entidad gestionada,
        //       con orphanRemoval=true, ese mismo remove dispararía un DELETE en BD.
        // 1. Si la lista está vacía, devuelve false.
        // 2. Quita el elemento 0 y devuelve true.
        // PISTA: if (f.getConceptos().isEmpty()) return false;
        //        f.getConceptos().remove(0); return true;
        // OJO: el test espera true y que getConceptos() quede en 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para removerPrimerConcepto");
    }

    /**
     * Reto Extra 8: Valida factura no nula.
     */
    public static boolean esValida(Factura119 f) {
        // GUÍA: una línea.
        // PISTA: return f != null;
        // OJO: el test espera true con new Factura119() y false con null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValida");
    }

    /**
     * Reto Extra 9: Vincula conceptos en lote.
     */
    public static void vincularConceptos(Factura119 f, java.util.List<Concepto119> lista) {
        // GUÍA: teoría 13.5. Usa f.add (ya implementado: sincroniza el lado dueño).
        // PISTA: lista.forEach(f::add);
        // OJO: el test pasa 2 conceptos y espera size 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vincularConceptos");
    }

    /**
     * Reto Extra 10: Retorna formato de factura.
     */
    public static String formatearFactura(Factura119 f) {
        // GUÍA: formato EXACTO.
        // PISTA: return "Factura[Id=" + f.getId() + ", Conceptos=" + f.getConceptos().size() + "]";
        // OJO: el test espera literalmente "Factura[Id=null, Conceptos=0]".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearFactura");
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

    public String getDescripcion() {
        return descripcion;
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
