package com.masterclass.nivel4_interfaces_funcionales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 4 - Ejercicio 31: BiFunction y BiPredicate")
class Ejercicio31_BiFunctionTest {
    @Test @DisplayName("31.1 - concatenar une con espacio") void concat() { assertThat(Ejercicio31_BiFunction.concatenar().apply("Hola","Mundo")).isEqualTo("Hola Mundo"); }
    @Test @DisplayName("31.2 - potencia calcula correctamente") void pot() { assertThat(Ejercicio31_BiFunction.potencia().apply(2.0,3.0)).isEqualTo(8.0); }
    @Test @DisplayName("31.3 - esMayor funciona") void mayor() { assertThat(Ejercicio31_BiFunction.esMayor().test(5,3)).isTrue(); assertThat(Ejercicio31_BiFunction.esMayor().test(2,7)).isFalse(); }
    @Test @DisplayName("31.4 - transformarMapa aplica bifuncion") void mapa() {
        var resultado = Ejercicio31_BiFunction.transformarMapa(Map.of("Ana", 10, "Luis", 20), (k, v) -> k + "=" + v);
        assertThat(resultado).containsEntry("Ana", "Ana=10").containsEntry("Luis", "Luis=20");
    }
}
