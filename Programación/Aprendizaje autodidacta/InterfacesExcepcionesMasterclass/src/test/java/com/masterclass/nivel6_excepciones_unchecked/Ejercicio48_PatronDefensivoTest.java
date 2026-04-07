package com.masterclass.nivel6_excepciones_unchecked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 6 - Ejercicio 48: Patron Defensivo")
class Ejercicio48_PatronDefensivoTest {
    @Test @DisplayName("48.1 - procesarDatos ok") void ok() {
        var r = Ejercicio48_PatronDefensivo.procesarDatos(List.of(10,20,30), "test");
        assertThat(r).containsEntry("clave","test").containsEntry("total","60");
    }
    @Test @DisplayName("48.2 - datos null lanza NPE") void npe() { assertThatThrownBy(() -> Ejercicio48_PatronDefensivo.procesarDatos(null, "x")).isInstanceOf(NullPointerException.class); }
    @Test @DisplayName("48.3 - datos vacio lanza IAE") void vacio() { assertThatThrownBy(() -> Ejercicio48_PatronDefensivo.procesarDatos(List.of(), "x")).isInstanceOf(IllegalArgumentException.class); }
    @Test @DisplayName("48.4 - clave null lanza IAE") void claveNull() { assertThatThrownBy(() -> Ejercicio48_PatronDefensivo.procesarDatos(List.of(1), null)).isInstanceOf(IllegalArgumentException.class); }
    @Test @DisplayName("48.5 - clave vacia lanza IAE") void claveVacia() { assertThatThrownBy(() -> Ejercicio48_PatronDefensivo.procesarDatos(List.of(1), "")).isInstanceOf(IllegalArgumentException.class); }
}
