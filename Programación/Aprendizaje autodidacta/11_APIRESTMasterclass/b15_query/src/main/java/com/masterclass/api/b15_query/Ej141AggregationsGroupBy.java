package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 141 · Agregaciones y GROUP BY.
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.2).
 */
public final class Ej141AggregationsGroupBy {

    private final EntityManager em;

    public Ej141AggregationsGroupBy(EntityManager em) {
        this.em = em;
    }

    /**
     * Cuenta ventas agrupadas por categoría.
     *
     * @return mapa categoría → nº de ventas, ordenado por categoría
     */
    public Map<String, Long> conteoPorCategoria() {
        Map<String, Long> out = new LinkedHashMap<>();
        // TODO 1: JPQL "select v.categoria, count(v) from Venta141 v
        //         group by v.categoria order by v.categoria".
        // TODO 2: el resultado es List<Object[]> (cada fila: [categoria, count]).
        // TODO 3: createQuery(jpql, Object[].class).getResultList().
        // TODO 4: recorre cada Object[].
        // TODO 5: fila[0] es la categoría (String).
        // TODO 6: fila[1] es el count (Long).
        // TODO 7: mete cada par en 'out' (LinkedHashMap conserva el orden).
        // TODO 8: GROUP BY agrupa filas; count() cuenta por grupo.
        // TODO 9: si no hay ventas, devuelve mapa vacío.
        // TODO 10: devuelve el mapa.
        return out;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el nombre del item de forma segura.
     */
    public static String obtenerNombre(Item141 i) {
        // TODO extra: Reto Extra 1: Obtiene el nombre del item de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 2: Obtiene la categoria de forma segura.
     */
    public static String obtenerCategoria(Item141 i) {
        // TODO extra: Reto Extra 2: Obtiene la categoria de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCategoria");
    }

    /**
     * Reto Extra 3: Obtiene el precio de forma segura.
     */
    public static double obtenerPrecio(Item141 i) {
        // TODO extra: Reto Extra 3: Obtiene el precio de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPrecio");
    }

    /**
     * Reto Extra 4: Crea un nuevo item.
     */
    public static Item141 crearItem(String nombre, String categoria, double precio) {
        // TODO extra: Reto Extra 4: Crea un nuevo item.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearItem");
    }

    /**
     * Reto Extra 5: Comprueba si el item tiene ID.
     */
    public static boolean tieneId(Item141 i) {
        // TODO extra: Reto Extra 5: Comprueba si el item tiene ID.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneId");
    }

    /**
     * Reto Extra 6: Obtiene el ID del item de forma segura.
     */
    public static Long obtenerId(Item141 i) {
        // TODO extra: Reto Extra 6: Obtiene el ID del item de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 7: Normaliza el texto de los campos.
     */
    public static String normalizarTexto(String s) {
        // TODO extra: Reto Extra 7: Normaliza el texto de los campos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarTexto");
    }

    /**
     * Reto Extra 8: Comprueba si el item es nuevo (ID nulo).
     */
    public static boolean esNuevo(Item141 i) {
        // TODO extra: Reto Extra 8: Comprueba si el item es nuevo (ID nulo).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 9: Comprueba si la categoria contiene una palabra clave.
     */
    public static boolean categoriaContiene(Item141 i, String keyword) {
        // TODO extra: Reto Extra 9: Comprueba si la categoria contiene una palabra clave.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para categoriaContiene");
    }

    /**
     * Reto Extra 10: Retorna formato del item.
     */
    public static String formatearItem(Item141 i) {
        // TODO extra: Reto Extra 10: Retorna formato del item.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearItem");
    }



}

@Entity
class Venta141 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;

    public Venta141() {
    }

    public Venta141(String categoria) {
        this.categoria = categoria;
    }
}
