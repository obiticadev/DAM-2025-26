package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej225CompletableFutureAdvancedTest {

    @Test
    void thenApplyEncadena() {
        assertEquals(42, Ej225CompletableFutureAdvanced.thenApplyEncadena(21));
    }

    @Test
    void allOfSuma() {
        assertEquals(10, Ej225CompletableFutureAdvanced.allOfSuma(10));
    }

    @Test
    void retoExtra01_supplyAsyncJoin() {
        assertEquals(7, Ej225CompletableFutureAdvanced.supplyAsyncJoin(7));
    }

    @Test
    void retoExtra02_thenComposeEncadena() {
        assertEquals(11, Ej225CompletableFutureAdvanced.thenComposeEncadena(10));
    }

    @Test
    void retoExtra03_thenCombineDos() {
        assertEquals(30, Ej225CompletableFutureAdvanced.thenCombineDos(10, 20));
    }

    @Test
    void retoExtra04_exceptionallyRecupera() {
        assertEquals(-1, Ej225CompletableFutureAdvanced.exceptionallyRecupera(-1));
    }

    @Test
    void retoExtra05_handleProcesaResultadoYError() {
        assertEquals(99, Ej225CompletableFutureAdvanced.handleProcesaResultadoYError(99));
    }

    @Test
    void retoExtra06_anyOfPrimero() {
        assertEquals(5, Ej225CompletableFutureAdvanced.anyOfPrimero(5));
    }

    @Test
    void retoExtra07_thenAcceptEfecto() {
        assertEquals(8, Ej225CompletableFutureAdvanced.thenAcceptEfecto(8));
    }

    @Test
    void retoExtra08_corrioEnExecutorDado() {
        assertTrue(Ej225CompletableFutureAdvanced.corrioEnExecutorDado());
    }

    @Test
    void retoExtra09_completarManualmente() {
        assertEquals(33, Ej225CompletableFutureAdvanced.completarManualmente(33));
    }

    @Test
    void retoExtra10_completeOnTimeoutFallback() {
        assertEquals(77, Ej225CompletableFutureAdvanced.completeOnTimeoutFallback(77));
    }
}
