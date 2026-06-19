package com.masterclass.api.b28_proc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 60, unit = TimeUnit.SECONDS)
class Ej229ProcessPipesIPCTest {

    @Test
    void enviarYRecibir() {
        assertEquals("HOLA", Ej229ProcessPipesIPC.enviarYRecibir("hola"));
    }

    @Test
    void contarLineasEnviadas() {
        assertEquals(3, Ej229ProcessPipesIPC.contarLineasEnviadas(List.of("a", "b", "c")));
    }

    @Test
    void retoExtra01_enviarLineaYRecibirMayusculas() {
        assertEquals("ABC", Ej229ProcessPipesIPC.enviarLineaYRecibirMayusculas());
    }

    @Test
    void retoExtra02_cerrarStdinSenialaEof() {
        assertTrue(Ej229ProcessPipesIPC.cerrarStdinSenialaEof());
    }

    @Test
    void retoExtra03_ipcConPrintWriter() {
        assertEquals("DATO", Ej229ProcessPipesIPC.ipcConPrintWriter());
    }

    @Test
    void retoExtra04_enviarCincoLineas() {
        assertEquals(5, Ej229ProcessPipesIPC.enviarCincoLineas());
    }

    @Test
    void retoExtra05_lineaVaciaCuentaComoLinea() {
        assertEquals(1, Ej229ProcessPipesIPC.lineaVaciaCuentaComoLinea());
    }

    @Test
    void retoExtra06_roundTripUpper() {
        assertEquals("TEST", Ej229ProcessPipesIPC.roundTripUpper());
    }

    @Test
    void retoExtra07_escribirBytesEnStdin() {
        assertEquals("BYTES", Ej229ProcessPipesIPC.escribirBytesEnStdin());
    }

    @Test
    void retoExtra08_intercambioTerminaConCero() {
        assertEquals(0, Ej229ProcessPipesIPC.intercambioTerminaConCero());
    }

    @Test
    void retoExtra09_ipcLineaLarga() {
        assertEquals(1000, Ej229ProcessPipesIPC.ipcLineaLarga());
    }

    @Test
    void retoExtra10_contarTresLineas() {
        assertEquals(3, Ej229ProcessPipesIPC.contarTresLineas());
    }
}
