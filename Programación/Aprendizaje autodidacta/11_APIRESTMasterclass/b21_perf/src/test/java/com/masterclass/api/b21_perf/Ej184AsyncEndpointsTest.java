package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej184AsyncEndpointsTest {

    @Test
    void sumaParalelaDeCuadrados() {
        assertEquals(14, Ej184AsyncEndpoints.sumarEnParalelo(List.of(1, 2, 3), x -> x * x));
    }

    @Test
    void vaciaEsCero() {
        assertEquals(0, Ej184AsyncEndpoints.sumarEnParalelo(List.of(), x -> x));
    }

    @Test
    void encadenaEnOrden() {
        assertEquals("AB", Ej184AsyncEndpoints.encadenar("a", String::toUpperCase, s -> s + "B"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej184AsyncEndpoints.sumarEnParalelo(null, x -> x));
        assertThrows(IllegalArgumentException.class,
                () -> Ej184AsyncEndpoints.encadenar("a", null, s -> s));
    }

    @Test
    void testRetoExtra01_esEntradaNula() {
        // Valida si contiene null.
        assertTrue(Ej184AsyncEndpoints.esEntradaNula(java.util.Arrays.asList(1, null)));
    }

    @Test
    void testRetoExtra02_crearFuture() {
        // Crea un future asincrono.
        assertNotNull(Ej184AsyncEndpoints.crearFuture(() -> "a"));
    }

    @Test
    void testRetoExtra03_obtenerResultadoFuture() {
        // Obtiene resultado bloqueando.
        assertEquals("a", Ej184AsyncEndpoints.obtenerResultadoFuture(java.util.concurrent.CompletableFuture.completedFuture("a")));
    }

    @Test
    void testRetoExtra04_futureCompletadoCon() {
        // Crea un future completado.
        assertTrue(Ej184AsyncEndpoints.futureCompletadoCon("a").isDone());
    }

    @Test
    void testRetoExtra05_combinarDos() {
        // Combina dos futures asincronos.
        assertEquals("ab", Ej184AsyncEndpoints.combinarDos(java.util.concurrent.CompletableFuture.completedFuture("a"), java.util.concurrent.CompletableFuture.completedFuture("b")).join());
    }

    @Test
    void testRetoExtra06_ejecutarRapido() {
        // Obtiene valor de inmediato o un default.
        assertEquals("default", Ej184AsyncEndpoints.ejecutarRapido(new java.util.concurrent.CompletableFuture<>()));
    }

    @Test
    void testRetoExtra07_esperarCualquiera() {
        // Espera al mas rapido.
        assertNotNull(Ej184AsyncEndpoints.esperarCualquiera(java.util.concurrent.CompletableFuture.completedFuture("a"), java.util.concurrent.CompletableFuture.completedFuture("b")));
    }

    @Test
    void testRetoExtra08_mapearResultado() {
        // Transforma asincronamente el resultado.
        assertEquals(4, Ej184AsyncEndpoints.mapearResultado(java.util.concurrent.CompletableFuture.completedFuture(2)).join());
    }

    @Test
    void testRetoExtra09_excepcionControlada() {
        // Provee fallback si falla el future.
        assertEquals("error", Ej184AsyncEndpoints.excepcionControlada(java.util.concurrent.CompletableFuture.failedFuture(new RuntimeException())).join());
    }

    @Test
    void testRetoExtra10_crearListaFutures() {
        // Crea una lista limpia de futures.
        assertNotNull(Ej184AsyncEndpoints.crearListaFutures());
    }

}