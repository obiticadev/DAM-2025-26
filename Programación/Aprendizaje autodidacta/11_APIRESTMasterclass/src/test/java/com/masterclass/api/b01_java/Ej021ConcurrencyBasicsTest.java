package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;

class Ej021ConcurrencyBasicsTest {

    @Test
    void asincronoMayus() {
        assertEquals("HOLA", Ej021ConcurrencyBasics.asincronoMayus("hola").join());
    }

    @Test
    void sumar() {
        var r = Ej021ConcurrencyBasics.sumar(
                CompletableFuture.completedFuture(3),
                CompletableFuture.completedFuture(4));
        assertEquals(7, r.join());
    }

    @Test
    void esperarTodos() {
        var fs = List.of(
                CompletableFuture.completedFuture("a"),
                CompletableFuture.completedFuture("b"));
        assertEquals(List.of("a", "b"), Ej021ConcurrencyBasics.esperarTodos(fs));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void retoExtra01_ejecutarEnHiloSeparado() throws Exception {
        java.util.concurrent.atomic.AtomicBoolean ejecutado = new java.util.concurrent.atomic.AtomicBoolean(false);
        Thread t = Ej021ConcurrencyBasics.ejecutarEnHiloSeparado(() -> ejecutado.set(true));
        assertNotNull(t);
        t.join(2000);
        assertTrue(ejecutado.get());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void retoExtra02_obtenerResultadoAsincrono() {
        CompletableFuture<String> fut = CompletableFuture.completedFuture("res");
        assertEquals("res", Ej021ConcurrencyBasics.obtenerResultadoAsincrono(fut));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void retoExtra03_combinarDosResultadosAsincronos() {
        CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(10);
        CompletableFuture<Integer> f2 = CompletableFuture.completedFuture(20);
        CompletableFuture<String> res = Ej021ConcurrencyBasics.combinarDosResultadosAsincronos(f1, f2, (x, y) -> x + "+" + y);
        assertEquals("10+20", res.join());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void retoExtra04_ejecutarConTimeout() {
        CompletableFuture<String> lento = new CompletableFuture<>();
        CompletableFuture<String> res = Ej021ConcurrencyBasics.ejecutarConTimeout(lento, 100, "defecto");
        assertEquals("defecto", res.join());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void retoExtra05_recuperarAnteErrorAsincrono() {
        CompletableFuture<String> fallo = CompletableFuture.failedFuture(new RuntimeException("Boom"));
        CompletableFuture<String> res = Ej021ConcurrencyBasics.recuperarAnteErrorAsincrono(fallo, "recuperado");
        assertEquals("recuperado", res.join());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void retoExtra06_ejecutarEnPoolAcotado() throws Exception {
        java.util.concurrent.atomic.AtomicBoolean ejecutado = new java.util.concurrent.atomic.AtomicBoolean(false);
        java.util.concurrent.ExecutorService exec = Ej021ConcurrencyBasics.ejecutarEnPoolAcotado(() -> ejecutado.set(true), 2);
        assertNotNull(exec);
        exec.awaitTermination(2000, java.util.concurrent.TimeUnit.MILLISECONDS);
        assertTrue(ejecutado.get() || exec.isShutdown());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void retoExtra07_esperarQueTerminenTodos() {
        CompletableFuture<String> f1 = CompletableFuture.completedFuture("1");
        CompletableFuture<String> f2 = CompletableFuture.completedFuture("2");
        assertDoesNotThrow(() -> Ej021ConcurrencyBasics.esperarQueTerminenTodos(List.of(f1, f2)));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void retoExtra08_ejecutarPrimeroQueTermine() {
        CompletableFuture<String> f1 = new CompletableFuture<>();
        CompletableFuture<String> f2 = CompletableFuture.completedFuture("ganador");
        CompletableFuture<String> res = Ej021ConcurrencyBasics.ejecutarPrimeroQueTermine(f1, f2);
        assertEquals("ganador", res.join());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void retoExtra09_mapearResultadoAsincrono() {
        CompletableFuture<Integer> fut = CompletableFuture.completedFuture(5);
        CompletableFuture<String> res = Ej021ConcurrencyBasics.mapearResultadoAsincrono(fut, x -> "val: " + x);
        assertEquals("val: 5", res.join());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void retoExtra10_encadenarFuturosAsincronos() {
        CompletableFuture<Integer> fut = CompletableFuture.completedFuture(5);
        CompletableFuture<String> res = Ej021ConcurrencyBasics.encadenarFuturosAsincronos(fut, x -> CompletableFuture.completedFuture("val: " + x));
        assertEquals("val: 5", res.join());
    }
}
