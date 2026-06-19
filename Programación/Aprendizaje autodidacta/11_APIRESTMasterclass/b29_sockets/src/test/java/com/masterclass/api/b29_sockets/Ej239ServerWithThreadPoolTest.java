package com.masterclass.api.b29_sockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 15, unit = TimeUnit.SECONDS)
class Ej239ServerWithThreadPoolTest {

    @Test
    void atenderConPoolFijo() {
        assertEquals(6, Ej239ServerWithThreadPool.atenderConPoolFijo(6, 2));
    }

    @Test
    void sumarConPool() {
        assertEquals(6L, Ej239ServerWithThreadPool.sumarConPool(List.of(1, 2, 3)));
    }

    @Test
    void retoExtra01_poolReutilizaHilos() {
        int usados = Ej239ServerWithThreadPool.poolReutilizaHilos(50, 3);
        assertTrue(usados > 0 && usados <= 3, "un pool de 3 no debe usar más de 3 hilos");
    }

    @Test
    void retoExtra02_concurrenciaLimitadaAlPool() {
        int pico = Ej239ServerWithThreadPool.concurrenciaLimitadaAlPool(2);
        assertTrue(pico >= 1 && pico <= 2, "la concurrencia no puede superar el tamaño del pool");
    }

    @Test
    void retoExtra03_colaAbsorbeTareasExtra() {
        assertEquals(20, Ej239ServerWithThreadPool.colaAbsorbeTareasExtra(20));
    }

    @Test
    void retoExtra04_rechazoConColaAcotada() {
        assertTrue(Ej239ServerWithThreadPool.rechazoConColaAcotada());
    }

    @Test
    void retoExtra05_threadFactoryNombraHilos() {
        assertTrue(Ej239ServerWithThreadPool.threadFactoryNombraHilos());
    }

    @Test
    void retoExtra06_cachedPoolAtiende() {
        assertEquals(10, Ej239ServerWithThreadPool.cachedPoolAtiende(10));
    }

    @Test
    void retoExtra07_excepcionEnHandlerNoMataPool() {
        assertEquals(4, Ej239ServerWithThreadPool.excepcionEnHandlerNoMataPool(4));
    }

    @Test
    void retoExtra08_shutdownYAwaitTermination() {
        assertTrue(Ej239ServerWithThreadPool.shutdownYAwaitTermination());
    }

    @Test
    void retoExtra09_submitTrasShutdownRechazado() {
        assertTrue(Ej239ServerWithThreadPool.submitTrasShutdownRechazado());
    }

    @Test
    void retoExtra10_poolUsaMenosHilosQueClientes() {
        assertTrue(Ej239ServerWithThreadPool.poolUsaMenosHilosQueClientes());
    }
}
