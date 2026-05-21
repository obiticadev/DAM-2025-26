package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class Ej012OptionalSafeAccessTest {

    @Test
    void primeroLargoIgnoraNull() {
        var r = Ej012OptionalSafeAccess.primeroLargo(Arrays.asList("an", null, "pedro", "li"), 3);
        assertEquals("pedro", r.orElseThrow());
    }

    @Test
    void primeroLargoVacio() {
        assertTrue(Ej012OptionalSafeAccess.primeroLargo(Arrays.asList("a", "b"), 5).isEmpty());
    }

    @Test
    void mayusOrDefault() {
        assertEquals("N/A", Ej012OptionalSafeAccess.enMayusOrDefault(null, "N/A"));
        assertEquals("HOLA", Ej012OptionalSafeAccess.enMayusOrDefault("hola", "N/A"));
    }

    @Test
    void requerido() {
        assertEquals("x", Ej012OptionalSafeAccess.requerido("x"));
        assertThrows(IllegalStateException.class, () -> Ej012OptionalSafeAccess.requerido(null));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_obtenerLargoSiPresente() {
        assertEquals(Optional.of(5), Ej012OptionalSafeAccess.obtenerLargoSiPresente(Optional.of("hello")));
        assertEquals(Optional.empty(), Ej012OptionalSafeAccess.obtenerLargoSiPresente(Optional.empty()));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_obtenerPrimeroValido() {
        var list = java.util.List.of(
            Optional.<String>empty(),
            Optional.of("first"),
            Optional.of("second")
        );
        assertEquals(Optional.of("first"), Ej012OptionalSafeAccess.obtenerPrimeroValido(list));
        assertEquals(Optional.empty(), Ej012OptionalSafeAccess.obtenerPrimeroValido(java.util.List.of(Optional.empty(), Optional.empty())));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_filtrarPorLongitud() {
        assertEquals(Optional.of("Java"), Ej012OptionalSafeAccess.filtrarPorLongitud(Optional.of("Java"), 3));
        assertEquals(Optional.empty(), Ej012OptionalSafeAccess.filtrarPorLongitud(Optional.of("Java"), 5));
        assertEquals(Optional.empty(), Ej012OptionalSafeAccess.filtrarPorLongitud(Optional.empty(), 3));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_obtenerConLazyFallback() {
        java.util.concurrent.atomic.AtomicInteger callCount = new java.util.concurrent.atomic.AtomicInteger(0);
        java.util.function.Supplier<String> fallbackSupplier = () -> {
            callCount.incrementAndGet();
            return "fallback";
        };
        
        assertEquals("value", Ej012OptionalSafeAccess.obtenerConLazyFallback(Optional.of("value"), fallbackSupplier));
        assertEquals(0, callCount.get(), "El supplier no debió ser invocado (lazy)");

        assertEquals("fallback", Ej012OptionalSafeAccess.obtenerConLazyFallback(Optional.empty(), fallbackSupplier));
        assertEquals(1, callCount.get(), "El supplier debió ser invocado");
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_lanzarExcepcionPersonalizada() {
        assertEquals("ok", Ej012OptionalSafeAccess.lanzarExcepcionPersonalizada(Optional.of("ok")));
        var ex = assertThrows(IllegalArgumentException.class, () -> Ej012OptionalSafeAccess.lanzarExcepcionPersonalizada(Optional.empty()));
        assertEquals("Valor requerido ausente", ex.getMessage());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_convertirAStreamYFiltrar() {
        assertEquals(java.util.List.of("HELLO"), Ej012OptionalSafeAccess.convertirAStreamYFiltrar(Optional.of("hello")));
        assertEquals(java.util.List.of(), Ej012OptionalSafeAccess.convertirAStreamYFiltrar(Optional.of("HELLO")));
        assertEquals(java.util.List.of(), Ej012OptionalSafeAccess.convertirAStreamYFiltrar(Optional.empty()));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_ejecutarAccionCondicional() {
        java.util.concurrent.atomic.AtomicBoolean presentCalled = new java.util.concurrent.atomic.AtomicBoolean(false);
        java.util.concurrent.atomic.AtomicBoolean emptyCalled = new java.util.concurrent.atomic.AtomicBoolean(false);

        Ej012OptionalSafeAccess.ejecutarAccionCondicional(
            Optional.of("x"),
            val -> presentCalled.set(true),
            () -> emptyCalled.set(true)
        );
        assertTrue(presentCalled.get());
        assertFalse(emptyCalled.get());

        presentCalled.set(false);
        emptyCalled.set(false);

        Ej012OptionalSafeAccess.ejecutarAccionCondicional(
            Optional.empty(),
            val -> presentCalled.set(true),
            () -> emptyCalled.set(true)
        );
        assertFalse(presentCalled.get());
        assertTrue(emptyCalled.get());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_mapearConFlatMap() {
        assertEquals(Optional.of("inside"), Ej012OptionalSafeAccess.mapearConFlatMap(Optional.of(Optional.of("inside"))));
        assertEquals(Optional.empty(), Ej012OptionalSafeAccess.mapearConFlatMap(Optional.of(Optional.empty())));
        assertEquals(Optional.empty(), Ej012OptionalSafeAccess.mapearConFlatMap(Optional.empty()));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_reemplazarPorVacioSiInvalido() {
        assertEquals(Optional.of("HELLO"), Ej012OptionalSafeAccess.reemplazarPorVacioSiInvalido(Optional.of("  hello  ")));
        assertEquals(Optional.empty(), Ej012OptionalSafeAccess.reemplazarPorVacioSiInvalido(Optional.of("     ")));
        assertEquals(Optional.empty(), Ej012OptionalSafeAccess.reemplazarPorVacioSiInvalido(Optional.empty()));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_obtenerPrimeroDeVarios() {
        assertEquals(Optional.of("first"), Ej012OptionalSafeAccess.obtenerPrimeroDeVarios(Optional.of("first"), Optional.of("second")));
        assertEquals(Optional.of("second"), Ej012OptionalSafeAccess.obtenerPrimeroDeVarios(Optional.empty(), Optional.of("second")));
        assertEquals(Optional.empty(), Ej012OptionalSafeAccess.obtenerPrimeroDeVarios(Optional.empty(), Optional.empty()));
    }
}

