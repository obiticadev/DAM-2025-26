package com.masterclass.api.b39_fxdeploy;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej310VersioningAndUpdateTest {

    @Test
    void compararVersiones() {
        assertEquals(-1, Ej310VersioningAndUpdate.compararVersiones("1.0.0", "1.0.1"));
        assertEquals(1, Ej310VersioningAndUpdate.compararVersiones("2.0.0", "1.9.9"));
        assertEquals(0, Ej310VersioningAndUpdate.compararVersiones("1.2.3", "1.2.3")); // iguales
        assertEquals(0, Ej310VersioningAndUpdate.compararVersiones(null, "0.0.0")); // null = 0.0.0
    }

    @Test
    void hayActualizacion() {
        assertTrue(Ej310VersioningAndUpdate.hayActualizacion("1.0.0", "1.2.0"));
        assertFalse(Ej310VersioningAndUpdate.hayActualizacion("2.0.0", "1.9.0"));
        assertFalse(Ej310VersioningAndUpdate.hayActualizacion("1.2.0", "1.2.0")); // iguales: no hay
    }

    @Test
    void esSemverValida() {
        assertTrue(Ej310VersioningAndUpdate.esSemverValida("1.2.3"));
        assertFalse(Ej310VersioningAndUpdate.esSemverValida("1.2")); // faltan trozos
        assertFalse(Ej310VersioningAndUpdate.esSemverValida("1.2.beta")); // no numérico
    }

    @Test
    void retoExtra01_parsearVersion() {
        assertArrayEquals(new int[]{2, 4, 1}, Ej310VersioningAndUpdate.parsearVersion("2.4.1"));
        assertArrayEquals(new int[]{1, 2, 0}, Ej310VersioningAndUpdate.parsearVersion("1.2")); // patch a 0
        assertArrayEquals(new int[]{0, 0, 0}, Ej310VersioningAndUpdate.parsearVersion("")); // caso límite
    }

    @Test
    void retoExtra02_major() {
        assertEquals(2, Ej310VersioningAndUpdate.major("2.4.1"));
        assertEquals(0, Ej310VersioningAndUpdate.major(null)); // caso límite
    }

    @Test
    void retoExtra03_incrementarPatch() {
        assertEquals("1.2.4", Ej310VersioningAndUpdate.incrementarPatch("1.2.3"));
        assertEquals("1.2.10", Ej310VersioningAndUpdate.incrementarPatch("1.2.9")); // sin acarreo
    }

    @Test
    void retoExtra04_incrementarMinor() {
        assertEquals("1.3.0", Ej310VersioningAndUpdate.incrementarMinor("1.2.3")); // patch a 0
    }

    @Test
    void retoExtra05_incrementarMajor() {
        assertEquals("2.0.0", Ej310VersioningAndUpdate.incrementarMajor("1.2.3"));
        assertEquals("1.0.0", Ej310VersioningAndUpdate.incrementarMajor("0.9.9"));
    }

    @Test
    void retoExtra06_formatoConV() {
        assertEquals("v1.2.3", Ej310VersioningAndUpdate.formatoConV("1.2.3"));
        assertEquals("v1.2.3", Ej310VersioningAndUpdate.formatoConV("v1.2.3")); // no duplica la v
    }

    @Test
    void retoExtra07_quitarV() {
        assertEquals("1.2.3", Ej310VersioningAndUpdate.quitarV("v1.2.3"));
        assertEquals("1.2.3", Ej310VersioningAndUpdate.quitarV("1.2.3")); // sin v, igual
    }

    @Test
    void retoExtra08_ultimaVersion() {
        assertEquals("2.1.0", Ej310VersioningAndUpdate.ultimaVersion(List.of("1.0.0", "2.1.0", "1.9.9")));
        assertEquals("0.0.0", Ej310VersioningAndUpdate.ultimaVersion(null)); // caso límite
    }

    @Test
    void retoExtra09_esPrerelease() {
        assertTrue(Ej310VersioningAndUpdate.esPrerelease("1.2.0-beta"));
        assertFalse(Ej310VersioningAndUpdate.esPrerelease("1.2.0"));
    }

    @Test
    void retoExtra10_tipoDeCambio() {
        assertEquals("major", Ej310VersioningAndUpdate.tipoDeCambio("1.0.0", "2.0.0"));
        assertEquals("minor", Ej310VersioningAndUpdate.tipoDeCambio("1.0.0", "1.1.0"));
        assertEquals("patch", Ej310VersioningAndUpdate.tipoDeCambio("1.0.0", "1.0.1"));
        assertEquals("ninguno", Ej310VersioningAndUpdate.tipoDeCambio("1.0.0", "1.0.0")); // sin cambio
    }
}
