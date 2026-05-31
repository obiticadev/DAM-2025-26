package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej183SpringCacheAbstractionTest {

    @Test
    void missCalculaYHitNoRecalcula() {
        Map<String, String> cache = new HashMap<>();
        int[] c = {0};
        assertEquals("A", Ej183SpringCacheAbstraction.getOrCompute(cache, "a", String::toUpperCase, c));
        assertEquals("A", Ej183SpringCacheAbstraction.getOrCompute(cache, "a", String::toUpperCase, c));
        assertEquals(1, c[0]);
    }

    @Test
    void evictForzaRecalculo() {
        Map<String, String> cache = new HashMap<>();
        int[] c = {0};
        Ej183SpringCacheAbstraction.getOrCompute(cache, "a", String::toUpperCase, c);
        assertTrue(Ej183SpringCacheAbstraction.evict(cache, "a"));
        assertFalse(Ej183SpringCacheAbstraction.evict(cache, "a"));
        Ej183SpringCacheAbstraction.getOrCompute(cache, "a", String::toUpperCase, c);
        assertEquals(2, c[0]);
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej183SpringCacheAbstraction.getOrCompute(null, "a", k -> k, new int[1]));
        assertThrows(IllegalArgumentException.class,
                () -> Ej183SpringCacheAbstraction.evict(new HashMap<>(), null));
    }

    @Test
    void testRetoExtra01_estaEnCache() {
        // Comprueba si la clave esta en cache.
        assertTrue(Ej183SpringCacheAbstraction.estaEnCache(java.util.Map.of("a", "b"), "a"));
    }

    @Test
    void testRetoExtra02_obtenerDeCache() {
        // Obtiene el valor de la cache.
        assertEquals("b", Ej183SpringCacheAbstraction.obtenerDeCache(java.util.Map.of("a", "b"), "a"));
    }

    @Test
    void testRetoExtra03_ponerEnCache() {
        // Guarda clave-valor en cache.
        assertEquals(1, Ej183SpringCacheAbstraction.ponerEnCache(new java.util.HashMap<>(), "a", "b").size());
    }

    @Test
    void testRetoExtra04_limpiarCache() {
        // Limpia toda la cache.
        assertEquals(0, Ej183SpringCacheAbstraction.limpiarCache(new java.util.HashMap<>(java.util.Map.of("a", "b"))).size());
    }

    @Test
    void testRetoExtra05_esClaveValida() {
        // Valida si la clave es valida.
        assertTrue(Ej183SpringCacheAbstraction.esClaveValida("a"));
    }

    @Test
    void testRetoExtra06_tamanioCache() {
        // Obtiene tamaño de cache.
        assertEquals(1, Ej183SpringCacheAbstraction.tamanioCache(java.util.Map.of("a", "b")));
    }

    @Test
    void testRetoExtra07_ponerMultiples() {
        // Guarda multiples entradas en cache.
        assertEquals(2, Ej183SpringCacheAbstraction.ponerMultiples(new java.util.HashMap<>(), java.util.Map.of("a", "1", "b", "2")).size());
    }

    @Test
    void testRetoExtra08_eliminarVarios() {
        // Elimina multiples claves.
        assertEquals(0, Ej183SpringCacheAbstraction.eliminarVarios(new java.util.HashMap<>(java.util.Map.of("a", "b")), java.util.List.of("a")).size());
    }

    @Test
    void testRetoExtra09_estaVacia() {
        // Comprueba si esta vacia la cache.
        assertTrue(Ej183SpringCacheAbstraction.estaVacia(java.util.Map.of()));
    }

    @Test
    void testRetoExtra10_obtenerClaves() {
        // Obtiene todas las claves de la cache.
        assertEquals(1, Ej183SpringCacheAbstraction.obtenerClaves(java.util.Map.of("a", "b")).size());
    }

}