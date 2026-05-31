package com.masterclass.api.b14_jpaadv;

import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

class Ej127SecondLevelCacheTest {

    @Test
    void missLuegoHit() {
        AtomicInteger cargas = new AtomicInteger();
        var c = new Ej127SecondLevelCache<Integer, String>(k -> {
            cargas.incrementAndGet();
            return "v" + k;
        });
        assertEquals("v1", c.get(1));   // miss
        assertEquals("v1", c.get(1));   // hit
        assertEquals("v1", c.get(1));   // hit
        assertEquals(1, cargas.get(), "solo debió cargar de BD una vez");
        assertEquals(2, c.hits());
        assertEquals(1, c.misses());
        assertEquals(2.0 / 3.0, c.hitRatio(), 0.0001);
    }

    @Test
    void invalidarFuerzaRecarga() {
        AtomicInteger cargas = new AtomicInteger();
        var c = new Ej127SecondLevelCache<Integer, String>(k -> {
            cargas.incrementAndGet();
            return "v";
        });
        c.get(1);
        c.invalidate(1);
        c.get(1);
        assertEquals(2, cargas.get());
    }

    @Test
    void ratioSinAccesos() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "x");
        assertEquals(0.0, c.hitRatio(), 0.0001);
    }

    @Test
    void testRetoExtra01() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertEquals(0, c.obtenerHits());
    }

    @Test
    void testRetoExtra02() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertEquals(0, c.obtenerMisses());
    }

    @Test
    void testRetoExtra03() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        c.get(1);
        c.limpiarCache();
        assertTrue(c.cacheEstaVacia());
    }

    @Test
    void testRetoExtra04() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertTrue(c.cacheEstaVacia());
    }

    @Test
    void testRetoExtra05() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertEquals(0, c.tamanoCache());
    }

    @Test
    void testRetoExtra06() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        c.get(1);
        assertTrue(c.contieneClave(1));
    }

    @Test
    void testRetoExtra07() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        c.precalentar(java.util.Map.of(1, "v1"));
        assertEquals("v1", c.obtenerSilencioso(1));
    }

    @Test
    void testRetoExtra08() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertNull(c.obtenerSilencioso(1));
    }

    @Test
    void testRetoExtra09() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        c.get(1);
        c.get(2);
        c.invalidarLote(java.util.List.of(1, 2));
        assertTrue(c.cacheEstaVacia());
    }

    @Test
    void testRetoExtra10() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertEquals(0.0, c.hitRatioPorcentaje(), 0.001);
    }

}
