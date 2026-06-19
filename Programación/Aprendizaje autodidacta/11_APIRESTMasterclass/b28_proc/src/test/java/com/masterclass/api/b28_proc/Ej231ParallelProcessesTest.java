package com.masterclass.api.b28_proc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 60, unit = TimeUnit.SECONDS)
class Ej231ParallelProcessesTest {

    @Test
    void sumarCodigosDeSalida() {
        assertEquals(6, Ej231ParallelProcesses.sumarCodigosDeSalida(List.of(1, 2, 3)));
    }

    @Test
    void todosTerminanConExito() {
        assertTrue(Ej231ParallelProcesses.todosTerminanConExito(4));
    }

    @Test
    void retoExtra01_lanzarTresEnParalelo() {
        assertEquals(3, Ej231ParallelProcesses.lanzarTresEnParalelo());
    }

    @Test
    void retoExtra02_todosVivosTrasArrancar() {
        assertTrue(Ej231ParallelProcesses.todosVivosTrasArrancar());
    }

    @Test
    void retoExtra03_unoFallaRestoOk() {
        assertTrue(Ej231ParallelProcesses.unoFallaRestoOk());
    }

    @Test
    void retoExtra04_sumaDeCodigosCeroUnoDos() {
        assertEquals(3, Ej231ParallelProcesses.sumaDeCodigosCeroUnoDos());
    }

    @Test
    void retoExtra05_esperarTodos() {
        assertTrue(Ej231ParallelProcesses.esperarTodos());
    }

    @Test
    void retoExtra06_pidsDistintos() {
        assertTrue(Ej231ParallelProcesses.pidsDistintos());
    }

    @Test
    void retoExtra07_arrancarTodosLuegoEsperar() {
        assertTrue(Ej231ParallelProcesses.arrancarTodosLuegoEsperar());
    }

    @Test
    void retoExtra08_listaProcesosTamano() {
        assertEquals(5, Ej231ParallelProcesses.listaProcesosTamano());
    }

    @Test
    void retoExtra09_sumaCodigosTodoCero() {
        assertEquals(0, Ej231ParallelProcesses.sumaCodigosTodoCero());
    }

    @Test
    void retoExtra10_contarExitosos() {
        assertEquals(2, Ej231ParallelProcesses.contarExitosos());
    }
}
