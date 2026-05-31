package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej166MockitoMocksTest {

    @Test
    void usuarioExiste() {
        RepositorioStub166 stub = id -> id == 1 ? "Ada" : null;
        assertEquals("Hola, Ada", Ej166MockitoMocks.saludar(stub, 1));
    }

    @Test
    void usuarioNoExiste() {
        RepositorioStub166 stub = id -> null;
        assertEquals("Hola, desconocido", Ej166MockitoMocks.saludar(stub, 99));
    }

    @Test
    void repoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej166MockitoMocks.saludar(null, 1));
    }

    @Test
    void testRetoExtra01_longitudNombre() {
        // Obtiene longitud del nombre.
        assertEquals(3, Ej166MockitoMocks.longitudNombre(id -> "Ada", 1));
    }

    @Test
    void testRetoExtra02_nombreEnMayusculas() {
        // Obtiene el nombre en mayusculas.
        assertEquals("ADA", Ej166MockitoMocks.nombreEnMayusculas(id -> "Ada", 1));
    }

    @Test
    void testRetoExtra03_nombreValido() {
        // Determina si el nombre es valido.
        assertTrue(Ej166MockitoMocks.nombreValido(id -> "Ada", 1));
    }

    @Test
    void testRetoExtra04_saludoConSufijo() {
        // Saluda al usuario con un sufijo.
        assertEquals("Hola Ada!", Ej166MockitoMocks.saludoConSufijo(id -> "Ada", 1, "!"));
    }

    @Test
    void testRetoExtra05_contieneSubcadena() {
        // Comprueba si el nombre contiene una subcadena.
        assertTrue(Ej166MockitoMocks.contieneSubcadena(id -> "Ada", 1, "Ad"));
    }

    @Test
    void testRetoExtra06_esInvitado() {
        // Determina si el usuario es un invitado.
        assertTrue(Ej166MockitoMocks.esInvitado(id -> "invitado", 1));
    }

    @Test
    void testRetoExtra07_saludoInverso() {
        // Devuelve el nombre invertido.
        assertEquals("adA", Ej166MockitoMocks.saludoInverso(id -> "Ada", 1));
    }

    @Test
    void testRetoExtra08_concatenarIdYNombre() {
        // Concatena el ID y el nombre.
        assertEquals("1:Ada", Ej166MockitoMocks.concatenarIdYNombre(id -> "Ada", 1));
    }

    @Test
    void testRetoExtra09_nombreLargo() {
        // Comprueba si el nombre es largo.
        assertFalse(Ej166MockitoMocks.nombreLargo(id -> "Ada", 1));
    }

    @Test
    void testRetoExtra10_buscarOError() {
        // Busca el nombre o lanza excepcion.
        assertThrows(IllegalArgumentException.class, () -> Ej166MockitoMocks.buscarOError(id -> null, 1));
    }

}