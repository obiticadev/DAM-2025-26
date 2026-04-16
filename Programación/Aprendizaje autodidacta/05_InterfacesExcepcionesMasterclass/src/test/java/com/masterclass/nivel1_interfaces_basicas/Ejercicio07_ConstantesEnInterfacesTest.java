package com.masterclass.nivel1_interfaces_basicas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 1 - Ejercicio 07: Constantes en Interfaces")
class Ejercicio07_ConstantesEnInterfacesTest {

    @Test
    @DisplayName("07.1 - obtenerMaxReintentos devuelve 3")
    void debeDevolver3() {
        assertThat(Ejercicio07_ConstantesEnInterfaces.obtenerMaxReintentos()).isEqualTo(3);
    }

    @Test
    @DisplayName("07.2 - obtenerTimeoutEnSegundos devuelve 5.0")
    void debeDevolver5Segundos() {
        assertThat(Ejercicio07_ConstantesEnInterfaces.obtenerTimeoutEnSegundos()).isEqualTo(5.0);
    }

    @Test
    @DisplayName("07.3 - aplicarDescuento a 100 devuelve 85.0")
    void debeAplicarDescuento() {
        assertThat(Ejercicio07_ConstantesEnInterfaces.aplicarDescuento(100.0)).isCloseTo(85.0, within(0.01));
    }

    @Test
    @DisplayName("07.4 - aplicarDescuento a 200 devuelve 170.0")
    void debeAplicarDescuento200() {
        assertThat(Ejercicio07_ConstantesEnInterfaces.aplicarDescuento(200.0)).isCloseTo(170.0, within(0.01));
    }

    @Test
    @DisplayName("07.5 - obtenerInfoConfiguracion tiene el formato correcto")
    void debeFormatearInfo() {
        String info = Ejercicio07_ConstantesEnInterfaces.obtenerInfoConfiguracion();
        assertThat(info).isEqualTo("App v2.1.0 | Timeout: 5000ms | Reintentos: 3");
    }
}
