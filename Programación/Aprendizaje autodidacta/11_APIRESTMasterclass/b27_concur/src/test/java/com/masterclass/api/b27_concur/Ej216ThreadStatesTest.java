package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 5, unit = TimeUnit.SECONDS)
class Ej216ThreadStatesTest {

    @Test
    void estadoTrasJoin() {
        assertEquals(Thread.State.TERMINATED, Ej216ThreadStates.estadoTrasJoin());
    }

    @Test
    void sleepDuraAlMenos() {
        assertTrue(Ej216ThreadStates.sleepDuraAlMenos(50));
    }

    @Test
    void retoExtra01_estadoNew() {
        assertEquals(Thread.State.NEW, Ej216ThreadStates.estadoNew());
    }

    @Test
    void retoExtra02_estadoRunnableDelMain() {
        assertEquals(Thread.State.RUNNABLE, Ej216ThreadStates.estadoRunnableDelMain());
    }

    @Test
    void retoExtra03_estadoTimedWaiting() {
        assertEquals(Thread.State.TIMED_WAITING, Ej216ThreadStates.estadoTimedWaiting());
    }

    @Test
    void retoExtra04_estadoWaiting() {
        assertEquals(Thread.State.WAITING, Ej216ThreadStates.estadoWaiting());
    }

    @Test
    void retoExtra05_interrupcionDuranteSleep() {
        assertTrue(Ej216ThreadStates.interrupcionDuranteSleep());
    }

    @Test
    void retoExtra06_flagSeLimpiaTrasInterruptedException() {
        assertTrue(Ej216ThreadStates.flagSeLimpiaTrasInterruptedException());
    }

    @Test
    void retoExtra07_interrumpirAntesDeSleepLanzaInmediato() {
        assertTrue(Ej216ThreadStates.interrumpirAntesDeSleepLanzaInmediato());
    }

    @Test
    void retoExtra08_yieldNoLanza() {
        assertTrue(Ej216ThreadStates.yieldNoLanza());
    }

    @Test
    void retoExtra09_joinConTimeoutRetornaAunqueVivo() {
        assertTrue(Ej216ThreadStates.joinConTimeoutRetornaAunqueVivo());
    }

    @Test
    void retoExtra10_reiniciarHiloLanzaIllegalThreadState() {
        assertTrue(Ej216ThreadStates.reiniciarHiloLanzaIllegalThreadState());
    }
}
