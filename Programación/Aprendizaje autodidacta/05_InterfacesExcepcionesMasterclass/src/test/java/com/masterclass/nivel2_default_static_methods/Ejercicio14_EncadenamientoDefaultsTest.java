package com.masterclass.nivel2_default_static_methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 2 - Ejercicio 14: Encadenamiento con Defaults")
class Ejercicio14_EncadenamientoDefaultsTest {

    @Test
    @DisplayName("14.1 - mayusculas transforma correctamente")
    void mayusculas() {
        assertThat(Ejercicio14_EncadenamientoDefaults.mayusculas().aplicar("hola")).isEqualTo("HOLA");
    }

    @Test
    @DisplayName("14.2 - sinEspacios elimina espacios")
    void sinEspacios() {
        assertThat(Ejercicio14_EncadenamientoDefaults.sinEspacios().aplicar("hola mundo")).isEqualTo("holamundo");
    }

    @Test
    @DisplayName("14.3 - enmarcar agrega >>> y <<<")
    void enmarcar() {
        assertThat(Ejercicio14_EncadenamientoDefaults.enmarcar().aplicar("test")).isEqualTo(">>>test<<<");
    }

    @Test
    @DisplayName("14.4 - encadenar mayusculas y luego sinEspacios")
    void encadenarDos() {
        var transformador = Ejercicio14_EncadenamientoDefaults.mayusculas()
                .yLuego(Ejercicio14_EncadenamientoDefaults.sinEspacios());
        assertThat(transformador.aplicar("hola mundo")).isEqualTo("HOLAMUNDO");
    }

    @Test
    @DisplayName("14.5 - transformarCompleto encadena los tres")
    void transformarCompleto() {
        assertThat(Ejercicio14_EncadenamientoDefaults.transformarCompleto("hola mundo"))
                .isEqualTo(">>>HOLAMUNDO<<<");
    }
}
