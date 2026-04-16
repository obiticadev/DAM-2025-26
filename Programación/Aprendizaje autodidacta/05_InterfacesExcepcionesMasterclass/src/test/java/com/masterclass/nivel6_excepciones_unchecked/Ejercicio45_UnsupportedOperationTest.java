package com.masterclass.nivel6_excepciones_unchecked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 6 - Ejercicio 45: UnsupportedOperation")
class Ejercicio45_UnsupportedOperationTest {
    @Test @DisplayName("45.1 - agregar y obtener funcionan") void ok() { var c = Ejercicio45_UnsupportedOperation.<String>crearColeccionSoloLectura(); c.agregar("A"); assertThat(c.obtener(0)).isEqualTo("A"); assertThat(c.tamano()).isEqualTo(1); }
    @Test @DisplayName("45.2 - eliminar lanza UnsupportedOperationException") void eliminar() { var c = Ejercicio45_UnsupportedOperation.<String>crearColeccionSoloLectura(); c.agregar("A"); assertThatThrownBy(() -> c.eliminar(0)).isInstanceOf(UnsupportedOperationException.class); }
}
