package com.masterclass.nivel2_default_static_methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 2 - Ejercicio 10: Sobreescribir Default")
class Ejercicio10_SobreescribirDefaultTest {

    @Test
    @DisplayName("10.1 - saludador normal usa el default")
    void saludadorNormalUsaDefault() {
        var s = Ejercicio10_SobreescribirDefault.crearSaludadorNormal("Ana");
        assertThat(s.saludar()).isEqualTo("Hola, soy Ana");
    }

    @Test
    @DisplayName("10.2 - saludador normal devuelve nombre correcto")
    void saludadorNormalNombre() {
        var s = Ejercicio10_SobreescribirDefault.crearSaludadorNormal("Ana");
        assertThat(s.getNombre()).isEqualTo("Ana");
    }

    @Test
    @DisplayName("10.3 - saludador VIP sobreescribe el default")
    void saludadorVIPSobreescribe() {
        var s = Ejercicio10_SobreescribirDefault.crearSaludadorVIP("Carlos");
        assertThat(s.saludar()).isEqualTo("Buenos dias! Me llamo Carlos y soy VIP");
    }

    @Test
    @DisplayName("10.4 - obtenerSaludos devuelve los saludos de todos")
    void debeObtenerSaludos() {
        var lista = List.of(
                Ejercicio10_SobreescribirDefault.crearSaludadorNormal("Ana"),
                Ejercicio10_SobreescribirDefault.crearSaludadorVIP("Carlos"),
                Ejercicio10_SobreescribirDefault.crearSaludadorNormal("Luis")
        );
        assertThat(Ejercicio10_SobreescribirDefault.obtenerSaludos(lista))
                .containsExactly(
                        "Hola, soy Ana",
                        "Buenos dias! Me llamo Carlos y soy VIP",
                        "Hola, soy Luis"
                );
    }
}
