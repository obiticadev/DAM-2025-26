package com.masterclass.nivel2_default_static_methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 2 - Ejercicio 12: Metodos Static en Interfaces")
class Ejercicio12_MetodosStaticEnInterfazTest {

    @Test
    @DisplayName("12.1 - validarNoVacio con dato no vacio devuelve true")
    void noVacioConDato() {
        assertThat(Ejercicio12_MetodosStaticEnInterfaz.validarNoVacio("hola")).isTrue();
    }

    @Test
    @DisplayName("12.2 - validarNoVacio con vacio devuelve false")
    void noVacioConVacio() {
        assertThat(Ejercicio12_MetodosStaticEnInterfaz.validarNoVacio("")).isFalse();
    }

    @Test
    @DisplayName("12.3 - validarNoVacio con null devuelve false")
    void noVacioConNull() {
        assertThat(Ejercicio12_MetodosStaticEnInterfaz.validarNoVacio(null)).isFalse();
    }

    @Test
    @DisplayName("12.4 - validarLongitudMinima funciona correctamente")
    void longitudMinima() {
        assertThat(Ejercicio12_MetodosStaticEnInterfaz.validarLongitudMinima("abc", 3)).isTrue();
        assertThat(Ejercicio12_MetodosStaticEnInterfaz.validarLongitudMinima("ab", 3)).isFalse();
    }

    @Test
    @DisplayName("12.5 - validarCompleto: dato valido pasa todo")
    void completoValido() {
        assertThat(Ejercicio12_MetodosStaticEnInterfaz.validarCompleto("abc")).isTrue();
    }

    @Test
    @DisplayName("12.6 - validarCompleto: dato con numeros falla")
    void completoConNumeros() {
        assertThat(Ejercicio12_MetodosStaticEnInterfaz.validarCompleto("abc123")).isFalse();
    }

    @Test
    @DisplayName("12.7 - validarCompleto: dato muy corto falla")
    void completoMuyCorto() {
        assertThat(Ejercicio12_MetodosStaticEnInterfaz.validarCompleto("ab")).isFalse();
    }

    @Test
    @DisplayName("12.8 - filtrarValidos filtra correctamente con validador")
    void filtraConValidador() {
        var datos = List.of("Hola", "", "Java", null, "Hi");
        var resultado = Ejercicio12_MetodosStaticEnInterfaz.filtrarValidos(
                datos,
                Ejercicio12_MetodosStaticEnInterfaz.Validador.longitudMinima(3)
        );
        assertThat(resultado).containsExactly("Hola", "Java");
    }
}
