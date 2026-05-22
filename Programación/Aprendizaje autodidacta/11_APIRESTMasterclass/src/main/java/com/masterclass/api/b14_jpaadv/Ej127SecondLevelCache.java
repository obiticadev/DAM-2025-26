package com.masterclass.api.b14_jpaadv;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Ejercicio 127 · Caché de segundo nivel (modelo conceptual).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.3).
 *
 * <p>Simula una caché compartida: la 1ª lectura va a "BD" (miss), las siguientes
 * se sirven de caché (hit) hasta que se invalida.
 */
public class Ej127SecondLevelCache<K, V> {

    private final Map<K, V> cache = new HashMap<>();
    private final Function<K, V> loaderBD;
    private int hits = 0;
    private int misses = 0;

    public Ej127SecondLevelCache(Function<K, V> loaderBD) {
        this.loaderBD = loaderBD;
    }

    /**
     * Devuelve el valor: de caché si está; si no, carga de "BD" y cachea.
     *
     * @param key clave
     * @return valor asociado
     */
    public V get(K key) {
        // TODO 1: si 'cache' contiene 'key' -> es un HIT.
        // TODO 2: incrementa 'hits' y devuelve el valor cacheado.
        // TODO 3: si NO está -> es un MISS.
        // TODO 4: incrementa 'misses'.
        // TODO 5: carga con loaderBD.apply(key).
        // TODO 6: guarda el valor en 'cache' para futuras lecturas.
        // TODO 7: devuelve el valor cargado.
        return null;
    }

    /**
     * Invalida una entrada (p.ej. tras un UPDATE): la próxima lectura será miss.
     *
     * @param key clave a evictar
     */
    public void invalidate(K key) {
        // TODO 8: elimina 'key' de la caché.
        // TODO 9: no toques los contadores aquí (invalidar no es hit ni miss).
    }

    /** @return ratio de aciertos [0,1]; 0 si no hubo accesos */
    public double hitRatio() {
        // TODO 10: total = hits + misses; si total==0 -> 0.0; si no, hits/total.
        return -1;
    }

    public int hits() {
        return hits;
    }

    public int misses() {
        return misses;
    }

    public static void main(String[] args) {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        c.get(1);
        c.get(1);
        System.out.println(c.hitRatio());
    }

    /**
     * Reto Extra 1: Obtiene el numero de hits de forma segura.
     */
    public int obtenerHits() {
        // TODO extra: Reto Extra 1: Obtiene el numero de hits de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerHits");
    }

    /**
     * Reto Extra 2: Obtiene el numero de misses de forma segura.
     */
    public int obtenerMisses() {
        // TODO extra: Reto Extra 2: Obtiene el numero de misses de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMisses");
    }

    /**
     * Reto Extra 3: Limpia completamente la cache.
     */
    public void limpiarCache() {
        // TODO extra: Reto Extra 3: Limpia completamente la cache.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarCache");
    }

    /**
     * Reto Extra 4: Comprueba si la cache esta vacia.
     */
    public boolean cacheEstaVacia() {
        // TODO extra: Reto Extra 4: Comprueba si la cache esta vacia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cacheEstaVacia");
    }

    /**
     * Reto Extra 5: Comprueba el tamano actual de la cache.
     */
    public int tamanoCache() {
        // TODO extra: Reto Extra 5: Comprueba el tamano actual de la cache.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoCache");
    }

    /**
     * Reto Extra 6: Comprueba si una clave existe en la cache fisica.
     */
    public boolean contieneClave(K key) {
        // TODO extra: Reto Extra 6: Comprueba si una clave existe en la cache fisica.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneClave");
    }

    /**
     * Reto Extra 7: Precalienta la cache con un mapa de entradas.
     */
    public void precalentar(Map<K, V> entradas) {
        // TODO extra: Reto Extra 7: Precalienta la cache con un mapa de entradas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precalentar");
    }

    /**
     * Reto Extra 8: Obtiene un valor sin contar como hit ni miss.
     */
    public V obtenerSilencioso(K key) {
        // TODO extra: Reto Extra 8: Obtiene un valor sin contar como hit ni miss.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerSilencioso");
    }

    /**
     * Reto Extra 9: Invalida multiples claves a la vez.
     */
    public void invalidarLote(Iterable<K> keys) {
        // TODO extra: Reto Extra 9: Invalida multiples claves a la vez.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para invalidarLote");
    }

    /**
     * Reto Extra 10: Retorna ratio de hits como porcentaje [0, 100].
     */
    public double hitRatioPorcentaje() {
        // TODO extra: Reto Extra 10: Retorna ratio de hits como porcentaje [0, 100].
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hitRatioPorcentaje");
    }



}
