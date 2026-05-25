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
        // TODO extra: Reto Extra 1: Cuenta los conceptos de una factura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarConceptos");
    }

    /**
     * Reto Extra 2: Comprueba si la factura tiene conceptos.
     */
    public static boolean tieneConceptos(Factura119 f) {
        // TODO extra: Reto Extra 2: Comprueba si la factura tiene conceptos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneConceptos");
    }

    /**
     * Reto Extra 3: Comprueba si un concepto esta en la factura.
     */
    public static boolean contieneConcepto(Factura119 f, Concepto119 c) {
        // TODO extra: Reto Extra 3: Comprueba si un concepto esta en la factura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneConcepto");
    }

    /**
     * Reto Extra 4: Comprueba si algun concepto tiene una descripcion.
     */
    public static boolean tieneDescripcion(Factura119 f, String desc) {
        // TODO extra: Reto Extra 4: Comprueba si algun concepto tiene una descripcion.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneDescripcion");
    }

    /**
     * Reto Extra 5: Cuenta descripciones largas.
     */
    public static int contarConceptosDescLarga(Factura119 f, int len) {
        // TODO extra: Reto Extra 5: Cuenta descripciones largas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarConceptosDescLarga");
    }

    /**
     * Reto Extra 6: Crea un concepto.
     */
    public static Concepto119 crearConcepto(String descripcion) {
        // TODO extra: Reto Extra 6: Crea un concepto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearConcepto");
    }

    /**
     * Reto Extra 7: Remueve el primer concepto.
     */
    public static boolean removerPrimerConcepto(Factura119 f) {
        // TODO extra: Reto Extra 7: Remueve el primer concepto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para removerPrimerConcepto");
    }

    /**
     * Reto Extra 8: Valida factura no nula.
     */
    public static boolean esValida(Factura119 f) {
        // TODO extra: Reto Extra 8: Valida factura no nula.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValida");
    }

    /**
     * Reto Extra 9: Vincula conceptos en lote.
     */
    public static void vincularConceptos(Factura119 f, java.util.List<Concepto119> lista) {
        // TODO extra: Reto Extra 9: Vincula conceptos en lote.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vincularConceptos");
    }

    /**
     * Reto Extra 10: Retorna formato de factura.
     */
    public static String formatearFactura(Factura119 f) {
        // TODO extra: Reto Extra 10: Retorna formato de factura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
