package com.masterclass.api.b26_io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej211Nio2PathFilesTest {

    @Test
    void crearYLeerTexto() {
        assertEquals("hola nio", Ej211Nio2PathFiles.crearYLeerTexto("hola nio"));
    }

    @Test
    void copiarYContar() {
        assertEquals(5L, Ej211Nio2PathFiles.copiarYContar("12345"));
    }

    @Test
    void retoExtra01_existsTrasCrear() {
        assertTrue(Ej211Nio2PathFiles.existsTrasCrear());
    }

    @Test
    void retoExtra02_noExisteTrasBorrar() {
        assertTrue(Ej211Nio2PathFiles.noExisteTrasBorrar());
    }

    @Test
    void retoExtra03_moverFichero() {
        assertTrue(Ej211Nio2PathFiles.moverFichero());
    }

    @Test
    void retoExtra04_copiarReplaceExisting() {
        assertEquals("nuevo", Ej211Nio2PathFiles.copiarReplaceExisting());
    }

    @Test
    void retoExtra05_crearDirectorio() {
        assertTrue(Ej211Nio2PathFiles.crearDirectorio());
    }

    @Test
    void retoExtra06_tamanoFichero() {
        assertEquals(6L, Ej211Nio2PathFiles.tamanoFichero());
    }

    @Test
    void retoExtra07_esRegularFile() {
        assertTrue(Ej211Nio2PathFiles.esRegularFile());
    }

    @Test
    void retoExtra08_resolveConstruyeRuta() {
        assertTrue(Ej211Nio2PathFiles.resolveConstruyeRuta());
    }

    @Test
    void retoExtra09_getFileName() {
        assertEquals("informe.pdf", Ej211Nio2PathFiles.getFileName());
    }

    @Test
    void retoExtra10_writeReadStringUtf8() {
        assertEquals("ñandú café", Ej211Nio2PathFiles.writeReadStringUtf8("ñandú café"));
    }

    @Test
    void retoExtra11_notExistsParaRutaInventada() {
        assertTrue(Ej211Nio2PathFiles.notExistsParaRutaInventada());
    }

    @Test
    void retoExtra12_crearDirectoriosAnidados() {
        assertTrue(Ej211Nio2PathFiles.crearDirectoriosAnidados());
    }
}
