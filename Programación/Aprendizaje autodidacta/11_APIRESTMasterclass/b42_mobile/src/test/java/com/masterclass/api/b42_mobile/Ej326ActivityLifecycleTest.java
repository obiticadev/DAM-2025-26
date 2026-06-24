package com.masterclass.api.b42_mobile;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej326ActivityLifecycleTest {

    @Test
    void siguienteEstado() {
        assertEquals("CREADA", Ej326ActivityLifecycle.siguienteEstado("NUEVA", "onCreate"));
        assertEquals("REANUDADA", Ej326ActivityLifecycle.siguienteEstado("PAUSADA", "onResume"));
        assertEquals("CREADA", Ej326ActivityLifecycle.siguienteEstado("CREADA", "onResume")); // caso límite: inválida -> sin cambio
    }

    @Test
    void estadoTrasCallbacks() {
        assertEquals("REANUDADA", Ej326ActivityLifecycle.estadoTrasCallbacks(
                List.of("onCreate", "onStart", "onResume")));
        assertEquals("NUEVA", Ej326ActivityLifecycle.estadoTrasCallbacks(List.of())); // caso límite: vacía
    }

    @Test
    void retoExtra01_esVisible() {
        assertTrue(Ej326ActivityLifecycle.esVisible("REANUDADA"));
        assertFalse(Ej326ActivityLifecycle.esVisible("PARADA"));
    }

    @Test
    void retoExtra02_esInteractuable() {
        assertTrue(Ej326ActivityLifecycle.esInteractuable("REANUDADA"));
        assertFalse(Ej326ActivityLifecycle.esInteractuable("PAUSADA"));
    }

    @Test
    void retoExtra03_esTerminal() {
        assertTrue(Ej326ActivityLifecycle.esTerminal("DESTRUIDA"));
        assertFalse(Ej326ActivityLifecycle.esTerminal("PARADA"));
    }

    @Test
    void retoExtra04_callbackDeEntrada() {
        assertEquals("onResume", Ej326ActivityLifecycle.callbackDeEntrada("REANUDADA"));
        assertEquals("", Ej326ActivityLifecycle.callbackDeEntrada("NUEVA"));
    }

    @Test
    void retoExtra05_secuenciaArranque() {
        assertEquals(List.of("onCreate", "onStart", "onResume"),
                Ej326ActivityLifecycle.secuenciaArranque());
    }

    @Test
    void retoExtra06_secuenciaCierre() {
        assertEquals(List.of("onPause", "onStop", "onDestroy"),
                Ej326ActivityLifecycle.secuenciaCierre());
    }

    @Test
    void retoExtra07_transicionValida() {
        assertTrue(Ej326ActivityLifecycle.transicionValida("CREADA", "onStart"));
        assertFalse(Ej326ActivityLifecycle.transicionValida("CREADA", "onResume"));
    }

    @Test
    void retoExtra08_contarTransicionesValidas() {
        assertEquals(2, Ej326ActivityLifecycle.contarTransicionesValidas(
                List.of("onCreate", "onResume", "onStart"))); // onResume sobre CREADA es inválida
        assertEquals(0, Ej326ActivityLifecycle.contarTransicionesValidas(List.of())); // caso límite
    }

    @Test
    void retoExtra09_debeLiberarRecursos() {
        assertTrue(Ej326ActivityLifecycle.debeLiberarRecursos("onPause"));
        assertFalse(Ej326ActivityLifecycle.debeLiberarRecursos("onResume"));
    }

    @Test
    void retoExtra10_callbacksRotacion() {
        assertEquals(List.of("onPause", "onStop", "onDestroy", "onCreate", "onStart", "onResume"),
                Ej326ActivityLifecycle.callbacksRotacion());
    }
}
