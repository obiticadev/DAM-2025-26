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

    /**
     * Reto Extra 1: Cuenta tareas de un proyecto.
     */
    public static int contarTareas(Proyecto122 p) {
        // TODO extra: Reto Extra 1: Cuenta tareas de un proyecto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarTareas");
    }

    /**
     * Reto Extra 2: Comprueba si tiene tareas.
     */
    public static boolean tieneTareas(Proyecto122 p) {
        // TODO extra: Reto Extra 2: Comprueba si tiene tareas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneTareas");
    }

    /**
     * Reto Extra 3: Comprueba si una tarea esta en proyecto.
     */
    public static boolean contieneTarea(Proyecto122 p, Tarea122 t) {
        // TODO extra: Reto Extra 3: Comprueba si una tarea esta en proyecto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneTarea");
    }

    /**
     * Reto Extra 4: Comprueba si alguna tarea tiene un titulo.
     */
    public static boolean tieneTitulo(Proyecto122 p, String titulo) {
        // TODO extra: Reto Extra 4: Comprueba si alguna tarea tiene un titulo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneTitulo");
    }

    /**
     * Reto Extra 5: Cuenta titulos largos.
     */
    public static int contarTareasTituloLargo(Proyecto122 p, int len) {
        // TODO extra: Reto Extra 5: Cuenta titulos largos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarTareasTituloLargo");
    }

    /**
     * Reto Extra 6: Crea una tarea.
     */
    public static Tarea122 crearTarea(String titulo) {
        // TODO extra: Reto Extra 6: Crea una tarea.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearTarea");
    }

    /**
     * Reto Extra 7: Remueve la primera tarea.
     */
    public static boolean removerPrimeraTarea(Proyecto122 p) {
        // TODO extra: Reto Extra 7: Remueve la primera tarea.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para removerPrimeraTarea");
    }

    /**
     * Reto Extra 8: Valida proyecto no nulo.
     */
    public static boolean esValido(Proyecto122 p) {
        // TODO extra: Reto Extra 8: Valida proyecto no nulo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValido");
    }

    /**
     * Reto Extra 9: Vincula tareas en lote.
     */
    public static void vincularTareas(Proyecto122 p, java.util.List<Tarea122> lista) {
        // TODO extra: Reto Extra 9: Vincula tareas en lote.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vincularTareas");
    }

    /**
     * Reto Extra 10: Retorna formato de proyecto.
     */
    public static String formatearProyecto(Proyecto122 p) {
        // TODO extra: Reto Extra 10: Retorna formato de proyecto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearProyecto");
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
