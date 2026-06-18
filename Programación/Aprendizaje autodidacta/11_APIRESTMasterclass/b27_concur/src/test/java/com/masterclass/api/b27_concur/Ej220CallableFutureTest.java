package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej220CallableFutureTest {

    @Test
    void resultadoDeCallable() {
        assertEquals(5050L, Ej220CallableFuture.resultadoDeCallable(100));
    }

    @Test
    void recogerVariosFutures() {
        assertEquals(50, Ej220CallableFuture.recogerVariosFutures(50));
    }

    @Test
    void retoExtra01_isDoneTrasGet() {
        assertTrue(Ej220CallableFuture.isDoneTrasGet(5));
    }

    @Test
    void retoExtra02_cancelTareaPendiente() {
        assertTrue(Ej220CallableFuture.cancelTareaPendiente());
    }

    @Test
    void retoExtra03_getConTimeoutDevuelve() {
        assertEquals(13, Ej220CallableFuture.getConTimeoutDevuelve(13));
    }

    @Test
    void retoExtra04_getConTimeoutLanzaTimeoutException() {
        assertTrue(Ej220CallableFuture.getConTimeoutLanzaTimeoutException());
    }

    @Test
    void retoExtra05_futureTaskEjecutable() {
        assertEquals(21, Ej220CallableFuture.futureTaskEjecutable(21));
    }

    @Test
    void retoExtra06_completionServiceTomaUno() {
        assertEquals(8, Ej220CallableFuture.completionServiceTomaUno(5, 8));
    }

    @Test
    void retoExtra07_cancelDevuelveFalseSiYaTermino() {
        assertTrue(Ej220CallableFuture.cancelDevuelveFalseSiYaTermino(3));
    }

    @Test
    void retoExtra08_isCancelledFalseSiCompleta() {
        assertTrue(Ej220CallableFuture.isCancelledFalseSiCompleta(3));
    }

    @Test
    void retoExtra09_getEsIdempotente() {
        assertEquals(17, Ej220CallableFuture.getEsIdempotente(17));
    }

    @Test
    void retoExtra10_getBloqueaHastaResultado() {
        assertEquals(99L, Ej220CallableFuture.getBloqueaHastaResultado(99));
    }
}
