package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej188NPlusOneAndQueryTuningTest {

    private final List<Long> ids = List.of(1L, 2L, 3L, 4L, 5L);

    @Test
    void naiveEsNMasUno() {
        assertEquals(6, Ej188NPlusOneAndQueryTuning.consultasNaive(ids));
        assertEquals(1, Ej188NPlusOneAndQueryTuning.consultasNaive(List.of()));
    }

    @Test
    void optimizadaUsaLotes() {
        assertEquals(4, Ej188NPlusOneAndQueryTuning.consultasOptimizadas(ids, 2));
        assertEquals(2, Ej188NPlusOneAndQueryTuning.consultasOptimizadas(ids, 10));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej188NPlusOneAndQueryTuning.consultasNaive(null));
        assertThrows(IllegalArgumentException.class,
                () -> Ej188NPlusOneAndQueryTuning.consultasOptimizadas(ids, 0));
    }

    @Test
    void testRetoExtra01_esListaValida() {
        // Valida lista.
        assertTrue(Ej188NPlusOneAndQueryTuning.esListaValida(java.util.List.of()));
    }

    @Test
    void testRetoExtra02_contieneIdNulo() {
        // Comprueba nulos.
        assertTrue(Ej188NPlusOneAndQueryTuning.contieneIdNulo(java.util.Arrays.asList(1L, null)));
    }

    @Test
    void testRetoExtra03_esLoteValido() {
        // Valida tamaño de lote.
        assertTrue(Ej188NPlusOneAndQueryTuning.esLoteValido(2));
    }

    @Test
    void testRetoExtra04_cantidadIds() {
        // Cantidad total de ids.
        assertEquals(3, Ej188NPlusOneAndQueryTuning.cantidadIds(java.util.List.of(1L, 2L, 3L)));
    }

    @Test
    void testRetoExtra05_calcularLotesEnteros() {
        // Calcula bloques de carga.
        assertEquals(3, Ej188NPlusOneAndQueryTuning.calcularLotesEnteros(5, 2));
    }

    @Test
    void testRetoExtra06_esOptimo() {
        // Valida si el lote cubre todo.
        assertTrue(Ej188NPlusOneAndQueryTuning.esOptimo(5, 5));
    }

    @Test
    void testRetoExtra07_diferenciaConsultas() {
        // Calcula diferencia.
        assertEquals(3, Ej188NPlusOneAndQueryTuning.diferenciaConsultas(6, 3));
    }

    @Test
    void testRetoExtra08_esMasEficiente() {
        // Comprueba si es mejor.
        assertTrue(Ej188NPlusOneAndQueryTuning.esMasEficiente(6, 3));
    }

    @Test
    void testRetoExtra09_obtenerIdUnico() {
        // Obtiene id de posicion.
        assertEquals(2L, Ej188NPlusOneAndQueryTuning.obtenerIdUnico(java.util.List.of(1L, 2L), 1));
    }

    @Test
    void testRetoExtra10_crearListaIds() {
        // Crea lista limpia.
        assertNotNull(Ej188NPlusOneAndQueryTuning.crearListaIds());
    }

}