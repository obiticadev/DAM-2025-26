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
}
