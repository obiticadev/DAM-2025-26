package com.masterclass.api.b26_io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej212Nio2ReadWriteWalkTest {

    @Test
    void escribirYLeerLineas() {
        assertEquals(List.of("a", "b", "c"), Ej212Nio2ReadWriteWalk.escribirYLeerLineas(List.of("a", "b", "c")));
    }

    @Test
    void contarFicherosEnArbol() {
        assertEquals(5L, Ej212Nio2ReadWriteWalk.contarFicherosEnArbol(5));
    }

    @Test
    void retoExtra01_readAllBytes() {
        assertEquals(6, Ej212Nio2ReadWriteWalk.readAllBytes());
    }

    @Test
    void retoExtra02_contarConFilesLines() {
        assertEquals(3L, Ej212Nio2ReadWriteWalk.contarConFilesLines());
    }

    @Test
    void retoExtra03_walkProfundidadUno() {
        assertEquals(4L, Ej212Nio2ReadWriteWalk.walkProfundidadUno());
    }

    @Test
    void retoExtra04_findPorExtension() {
        assertEquals(2L, Ej212Nio2ReadWriteWalk.findPorExtension());
    }

    @Test
    void retoExtra05_listDirectorio() {
        assertEquals(3L, Ej212Nio2ReadWriteWalk.listDirectorio());
    }

    @Test
    void retoExtra06_basicFileAttributes() {
        assertTrue(Ej212Nio2ReadWriteWalk.basicFileAttributes());
    }

    @Test
    void retoExtra07_lastModifiedTimeNoNulo() {
        assertTrue(Ej212Nio2ReadWriteWalk.lastModifiedTimeNoNulo());
    }

    @Test
    void retoExtra08_mismatchDetectaDiferencia() {
        assertTrue(Ej212Nio2ReadWriteWalk.mismatchDetectaDiferencia());
    }

    @Test
    void retoExtra09_writeAppendLineas() {
        assertEquals(4L, Ej212Nio2ReadWriteWalk.writeAppendLineas());
    }

    @Test
    void retoExtra10_walkSoloDirectorios() {
        assertEquals(3L, Ej212Nio2ReadWriteWalk.walkSoloDirectorios());
    }

    @Test
    void retoExtra11_newBufferedReaderLines() {
        assertEquals(3L, Ej212Nio2ReadWriteWalk.newBufferedReaderLines());
    }

    @Test
    void retoExtra12_creationTimeNoNulo() {
        assertTrue(Ej212Nio2ReadWriteWalk.creationTimeNoNulo());
    }
}
