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
}
