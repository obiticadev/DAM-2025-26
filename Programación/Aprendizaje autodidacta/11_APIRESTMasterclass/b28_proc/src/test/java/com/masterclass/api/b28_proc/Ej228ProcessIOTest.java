package com.masterclass.api.b28_proc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 60, unit = TimeUnit.SECONDS)
class Ej228ProcessIOTest {

    @Test
    void ejecutarYLeerSalida() {
        assertEquals("hola", Ej228ProcessIO.ejecutarYLeerSalida("hola"));
    }

    @Test
    void leerStderr() {
        assertEquals("fallo", Ej228ProcessIO.leerStderr("fallo"));
    }

    @Test
    void retoExtra01_capturarSuma() {
        assertEquals("5", Ej228ProcessIO.capturarSuma());
    }

    @Test
    void retoExtra02_stdoutYStderrSeparados() {
        assertTrue(Ej228ProcessIO.stdoutYStderrSeparados());
    }

    @Test
    void retoExtra03_redirectErrorStreamMezcla() {
        assertEquals(2, Ej228ProcessIO.redirectErrorStreamMezcla());
    }

    @Test
    void retoExtra04_leerConReadAllBytes() {
        assertTrue(Ej228ProcessIO.leerConReadAllBytes());
    }

    @Test
    void retoExtra05_stdoutVacioSiSoloStderr() {
        assertTrue(Ej228ProcessIO.stdoutVacioSiSoloStderr());
    }

    @Test
    void retoExtra06_inheritIONoCuelga() {
        assertTrue(Ej228ProcessIO.inheritIONoCuelga());
    }

    @Test
    void retoExtra07_pipeConBufferedReader() {
        assertEquals("pipe", Ej228ProcessIO.pipeConBufferedReader());
    }

    @Test
    void retoExtra08_leerSalidaYLuegoWaitFor() {
        assertEquals(0, Ej228ProcessIO.leerSalidaYLuegoWaitFor());
    }

    @Test
    void retoExtra09_redirectOutputAFichero() {
        assertTrue(Ej228ProcessIO.redirectOutputAFichero());
    }

    @Test
    void retoExtra10_lecturaConInputStreamReaderUtf8() {
        assertEquals("dato", Ej228ProcessIO.lecturaConInputStreamReaderUtf8());
    }
}
