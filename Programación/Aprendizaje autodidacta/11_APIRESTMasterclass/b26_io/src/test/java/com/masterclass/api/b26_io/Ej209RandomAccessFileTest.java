package com.masterclass.api.b26_io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej209RandomAccessFileTest {

    @Test
    void leerEnOffset() {
        assertEquals(30, Ej209RandomAccessFile.leerEnOffset(new byte[]{10, 20, 30, 40}, 2));
    }

    @Test
    void actualizarEnSitio() {
        assertArrayEquals(new byte[]{1, 9, 3}, Ej209RandomAccessFile.actualizarEnSitio(new byte[]{1, 2, 3}, 1, 9));
    }

    @Test
    void retoExtra01_longitudDelFichero() {
        assertEquals(6L, Ej209RandomAccessFile.longitudDelFichero(new byte[6]));
    }

    @Test
    void retoExtra02_seekMasAllaExtiende() {
        assertEquals(11L, Ej209RandomAccessFile.seekMasAllaExtiende());
    }

    @Test
    void retoExtra03_setLengthTrunca() {
        assertEquals(4L, Ej209RandomAccessFile.setLengthTrunca());
    }

    @Test
    void retoExtra04_escribirYLeerInt() {
        assertEquals(123456, Ej209RandomAccessFile.escribirYLeerInt(123456));
    }

    @Test
    void retoExtra05_escribirYLeerUTF() {
        assertEquals("registro", Ej209RandomAccessFile.escribirYLeerUTF("registro"));
    }

    @Test
    void retoExtra06_leerInverso() {
        assertArrayEquals(new byte[]{3, 2, 1}, Ej209RandomAccessFile.leerInverso(new byte[]{1, 2, 3}));
    }

    @Test
    void retoExtra07_punteroAvanzaAlLeer() {
        assertEquals(3L, Ej209RandomAccessFile.punteroAvanzaAlLeer());
    }

    @Test
    void retoExtra08_leerRegistroFijo() {
        assertEquals(102, Ej209RandomAccessFile.leerRegistroFijo());
    }

    @Test
    void retoExtra09_modoSoloLecturaNoEscribe() {
        assertTrue(Ej209RandomAccessFile.modoSoloLecturaNoEscribe());
    }

    @Test
    void retoExtra10_escribirYLeerDouble() {
        assertEquals(3.14159, Ej209RandomAccessFile.escribirYLeerDouble(3.14159), 0.0);
    }

    @Test
    void retoExtra11_seekCeroRelee() {
        assertEquals(7, Ej209RandomAccessFile.seekCeroRelee(new byte[]{7, 8, 9}));
    }

    @Test
    void retoExtra12_accesoAleatorioDirecto() {
        assertEquals(50, Ej209RandomAccessFile.accesoAleatorioDirecto());
    }
}
