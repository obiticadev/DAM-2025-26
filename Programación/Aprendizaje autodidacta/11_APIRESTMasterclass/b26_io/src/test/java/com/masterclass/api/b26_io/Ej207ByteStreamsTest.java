package com.masterclass.api.b26_io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej207ByteStreamsTest {

    @Test
    void copiarBytes() {
        assertArrayEquals(new byte[]{1, 2, 3}, Ej207ByteStreams.copiarBytes(new byte[]{1, 2, 3}));
    }

    @Test
    void contarBytes() {
        assertEquals(100L, Ej207ByteStreams.contarBytes(new byte[100]));
    }

    @Test
    void retoExtra01_copiarDetectandoEof() {
        assertEquals(5L, Ej207ByteStreams.copiarDetectandoEof(new byte[]{1, 2, 3, 4, 5}));
    }

    @Test
    void retoExtra02_copiarConBuffer1024() {
        assertEquals(3000L, Ej207ByteStreams.copiarConBuffer1024(new byte[3000]));
    }

    @Test
    void retoExtra03_volcarAMemoria() {
        assertEquals(7, Ej207ByteStreams.volcarAMemoria(new byte[7]));
    }

    @Test
    void retoExtra04_appendDuplicaTamano() {
        assertEquals(20L, Ej207ByteStreams.appendDuplicaTamano(new byte[10]));
    }

    @Test
    void retoExtra05_dosFicherosIgualesPorContenido() {
        assertTrue(Ej207ByteStreams.dosFicherosIgualesPorContenido(new byte[]{9, 8, 7}));
    }

    @Test
    void retoExtra06_escribirYLeerUnByte() {
        assertEquals(65, Ej207ByteStreams.escribirYLeerUnByte(65));
    }

    @Test
    void retoExtra07_copiarConTransferTo() {
        assertEquals(50L, Ej207ByteStreams.copiarConTransferTo(new byte[50]));
    }

    @Test
    void retoExtra08_copiarConBufferedStreams() {
        assertArrayEquals(new byte[]{4, 5, 6}, Ej207ByteStreams.copiarConBufferedStreams(new byte[]{4, 5, 6}));
    }

    @Test
    void retoExtra09_ficheroVacioTieneCeroBytes() {
        assertEquals(0L, Ej207ByteStreams.ficheroVacioTieneCeroBytes());
    }

    @Test
    void retoExtra10_flushAseguraEscritura() {
        assertArrayEquals(new byte[]{1, 1, 1}, Ej207ByteStreams.flushAseguraEscritura(new byte[]{1, 1, 1}));
    }

    @Test
    void retoExtra11_leerTrasEofDevuelveMenosUno() {
        assertTrue(Ej207ByteStreams.leerTrasEofDevuelveMenosUno(new byte[]{1, 2}));
    }

    @Test
    void retoExtra12_tryWithResourcesCierraStream() {
        assertTrue(Ej207ByteStreams.tryWithResourcesCierraStream(new byte[]{1}));
    }
}
