package com.masterclass.api.b39_fxdeploy;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej308ModularJlinkTest {

    private static final List<String> MI = List.of(
            "module com.masterclass.app {",
            "    requires javafx.controls;",
            "    requires transitive java.sql;",
            "    exports com.masterclass.app.api;",
            "}");

    @Test
    void nombreModulo() {
        assertEquals("com.masterclass.app", Ej308ModularJlink.nombreModulo(MI));
        assertEquals("", Ej308ModularJlink.nombreModulo(null)); // caso límite
    }

    @Test
    void requires() {
        assertEquals(List.of("javafx.controls", "java.sql"), Ej308ModularJlink.requires(MI));
        assertEquals(List.of(), Ej308ModularJlink.requires(null)); // caso límite
    }

    @Test
    void exports() {
        assertEquals(List.of("com.masterclass.app.api"), Ej308ModularJlink.exports(MI));
        assertEquals(List.of(), Ej308ModularJlink.exports(null)); // caso límite
    }

    @Test
    void retoExtra01_numeroDeRequires() {
        assertEquals(2, Ej308ModularJlink.numeroDeRequires(MI));
        assertEquals(0, Ej308ModularJlink.numeroDeRequires(null)); // caso límite
    }

    @Test
    void retoExtra02_tieneRequires() {
        assertTrue(Ej308ModularJlink.tieneRequires(MI, "javafx.controls"));
        assertFalse(Ej308ModularJlink.tieneRequires(MI, "java.net.http")); // caso límite
    }

    @Test
    void retoExtra03_requiresTransitive() {
        assertEquals(List.of("java.sql"), Ej308ModularJlink.requiresTransitive(MI));
        assertEquals(List.of(), Ej308ModularJlink.requiresTransitive(null)); // caso límite
    }

    @Test
    void retoExtra04_numeroDeExports() {
        assertEquals(1, Ej308ModularJlink.numeroDeExports(MI));
        assertEquals(0, Ej308ModularJlink.numeroDeExports(null)); // caso límite
    }

    @Test
    void retoExtra05_nombreModuloValido() {
        assertTrue(Ej308ModularJlink.nombreModuloValido("com.masterclass.app"));
        assertFalse(Ej308ModularJlink.nombreModuloValido("com.app-ui")); // guion no válido
        assertFalse(Ej308ModularJlink.nombreModuloValido("2cosas")); // empieza por dígito
    }

    @Test
    void retoExtra06_argAddModules() {
        assertEquals("--add-modules java.base,javafx.controls",
                Ej308ModularJlink.argAddModules(List.of("java.base", "javafx.controls")));
        assertEquals("", Ej308ModularJlink.argAddModules(null)); // caso límite
    }

    @Test
    void retoExtra07_comandoJlink() {
        assertEquals("jlink --add-modules java.base --output runtime",
                Ej308ModularJlink.comandoJlink(List.of("java.base"), "runtime"));
        assertEquals("jlink --add-modules java.base --output runtime",
                Ej308ModularJlink.comandoJlink(List.of("java.base"), null)); // salida por defecto
    }

    @Test
    void retoExtra08_moduloAutomatico() {
        assertEquals("b39.fxdeploy", Ej308ModularJlink.moduloAutomatico("b39_fxdeploy"));
        assertEquals("my.lib", Ej308ModularJlink.moduloAutomatico("my-lib"));
    }

    @Test
    void retoExtra09_esModuloJdk() {
        assertTrue(Ej308ModularJlink.esModuloJdk("java.base"));
        assertTrue(Ej308ModularJlink.esModuloJdk("jdk.crypto.ec"));
        assertFalse(Ej308ModularJlink.esModuloJdk("javafx.controls")); // no es del JDK
    }

    @Test
    void retoExtra10_modulosDeTerceros() {
        assertEquals(List.of("javafx.controls"), Ej308ModularJlink.modulosDeTerceros(MI)); // java.sql es del JDK
    }
}
