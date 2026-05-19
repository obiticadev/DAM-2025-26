package com.masterclass.api.b13_rel;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 122 · JOIN FETCH y EntityGraph.
 *
 * <p>Teoría: {@code teoria/13_Relaciones_JPA.md} (sección 13.3).
 */
public final class Ej122JoinFetchAndEntityGraph {

    private Ej122JoinFetchAndEntityGraph() {
    }

    /**
     * Carga un proyecto con sus tareas usando un EntityGraph (sin escribir JOIN FETCH).
     *
     * @param em EntityManager
     * @param id id del proyecto
     * @return el proyecto con 'tareas' ya inicializadas
     */
    public static Proyecto122 cargarConGrafo(EntityManager em, Long id) {
        // TODO 1: crea un EntityGraph: em.createEntityGraph(Proyecto122.class).
        // TODO 2: añade el atributo a cargar: graph.addAttributeNodes("tareas").
        // TODO 3: prepara los hints: Map.of("jakarta.persistence.fetchgraph", graph)
        //         (o "loadgraph").
        // TODO 4: usa em.find(Proyecto122.class, id, hints).
        // TODO 5: la colección 'tareas' debe quedar inicializada por el grafo.
        // TODO 6: devuelve el proyecto.
        // TODO 7: el EntityGraph evita escribir JPQL para casos de carga.
        return null;
    }

    /**
     * Variante con JPQL JOIN FETCH.
     *
     * @param em EntityManager
     * @param id id del proyecto
     * @return proyecto con tareas inicializadas
     */
    public static Proyecto122 cargarConJoinFetch(EntityManager em, Long id) {
        // TODO 8: JPQL "select p from Proyecto122 p join fetch p.tareas where p.id = :id".
        // TODO 9: setParameter("id", id) y getSingleResult().
        // TODO 10: tras esto 'tareas' está inicializada aunque se haga em.clear() después.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: crea un EntityGraph: em.createEntityGraph(Proyecto122.class).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: añade el atributo a cargar: graph.addAttributeNodes("tareas").
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: prepara los hints: Map.of("jakarta.persistence.fetchgraph", graph)
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: usa em.find(Proyecto122.class, id, hints).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: la colección 'tareas' debe quedar inicializada por el grafo.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: devuelve el proyecto.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: el EntityGraph evita escribir JPQL para casos de carga.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: JPQL "select p from Proyecto122 p join fetch p.tareas where p.id = :id".
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: setParameter("id", id) y getSingleResult().
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: tras esto 'tareas' está inicializada aunque se haga em.clear() después.
    }

}

@Entity
class Tarea122 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "proy_id")
    private Proyecto122 proyecto;

    protected Tarea122() {
    }

    public Tarea122(String titulo) {
        this.titulo = titulo;
    }

    public void setProyecto(Proyecto122 p) {
        this.proyecto = p;
    }
}

@Entity
class Proyecto122 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tarea122> tareas = new ArrayList<>();

    public Proyecto122() {
    }

    public Proyecto122(String nombre) {
        this.nombre = nombre;
    }

    public void add(Tarea122 t) {
        tareas.add(t);
        t.setProyecto(this);
    }

    public Long getId() {
        return id;
    }

    public List<Tarea122> getTareas() {
        return tareas;
    }
}
