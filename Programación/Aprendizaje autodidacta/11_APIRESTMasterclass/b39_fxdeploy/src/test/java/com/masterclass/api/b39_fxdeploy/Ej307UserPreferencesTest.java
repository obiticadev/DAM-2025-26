package com.masterclass.api.b39_fxdeploy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.junit.jupiter.api.Assertions.*;

class Ej307UserPreferencesTest {

    /** Nodo TEMPORAL para no tocar las preferencias reales de la máquina; se limpia tras cada test. */
    private Preferences nodo;

    @BeforeEach
    void crearNodo() {
        nodo = Preferences.userRoot().node("masterclass/b39/test-" + System.nanoTime());
    }

    @AfterEach
    void borrarNodo() throws BackingStoreException {
        nodo.removeNode(); // borra el nodo temporal por completo
    }

    @Test
    void guardarPreferencia() {
        assertTrue(Ej307UserPreferences.guardarPreferencia(nodo, "tema", "oscuro"));
        assertEquals("oscuro", nodo.get("tema", "claro"));
        assertFalse(Ej307UserPreferences.guardarPreferencia(null, "x", "y")); // caso límite
    }

    @Test
    void leerPreferencia() {
        nodo.put("tema", "oscuro");
        assertEquals("oscuro", Ej307UserPreferences.leerPreferencia(nodo, "tema", "claro"));
        assertEquals("claro", Ej307UserPreferences.leerPreferencia(nodo, "inexistente", "claro")); // por defecto
    }

    @Test
    void borrarPreferencia() {
        nodo.put("tema", "oscuro");
        assertTrue(Ej307UserPreferences.borrarPreferencia(nodo, "tema"));
        assertEquals("claro", nodo.get("tema", "claro")); // ya no está
        assertFalse(Ej307UserPreferences.borrarPreferencia(null, "x")); // caso límite
    }

    @Test
    void retoExtra01_guardarEntero() {
        assertTrue(Ej307UserPreferences.guardarEntero(nodo, "ancho", 800));
        assertEquals(800, nodo.getInt("ancho", 0));
    }

    @Test
    void retoExtra02_leerEntero() {
        nodo.putInt("ancho", 800);
        assertEquals(800, Ej307UserPreferences.leerEntero(nodo, "ancho", 0));
        assertEquals(640, Ej307UserPreferences.leerEntero(nodo, "inexistente", 640)); // por defecto
    }

    @Test
    void retoExtra03_guardarBooleano() {
        assertTrue(Ej307UserPreferences.guardarBooleano(nodo, "oscuro", true));
        assertTrue(nodo.getBoolean("oscuro", false));
    }

    @Test
    void retoExtra04_leerBooleano() {
        nodo.putBoolean("oscuro", true);
        assertTrue(Ej307UserPreferences.leerBooleano(nodo, "oscuro", false));
        assertFalse(Ej307UserPreferences.leerBooleano(nodo, "inexistente", false)); // por defecto
    }

    @Test
    void retoExtra05_claveValida() {
        assertTrue(Ej307UserPreferences.claveValida("tema"));
        assertFalse(Ej307UserPreferences.claveValida("x".repeat(100))); // pasa de 80
        assertFalse(Ej307UserPreferences.claveValida("")); // caso límite
    }

    @Test
    void retoExtra06_existeClave() {
        nodo.put("tema", "oscuro");
        assertTrue(Ej307UserPreferences.existeClave(nodo, "tema"));
        assertFalse(Ej307UserPreferences.existeClave(nodo, "otra")); // caso límite
    }

    @Test
    void retoExtra07_listarClaves() {
        nodo.put("b", "2");
        nodo.put("a", "1");
        assertEquals(List.of("a", "b"), Ej307UserPreferences.listarClaves(nodo)); // ordenadas
        assertEquals(List.of(), Ej307UserPreferences.listarClaves(null)); // caso límite
    }

    @Test
    void retoExtra08_limpiarNodo() {
        nodo.put("a", "1");
        nodo.put("b", "2");
        assertTrue(Ej307UserPreferences.limpiarNodo(nodo));
        assertEquals(List.of(), Ej307UserPreferences.listarClaves(nodo)); // vacío
    }

    @Test
    void retoExtra09_leerDoble() {
        nodo.putDouble("volumen", 0.8);
        assertEquals(0.8, Ej307UserPreferences.leerDoble(nodo, "volumen", 1.0), 1e-9);
        assertEquals(1.0, Ej307UserPreferences.leerDoble(nodo, "inexistente", 1.0), 1e-9); // por defecto
    }

    @Test
    void retoExtra10_rutaNodo() {
        assertTrue(Ej307UserPreferences.rutaNodo(nodo).startsWith("/"));
        assertEquals("", Ej307UserPreferences.rutaNodo(null)); // caso límite
    }
}
