package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej222SemaphoresTest {

    @Test
    void semaforoLimitaConcurrencia() {
        int max = Ej222Semaphores.semaforoLimitaConcurrencia(3, 50);
        assertTrue(max > 0 && max <= 3, "un semáforo de 3 no debe dejar más de 3 a la vez");
    }

    @Test
    void latchEsperaATodos() {
        assertTrue(Ej222Semaphores.latchEsperaATodos(10));
    }

    @Test
    void retoExtra01_semaforoComoMutex() {
        assertEquals(400_000, Ej222Semaphores.semaforoComoMutex(4, 100_000));
    }

    @Test
    void retoExtra02_latchEsDeUnSoloUso() {
        assertTrue(Ej222Semaphores.latchEsDeUnSoloUso());
    }

    @Test
    void retoExtra03_cyclicBarrierTodosLlegan() {
        assertEquals(5, Ej222Semaphores.cyclicBarrierTodosLlegan(5));
    }

    @Test
    void retoExtra04_tryAcquireSinPermisos() {
        assertTrue(Ej222Semaphores.tryAcquireSinPermisos());
    }

    @Test
    void retoExtra05_barrierAction() {
        assertEquals(1, Ej222Semaphores.barrierAction(4));
    }

    @Test
    void retoExtra06_latchComoPistolaDeSalida() {
        assertTrue(Ej222Semaphores.latchComoPistolaDeSalida());
    }

    @Test
    void retoExtra07_cyclicBarrierReutilizable() {
        assertEquals(3 * 4, Ej222Semaphores.cyclicBarrierReutilizable(3, 4));
    }

    @Test
    void retoExtra08_availablePermitsTrasAdquirir() {
        assertEquals(3, Ej222Semaphores.availablePermitsTrasAdquirir(5));
    }

    @Test
    void retoExtra09_latchAwaitConTimeoutExpira() {
        assertTrue(Ej222Semaphores.latchAwaitConTimeoutExpira());
    }

    @Test
    void retoExtra10_semaforoFair() {
        assertEquals(400_000, Ej222Semaphores.semaforoFair(4, 100_000));
    }
}
