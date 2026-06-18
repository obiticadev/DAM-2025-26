package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej221LocksTest {

    private static final int HILOS = 4;
    private static final int ITER = 100_000;
    private static final int TOTAL = HILOS * ITER;

    @Test
    void contadorConLock() {
        assertEquals(TOTAL, Ej221Locks.contadorConLock(HILOS, ITER));
    }

    @Test
    void tryLockEvitaBloqueo() {
        assertTrue(Ej221Locks.tryLockEvitaBloqueo());
    }

    @Test
    void retoExtra01_reentrante() {
        assertTrue(Ej221Locks.reentrante());
    }

    @Test
    void retoExtra02_readWriteLockLectoresYEscritor() {
        assertEquals(42, Ej221Locks.readWriteLockLectoresYEscritor(42));
    }

    @Test
    void retoExtra03_tryLockConTimeout() {
        assertTrue(Ej221Locks.tryLockConTimeout());
    }

    @Test
    void retoExtra04_conditionProductorConsumidor() {
        assertEquals(500, Ej221Locks.conditionProductorConsumidor(500));
    }

    @Test
    void retoExtra05_unlockEnFinally() {
        assertTrue(Ej221Locks.unlockEnFinally());
    }

    @Test
    void retoExtra06_isLockedRefleja() {
        assertTrue(Ej221Locks.isLockedRefleja());
    }

    @Test
    void retoExtra07_fairLock() {
        assertEquals(TOTAL, Ej221Locks.fairLock(HILOS, ITER));
    }

    @Test
    void retoExtra08_lockInterruptibly() {
        assertTrue(Ej221Locks.lockInterruptibly());
    }

    @Test
    void retoExtra09_getHoldCount() {
        assertEquals(3, Ej221Locks.getHoldCount(3));
    }

    @Test
    void retoExtra10_conditionAwaitSignal() {
        assertTrue(Ej221Locks.conditionAwaitSignal());
    }
}
