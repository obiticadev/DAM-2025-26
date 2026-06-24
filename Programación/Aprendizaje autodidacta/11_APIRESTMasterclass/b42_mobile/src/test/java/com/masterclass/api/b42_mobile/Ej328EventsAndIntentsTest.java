package com.masterclass.api.b42_mobile;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej328EventsAndIntentsTest {

    @Test
    void navegar() {
        assertEquals(List.of("Home", "Detalle"),
                Ej328EventsAndIntents.navegar(List.of("Home"), "Detalle"));
        assertEquals(List.of("Home"),
                Ej328EventsAndIntents.navegar(List.of("Home"), "")); // caso límite: destino vacío no apila
    }

    @Test
    void extraComoTexto() {
        assertEquals("42", Ej328EventsAndIntents.extraComoTexto(Map.of("id", "42"), "id"));
        assertEquals("", Ej328EventsAndIntents.extraComoTexto(Map.of("id", "42"), "no")); // caso límite: ausente
        assertEquals("", Ej328EventsAndIntents.extraComoTexto(null, "id"));               // caso límite: sin extras
    }

    @Test
    void retoExtra01_cima() {
        assertEquals("Detalle", Ej328EventsAndIntents.cima(List.of("Home", "Detalle")));
        assertEquals("", Ej328EventsAndIntents.cima(List.of()));
    }

    @Test
    void retoExtra02_estaVacia() {
        assertTrue(Ej328EventsAndIntents.estaVacia(List.of()));
        assertFalse(Ej328EventsAndIntents.estaVacia(List.of("Home")));
    }

    @Test
    void retoExtra03_ponerExtra() {
        Map<String, String> original = Map.of("a", "1");
        Map<String, String> nuevo = Ej328EventsAndIntents.ponerExtra(original, "b", "2");
        assertEquals("1", nuevo.get("a"));
        assertEquals("2", nuevo.get("b"));
        assertEquals(1, original.size()); // el original NO cambia
        assertThrows(IllegalArgumentException.class, () -> Ej328EventsAndIntents.ponerExtra(original, " ", "x"));
    }

    @Test
    void retoExtra04_accionImplicita() {
        assertEquals("ACTION_SEND", Ej328EventsAndIntents.accionImplicita("compartir"));
        assertEquals("", Ej328EventsAndIntents.accionImplicita("bailar"));
    }

    @Test
    void retoExtra05_esResultadoOk() {
        assertTrue(Ej328EventsAndIntents.esResultadoOk(-1));  // RESULT_OK == -1
        assertFalse(Ej328EventsAndIntents.esResultadoOk(0));  // RESULT_CANCELED == 0
    }

    @Test
    void retoExtra06_requestCodeValido() {
        assertTrue(Ej328EventsAndIntents.requestCodeValido(0));
        assertTrue(Ej328EventsAndIntents.requestCodeValido(65535));
        assertFalse(Ej328EventsAndIntents.requestCodeValido(-1));
        assertFalse(Ej328EventsAndIntents.requestCodeValido(70000));
    }

    @Test
    void retoExtra07_volverAtras() {
        assertEquals(List.of("Home"),
                Ej328EventsAndIntents.volverAtras(List.of("Home", "Detalle")));
        assertEquals(List.of("Home"),
                Ej328EventsAndIntents.volverAtras(List.of("Home"))); // no se vacía la última
    }

    @Test
    void retoExtra08_contarPantallas() {
        assertEquals(3, Ej328EventsAndIntents.contarPantallas(List.of("A", "B", "C")));
        assertEquals(0, Ej328EventsAndIntents.contarPantallas(null));
    }

    @Test
    void retoExtra09_uriLlamada() {
        assertEquals("tel:600123123", Ej328EventsAndIntents.uriLlamada("600 123 123"));
        assertThrows(IllegalArgumentException.class, () -> Ej328EventsAndIntents.uriLlamada(" "));
    }

    @Test
    void retoExtra10_parametroDeepLink() {
        assertEquals("42", Ej328EventsAndIntents.parametroDeepLink("app://detalle?id=42&tab=info", "id"));
        assertEquals("", Ej328EventsAndIntents.parametroDeepLink("app://detalle?id=42", "no"));
    }
}
