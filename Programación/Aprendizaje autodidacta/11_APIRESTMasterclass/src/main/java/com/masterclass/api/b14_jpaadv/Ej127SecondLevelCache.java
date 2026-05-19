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
}
