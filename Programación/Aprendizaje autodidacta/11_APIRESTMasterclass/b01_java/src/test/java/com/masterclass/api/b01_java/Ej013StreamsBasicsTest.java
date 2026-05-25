package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej013StreamsBasicsTest {

    @Test
    void nombresCarosEnMayus() {
        var entradas = List.of(
                Map.entry("teclado", 100.0),
                Map.entry("cable", 5.0),
                Map.entry("monitor", 200.0));
        assertEquals(List.of("TECLADO", "MONITOR"),
                Ej013StreamsBasics.nombresCarosEnMayus(entradas, 50));
    }

    @Test
    void total() {
        assertEquals(35.5, Ej013StreamsBasics.total(List.of(10.0, 20.0, 5.5)), 0.0001);
        assertEquals(0.0, Ej013StreamsBasics.total(List.of()), 0.0001);
    }

    @Test
    void contarPares() {
        assertEquals(2, Ej013StreamsBasics.contarPares(List.of(1, 2, 3, 4)));
    }

    @Test
    void retoExtra01_filtrarMayoresDeEdad() {
        assertEquals(List.of(18, 21), Ej013StreamsBasics.filtrarMayoresDeEdad(List.of(15, 18, 12, 21)));
        assertEquals(List.of(), Ej013StreamsBasics.filtrarMayoresDeEdad(List.of(10, 5)));
    }

    @Test
    void retoExtra02_convertirAMayusculas() {
        assertEquals(List.of("HELLO", "WORLD"), Ej013StreamsBasics.convertirAMayusculas(java.util.Arrays.asList("hello", null, "", "world")));
    }

    @Test
    void retoExtra03_calcularSumaCuadrados() {
        assertEquals(14, Ej013StreamsBasics.calcularSumaCuadrados(List.of(1, 2, 3)));
        assertEquals(0, Ej013StreamsBasics.calcularSumaCuadrados(List.of()));
    }

    @Test
    void retoExtra04_contarCadenasVacias() {
        assertEquals(2, Ej013StreamsBasics.contarCadenasVacias(List.of("", "   ", "hello", "world")));
        assertEquals(0, Ej013StreamsBasics.contarCadenasVacias(List.of("hello")));
    }

    @Test
    void retoExtra05_unirConComas() {
        assertEquals("[one, two, three]", Ej013StreamsBasics.unirConComas(List.of("one", "two", "three")));
        assertEquals("[]", Ej013StreamsBasics.unirConComas(List.of()));
    }

    @Test
    void retoExtra06_obtenerElementosUnicos() {
        assertEquals(List.of("apple", "banana", "pear"), Ej013StreamsBasics.obtenerElementosUnicos(List.of("banana", "apple", "banana", "pear")));
    }

    @Test
    void retoExtra07_limitarYDescartar() {
        assertEquals(List.of("c", "d"), Ej013StreamsBasics.limitarYDescartar(List.of("a", "b", "c", "d", "e"), 2, 2));
        assertEquals(List.of("a", "b"), Ej013StreamsBasics.limitarYDescartar(List.of("a", "b"), 0, 5));
    }

    @Test
    void retoExtra08_algunoEmpiezaCon() {
        assertTrue(Ej013StreamsBasics.algunoEmpiezaCon(List.of("Spring", "Java", "Kotlin"), "sp"));
        assertFalse(Ej013StreamsBasics.algunoEmpiezaCon(List.of("Spring", "Java", "Kotlin"), "py"));
    }

    @Test
    void retoExtra09_todosSonPositivos() {
        assertTrue(Ej013StreamsBasics.todosSonPositivos(List.of(1, 2, 3)));
        assertFalse(Ej013StreamsBasics.todosSonPositivos(List.of(1, -2, 3)));
        assertTrue(Ej013StreamsBasics.todosSonPositivos(List.of()));
    }

    @Test
    void retoExtra10_obtenerMaximo() {
        assertEquals(java.util.Optional.of(15), Ej013StreamsBasics.obtenerMaximo(List.of(5, 15, 3, 9)));
        assertEquals(java.util.Optional.empty(), Ej013StreamsBasics.obtenerMaximo(List.of()));
    }
}

