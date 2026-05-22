package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej181StructuredLoggingMdcTest {

    @Test
    void serializaConMdc() {
        String json = Ej181StructuredLoggingMdc.formatear("info", "hola", Map.of("traceId", "abc"));
        assertTrue(json.contains("\"level\":\"INFO\""), json);
        assertTrue(json.contains("\"message\":\"hola\""), json);
        assertTrue(json.contains("\"traceId\":\"abc\""), json);
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej181StructuredLoggingMdc.formatear(" ", "m", Map.of()));
        assertThrows(IllegalArgumentException.class,
                () -> Ej181StructuredLoggingMdc.formatear("INFO", null, Map.of()));
        assertThrows(IllegalArgumentException.class,
                () -> Ej181StructuredLoggingMdc.formatear("INFO", "m", null));
    }

    @Test
    void testRetoExtra01_esNivelValido() {
        // Valida si el nivel no es blanco.
        assertTrue(Ej181StructuredLoggingMdc.esNivelValido("INFO"));
    }

    @Test
    void testRetoExtra02_esMensajeValido() {
        // Valida mensaje no nulo.
        assertTrue(Ej181StructuredLoggingMdc.esMensajeValido("ok"));
    }

    @Test
    void testRetoExtra03_esMdcValido() {
        // Valida mdc no nulo.
        assertTrue(Ej181StructuredLoggingMdc.esMdcValido(java.util.Map.of()));
    }

    @Test
    void testRetoExtra04_normalizarNivel() {
        // Limpia y capitaliza nivel.
        assertEquals("INFO", Ej181StructuredLoggingMdc.normalizarNivel(" info "));
    }

    @Test
    void testRetoExtra05_crearMdcMap() {
        // Crea un LinkedHashMap para preservar orden.
        assertNotNull(Ej181StructuredLoggingMdc.crearMdcMap());
    }

    @Test
    void testRetoExtra06_insertarMdc() {
        // Inserta clave-valor en mdc.
        assertEquals(1, Ej181StructuredLoggingMdc.insertarMdc(new java.util.HashMap<>(), "k", "v").size());
    }

    @Test
    void testRetoExtra07_limpiarMdc() {
        // Limpia todas las claves del mdc.
        assertEquals(0, Ej181StructuredLoggingMdc.limpiarMdc(new java.util.HashMap<>(java.util.Map.of("a", "b"))).size());
    }

    @Test
    void testRetoExtra08_contieneTrazaMdc() {
        // Verifica si contiene traceId.
        assertTrue(Ej181StructuredLoggingMdc.contieneTrazaMdc(java.util.Map.of("traceId", "123")));
    }

    @Test
    void testRetoExtra09_serializarVacio() {
        // Serializa un log vacio.
        assertEquals("{}", Ej181StructuredLoggingMdc.serializarVacio());
    }

    @Test
    void testRetoExtra10_obtenerDeJson() {
        // Obtiene un campo textual del JSON.
        assertEquals("INFO", Ej181StructuredLoggingMdc.obtenerDeJson("{\"level\":\"INFO\"}", "level"));
    }

}