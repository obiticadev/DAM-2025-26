package com.masterclass.nivel4_interfaces_funcionales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 4 - Ejercicio 32: Pipeline Funcional")
class Ejercicio32_PipelineFuncionalTest {
    @Test @DisplayName("32.1 - pipeline filtra y transforma") void pipeline() { assertThat(Ejercicio32_PipelineFuncional.pipeline(List.of(1,2,3,4,5,6), n -> n%2==0, n -> n*10)).containsExactly(20,40,60); }
    @Test @DisplayName("32.2 - procesarNombres filtra >= 4 y mayusculas") void nombres() { assertThat(Ejercicio32_PipelineFuncional.procesarNombres(List.of("Ana","Carlos","Li","Maria"))).containsExactly("CARLOS","MARIA"); }
    @Test @DisplayName("32.3 - procesarNumeros filtra pares y x3") void numeros() { assertThat(Ejercicio32_PipelineFuncional.procesarNumeros(List.of(1,2,3,4,5,6))).containsExactly(6,12,18); }
}
