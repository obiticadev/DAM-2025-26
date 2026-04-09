package com.masterclass.nivel2_default_static_methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 2 - Ejercicio 11: Diamond Problem")
class Ejercicio11_DiamondProblemTest {

    @Test
    @DisplayName("11.1 - el objeto es Trabajador Y Voluntario")
    void debeSerAmbos() {
        Object obj = Ejercicio11_DiamondProblem.crearTrabajadorVoluntario("Pedro");
        assertThat(obj).isInstanceOf(Ejercicio11_DiamondProblem.Trabajador.class);
        assertThat(obj).isInstanceOf(Ejercicio11_DiamondProblem.Voluntario.class);
    }

    @Test
    @DisplayName("11.2 - identificarse resuelve el conflicto correctamente")
    void debeResolverConflicto() {
        Object obj = Ejercicio11_DiamondProblem.crearTrabajadorVoluntario("Pedro");
        String id = Ejercicio11_DiamondProblem.obtenerIdentificacion(obj);
        assertThat(id).isEqualTo("Soy un trabajador-voluntario: Pedro");
    }

    @Test
    @DisplayName("11.3 - prioridad suma ambos defaults (1 + 2 = 3)")
    void debeSumarPrioridades() {
        Object obj = Ejercicio11_DiamondProblem.crearTrabajadorVoluntario("Pedro");
        int prioridad = Ejercicio11_DiamondProblem.obtenerPrioridad(obj);
        assertThat(prioridad).isEqualTo(3);
    }
}
