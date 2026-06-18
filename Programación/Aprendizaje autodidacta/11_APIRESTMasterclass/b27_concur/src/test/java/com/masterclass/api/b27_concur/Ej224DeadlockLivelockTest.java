package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 15, unit = TimeUnit.SECONDS)
class Ej224DeadlockLivelockTest {

    @Test
    void provocarDeadlock() {
        assertTrue(Ej224DeadlockLivelock.provocarDeadlock(), "los dos hilos deben quedar bloqueados");
    }

    @Test
    void evitarDeadlockOrdenando() {
        assertTrue(Ej224DeadlockLivelock.evitarDeadlockOrdenando(), "con orden consistente deben terminar");
    }

    @Test
    void retoExtra01_ordenGlobalEvita() {
        assertTrue(Ej224DeadlockLivelock.ordenGlobalEvita());
    }

    @Test
    void retoExtra02_tryLockEvitaDeadlock() {
        assertTrue(Ej224DeadlockLivelock.tryLockEvitaDeadlock());
    }

    @Test
    void retoExtra03_transferenciasSinDeadlock() {
        assertEquals(10L * 1000L, Ej224DeadlockLivelock.transferenciasSinDeadlock(10, 1000, 5000));
    }

    @Test
    void retoExtra04_unSoloLockNoProduceDeadlock() {
        assertTrue(Ej224DeadlockLivelock.unSoloLockNoProduceDeadlock());
    }

    @Test
    void retoExtra05_detectarConThreadMXBean() {
        assertTrue(Ej224DeadlockLivelock.detectarConThreadMXBean());
    }

    @Test
    void retoExtra06_ordenarPorIdentityHashCode() {
        assertTrue(Ej224DeadlockLivelock.ordenarPorIdentityHashCode());
    }

    @Test
    void retoExtra07_livelockTerminaConBackoff() {
        assertTrue(Ej224DeadlockLivelock.livelockTerminaConBackoff());
    }

    @Test
    void retoExtra08_lockInterruptiblyRompeEspera() {
        assertTrue(Ej224DeadlockLivelock.lockInterruptiblyRompeEspera());
    }

    @Test
    void retoExtra09_nestedSynchronizedMismoOrden() {
        assertTrue(Ej224DeadlockLivelock.nestedSynchronizedMismoOrden());
    }

    @Test
    void retoExtra10_deadlockConSynchronized() {
        assertTrue(Ej224DeadlockLivelock.deadlockConSynchronized());
    }
}
