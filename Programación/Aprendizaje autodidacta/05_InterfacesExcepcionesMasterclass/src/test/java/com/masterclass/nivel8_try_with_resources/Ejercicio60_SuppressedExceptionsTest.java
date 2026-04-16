package com.masterclass.nivel8_try_with_resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 8 - Ejercicio 60: Suppressed Exceptions")
class Ejercicio60_SuppressedExceptionsTest {
    @Test @DisplayName("60.1 - sin errores") void ok() { assertThat(Ejercicio60_SuppressedExceptions.usarRecurso(false, false)).isEqualTo("OK"); }
    @Test @DisplayName("60.2 - error en uso solo") void uso() { assertThat(Ejercicio60_SuppressedExceptions.usarRecurso(true, false)).contains("Error en uso").contains("Suppressed: 0"); }
    @Test @DisplayName("60.3 - error en ambos con suppressed") void ambos() { assertThat(Ejercicio60_SuppressedExceptions.usarRecurso(true, true)).contains("Error en uso").contains("Suppressed: 1"); }
    @Test @DisplayName("60.4 - error solo al cerrar") void cerrar() { assertThat(Ejercicio60_SuppressedExceptions.usarRecurso(false, true)).contains("Error al cerrar"); }
}
