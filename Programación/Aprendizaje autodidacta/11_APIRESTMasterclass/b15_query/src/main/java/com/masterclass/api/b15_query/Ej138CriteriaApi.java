package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

/**
 * Ejercicio 138 · Criteria API tipada (agregado).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.3).
 */
public final class Ej138CriteriaApi {

    private final EntityManager em;

    public Ej138CriteriaApi(EntityManager em) {
        this.em = em;
    }

    /**
     * Suma el importe de las ventas de una región usando Criteria.
     *
     * @param region región a filtrar
     * @return suma de importes (0.0 si no hay)
     */
    public double totalPorRegion(String region) {
        // TODO 1: CriteriaBuilder cb = em.getCriteriaBuilder().
        // TODO 2: CriteriaQuery<Double> cq = cb.createQuery(Double.class).
        // TODO 3: Root<Venta138> root = cq.from(Venta138.class).
        // TODO 4: cq.select(cb.sum(root.get("importe"))) (agregado tipado).
        // TODO 5: cq.where(cb.equal(root.get("region"), region)).
        // TODO 6: ejecuta em.createQuery(cq).getSingleResult().
        // TODO 7: el resultado puede ser null si no hay filas.
        // TODO 8: si es null, devuelve 0.0.
        // TODO 9: si no, devuelve el double.
        // TODO 10: Criteria es type-safe: errores de campo se ven antes (vs JPQL string).
        return -1;
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
     * Reto Extra 2: Comprueba si un valor es positivo.
     */
    public static boolean esPrecioValido(Double precio) {
        // TODO extra: Reto Extra 2: Comprueba si un valor es positivo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioValido");
    }

    /**
     * Reto Extra 3: Retorna expresion LIKE normalizada.
     */
    public static String formatearLike(String f) {
        // TODO extra: Reto Extra 3: Retorna expresion LIKE normalizada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearLike");
    }

    /**
     * Reto Extra 4: Comprueba si la categoria esta vacia.
     */
    public static boolean esCategoriaVacia(String cat) {
        // TODO extra: Reto Extra 4: Comprueba si la categoria esta vacia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCategoriaVacia");
    }

    /**
     * Reto Extra 5: Comprueba si el ID es valido (no nulo).
     */
    public static boolean esIdValido(Long id) {
        // TODO extra: Reto Extra 5: Comprueba si el ID es valido (no nulo).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdValido");
    }

    /**
     * Reto Extra 6: Comprueba si la lista de ordenacion es segura.
     */
    public static boolean esOrdenacionValida(String campo) {
        // TODO extra: Reto Extra 6: Comprueba si la lista de ordenacion es segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esOrdenacionValida");
    }

    /**
     * Reto Extra 7: Normaliza el nombre de un campo.
     */
    public static String normalizarCampo(String c) {
        // TODO extra: Reto Extra 7: Normaliza el nombre de un campo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarCampo");
    }

    /**
     * Reto Extra 8: Comprueba si se debe filtrar por precio.
     */
    public static boolean debeFiltrarPrecio(Double precio) {
        // TODO extra: Reto Extra 8: Comprueba si se debe filtrar por precio.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para debeFiltrarPrecio");
    }

    /**
     * Reto Extra 9: Compara de forma logica dos precios.
     */
    public static boolean rangoPreciosValido(Double min, Double max) {
        // TODO extra: Reto Extra 9: Compara de forma logica dos precios.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rangoPreciosValido");
    }

    /**
     * Reto Extra 10: Retorna formato de criteria.
     */
    public static String formatearCriteria(String cat, Double maxPrecio) {
        // TODO extra: Reto Extra 10: Retorna formato de criteria.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearCriteria");
    }



}

@Entity
class Venta138 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String region;
    private double importe;

    public Venta138() {
    }

    public Venta138(String region, double importe) {
        this.region = region;
        this.importe = importe;
    }
}
