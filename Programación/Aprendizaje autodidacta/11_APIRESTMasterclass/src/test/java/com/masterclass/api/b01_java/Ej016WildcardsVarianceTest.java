package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej016WildcardsVarianceTest {

    @Test
    void sumarMixto() {
        assertEquals(6.5, Ej016WildcardsVariance.sumar(List.of(1, 2.5, 3L)), 0.0001);
    }

    @Test
    void rellenar() {
        List<Number> destino = new ArrayList<>();
        Ej016WildcardsVariance.rellenar(destino);
        assertEquals(List.of(1, 2, 3), destino);
    }

    @Test
    void contarDeTipo() {
        List<Object> items = List.of(1, "a", 2, "b", 3);
        assertEquals(3, Ej016WildcardsVariance.contarDeTipo(items, Integer.class));
        assertEquals(2, Ej016WildcardsVariance.contarDeTipo(items, String.class));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_copiarElementos() {
        List<Integer> origen = List.of(10, 20);
        List<Number> destino = new ArrayList<>();
        Ej016WildcardsVariance.copiarElementos(origen, destino);
        assertEquals(List.of(10, 20), destino);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_calcularSumaColeccion() {
        assertEquals(10.5, Ej016WildcardsVariance.calcularSumaColeccion(List.of(1, 2.5, 3L, 4.0)), 0.0001);
        assertEquals(0.0, Ej016WildcardsVariance.calcularSumaColeccion(List.of()), 0.0001);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_agregarNumeros() {
        List<Number> destino = new ArrayList<>();
        Ej016WildcardsVariance.agregarNumeros(destino, 3);
        assertEquals(List.of(1, 2, 3), destino);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_filtrarListaSoloLectura() {
        List<Integer> list = List.of(1, 2, 3, 4);
        Predicate<Number> filter = num -> num.doubleValue() % 2 == 0;
        List<Integer> res = Ej016WildcardsVariance.filtrarListaSoloLectura(list, filter);
        assertEquals(List.of(2, 4), res);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_encontrarMaximoCovariante() {
        List<String> list = List.of("apple", "pear", "banana");
        assertEquals(Optional.of("pear"), Ej016WildcardsVariance.encontrarMaximoCovariante(list));
        assertEquals(Optional.empty(), Ej016WildcardsVariance.encontrarMaximoCovariante(List.of()));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_combinarColeccionesCovariantes() {
        List<Integer> c1 = List.of(1, 2);
        List<Double> c2 = List.of(3.0, 4.0);
        List<Number> combined = Ej016WildcardsVariance.combinarColeccionesCovariantes(c1, c2);
        assertEquals(List.of(1, 2, 3.0, 4.0), combined);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_esElementoPresente() {
        List<String> list = List.of("a", "b");
        assertTrue(Ej016WildcardsVariance.esElementoPresente(list, "a"));
        assertFalse(Ej016WildcardsVariance.esElementoPresente(list, "z"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_imprimirColeccionComodin() {
        List<String> list = List.of("a", "b");
        assertEquals("items: [a, b]", Ej016WildcardsVariance.imprimirColeccionComodin(list));
        assertEquals("items: []", Ej016WildcardsVariance.imprimirColeccionComodin(List.of()));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_reemplazarValores() {
        List<Number> list = new ArrayList<>(List.of(1, 2, 1, 3));
        Ej016WildcardsVariance.reemplazarValores(list, 1, 99);
        assertEquals(List.of(99, 2, 99, 3), list);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_extraerYConsumir() {
        List<Integer> list = List.of(1, 2, 3);
        List<Number> consumed = new ArrayList<>();
        Ej016WildcardsVariance.extraerYConsumir(list, consumed::add);
        assertEquals(List.of(1, 2, 3), consumed);
    }
}

