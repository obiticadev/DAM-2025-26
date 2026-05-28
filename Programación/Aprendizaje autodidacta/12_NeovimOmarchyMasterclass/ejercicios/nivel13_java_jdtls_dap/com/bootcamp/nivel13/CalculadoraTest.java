package com.bootcamp.nivel13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Calculadora — tests JUnit")
class CalculadoraTest {

    @Test
    @DisplayName("sumar(2, 3) = 5")
    void sumarDosMasTres() {
        assertThat(new Calculadora().sumar(2, 3)).isEqualTo(5);
    }

    @Test
    @DisplayName("multiplicar(4, 5) = 20")
    void multiplicarCuatroPorCinco() {
        assertThat(new Calculadora().multiplicar(4, 5)).isEqualTo(20);
    }

    @Test
    @DisplayName("sumarVarios(1,2,3,4) = 10")
    void sumarVariosLista() {
        assertThat(new Calculadora().sumarVarios(1, 2, 3, 4)).isEqualTo(10);
    }
}
