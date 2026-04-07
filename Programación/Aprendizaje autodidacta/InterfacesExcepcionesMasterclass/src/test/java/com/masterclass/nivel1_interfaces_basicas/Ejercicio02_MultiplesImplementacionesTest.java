package com.masterclass.nivel1_interfaces_basicas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 1 - Ejercicio 02: Multiples Implementaciones")
class Ejercicio02_MultiplesImplementacionesTest {

    @Test
    @DisplayName("02.1 - crearSuma calcula 3 + 5 = 8")
    void debeSumar() {
        var suma = Ejercicio02_MultiplesImplementaciones.crearSuma();
        assertThat(suma.calcular(3, 5)).isEqualTo(8.0);
    }

    @Test
    @DisplayName("02.2 - crearResta calcula 10 - 4 = 6")
    void debeRestar() {
        var resta = Ejercicio02_MultiplesImplementaciones.crearResta();
        assertThat(resta.calcular(10, 4)).isEqualTo(6.0);
    }

    @Test
    @DisplayName("02.3 - crearMultiplicacion calcula 7 * 3 = 21")
    void debeMultiplicar() {
        var multi = Ejercicio02_MultiplesImplementaciones.crearMultiplicacion();
        assertThat(multi.calcular(7, 3)).isEqualTo(21.0);
    }

    @Test
    @DisplayName("02.4 - crearDivision calcula 20 / 4 = 5")
    void debeDividir() {
        var div = Ejercicio02_MultiplesImplementaciones.crearDivision();
        assertThat(div.calcular(20, 4)).isEqualTo(5.0);
    }

    @Test
    @DisplayName("02.5 - crearDivision entre 0 devuelve NaN")
    void debeDevolverNaNAlDividirPorCero() {
        var div = Ejercicio02_MultiplesImplementaciones.crearDivision();
        assertThat(div.calcular(10, 0)).isNaN();
    }

    @Test
    @DisplayName("02.6 - todas las operaciones son instancias de OperacionMatematica")
    void todasSonOperacionMatematica() {
        assertThat(Ejercicio02_MultiplesImplementaciones.crearSuma())
                .isInstanceOf(Ejercicio02_MultiplesImplementaciones.OperacionMatematica.class);
        assertThat(Ejercicio02_MultiplesImplementaciones.crearResta())
                .isInstanceOf(Ejercicio02_MultiplesImplementaciones.OperacionMatematica.class);
        assertThat(Ejercicio02_MultiplesImplementaciones.crearMultiplicacion())
                .isInstanceOf(Ejercicio02_MultiplesImplementaciones.OperacionMatematica.class);
        assertThat(Ejercicio02_MultiplesImplementaciones.crearDivision())
                .isInstanceOf(Ejercicio02_MultiplesImplementaciones.OperacionMatematica.class);
    }

    @Test
    @DisplayName("02.7 - suma con numeros negativos: -3 + -5 = -8")
    void debeSumarNegativos() {
        var suma = Ejercicio02_MultiplesImplementaciones.crearSuma();
        assertThat(suma.calcular(-3, -5)).isEqualTo(-8.0);
    }
}
