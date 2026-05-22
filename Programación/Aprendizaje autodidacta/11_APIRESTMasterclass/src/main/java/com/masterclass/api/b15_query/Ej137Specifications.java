package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 137 · Specifications (predicados componibles con Criteria).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.3).
 */
public final class Ej137Specifications {

    private final EntityManager em;

    public Ej137Specifications(EntityManager em) {
        this.em = em;
    }

    /**
     * Busca libros componiendo predicados con CriteriaBuilder.
     *
     * @param generoOpt   filtro opcional por género (null = ignorar)
     * @param paginasMin  filtro opcional páginas mínimas (null = ignorar)
     * @return libros que cumplen TODOS los predicados activos
     */
    public List<Libro137> buscar(String generoOpt, Integer paginasMin) {
        // TODO 1: obtén CriteriaBuilder con em.getCriteriaBuilder().
        // TODO 2: crea CriteriaQuery<Libro137> cq = cb.createQuery(Libro137.class).
        // TODO 3: Root<Libro137> root = cq.from(Libro137.class).
        // TODO 4: crea una List<Predicate> vacía.
        // TODO 5: si generoOpt != null, añade cb.equal(root.get("genero"), generoOpt).
        // TODO 6: si paginasMin != null, añade cb.ge(root.get("paginas"), paginasMin).
        // TODO 7: cq.where(cb.and(predicados.toArray(new Predicate[0]))).
        // TODO 8: cq.orderBy(cb.asc(root.get("id"))).
        // TODO 9: em.createQuery(cq).getResultList().
        // TODO 10: devuelve la lista (predicados combinados con AND).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Comprueba si un filtro es nulo o vacio.
     */
    public static boolean esFiltroVacio(String f) {
        // TODO extra: Reto Extra 1: Comprueba si un filtro es nulo o vacio.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroVacio");
    }

    /**
     * Reto Extra 2: Comprueba si el precio minimo es valido.
     */
    public static boolean esPrecioValido(Double precio) {
        // TODO extra: Reto Extra 2: Comprueba si el precio minimo es valido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioValido");
    }

    /**
     * Reto Extra 3: Genera una expresion LIKE de forma segura.
     */
    public static String formatearLike(String f) {
        // TODO extra: Reto Extra 3: Genera una expresion LIKE de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearLike");
    }

    /**
     * Reto Extra 4: Comprueba si el precio maximo es nulo.
     */
    public static boolean esPrecioNulo(Double p) {
        // TODO extra: Reto Extra 4: Comprueba si el precio maximo es nulo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioNulo");
    }

    /**
     * Reto Extra 5: Comprueba si la categoria esta vacia.
     */
    public static boolean esCategoriaVacia(String c) {
        // TODO extra: Reto Extra 5: Comprueba si la categoria esta vacia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCategoriaVacia");
    }

    /**
     * Reto Extra 6: Comprueba si se requiere filtrar por nombre.
     */
    public static boolean requiereFiltroNombre(String nombre) {
        // TODO extra: Reto Extra 6: Comprueba si se requiere filtrar por nombre.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requiereFiltroNombre");
    }

    /**
     * Reto Extra 7: Normaliza el nombre de la categoria.
     */
    public static String normalizarCategoria(String cat) {
        // TODO extra: Reto Extra 7: Normaliza el nombre de la categoria.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarCategoria");
    }

    /**
     * Reto Extra 8: Comprueba si hay especificaciones activas.
     */
    public static boolean tieneEspecificaciones(String nombre, Double min, Double max) {
        // TODO extra: Reto Extra 8: Comprueba si hay especificaciones activas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneEspecificaciones");
    }

    /**
     * Reto Extra 9: Compara dos filtros de precio de forma logica.
     */
    public static boolean preciosCoherentes(Double min, Double max) {
        // TODO extra: Reto Extra 9: Compara dos filtros de precio de forma logica.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para preciosCoherentes");
    }

    /**
     * Reto Extra 10: Retorna representacion de especificaciones activas.
     */
    public static String formatearEspecificacion(String nom, Double min, Double max) {
        // TODO extra: Reto Extra 10: Retorna representacion de especificaciones activas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearEspecificacion");
    }



}

@Entity
class Libro137 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genero;
    private int paginas;

    public Libro137() {
    }

    public Libro137(String genero, int paginas) {
        this.genero = genero;
        this.paginas = paginas;
    }

    public String getGenero() {
        return genero;
    }

    public int getPaginas() {
        return paginas;
    }
}
