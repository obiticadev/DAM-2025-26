package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 5, unit = TimeUnit.SECONDS)
class Ej215ThreadRunnableTest {

    @Test
    void sumarEnHilo() {
        assertEquals(5050L, Ej215ThreadRunnable.sumarEnHilo(100));
        assertEquals(0L, Ej215ThreadRunnable.sumarEnHilo(0));
    }

    @Test
    void ejecutarTareas() {
        AtomicInteger ejecutadas = new AtomicInteger();
        List<Runnable> tareas = List.of(
                ejecutadas::incrementAndGet,
                ejecutadas::incrementAndGet,
                ejecutadas::incrementAndGet);
        assertEquals(3, Ej215ThreadRunnable.ejecutarTareas(tareas));
        assertEquals(3, ejecutadas.get());
    }

    @Test
    void retoExtra01_sumaConHolder() {
        assertEquals(5050L, Ej215ThreadRunnable.sumaConHolder(100));
    }

    @Test
    void retoExtra02_nombreDelHilo() {
        assertEquals("worker-1", Ej215ThreadRunnable.nombreDelHilo("worker-1"));
    }

    @Test
    void retoExtra03_runEjecutaEnHiloLlamador() {
        assertTrue(Ej215ThreadRunnable.runEjecutaEnHiloLlamador());
    }

    @Test
    void retoExtra04_contarConVariosHilos() {
        assertEquals(10, Ej215ThreadRunnable.contarConVariosHilos(10));
    }

    @Test
    void retoExtra05_isAliveCicloDeVida() {
        assertTrue(Ej215ThreadRunnable.isAliveCicloDeVida());
    }

    @Test
    void retoExtra06_interrumpirHilo() {
        assertTrue(Ej215ThreadRunnable.interrumpirHilo());
    }

    @Test
    void retoExtra07_prioridadDelHilo() {
        assertEquals(7, Ej215ThreadRunnable.prioridadDelHilo(7));
    }

    @Test
    void retoExtra08_esDaemon() {
        assertTrue(Ej215ThreadRunnable.esDaemon());
    }

    @Test
    void retoExtra09_joinConResultado() {
        assertEquals(42L, Ej215ThreadRunnable.joinConResultado());
    }

    @Test
    void retoExtra10_lanzarYEsperarTodos() {
        assertEquals(50, Ej215ThreadRunnable.lanzarYEsperarTodos(50));
    }
}
