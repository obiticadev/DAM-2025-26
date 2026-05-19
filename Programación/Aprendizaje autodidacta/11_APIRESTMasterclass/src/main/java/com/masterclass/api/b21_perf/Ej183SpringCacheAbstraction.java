package com.masterclass.api.b21_perf;

import java.util.Map;
import java.util.function.Function;

/**
 * Ejercicio 183 · Abstraccion de cache de Spring.
 *
 * <p>Teoria: {@code teoria/21_Rendimiento_Resiliencia.md} (seccion 21.1).
 *
 * <p>{@code @Cacheable} evita recomputar; {@code @CacheEvict} invalida.
 * Aqui modelamos una cache en memoria (Map) con get-or-compute y evict,
 * contando las invocaciones REALES al calculo costoso para demostrar el ahorro.
 */
public final class Ej183SpringCacheAbstraction {

    private Ej183SpringCacheAbstraction() {
    }

    /**
     * Devuelve el valor cacheado para la clave o lo calcula y lo guarda (get-or-compute).
     *
     * @param cache     mapa que actua de almacen de cache (no null, mutable)
     * @param clave     clave a consultar (no null)
     * @param calculo   funcion costosa que produce el valor si hay miss (no null)
     * @param contador  contador de 1 elemento donde se incrementa cada calculo REAL
     * @return el valor asociado a la clave (cacheado o recien calculado)
     * @throws IllegalArgumentException si algun argumento es null o contador no tiene tamano 1
     */
    public static String getOrCompute(Map<String, String> cache, String clave,
                                       Function<String, String> calculo, int[] contador) {
        // TODO 1: si cache es null -> IllegalArgumentException.
        // TODO 2: si clave es null -> IllegalArgumentException.
        // TODO 3: si calculo es null -> IllegalArgumentException.
        // TODO 4: si contador es null o contador.length != 1 -> IllegalArgumentException.
        // TODO 5: si la clave ya esta en cache (cache hit), NO invoques 'calculo'.
        // TODO 6: en un hit, devuelve directamente el valor cacheado sin tocar contador.
        // TODO 7: en un miss, invoca 'calculo.apply(clave)' UNA sola vez.
        // TODO 8: incrementa contador[0] solo cuando hubo calculo real (miss).
        // TODO 9: guarda el valor calculado en cache para futuras lecturas.
        // TODO 10: devuelve el valor (recien calculado en el miss).
        return null;
    }

    /**
     * Invalida (evict) una entrada de la cache; el siguiente acceso sera un miss.
     *
     * @param cache mapa que actua de cache (no null)
     * @param clave clave a eliminar (no null)
     * @return true si habia una entrada y se elimino; false si no existia
     * @throws IllegalArgumentException si cache o clave son null
     */
    public static boolean evict(Map<String, String> cache, String clave) {
        // TODO 1: si cache es null -> IllegalArgumentException.
        // TODO 2: si clave es null -> IllegalArgumentException.
        // TODO 3: comprueba si la clave esta presente antes de eliminar.
        // TODO 4: guarda ese booleano "estabaPresente" para el valor de retorno.
        // TODO 5: elimina la entrada del mapa (remove).
        // TODO 6: tras el evict, esa clave provocara un nuevo calculo en getOrCompute.
        // TODO 7: evict de clave inexistente NO es error: simplemente devuelve false.
        // TODO 8: no toques otras entradas de la cache (evict selectivo, no clear).
        // TODO 9: la operacion es idempotente: evict dos veces deja el mismo estado.
        // TODO 10: devuelve "estabaPresente".
        return false;
    }

    public static void main(String[] args) {
        int[] c = {0};
        java.util.Map<String, String> cache = new java.util.HashMap<>();
        System.out.println(getOrCompute(cache, "a", k -> k.toUpperCase(), c));
        System.out.println("calculos=" + c[0]);
    }
}
