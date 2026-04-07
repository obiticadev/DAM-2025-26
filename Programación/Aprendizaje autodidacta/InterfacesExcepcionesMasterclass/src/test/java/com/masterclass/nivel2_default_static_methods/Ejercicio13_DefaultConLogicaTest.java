package com.masterclass.nivel2_default_static_methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 2 - Ejercicio 13: Default con Logica (Template)")
class Ejercicio13_DefaultConLogicaTest {

    @Test
    @DisplayName("13.1 - procesador mayusculas transforma correctamente")
    void mayusculasTransforma() {
        var p = Ejercicio13_DefaultConLogica.crearProcesadorMayusculas();
        assertThat(p.transformar("hola")).isEqualTo("HOLA");
    }

    @Test
    @DisplayName("13.2 - procesarLista con mayusculas")
    void mayusculasProcesarLista() {
        var p = Ejercicio13_DefaultConLogica.crearProcesadorMayusculas();
        assertThat(p.procesarLista(List.of("hola", "mundo")))
                .isEqualTo("HOLA, MUNDO");
    }

    @Test
    @DisplayName("13.3 - procesarConIndice con mayusculas")
    void mayusculasProcesarConIndice() {
        var p = Ejercicio13_DefaultConLogica.crearProcesadorMayusculas();
        assertThat(p.procesarConIndice(List.of("hola", "mundo")))
                .isEqualTo("1. HOLA\n2. MUNDO");
    }

    @Test
    @DisplayName("13.4 - procesador enfasis envuelve con asteriscos")
    void enfasisTransforma() {
        var p = Ejercicio13_DefaultConLogica.crearProcesadorEnfasis();
        assertThat(p.transformar("dato")).isEqualTo("*dato*");
    }

    @Test
    @DisplayName("13.5 - procesarLista con enfasis")
    void enfasisProcesarLista() {
        var p = Ejercicio13_DefaultConLogica.crearProcesadorEnfasis();
        assertThat(p.procesarLista(List.of("a", "b", "c")))
                .isEqualTo("*a*, *b*, *c*");
    }

    @Test
    @DisplayName("13.6 - procesador inicial devuelve primera letra")
    void inicialTransforma() {
        var p = Ejercicio13_DefaultConLogica.crearProcesadorInicial();
        assertThat(p.transformar("Hola")).isEqualTo("H");
    }

    @Test
    @DisplayName("13.7 - procesador inicial con texto vacio devuelve vacio")
    void inicialVacio() {
        var p = Ejercicio13_DefaultConLogica.crearProcesadorInicial();
        assertThat(p.transformar("")).isEqualTo("");
    }
}
