package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej165UnitTestJUnit5Test {

    @Test
    void descuentoNormal() {
        assertEquals(80.0, Ej165UnitTestJUnit5.aplicarDescuento(100.0, 20.0));
    }

    @Test
    void limites() {
        assertEquals(100.0, Ej165UnitTestJUnit5.aplicarDescuento(100.0, 0.0));
        assertEquals(0.0, Ej165UnitTestJUnit5.aplicarDescuento(100.0, 100.0));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej165UnitTestJUnit5.aplicarDescuento(0.0, 10.0));
        assertThrows(IllegalArgumentException.class,
                () -> Ej165UnitTestJUnit5.aplicarDescuento(100.0, 150.0));
    }

    @Test
    void testRetoExtra01_adicionar() {
        // Suma dos numeros reales.
        assertEquals(5.0, Ej165UnitTestJUnit5.adicionar(2.0, 3.0));
    }

    @Test
    void testRetoExtra02_restar() {
        // Resta dos numeros reales.
        assertEquals(-1.0, Ej165UnitTestJUnit5.restar(2.0, 3.0));
    }

    @Test
    void testRetoExtra03_multiplicar() {
        // Multiplica dos numeros reales.
        assertEquals(6.0, Ej165UnitTestJUnit5.multiplicar(2.0, 3.0));
    }

    @Test
    void testRetoExtra04_dividir() {
        // Divide dos numeros reales.
        assertThrows(ArithmeticException.class, () -> Ej165UnitTestJUnit5.dividir(1.0, 0.0));
    }

    @Test
    void testRetoExtra05_esPar() {
        // Determina si un entero es par.
        assertTrue(Ej165UnitTestJUnit5.esPar(4));
    }

    @Test
    void testRetoExtra06_obtenerVacio() {
        // Devuelve una cadena vacia.
        assertEquals("", Ej165UnitTestJUnit5.obtenerVacio());
    }

    @Test
    void testRetoExtra07_retornarNulo() {
        // Devuelve un valor nulo.
        assertNull(Ej165UnitTestJUnit5.retornarNulo());
    }

    @Test
    void testRetoExtra08_concatenarTextos() {
        // Concatena dos cadenas.
        assertEquals("ab", Ej165UnitTestJUnit5.concatenarTextos("a", "b"));
    }

    @Test
    void testRetoExtra09_obtenerMayor() {
        // Devuelve el mayor de dos enteros.
        assertEquals(5, Ej165UnitTestJUnit5.obtenerMayor(3, 5));
    }

    @Test
    void testRetoExtra10_obtenerMenor() {
        // Devuelve el menor de dos enteros.
        assertEquals(3, Ej165UnitTestJUnit5.obtenerMenor(3, 5));
    }

}