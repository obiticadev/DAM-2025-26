package com.masterclass.nivel3_herencia_interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 3 - Ejercicio 17: Interfaz Extiende Interfaz")
class Ejercicio17_InterfazExtiendeInterfazTest {

    @Test
    @DisplayName("17.1 - crearEntidad devuelve Nombrable con id y nombre")
    void debeCrearEntidad() {
        var e = Ejercicio17_InterfazExtiendeInterfaz.crearEntidad("001", "Java");
        assertThat(e.getId()).isEqualTo("001");
        assertThat(e.getNombre()).isEqualTo("Java");
    }

    @Test
    @DisplayName("17.2 - Nombrable tambien es Identificable")
    void nombreEsIdentificable() {
        var e = Ejercicio17_InterfazExtiendeInterfaz.crearEntidad("001", "Java");
        assertThat(e).isInstanceOf(Ejercicio17_InterfazExtiendeInterfaz.Identificable.class);
    }

    @Test
    @DisplayName("17.3 - formatearId formatea correctamente un Nombrable")
    void formatearIdConNombrable() {
        var e = Ejercicio17_InterfazExtiendeInterfaz.crearEntidad("ABC", "Test");
        assertThat(Ejercicio17_InterfazExtiendeInterfaz.formatearId(e)).isEqualTo("ID: ABC");
    }
}
