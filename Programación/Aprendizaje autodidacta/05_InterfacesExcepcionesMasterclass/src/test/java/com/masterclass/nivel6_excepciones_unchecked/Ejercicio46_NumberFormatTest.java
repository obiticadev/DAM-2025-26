package com.masterclass.nivel6_excepciones_unchecked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 6 - Ejercicio 46: NumberFormat")
class Ejercicio46_NumberFormatTest {
    @Test @DisplayName("46.1 - sumarValores con validos") void sumaOk() { assertThat(Ejercicio46_NumberFormat.sumarValores(List.of("1.5","2.5","3.0"))).isEqualTo(7.0); }
    @Test @DisplayName("46.2 - sumarValores con invalidos usa 0") void sumaInv() { assertThat(Ejercicio46_NumberFormat.sumarValores(List.of("1","abc","3"))).isEqualTo(4.0); }
    @Test @DisplayName("46.3 - parsearValidos filtra invalidos") void parsear() { assertThat(Ejercicio46_NumberFormat.parsearValidos(List.of("1","abc","3","xyz","5"))).containsExactly(1,3,5); }
    @Test @DisplayName("46.4 - parsearValidos todos invalidos") void todosInv() { assertThat(Ejercicio46_NumberFormat.parsearValidos(List.of("abc","xyz"))).isEmpty(); }
}
