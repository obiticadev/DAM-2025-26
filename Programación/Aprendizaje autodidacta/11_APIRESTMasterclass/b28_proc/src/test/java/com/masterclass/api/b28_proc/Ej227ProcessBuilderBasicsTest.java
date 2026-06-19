package com.masterclass.api.b28_proc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 60, unit = TimeUnit.SECONDS)
class Ej227ProcessBuilderBasicsTest {

    @Test
    void ejecutarYObtenerCodigo() {
        assertEquals(0, Ej227ProcessBuilderBasics.ejecutarYObtenerCodigo(0));
        assertEquals(3, Ej227ProcessBuilderBasics.ejecutarYObtenerCodigo(3));
    }

    @Test
    void procesoYaNoEstaVivoTrasWaitFor() {
        assertTrue(Ej227ProcessBuilderBasics.procesoYaNoEstaVivoTrasWaitFor());
    }

    @Test
    void retoExtra01_exitCeroEsExito() {
        assertTrue(Ej227ProcessBuilderBasics.exitCeroEsExito());
    }

    @Test
    void retoExtra02_exitCincoSePropaga() {
        assertEquals(5, Ej227ProcessBuilderBasics.exitCincoSePropaga());
    }

    @Test
    void retoExtra03_pidEsPositivo() {
        assertTrue(Ej227ProcessBuilderBasics.pidEsPositivo());
    }

    @Test
    void retoExtra04_isAliveTrasArrancar() {
        assertTrue(Ej227ProcessBuilderBasics.isAliveTrasArrancar());
    }

    @Test
    void retoExtra05_comandoInexistenteLanzaIOException() {
        assertTrue(Ej227ProcessBuilderBasics.comandoInexistenteLanzaIOException());
    }

    @Test
    void retoExtra06_commandDevuelveElComando() {
        assertTrue(Ej227ProcessBuilderBasics.commandDevuelveElComando());
    }

    @Test
    void retoExtra07_exitValueEnProcesoVivoLanza() {
        assertTrue(Ej227ProcessBuilderBasics.exitValueEnProcesoVivoLanza());
    }

    @Test
    void retoExtra08_dosProcesosIndependientes() {
        assertTrue(Ej227ProcessBuilderBasics.dosProcesosIndependientes());
    }

    @Test
    void retoExtra09_javaBinExiste() {
        assertTrue(Ej227ProcessBuilderBasics.javaBinExiste());
    }

    @Test
    void retoExtra10_waitForDevuelveElCodigo() {
        assertEquals(7, Ej227ProcessBuilderBasics.waitForDevuelveElCodigo());
    }
}
