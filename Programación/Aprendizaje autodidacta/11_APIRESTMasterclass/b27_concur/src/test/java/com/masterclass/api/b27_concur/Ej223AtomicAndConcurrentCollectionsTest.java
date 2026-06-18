package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej223AtomicAndConcurrentCollectionsTest {

    @Test
    void atomicIncrement() {
        assertEquals(400_000, Ej223AtomicAndConcurrentCollections.atomicIncrement(4, 100_000));
    }

    @Test
    void concurrentMapCuentaClave() {
        assertEquals(400_000, Ej223AtomicAndConcurrentCollections.concurrentMapCuentaClave(4, 100_000));
    }

    @Test
    void retoExtra01_longAdderSuma() {
        assertEquals(400_000L, Ej223AtomicAndConcurrentCollections.longAdderSuma(4, 100_000));
    }

    @Test
    void retoExtra02_compareAndSetLoop() {
        assertEquals(1000, Ej223AtomicAndConcurrentCollections.compareAndSetLoop(1000));
    }

    @Test
    void retoExtra03_updateAndGet() {
        assertEquals(15, Ej223AtomicAndConcurrentCollections.updateAndGet(10, 5));
    }

    @Test
    void retoExtra04_computeIfAbsentClavesDistintas() {
        assertEquals(100, Ej223AtomicAndConcurrentCollections.computeIfAbsentClavesDistintas(100));
    }

    @Test
    void retoExtra05_blockingQueuePutTake() {
        assertEquals(1000, Ej223AtomicAndConcurrentCollections.blockingQueuePutTake(1000));
    }

    @Test
    void retoExtra06_copyOnWriteAddDesdeHilos() {
        assertEquals(400, Ej223AtomicAndConcurrentCollections.copyOnWriteAddDesdeHilos(4, 100));
    }

    @Test
    void retoExtra07_atomicReferenceCAS() {
        assertTrue(Ej223AtomicAndConcurrentCollections.atomicReferenceCAS());
    }

    @Test
    void retoExtra08_concurrentLinkedQueueOfferPoll() {
        assertEquals(500, Ej223AtomicAndConcurrentCollections.concurrentLinkedQueueOfferPoll(500));
    }

    @Test
    void retoExtra09_adderVsAtomicMismoResultado() {
        assertEquals(400_000L, Ej223AtomicAndConcurrentCollections.adderVsAtomicMismoResultado(4, 100_000));
    }

    @Test
    void retoExtra10_cuentaPalabrasDistintas() {
        List<String> palabras = List.of("rojo", "verde", "rojo", "azul", "verde", "rojo");
        assertEquals(3, Ej223AtomicAndConcurrentCollections.cuentaPalabrasDistintas(palabras));
    }
}
