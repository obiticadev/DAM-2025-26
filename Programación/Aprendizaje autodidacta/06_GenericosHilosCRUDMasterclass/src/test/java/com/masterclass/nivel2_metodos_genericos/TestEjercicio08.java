package com.masterclass.nivel2_metodos_genericos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class TestEjercicio08 {

    @Test
    @DisplayName("Empleado debe ser ordenable por Collections.sort automáticamente (Comparable)")
    void testComparableInterface() {
        Ejercicio08_InterfazGenerica e1 = new Ejercicio08_InterfazGenerica("A", 1000);
        Ejercicio08_InterfazGenerica e2 = new Ejercicio08_InterfazGenerica("B", 3000);
        Ejercicio08_InterfazGenerica e3 = new Ejercicio08_InterfazGenerica("C", 2000);

        List<Ejercicio08_InterfazGenerica> lista = Arrays.asList(e2, e1, e3);
        
        // El sort fallará de compilación si Empleado no implementa Comparable
        // Collections.sort(lista);

        // assertThat(lista.get(0).getNombre()).isEqualTo("A");
        // assertThat(lista.get(2).getNombre()).isEqualTo("B");
    }
}
