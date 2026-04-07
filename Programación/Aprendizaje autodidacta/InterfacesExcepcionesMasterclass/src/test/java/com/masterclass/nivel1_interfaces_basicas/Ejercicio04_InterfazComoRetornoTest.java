package com.masterclass.nivel1_interfaces_basicas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 1 - Ejercicio 04: Interfaz como Tipo de Retorno")
class Ejercicio04_InterfazComoRetornoTest {

    @Test
    @DisplayName("04.1 - formateador 'mayusculas' convierte a mayusculas")
    void debeMayusculas() {
        var f = Ejercicio04_InterfazComoRetorno.crearFormateador("mayusculas");
        assertThat(f.formatear("hola mundo")).isEqualTo("HOLA MUNDO");
    }

    @Test
    @DisplayName("04.2 - formateador 'minusculas' convierte a minusculas")
    void debeMinusculas() {
        var f = Ejercicio04_InterfazComoRetorno.crearFormateador("minusculas");
        assertThat(f.formatear("HOLA MUNDO")).isEqualTo("hola mundo");
    }

    @Test
    @DisplayName("04.3 - formateador 'invertir' invierte el texto")
    void debeInvertir() {
        var f = Ejercicio04_InterfazComoRetorno.crearFormateador("invertir");
        assertThat(f.formatear("Hola")).isEqualTo("aloH");
    }

    @Test
    @DisplayName("04.4 - formateador 'censurar' reemplaza vocales por *")
    void debeCensurar() {
        var f = Ejercicio04_InterfazComoRetorno.crearFormateador("censurar");
        assertThat(f.formatear("Hola Mundo")).isEqualTo("H*l* M*nd*");
    }

    @Test
    @DisplayName("04.5 - formateador desconocido devuelve texto sin cambios")
    void debeIdentidad() {
        var f = Ejercicio04_InterfazComoRetorno.crearFormateador("desconocido");
        assertThat(f.formatear("test")).isEqualTo("test");
    }

    @Test
    @DisplayName("04.6 - aplicarCadena encadena mayusculas e invertir")
    void debeEncadenarFormateadores() {
        assertThat(Ejercicio04_InterfazComoRetorno.aplicarCadena("Hola", "mayusculas", "invertir"))
                .isEqualTo("ALOH");
    }

    @Test
    @DisplayName("04.7 - aplicarCadena sin formateadores devuelve texto original")
    void debeDevolverOriginalSinFormateadores() {
        assertThat(Ejercicio04_InterfazComoRetorno.aplicarCadena("test"))
                .isEqualTo("test");
    }
}
