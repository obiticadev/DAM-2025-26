package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 5, unit = TimeUnit.SECONDS)
class Ej226ThreadPriorityAndContextTest {

    @Test
    void prioridadSeAlmacena() {
        assertEquals(7, Ej226ThreadPriorityAndContext.prioridadSeAlmacena(7));
    }

    @Test
    void threadLocalAisladoPorHilo() {
        assertTrue(Ej226ThreadPriorityAndContext.threadLocalAisladoPorHilo());
    }

    @Test
    void retoExtra01_esDaemonConfigurable() {
        assertTrue(Ej226ThreadPriorityAndContext.esDaemonConfigurable());
    }

    @Test
    void retoExtra02_threadLocalValorInicial() {
        assertEquals(99, Ej226ThreadPriorityAndContext.threadLocalValorInicial(99));
    }

    @Test
    void retoExtra03_nombrePersonalizado() {
        assertEquals("worker-7", Ej226ThreadPriorityAndContext.nombrePersonalizado("worker-7"));
    }

    @Test
    void retoExtra04_prioridadMaxEsDiez() {
        assertEquals(10, Ej226ThreadPriorityAndContext.prioridadMaxEsDiez());
    }

    @Test
    void retoExtra05_uncaughtExceptionHandlerSeInvoca() {
        assertTrue(Ej226ThreadPriorityAndContext.uncaughtExceptionHandlerSeInvoca());
    }

    @Test
    void retoExtra06_inheritableThreadLocalHereda() {
        assertEquals(55, Ej226ThreadPriorityAndContext.inheritableThreadLocalHereda(55));
    }

    @Test
    void retoExtra07_threadLocalRemoveVuelveAInicial() {
        assertEquals(5, Ej226ThreadPriorityAndContext.threadLocalRemoveVuelveAInicial(5));
    }

    @Test
    void retoExtra08_threadFactoryPersonalizada() {
        assertTrue(Ej226ThreadPriorityAndContext.threadFactoryPersonalizada());
    }

    @Test
    void retoExtra09_threadIdEsPositivo() {
        assertTrue(Ej226ThreadPriorityAndContext.threadIdEsPositivo() > 0);
    }

    @Test
    void retoExtra10_numeroDeProcesadores() {
        assertTrue(Ej226ThreadPriorityAndContext.numeroDeProcesadores() >= 1);
    }
}
