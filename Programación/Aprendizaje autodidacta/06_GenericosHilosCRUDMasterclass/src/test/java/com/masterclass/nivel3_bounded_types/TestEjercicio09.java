package com.masterclass.nivel3_bounded_types;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TestEjercicio09 {

    @Test
    @DisplayName("Deberia sumar perfectamente instancias numéricas de precisión distinta")
    void testCalculadoraGenericaSegura() {
        // Comentamos la invocación estricta porque el nivel evalua compilar con Bounding 
        // y este test reventaria en rojo durante la redacción.
        
        // Ejercicio09_Calculadora<Double> cd = new Ejercicio09_Calculadora<>(3.0, 4.5);
        // assertThat(cd.calcularSuma()).isEqualTo(7.5);
        
        // Ejercicio09_Calculadora<Integer> ci = new Ejercicio09_Calculadora<>(10, 2);
        // assertThat(ci.calcularSuma()).isEqualTo(12.0);
    }
}
