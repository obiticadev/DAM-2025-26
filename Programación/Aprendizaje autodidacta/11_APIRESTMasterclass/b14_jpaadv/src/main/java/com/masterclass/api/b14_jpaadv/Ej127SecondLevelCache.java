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
        // GUÍA: una línea — return hits;
        // (es el mismo valor que el método hits() ya existente). El test, recién
        // construida la caché, espera 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerHits");
    }

    /**
     * Reto Extra 2: Obtiene el numero de misses de forma segura.
     */
    public int obtenerMisses() {
        // GUÍA: una línea — return misses;
        // Igual que el reto 1 pero con el otro contador. El test espera 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMisses");
    }

    /**
     * Reto Extra 3: Limpia completamente la cache.
     */
    public void limpiarCache() {
        // GUÍA: vacía el Map interno — cache.clear();
        // OJO: igual que invalidate, NO toca los contadores hits/misses. El test
        //      hace get(1), limpiarCache() y luego espera cacheEstaVacia()==true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarCache");
    }

    /**
     * Reto Extra 4: Comprueba si la cache esta vacia.
     */
    public boolean cacheEstaVacia() {
        // GUÍA: una línea — return cache.isEmpty();
        // El test, recién construida, espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cacheEstaVacia");
    }

    /**
     * Reto Extra 5: Comprueba el tamano actual de la cache.
     */
    public int tamanoCache() {
        // GUÍA: una línea — return cache.size();
        // El test, recién construida, espera 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoCache");
    }

    /**
     * Reto Extra 6: Comprueba si una clave existe en la cache fisica.
     */
    public boolean contieneClave(K key) {
        // GUÍA: una línea — return cache.containsKey(key);
        // El test hace get(1) (que cachea) y luego espera contieneClave(1)==true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneClave");
    }

    /**
     * Reto Extra 7: Precalienta la cache con un mapa de entradas.
     */
    public void precalentar(Map<K, V> entradas) {
        // GUÍA: vuelca todas las entradas en la caché de golpe — cache.putAll(entradas);
        // CULTURA: "precalentar" (cache warming) es cargar datos frecuentes al
        // arrancar para que las primeras peticiones ya sean hits.
        // OJO: el test precalienta {1:"v1"} y luego espera obtenerSilencioso(1)=="v1".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precalentar");
    }

    /**
     * Reto Extra 8: Obtiene un valor sin contar como hit ni miss.
     */
    public V obtenerSilencioso(K key) {
        // GUÍA: lee del Map SIN tocar contadores ni cargar de BD.
        // 1. Una línea: return cache.get(key);
        // OJO: NO uses loaderBD aquí. Si la clave no está, cache.get devuelve null
        //      (el test sobre caché vacía espera null; tras precalentar, espera "v1").
        //      Contrasta con get(): aquel sí cuenta hit/miss y carga en miss.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerSilencioso");
    }

    /**
     * Reto Extra 9: Invalida multiples claves a la vez.
     */
    public void invalidarLote(Iterable<K> keys) {
        // GUÍA: recorre las claves y quita cada una (reutiliza invalidate o cache.remove).
        // 1. for (K k : keys) invalidate(k);   // o cache.remove(k);
        // OJO: el test cachea 1 y 2, invalida ambas y espera cacheEstaVacia()==true.
        // PISTA: Iterable<K> se recorre con for-each directamente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para invalidarLote");
    }

    /**
     * Reto Extra 10: Retorna ratio de hits como porcentaje [0, 100].
     */
    public double hitRatioPorcentaje() {
        // GUÍA: reutiliza hitRatio() (que ya maneja la división por cero) y escálalo.
        // 1. Una línea: return hitRatio() * 100;
        // OJO: caché sin accesos → hitRatio() es 0.0 → 0.0%. El test lo espera.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hitRatioPorcentaje");
    }



}
