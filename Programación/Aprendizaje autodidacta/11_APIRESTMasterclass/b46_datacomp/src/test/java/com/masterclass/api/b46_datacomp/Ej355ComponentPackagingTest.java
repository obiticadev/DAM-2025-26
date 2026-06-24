package com.masterclass.api.b46_datacomp;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import static org.junit.jupiter.api.Assertions.*;

class Ej355ComponentPackagingTest {

    private static Manifest manifiestoCompleto() {
        Manifest m = new Manifest();
        Attributes a = m.getMainAttributes();
        a.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        a.put(Attributes.Name.IMPLEMENTATION_TITLE, "b46-datacomp");
        a.put(Attributes.Name.IMPLEMENTATION_VERSION, "1.0.0");
        a.put(Attributes.Name.IMPLEMENTATION_VENDOR, "Masterclass");
        a.put(new Attributes.Name("Automatic-Module-Name"), "com.masterclass.datacomp");
        return m;
    }

    @Test
    void leerMetadatos() {
        var meta = Ej355ComponentPackaging.leerMetadatos(manifiestoCompleto());
        assertEquals("b46-datacomp", meta.get("nombre"));
        assertEquals("1.0.0", meta.get("version"));
        assertEquals("Masterclass", meta.get("vendor"));
        assertEquals(java.util.Map.of(), Ej355ComponentPackaging.leerMetadatos(null)); // caso límite
    }

    @Test
    void validarManifiesto() {
        assertTrue(Ej355ComponentPackaging.validarManifiesto(manifiestoCompleto()));
        assertFalse(Ej355ComponentPackaging.validarManifiesto(new Manifest())); // vacío: sin título/versión
        assertFalse(Ej355ComponentPackaging.validarManifiesto(null));           // caso límite
    }

    @Test
    void testRetoExtra01_versionDesdeManifiesto() {
        assertEquals("1.0.0", Ej355ComponentPackaging.versionDesdeManifiesto(manifiestoCompleto()));
    }

    @Test
    void testRetoExtra02_vendorODefecto() {
        assertEquals("Desconocido", Ej355ComponentPackaging.vendorODefecto(new Manifest()));
        assertEquals("Masterclass", Ej355ComponentPackaging.vendorODefecto(manifiestoCompleto()));
    }

    @Test
    void testRetoExtra03_automaticModuleName() {
        assertEquals("com.masterclass.datacomp",
                Ej355ComponentPackaging.automaticModuleName(manifiestoCompleto()));
    }

    @Test
    void testRetoExtra04_esVersionSemver() {
        assertTrue(Ej355ComponentPackaging.esVersionSemver("1.0.0"));
        assertFalse(Ej355ComponentPackaging.esVersionSemver("1.0"));
    }

    @Test
    void testRetoExtra05_compararVersiones() {
        assertEquals(-1, Ej355ComponentPackaging.compararVersiones("1.2.0", "1.10.0"));
        assertEquals(0, Ej355ComponentPackaging.compararVersiones("2.0.0", "2.0.0"));
        assertEquals(1, Ej355ComponentPackaging.compararVersiones("2.0.1", "2.0.0"));
    }

    @Test
    void testRetoExtra06_rutaServiceProvider() {
        assertEquals("META-INF/services/java.lang.Runnable",
                Ej355ComponentPackaging.rutaServiceProvider(Runnable.class));
    }

    @Test
    void testRetoExtra07_contenidoServiceProvider() {
        assertEquals("a.B\nc.D", Ej355ComponentPackaging.contenidoServiceProvider(List.of("a.B", "c.D")));
    }

    @Test
    void testRetoExtra08_incrementarVersion() {
        assertEquals("1.3.0", Ej355ComponentPackaging.incrementarVersion("1.2.3", "minor"));
        assertEquals("2.0.0", Ej355ComponentPackaging.incrementarVersion("1.2.3", "major"));
    }

    @Test
    void testRetoExtra09_esCompatibleBinariamente() {
        assertTrue(Ej355ComponentPackaging.esCompatibleBinariamente("1.2.0", "1.9.9"));
        assertFalse(Ej355ComponentPackaging.esCompatibleBinariamente("1.2.0", "2.0.0"));
    }

    @Test
    void testRetoExtra10_moduleInfoRequires() {
        assertEquals("requires java.sql;\nrequires java.desktop;",
                Ej355ComponentPackaging.moduleInfoRequires(List.of("java.sql", "java.desktop")));
    }
}
