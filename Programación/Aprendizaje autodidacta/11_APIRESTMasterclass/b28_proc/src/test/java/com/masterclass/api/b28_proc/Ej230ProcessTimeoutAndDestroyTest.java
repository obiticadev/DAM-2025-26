package com.masterclass.api.b28_proc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 60, unit = TimeUnit.SECONDS)
class Ej230ProcessTimeoutAndDestroyTest {

    @Test
    void terminaDentroDelPlazo() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.terminaDentroDelPlazo(100, 5000));
    }

    @Test
    void matarProcesoColgado() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.matarProcesoColgado());
    }

    @Test
    void retoExtra01_waitForTimeoutFalseSiNoTermina() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.waitForTimeoutFalseSiNoTermina());
    }

    @Test
    void retoExtra02_destroyTerminaProceso() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.destroyTerminaProceso());
    }

    @Test
    void retoExtra03_destroyForciblyTermina() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.destroyForciblyTermina());
    }

    @Test
    void retoExtra04_destroyProduceCodigoNoCero() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.destroyProduceCodigoNoCero());
    }

    @Test
    void retoExtra05_isAliveFalseTrasTerminar() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.isAliveFalseTrasTerminar());
    }

    @Test
    void retoExtra06_waitForConUnidadTrue() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.waitForConUnidadTrue());
    }

    @Test
    void retoExtra07_procesoLargoSigueVivo() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.procesoLargoSigueVivo());
    }

    @Test
    void retoExtra08_destroyEsIdempotente() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.destroyEsIdempotente());
    }

    @Test
    void retoExtra09_timeoutNoMataElProceso() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.timeoutNoMataElProceso());
    }

    @Test
    void retoExtra10_onExitCompletaAlTerminar() {
        assertTrue(Ej230ProcessTimeoutAndDestroy.onExitCompletaAlTerminar());
    }
}
