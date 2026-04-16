package com.masterclass.nivel3_herencia_interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 3 - Ejercicio 22: Bounded Generics")
class Ejercicio22_BoundedGenericsTest {

    @Test
    @DisplayName("22.1 - ordenador natural ordena ascendente")
    void naturalAscendente() {
        var ord = Ejercicio22_BoundedGenerics.<Integer>crearOrdenadorNatural();
        assertThat(ord.ordenar(List.of(5, 3, 8, 1))).containsExactly(1, 3, 5, 8);
    }

    @Test
    @DisplayName("22.2 - ordenador natural maximo")
    void naturalMaximo() {
        var ord = Ejercicio22_BoundedGenerics.<Integer>crearOrdenadorNatural();
        assertThat(ord.maximo(List.of(5, 3, 8, 1))).isEqualTo(8);
    }

    @Test
    @DisplayName("22.3 - ordenador natural minimo")
    void naturalMinimo() {
        var ord = Ejercicio22_BoundedGenerics.<Integer>crearOrdenadorNatural();
        assertThat(ord.minimo(List.of(5, 3, 8, 1))).isEqualTo(1);
    }

    @Test
    @DisplayName("22.4 - ordenador inverso ordena descendente")
    void inversoDescendente() {
        var ord = Ejercicio22_BoundedGenerics.<Integer>crearOrdenadorInverso();
        assertThat(ord.ordenar(List.of(5, 3, 8, 1))).containsExactly(8, 5, 3, 1);
    }

    @Test
    @DisplayName("22.5 - ordenador con strings funciona")
    void naturalStrings() {
        var ord = Ejercicio22_BoundedGenerics.<String>crearOrdenadorNatural();
        assertThat(ord.ordenar(List.of("c", "a", "b"))).containsExactly("a", "b", "c");
    }

    @Test
    @DisplayName("22.6 - maximo con lista vacia devuelve null")
    void maximoVacio() {
        var ord = Ejercicio22_BoundedGenerics.<Integer>crearOrdenadorNatural();
        assertThat(ord.maximo(List.of())).isNull();
    }
}
