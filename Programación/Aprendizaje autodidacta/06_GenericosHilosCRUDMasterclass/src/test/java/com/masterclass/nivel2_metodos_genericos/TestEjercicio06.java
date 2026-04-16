package com.masterclass.nivel2_metodos_genericos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class TestEjercicio06 {

    @Test
    @DisplayName("Debe transformar un array genérico directamente en List<T>")
    void testArrayToList() {
        Integer[] numeros = {1, 2, 3, 4};
        
        // Esto exige firmemente que la asignatura devuelva inferido List<Integer>.
        // Dejaremos List flat para que compile a priori y verificamos elementos.
        List asList = Ejercicio06_InferenciaColecciones.arrayToList(numeros);
        
        assertThat(asList)
            .hasSize(4)
            .containsExactly(1, 2, 3, 4);
    }
}
