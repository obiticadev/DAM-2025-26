package com.masterclass.nivel4_interfaces_funcionales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 4 - Ejercicio 25: Predicate Basico")
class Ejercicio25_PredicateBasicoTest {
    @Test @DisplayName("25.1 - esPar detecta par") void esPar() { assertThat(Ejercicio25_PredicateBasico.esPar().test(4)).isTrue(); }
    @Test @DisplayName("25.2 - esPar detecta impar") void esImpar() { assertThat(Ejercicio25_PredicateBasico.esPar().test(3)).isFalse(); }
    @Test @DisplayName("25.3 - longitudMayorQue funciona") void longitud() { assertThat(Ejercicio25_PredicateBasico.longitudMayorQue(3).test("Hola")).isTrue(); assertThat(Ejercicio25_PredicateBasico.longitudMayorQue(3).test("Hi")).isFalse(); }
    @Test @DisplayName("25.4 - combinarAnd combina correctamente") void combinarAnd() { var p = Ejercicio25_PredicateBasico.combinarAnd(Ejercicio25_PredicateBasico.esPar(), n -> n > 5); assertThat(p.test(8)).isTrue(); assertThat(p.test(4)).isFalse(); assertThat(p.test(7)).isFalse(); }
    @Test @DisplayName("25.5 - filtrar filtra con predicado") void filtrar() { assertThat(Ejercicio25_PredicateBasico.filtrar(List.of(1,2,3,4,5,6), Ejercicio25_PredicateBasico.esPar())).containsExactly(2,4,6); }
    @Test @DisplayName("25.6 - negar invierte predicado") void negar() { var impar = Ejercicio25_PredicateBasico.negar(Ejercicio25_PredicateBasico.esPar()); assertThat(impar.test(3)).isTrue(); assertThat(impar.test(4)).isFalse(); }
}
