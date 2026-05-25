package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 133 · Paginación (offset/limit + total).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.1).
 */
public final class Ej133Pagination {

    /** Página: contenido + metadatos. */
    public record Pagina<T>(List<T> contenido, long total, int pagina, int tamano) {
        public int totalPaginas() {
            return tamano == 0 ? 0 : (int) Math.ceil((double) total / tamano);
        }
    }

    private final EntityManager em;

    public Ej133Pagination(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve una página de items ordenados por id.
     *
     * @param pagina índice 0-based
     * @param tamano tamaño de página (&gt; 0)
     * @return Pagina con contenido y total global
     * @throws IllegalArgumentException si pagina &lt; 0 o tamano &lt;= 0
     */
    public Pagina<Item133> page(int pagina, int tamano) {
        // TODO 1: valida pagina >= 0 y tamano > 0.
        // TODO 2: calcula el offset = pagina * tamano.
        // TODO 3: query de datos JPQL "select i from Item133 i order by i.id".
        // TODO 4: aplica setFirstResult(offset).
        // TODO 5: aplica setMaxResults(tamano).
        // TODO 6: getResultList() para el contenido.
        // TODO 7: query aparte "select count(i) from Item133 i" para el total.
        // TODO 8: getSingleResult() -> long total.
        // TODO 9: construye la Pagina con contenido, total, pagina, tamano.
        // TODO 10: devuélvela (totalPaginas lo calcula el record).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Calcula el offset de paginacion de forma segura.
     */
    public static int calcularOffset(int pagina, int tamano) {
        // TODO extra: Reto Extra 1: Calcula el offset de paginacion de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularOffset");
    }

    /**
     * Reto Extra 2: Valida los parametros de paginacion.
     */
    public static boolean esValida(int pagina, int tamano) {
        // TODO extra: Reto Extra 2: Valida los parametros de paginacion.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValida");
    }

    /**
     * Reto Extra 3: Comprueba si hay una pagina siguiente.
     */
    public static boolean tieneSiguiente(Pagina<?> p) {
        // TODO extra: Reto Extra 3: Comprueba si hay una pagina siguiente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneSiguiente");
    }

    /**
     * Reto Extra 4: Comprueba si hay una pagina anterior.
     */
    public static boolean tieneAnterior(Pagina<?> p) {
        // TODO extra: Reto Extra 4: Comprueba si hay una pagina anterior.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneAnterior");
    }

    /**
     * Reto Extra 5: Comprueba si es la primera pagina.
     */
    public static boolean esPrimera(Pagina<?> p) {
        // TODO extra: Reto Extra 5: Comprueba si es la primera pagina.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrimera");
    }

    /**
     * Reto Extra 6: Comprueba si es la ultima pagina.
     */
    public static boolean esUltima(Pagina<?> p) {
        // TODO extra: Reto Extra 6: Comprueba si es la ultima pagina.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUltima");
    }

    /**
     * Reto Extra 7: Obtiene el indice de la siguiente pagina.
     */
    public static int indiceSiguiente(Pagina<?> p) {
        // TODO extra: Reto Extra 7: Obtiene el indice de la siguiente pagina.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceSiguiente");
    }

    /**
     * Reto Extra 8: Obtiene el indice de la anterior pagina.
     */
    public static int indiceAnterior(Pagina<?> p) {
        // TODO extra: Reto Extra 8: Obtiene el indice de la anterior pagina.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceAnterior");
    }

    /**
     * Reto Extra 9: Comprueba si el contenido de la pagina esta vacio.
     */
    public static boolean estaVacia(Pagina<?> p) {
        // TODO extra: Reto Extra 9: Comprueba si el contenido de la pagina esta vacio.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaVacia");
    }

    /**
     * Reto Extra 10: Retorna formato descriptivo de la pagina.
     */
    public static String formatearPagina(Pagina<?> p) {
        // TODO extra: Reto Extra 10: Retorna formato descriptivo de la pagina.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearPagina");
    }



}

@Entity
class Item133 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Item133() {
    }

    public Item133(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
