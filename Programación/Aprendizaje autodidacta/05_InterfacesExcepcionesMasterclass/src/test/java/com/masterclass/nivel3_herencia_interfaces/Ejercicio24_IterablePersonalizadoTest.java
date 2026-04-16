package com.masterclass.nivel3_herencia_interfaces;

import com.masterclass.nivel3_herencia_interfaces.Ejercicio24_IterablePersonalizado.RangoNumerico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 3 - Ejercicio 24: Iterable Personalizado")
class Ejercicio24_IterablePersonalizadoTest {

    @Test
    @DisplayName("24.1 - RangoNumerico itera todos los valores")
    void iteraTodosLosValores() {
        var rango = new RangoNumerico(1, 5);
        List<Integer> valores = new ArrayList<>();
        for (int n : rango) valores.add(n);
        assertThat(valores).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    @DisplayName("24.2 - sumarRango suma correctamente 1 a 5 = 15")
    void sumarRango() {
        assertThat(Ejercicio24_IterablePersonalizado.sumarRango(new RangoNumerico(1, 5)))
                .isEqualTo(15);
    }

    @Test
    @DisplayName("24.3 - contarElementos cuenta correctamente")
    void contarElementos() {
        assertThat(Ejercicio24_IterablePersonalizado.contarElementos(new RangoNumerico(3, 7)))
                .isEqualTo(5);
    }

    @Test
    @DisplayName("24.4 - rango de un solo elemento funciona")
    void rangoUnElemento() {
        var rango = new RangoNumerico(5, 5);
        List<Integer> valores = new ArrayList<>();
        for (int n : rango) valores.add(n);
        assertThat(valores).containsExactly(5);
    }

    @Test
    @DisplayName("24.5 - sumarRango con rango grande")
    void sumarRangoGrande() {
        assertThat(Ejercicio24_IterablePersonalizado.sumarRango(new RangoNumerico(1, 100)))
                .isEqualTo(5050);
    }
}
