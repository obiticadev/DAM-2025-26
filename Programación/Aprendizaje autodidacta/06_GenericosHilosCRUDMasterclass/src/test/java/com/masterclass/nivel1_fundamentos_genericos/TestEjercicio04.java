package com.masterclass.nivel1_fundamentos_genericos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class TestEjercicio04 {

    @Test
    @DisplayName("La lista devuelta por el método usa Wrapper Classes primitivas correctamente")
    void testListAutoboxingOfPrimitives() {
        Ejercicio04_TiposPrimitivos ej = new Ejercicio04_TiposPrimitivos();
        
        // Decompila o descomenta el tester
        /*
        List<Integer> edades = ej.obtenerEdades();
        
        assertThat(edades)
            .as("La lista debe tener 2 elementos y ser Integer Boxed")
            .hasSize(2)
            .containsExactly(25, 40);
        */
    }
}
