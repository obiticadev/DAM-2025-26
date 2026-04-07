package com.masterclass.nivel8_try_with_resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 8 - Ejercicio 59: Custom AutoCloseable")
class Ejercicio59_CustomAutoCloseableTest {
    @Test @DisplayName("59.1 - Temporizador mide duracion") void timer() {
        var t = new Ejercicio59_CustomAutoCloseable.Temporizador();
        t.close();
        assertThat(t.getDuracionNanos()).isGreaterThan(0);
    }
    @Test @DisplayName("59.2 - Pool adquirir y devolver") void pool() {
        var p = new Ejercicio59_CustomAutoCloseable.Pool(2);
        assertThat(p.disponibles()).isEqualTo(2);
        var obj = p.adquirir();
        assertThat(p.disponibles()).isEqualTo(1);
        p.devolver(obj);
        assertThat(p.disponibles()).isEqualTo(2);
    }
    @Test @DisplayName("59.3 - Pool vacio lanza") void poolVacio() {
        var p = new Ejercicio59_CustomAutoCloseable.Pool(1);
        p.adquirir();
        assertThatThrownBy(p::adquirir).isInstanceOf(IllegalStateException.class);
    }
    @Test @DisplayName("59.4 - Pool close limpia") void poolClose() {
        var p = new Ejercicio59_CustomAutoCloseable.Pool(3);
        p.close();
        assertThat(p.disponibles()).isEqualTo(0);
    }
}
