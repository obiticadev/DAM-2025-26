package com.masterclass.api.b39_fxdeploy;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej305JavadocAndManifestTest {

    private static final List<String> MANIFEST = List.of(
            "Manifest-Version: 1.0",
            "Main-Class: com.masterclass.api.App",
            "Implementation-Title: Mi App",
            "Implementation-Vendor: ACME",
            "Implementation-Version: 2.4.1",
            "");

    @Test
    void parsearManifest() {
        Map<String, String> m = Ej305JavadocAndManifest.parsearManifest(MANIFEST);
        assertEquals(5, m.size()); // la línea en blanco se ignora
        assertEquals("com.masterclass.api.App", m.get("Main-Class"));
        assertTrue(Ej305JavadocAndManifest.parsearManifest(null).isEmpty()); // caso límite
    }

    @Test
    void valorDe() {
        Map<String, String> m = Ej305JavadocAndManifest.parsearManifest(MANIFEST);
        assertEquals("com.masterclass.api.App", Ej305JavadocAndManifest.valorDe(m, "Main-Class"));
        assertEquals("", Ej305JavadocAndManifest.valorDe(m, "No-Existe")); // caso límite
    }

    @Test
    void versionImplementacion() {
        Map<String, String> m = Ej305JavadocAndManifest.parsearManifest(MANIFEST);
        assertEquals("2.4.1", Ej305JavadocAndManifest.versionImplementacion(m));
        assertEquals("0.0.0", Ej305JavadocAndManifest.versionImplementacion(Map.of())); // sin atributo
    }

    @Test
    void retoExtra01_tituloImplementacion() {
        Map<String, String> m = Ej305JavadocAndManifest.parsearManifest(MANIFEST);
        assertEquals("Mi App", Ej305JavadocAndManifest.tituloImplementacion(m));
        assertEquals("", Ej305JavadocAndManifest.tituloImplementacion(Map.of())); // caso límite
    }

    @Test
    void retoExtra02_vendorImplementacion() {
        Map<String, String> m = Ej305JavadocAndManifest.parsearManifest(MANIFEST);
        assertEquals("ACME", Ej305JavadocAndManifest.vendorImplementacion(m));
    }

    @Test
    void retoExtra03_claseMain() {
        Map<String, String> m = Ej305JavadocAndManifest.parsearManifest(MANIFEST);
        assertEquals("com.masterclass.api.App", Ej305JavadocAndManifest.claseMain(m));
    }

    @Test
    void retoExtra04_tieneAtributo() {
        Map<String, String> m = Ej305JavadocAndManifest.parsearManifest(MANIFEST);
        assertTrue(Ej305JavadocAndManifest.tieneAtributo(m, "Main-Class"));
        assertFalse(Ej305JavadocAndManifest.tieneAtributo(m, "No-Existe")); // caso límite
    }

    @Test
    void retoExtra05_numeroDeAtributos() {
        Map<String, String> m = Ej305JavadocAndManifest.parsearManifest(MANIFEST);
        assertEquals(5, Ej305JavadocAndManifest.numeroDeAtributos(m));
        assertEquals(0, Ej305JavadocAndManifest.numeroDeAtributos(null)); // caso límite
    }

    @Test
    void retoExtra06_lineaManifest() {
        assertEquals("Main-Class: com.App", Ej305JavadocAndManifest.lineaManifest("Main-Class", "com.App"));
        assertEquals("", Ej305JavadocAndManifest.lineaManifest(null, "x")); // caso límite
    }

    @Test
    void retoExtra07_classpath() {
        Map<String, String> m = Ej305JavadocAndManifest.manifestDe("Class-Path", "lib/a.jar lib/b.jar");
        assertEquals(List.of("lib/a.jar", "lib/b.jar"), Ej305JavadocAndManifest.classpath(m));
        assertEquals(List.of(), Ej305JavadocAndManifest.classpath(Map.of())); // sin Class-Path
    }

    @Test
    void retoExtra08_porcentajeDocumentado() {
        assertEquals(70, Ej305JavadocAndManifest.porcentajeDocumentado(7, 10));
        assertEquals(33, Ej305JavadocAndManifest.porcentajeDocumentado(1, 3)); // trunca
        assertEquals(0, Ej305JavadocAndManifest.porcentajeDocumentado(0, 0)); // caso límite
    }

    @Test
    void retoExtra09_nombreJar() {
        assertEquals("b39_fxdeploy-1.0.0.jar", Ej305JavadocAndManifest.nombreJar("b39_fxdeploy", "1.0.0"));
        assertEquals("app.jar", Ej305JavadocAndManifest.nombreJar(null, null)); // caso límite
    }

    @Test
    void retoExtra10_esVersionValida() {
        assertTrue(Ej305JavadocAndManifest.esVersionValida("1.0.0"));
        assertFalse(Ej305JavadocAndManifest.esVersionValida("1.0")); // faltan trozos
        assertFalse(Ej305JavadocAndManifest.esVersionValida("1.x.0")); // no es número
    }
}
