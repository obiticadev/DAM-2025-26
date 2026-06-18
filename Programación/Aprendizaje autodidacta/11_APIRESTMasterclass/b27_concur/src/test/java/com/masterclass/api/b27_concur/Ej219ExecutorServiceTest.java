package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej219ExecutorServiceTest {

    @Test
    void contarTareasEjecutadas() {
        assertEquals(1000, Ej219ExecutorService.contarTareasEjecutadas(1000, 4));
    }

    @Test
    void sumaConFutures() {
        assertEquals(5050L, Ej219ExecutorService.sumaConFutures(1, 100, 4));
        assertEquals(500500L, Ej219ExecutorService.sumaConFutures(1, 1000, 8));
    }

    @Test
    void retoExtra01_submitCallableDevuelve() {
        assertEquals(42, Ej219ExecutorService.submitCallableDevuelve(42));
    }

    @Test
    void retoExtra02_invokeAllSuma() {
        assertEquals(55L, Ej219ExecutorService.invokeAllSuma(10));
    }

    @Test
    void retoExtra03_invokeAnyDevuelveUno() {
        assertEquals(7, Ej219ExecutorService.invokeAnyDevuelveUno(7));
    }

    @Test
    void retoExtra04_awaitTerminationTrasShutdown() {
        assertTrue(Ej219ExecutorService.awaitTerminationTrasShutdown(100));
    }

    @Test
    void retoExtra05_singleThreadSerializa() {
        assertEquals(500, Ej219ExecutorService.singleThreadSerializa(500));
    }

    @Test
    void retoExtra06_cachedPoolCuenta() {
        assertEquals(500, Ej219ExecutorService.cachedPoolCuenta(500));
    }

    @Test
    void retoExtra07_rejectedExecutionTrasShutdown() {
        assertTrue(Ej219ExecutorService.rejectedExecutionTrasShutdown());
    }

    @Test
    void retoExtra08_scheduledUnaVez() {
        assertEquals(99L, Ej219ExecutorService.scheduledUnaVez(99));
    }

    @Test
    void retoExtra09_reutilizaHilosDelPool() {
        int usados = Ej219ExecutorService.reutilizaHilosDelPool(1000, 3);
        assertTrue(usados > 0 && usados <= 3, "un pool de 3 no debe usar más de 3 hilos");
    }

    @Test
    void retoExtra10_futureGetPropagaExecutionException() {
        assertTrue(Ej219ExecutorService.futureGetPropagaExecutionException());
    }
}
