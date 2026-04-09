package com.masterclass.nivel4_interfaces_funcionales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 4 - Ejercicio 26: Function y Composicion")
class Ejercicio26_FunctionYComposicionTest {
    @Test @DisplayName("26.1 - longitud calcula correctamente") void longitud() { assertThat(Ejercicio26_FunctionYComposicion.longitud().apply("Hola")).isEqualTo(4); }
    @Test @DisplayName("26.2 - duplicar multiplica por 2") void duplicar() { assertThat(Ejercicio26_FunctionYComposicion.duplicar().apply(5)).isEqualTo(10); }
    @Test @DisplayName("26.3 - dobleLongitud compone ambas") void dobleLongitud() { assertThat(Ejercicio26_FunctionYComposicion.dobleLongitud().apply("Hola")).isEqualTo(8); }
    @Test @DisplayName("26.4 - mapear transforma lista") void mapear() { assertThat(Ejercicio26_FunctionYComposicion.mapear(List.of("a","bb","ccc"), Ejercicio26_FunctionYComposicion.longitud())).containsExactly(1,2,3); }
    @Test @DisplayName("26.5 - mapear lista vacia") void mapearVacia() { assertThat(Ejercicio26_FunctionYComposicion.mapear(List.of(), Ejercicio26_FunctionYComposicion.longitud())).isEmpty(); }
}
