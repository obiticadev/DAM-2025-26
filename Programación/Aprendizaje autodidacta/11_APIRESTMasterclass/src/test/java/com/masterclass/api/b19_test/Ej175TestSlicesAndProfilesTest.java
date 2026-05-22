package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej175TestSlicesAndProfilesTest {

    private final Map<String, String> props = Map.of("url", "prod-db", "test.url", "h2-mem");

    @Test
    void perfilGanaSobreBase() {
        assertEquals("h2-mem", Ej175TestSlicesAndProfiles.resolver(props, "test", "url"));
    }

    @Test
    void fallbackABase() {
        assertEquals("prod-db", Ej175TestSlicesAndProfiles.resolver(props, "prod", "url"));
    }

    @Test
    void noExiste() {
        assertNull(Ej175TestSlicesAndProfiles.resolver(props, "test", "ausente"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej175TestSlicesAndProfiles.resolver(null, "test", "url"));
    }

    @Test
    void testRetoExtra01_contienePropiedad() {
        // Verifica clave.
        assertTrue(Ej175TestSlicesAndProfiles.contienePropiedad(java.util.Map.of("a", "b"), "a"));
    }

    @Test
    void testRetoExtra02_obtenerPropiedad() {
        // Obtiene valor.
        assertEquals("b", Ej175TestSlicesAndProfiles.obtenerPropiedad(java.util.Map.of("a", "b"), "a"));
    }

    @Test
    void testRetoExtra03_esPerfilTest() {
        // Valida si es perfil test.
        assertTrue(Ej175TestSlicesAndProfiles.esPerfilTest("test"));
    }

    @Test
    void testRetoExtra04_obtenerClavePerfil() {
        // Formatea clave de perfil.
        assertEquals("test.url", Ej175TestSlicesAndProfiles.obtenerClavePerfil("test", "url"));
    }

    @Test
    void testRetoExtra05_obtenerTamanioProps() {
        // Obtiene total de propiedades.
        assertEquals(1, Ej175TestSlicesAndProfiles.obtenerTamanioProps(java.util.Map.of("a", "b")));
    }

    @Test
    void testRetoExtra06_esMapaVacio() {
        // Valida si esta vacio.
        assertTrue(Ej175TestSlicesAndProfiles.esMapaVacio(java.util.Map.of()));
    }

    @Test
    void testRetoExtra07_resolverConDefault() {
        // Resuelve con fallback por defecto.
        assertEquals("def", Ej175TestSlicesAndProfiles.resolverConDefault(java.util.Map.of(), "test", "url", "def"));
    }

    @Test
    void testRetoExtra08_esPropiedadActiva() {
        // Verifica valor efectivo.
        assertTrue(Ej175TestSlicesAndProfiles.esPropiedadActiva(java.util.Map.of("test.url", "h2"), "test", "url", "h2"));
    }

    @Test
    void testRetoExtra09_limpiarPropiedades() {
        // Crea una copia mutable.
        assertEquals(1, Ej175TestSlicesAndProfiles.limpiarPropiedades(java.util.Map.of("a", "b")).size());
    }

    @Test
    void testRetoExtra10_fusionarPropiedades() {
        // Combina propiedades.
        assertEquals(2, Ej175TestSlicesAndProfiles.fusionarPropiedades(java.util.Map.of("a", "1"), java.util.Map.of("b", "2")).size());
    }

}