package com.masterclass.nivel2_metodos_genericos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TestEjercicio07 {

    @Test
    @DisplayName("El constructor genérico debe inferir el tipo y generar el mismo log")
    void constructorInfiereTipos() {
        Ejercicio07_ConstructorGenerico loggerStr = new Ejercicio07_ConstructorGenerico("Hack");
        assertThat(loggerStr.getLogFinal()).isEqualTo("[String] - Hack");

        Ejercicio07_ConstructorGenerico loggerDouble = new Ejercicio07_ConstructorGenerico(9.99);
        assertThat(loggerDouble.getLogFinal()).isEqualTo("[Double] - 9.99");
    }
}
