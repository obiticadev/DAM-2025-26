package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej170DataJpaTestTest {

    private final RepoMem170 repo = () -> Map.of("Ada", 30, "Bob", 18, "Cid", 25);

    @Test
    void filtraYOrdena() {
        assertEquals(List.of("Ada", "Cid"),
                Ej170DataJpaTest.findByEdadMayorQue(repo, 20));
    }

    @Test
    void nadieSupera() {
        assertTrue(Ej170DataJpaTest.findByEdadMayorQue(repo, 99).isEmpty());
    }

    @Test
    void repoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej170DataJpaTest.findByEdadMayorQue(null, 10));
    }

    @Test
    void testRetoExtra01_contarEntidades() {
        // Cuenta total de entidades en el repo.
        assertEquals(2, Ej170DataJpaTest.contarEntidades(() -> java.util.Map.of("A", 1, "B", 2)));
    }

    @Test
    void testRetoExtra02_obtenerEdadDe() {
        // Obtiene edad de una persona.
        assertEquals(25, Ej170DataJpaTest.obtenerEdadDe(() -> java.util.Map.of("Ada", 25), "Ada"));
    }

    @Test
    void testRetoExtra03_esListaVacia() {
        // Comprueba si la lista esta vacia.
        assertTrue(Ej170DataJpaTest.esListaVacia(java.util.List.of()));
    }

    @Test
    void testRetoExtra04_contieneNombre() {
        // Verifica existencia del nombre.
        assertTrue(Ej170DataJpaTest.contieneNombre(() -> java.util.Map.of("Ada", 25), "Ada"));
    }

    @Test
    void testRetoExtra05_obtenerEdades() {
        // Obtiene todas las edades.
        assertEquals(1, Ej170DataJpaTest.obtenerEdades(() -> java.util.Map.of("A", 10)).size());
    }

    @Test
    void testRetoExtra06_edadMaxima() {
        // Calcula la edad maxima registrada.
        assertEquals(30, Ej170DataJpaTest.edadMaxima(() -> java.util.Map.of("Ada", 30, "Bob", 18)));
    }

    @Test
    void testRetoExtra07_edadMinima() {
        // Calcula la edad minima registrada.
        assertEquals(18, Ej170DataJpaTest.edadMinima(() -> java.util.Map.of("Ada", 30, "Bob", 18)));
    }

    @Test
    void testRetoExtra08_estaVacio() {
        // Verifica si el repo esta vacio.
        assertTrue(Ej170DataJpaTest.estaVacio(java.util.Map::of));
    }

    @Test
    void testRetoExtra09_edadPromedio() {
        // Calcula la edad promedio de las personas.
        assertEquals(24.0, Ej170DataJpaTest.edadPromedio(() -> java.util.Map.of("Ada", 30, "Bob", 18)));
    }

    @Test
    void testRetoExtra10_nombresMayoresQue() {
        // Nombres que superan la edad dada.
        assertEquals(1, Ej170DataJpaTest.nombresMayoresQue(() -> java.util.Map.of("Ada", 30, "Bob", 18), 20).size());
    }

}