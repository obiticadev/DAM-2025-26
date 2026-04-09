package com.masterclass.nivel6_excepciones_unchecked;

import com.masterclass.nivel6_excepciones_unchecked.Ejercicio42_IllegalState.Pila;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 6 - Ejercicio 42: IllegalState")
class Ejercicio42_IllegalStateTest {
    @Test @DisplayName("42.1 - push y pop") void pushPop() { var p = new Pila<Integer>(3); p.push(1); p.push(2); assertThat(p.pop()).isEqualTo(2); assertThat(p.pop()).isEqualTo(1); }
    @Test @DisplayName("42.2 - peek sin pop") void peek() { var p = new Pila<String>(3); p.push("A"); assertThat(p.peek()).isEqualTo("A"); assertThat(p.size()).isEqualTo(1); }
    @Test @DisplayName("42.3 - pop vacia lanza") void popVacia() { assertThatThrownBy(() -> new Pila<>(3).pop()).isInstanceOf(IllegalStateException.class); }
    @Test @DisplayName("42.4 - push llena lanza") void pushLlena() { var p = new Pila<Integer>(1); p.push(1); assertThatThrownBy(() -> p.push(2)).isInstanceOf(IllegalStateException.class); }
    @Test @DisplayName("42.5 - size correcto") void size() { var p = new Pila<Integer>(5); assertThat(p.size()).isEqualTo(0); p.push(1); assertThat(p.size()).isEqualTo(1); }
}
