package com.masterclass.api.b26_io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej208CharStreamsTest {

    @Test
    void leerLineas() {
        assertEquals(List.of("uno", "dos", "tres"), Ej208CharStreams.leerLineas("uno\ndos\ntres", "UTF-8"));
    }

    @Test
    void escribirYContarPalabras() {
        assertEquals(3, Ej208CharStreams.escribirYContarPalabras("hola mundo cruel"));
    }

    @Test
    void retoExtra01_contarLineas() {
        assertEquals(3, Ej208CharStreams.contarLineas("a\nb\nc"));
    }

    @Test
    void retoExtra02_mojibakeAlCambiarCharset() {
        assertTrue(Ej208CharStreams.mojibakeAlCambiarCharset());
    }

    @Test
    void retoExtra03_utf8RespetaAcentos() {
        assertEquals("ñandú café", Ej208CharStreams.utf8RespetaAcentos("ñandú café"));
    }

    @Test
    void retoExtra04_leerTodoComoString() {
        assertEquals("linea-unica", Ej208CharStreams.leerTodoComoString("linea-unica"));
    }

    @Test
    void retoExtra05_contarLineasConStream() {
        assertEquals(3L, Ej208CharStreams.contarLineasConStream("x\ny\nz"));
    }

    @Test
    void retoExtra06_printWriterEscribeLineas() {
        assertEquals(2, Ej208CharStreams.printWriterEscribeLineas());
    }

    @Test
    void retoExtra07_charsetPorDefecto() {
        assertEquals("UTF-8", Ej208CharStreams.charsetPorDefecto());
    }

    @Test
    void retoExtra08_bytesEnIsoLatin1() {
        assertEquals(3, Ej208CharStreams.bytesEnIsoLatin1("abc"));
    }

    @Test
    void retoExtra09_utf8SinBom() {
        assertTrue(Ej208CharStreams.utf8SinBom());
    }

    @Test
    void retoExtra10_separadorDeLineaNoVacio() {
        assertTrue(Ej208CharStreams.separadorDeLineaNoVacio());
    }

    @Test
    void retoExtra11_leerCaracterACaracter() {
        assertEquals(5, Ej208CharStreams.leerCaracterACaracter("abcde"));
    }

    @Test
    void retoExtra12_roundTripUtf8Idempotente() {
        assertTrue(Ej208CharStreams.roundTripUtf8Idempotente());
    }
}
