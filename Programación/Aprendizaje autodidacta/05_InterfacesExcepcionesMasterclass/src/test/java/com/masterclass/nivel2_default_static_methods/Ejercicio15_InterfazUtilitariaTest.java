package com.masterclass.nivel2_default_static_methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 2 - Ejercicio 15: Interfaz Utilitaria")
class Ejercicio15_InterfazUtilitariaTest {

    @Test
    @DisplayName("15.1 - comparador enteros: 5 es mayor que 3")
    void enterosEsMayor() {
        var c = Ejercicio15_InterfazUtilitaria.comparadorEnteros();
        assertThat(c.esMayor(5, 3)).isTrue();
    }

    @Test
    @DisplayName("15.2 - comparador enteros: minimo de 7 y 2 es 2")
    void enterosMinimo() {
        var c = Ejercicio15_InterfazUtilitaria.comparadorEnteros();
        assertThat(c.minimo(7, 2)).isEqualTo(2);
    }

    @Test
    @DisplayName("15.3 - comparador enteros: sonIguales(5, 5) es true")
    void enterosIguales() {
        var c = Ejercicio15_InterfazUtilitaria.comparadorEnteros();
        assertThat(c.sonIguales(5, 5)).isTrue();
    }

    @Test
    @DisplayName("15.4 - comparador por longitud: 'Java' es mayor que 'Go'")
    void longitudMayor() {
        var c = Ejercicio15_InterfazUtilitaria.comparadorPorLongitud();
        assertThat(c.esMayor("Java", "Go")).isTrue();
    }

    @Test
    @DisplayName("15.5 - comparador por longitud: maximo entre 'Hola' y 'Hi' es 'Hola'")
    void longitudMaximo() {
        var c = Ejercicio15_InterfazUtilitaria.comparadorPorLongitud();
        assertThat(c.maximo("Hola", "Hi")).isEqualTo("Hola");
    }

    @Test
    @DisplayName("15.6 - encontrarMaximo con enteros")
    void maximoEnteros() {
        var max = Ejercicio15_InterfazUtilitaria.encontrarMaximo(
                List.of(3, 7, 1, 9, 4),
                Ejercicio15_InterfazUtilitaria.comparadorEnteros()
        );
        assertThat(max).isEqualTo(9);
    }

    @Test
    @DisplayName("15.7 - encontrarMaximo con lista vacia devuelve null")
    void maximoListaVacia() {
        var max = Ejercicio15_InterfazUtilitaria.encontrarMaximo(
                List.of(),
                Ejercicio15_InterfazUtilitaria.comparadorEnteros()
        );
        assertThat(max).isNull();
    }
}
