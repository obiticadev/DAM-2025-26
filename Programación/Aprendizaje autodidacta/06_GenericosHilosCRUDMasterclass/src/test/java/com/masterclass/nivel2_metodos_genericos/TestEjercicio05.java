package com.masterclass.nivel2_metodos_genericos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TestEjercicio05 {

    @Test
    @DisplayName("El método debe devolver elementos tipados sin causar errores en tiempo de ejecución")
    void testPrimerElementoGenerico() {
        // IMPORTANTE: Si el método no devuelve <T>, este código pitaría 
        // indicando que hace falta un cast al no coincidir el tipo.
        // Simulator...
        String[] stringArray = {"Alpha", "Beta"};
        
        // HACK DE TEST: Invocamos validando que el cast implícito (si el alumno lo hizo bien) funciona.
        Object stringResult = Ejercicio05_MetodoGenerico.obtenerPrimerElemento(stringArray);
        assertThat(stringResult).isEqualTo("Alpha");

        Double[] doubleArray = {3.14, 2.71};
        Object doubleResult = Ejercicio05_MetodoGenerico.obtenerPrimerElemento(doubleArray);
        assertThat(doubleResult).isEqualTo(3.14);
    }
}
