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

        /**
     * RETO EXTRA 01: Comprueba si la clave esta en cache.
     */
    public static boolean estaEnCache(java.util.Map<String, String> cache, String k) {
        // GUÍA: teoría 21.1 — el paso "¿hit o miss?".
        // Una línea: return cache.containsKey(k);
        // El test pasa Map.of("a","b") y "a" → espera true. containsKey es la
        // pregunta exacta que hace @Cacheable antes de decidir si calcula.
        // CULTURA: este containsKey es el "hit-check" que getOrCompute usa en
        // el TODO 5 del ejercicio base; reutiliza la misma idea.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaEnCache");
    }

    /**
     * RETO EXTRA 02: Obtiene el valor de la cache.
     */
    public static String obtenerDeCache(java.util.Map<String, String> cache, String k) {
        // GUÍA: una línea — return cache.get(k);
        // El test: Map.of("a","b"), "a" → "b". get devuelve el valor o null si
        // no está (eso sería un miss). No confundir con getOrDefault: aquí basta
        // con get porque la clave existe en el test.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerDeCache");
    }

    /**
     * RETO EXTRA 03: Guarda clave-valor en cache.
     */
    public static java.util.Map<String, String> ponerEnCache(java.util.Map<String, String> cache, String k, String v) {
        // GUÍA: dos pasos — cache.put(k, v); return cache;
        // El test guarda en un new HashMap<>() y comprueba .size() == 1. Devuelve
        // el MISMO mapa (no una copia): es la operación de escritura del miss.
        // OJO: el test te da un HashMap mutable a propósito; si recibieras un
        // Map.of() inmutable, put lanzaría UnsupportedOperationException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ponerEnCache");
    }

    /**
     * RETO EXTRA 04: Limpia toda la cache.
     */
    public static java.util.Map<String, String> limpiarCache(java.util.Map<String, String> cache) {
        // GUÍA: dos pasos — cache.clear(); return cache;
        // El test mete {"a":"b"} en un new HashMap<>() y espera .size() == 0.
        // Esto es el equivalente a @CacheEvict(allEntries = true): vacía TODO,
        // a diferencia de evict() que borra una sola clave (21.1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarCache");
    }

    /**
     * RETO EXTRA 05: Valida si la clave es valida.
     */
    public static boolean esClaveValida(String k) {
        // GUÍA: una línea — return k != null && !k.isBlank();
        // El test solo comprueba "a" → true, pero una clave de caché válida no
        // puede ser null ni estar en blanco (no podrías indexar por ella).
        // PISTA: isBlank() (Java 11) cubre "", "   " y tabuladores; isEmpty()
        // solo cubre la cadena vacía exacta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esClaveValida");
    }

    /**
     * RETO EXTRA 06: Obtiene tamaño de cache.
     */
    public static int tamanioCache(java.util.Map<String, String> cache) {
        // GUÍA: una línea — return cache.size();
        // El test: Map.of("a","b") → 1. size() es el número de entradas; en una
        // caché real es la métrica que vigilas para no quedarte sin memoria.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanioCache");
    }

    /**
     * RETO EXTRA 07: Guarda multiples entradas en cache.
     */
    public static java.util.Map<String, String> ponerMultiples(java.util.Map<String, String> cache, java.util.Map<String, String> m) {
        // GUÍA: dos pasos — cache.putAll(m); return cache;
        // El test mete Map.of("a","1","b","2") en un new HashMap<>() vacío y
        // espera .size() == 2. putAll copia todas las entradas de 'm' de golpe;
        // es el "precalentamiento" de caché (warm-up) que hace una app al
        // arrancar. Reutiliza la idea de ponerEnCache pero en bloque.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ponerMultiples");
    }

    /**
     * RETO EXTRA 08: Elimina multiples claves.
     */
    public static java.util.Map<String, String> eliminarVarios(java.util.Map<String, String> cache, java.util.List<String> ks) {
        // GUÍA: recorre las claves y elimínalas — luego devuelve el mapa.
        // PISTA: ks.forEach(cache::remove); return cache;
        //        (remove de clave inexistente NO falla, como en evict de 21.1).
        // El test mete {"a":"b"} y elimina List.of("a") → .size() == 0. Es un
        // evict en lote: invalidar varias claves de golpe (p. ej. al borrar un
        // usuario, todas sus entradas cacheadas). Reutiliza la idea de evict().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para eliminarVarios");
    }

    /**
     * RETO EXTRA 09: Comprueba si esta vacia la cache.
     */
    public static boolean estaVacia(java.util.Map<String, String> cache) {
        // GUÍA: una línea — return cache.isEmpty();
        // El test: Map.of() → true. isEmpty() es más claro y eficiente que
        // size() == 0 (no recorre, consulta el flag interno).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaVacia");
    }

    /**
     * RETO EXTRA 10: Obtiene todas las claves de la cache.
     */
    public static java.util.List<String> obtenerClaves(java.util.Map<String, String> cache) {
        // GUÍA: una línea — return new java.util.ArrayList<>(cache.keySet());
        // El test: Map.of("a","b") → lista de .size() == 1.
        // OJO: el método devuelve List<String>, pero keySet() devuelve un Set;
        // envuélvelo en una List (el constructor de ArrayList acepta cualquier
        // Collection). Devolver keySet() directo NO compila por el tipo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerClaves");
    }

}
