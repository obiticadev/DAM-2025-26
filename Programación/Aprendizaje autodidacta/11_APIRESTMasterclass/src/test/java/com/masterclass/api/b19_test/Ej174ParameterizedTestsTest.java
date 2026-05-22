package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej174ParameterizedTestsTest {

    @Test
    void todosPasan() {
        List<Caso174> casos = List.of(new Caso174(2, 4), new Caso174(3, 9));
        assertTrue(Ej174ParameterizedTests.casosFallidos(casos, x -> x * x).isEmpty());
    }

    @Test
    void detectaFallidos() {
        List<Caso174> casos = List.of(new Caso174(2, 4), new Caso174(3, 8), new Caso174(4, 16));
        assertEquals(List.of(1),
                Ej174ParameterizedTests.casosFallidos(casos, x -> x * x));
    }

    @Test
    void nulls() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej174ParameterizedTests.casosFallidos(null, x -> x));
    }

    @Test
    void testRetoExtra01_esCasoValido() {
        // Determina si el caso es valido.
        assertTrue(Ej174ParameterizedTests.esCasoValido(new Caso174(5, 10)));
    }

    @Test
    void testRetoExtra02_crearCaso() {
        // Crea un nuevo caso.
        assertNotNull(Ej174ParameterizedTests.crearCaso(1, 2));
    }

    @Test
    void testRetoExtra03_obtenerEntrada() {
        // Obtiene valor de entrada.
        assertEquals(5, Ej174ParameterizedTests.obtenerEntrada(new Caso174(5, 10)));
    }

    @Test
    void testRetoExtra04_obtenerEsperado() {
        // Obtiene valor esperado.
        assertEquals(10, Ej174ParameterizedTests.obtenerEsperado(new Caso174(5, 10)));
    }

    @Test
    void testRetoExtra05_sonDiferentes() {
        // Valida si difieren.
        assertTrue(Ej174ParameterizedTests.sonDiferentes(new Caso174(5, 10), 9));
    }

    @Test
    void testRetoExtra06_sonIguales() {
        // Valida si coinciden.
        assertTrue(Ej174ParameterizedTests.sonIguales(new Caso174(5, 10), 10));
    }

    @Test
    void testRetoExtra07_tamanioCasos() {
        // Obtiene total de casos.
        assertEquals(2, Ej174ParameterizedTests.tamanioCasos(java.util.List.of(new Caso174(1, 2), new Caso174(3, 4))));
    }

    @Test
    void testRetoExtra08_primerCaso() {
        // Obtiene el primer caso.
        assertNotNull(Ej174ParameterizedTests.primerCaso(java.util.List.of(new Caso174(1, 2))));
    }

    @Test
    void testRetoExtra09_esCasoFalla() {
        // Verifica si el operador falla.
        assertTrue(Ej174ParameterizedTests.esCasoFalla(new Caso174(2, 5), x -> x * 2));
    }

    @Test
    void testRetoExtra10_esCasoExito() {
        // Verifica si el operador tiene exito.
        assertTrue(Ej174ParameterizedTests.esCasoExito(new Caso174(2, 4), x -> x * 2));
    }

}