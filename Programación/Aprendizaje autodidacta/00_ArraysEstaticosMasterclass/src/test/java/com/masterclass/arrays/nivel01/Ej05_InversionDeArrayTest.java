package com.masterclass.arrays.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 05 — Inversión de Array
 */
@DisplayName("Ej05 - Inversión de Array")
class Ej05_InversionDeArrayTest {

    // ─── invertirInPlace ─────────────────────────────────────

    @Nested
    @DisplayName("invertirInPlace")
    class InvertirInPlace {

        @Test
        @DisplayName("Invertir array par: {1,2,3,4} → {4,3,2,1}")
        void deberiaInvertirArrayPar() {
            int[] datos = {1, 2, 3, 4};
            Ej05_InversionDeArray.invertirInPlace(datos);
            assertThat(datos).containsExactly(4, 3, 2, 1);
        }

        @Test
        @DisplayName("Invertir array impar: {1,2,3,4,5} → {5,4,3,2,1}")
        void deberiaInvertirArrayImpar() {
            int[] datos = {1, 2, 3, 4, 5};
            Ej05_InversionDeArray.invertirInPlace(datos);
            assertThat(datos).containsExactly(5, 4, 3, 2, 1);
        }

        @Test
        @DisplayName("Array de un elemento no cambia")
        void noDeberiaModificarArrayDeUno() {
            int[] datos = {42};
            Ej05_InversionDeArray.invertirInPlace(datos);
            assertThat(datos).containsExactly(42);
        }

        @Test
        @DisplayName("Array vacío no lanza excepción")
        void noDeberiaLanzarExcepcionSiVacio() {
            assertThatCode(() -> Ej05_InversionDeArray.invertirInPlace(new int[]{}))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Array null lanza IllegalArgumentException")
        void deberiaLanzarExcepcionSiNull() {
            assertThatThrownBy(() -> Ej05_InversionDeArray.invertirInPlace(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── invertirConCopia ────────────────────────────────────

    @Nested
    @DisplayName("invertirConCopia")
    class InvertirConCopia {

        @Test
        @DisplayName("Devuelve nuevo array invertido")
        void deberiaDevolverNuevoArray() {
            int[] original = {10, 20, 30};
            int[] resultado = Ej05_InversionDeArray.invertirConCopia(original);
            assertThat(resultado).containsExactly(30, 20, 10);
        }

        @Test
        @DisplayName("No modifica el original")
        void noDeberiaModificarOriginal() {
            int[] original = {10, 20, 30};
            Ej05_InversionDeArray.invertirConCopia(original);
            assertThat(original).containsExactly(10, 20, 30);
        }

        @Test
        @DisplayName("Resultado es un objeto distinto del original")
        void deberiaSerObjetoDistinto() {
            int[] original = {1, 2, 3};
            int[] resultado = Ej05_InversionDeArray.invertirConCopia(original);
            assertThat(resultado).isNotSameAs(original);
        }

        @Test
        @DisplayName("Array null lanza IllegalArgumentException")
        void deberiaLanzarExcepcionSiNull() {
            assertThatThrownBy(() -> Ej05_InversionDeArray.invertirConCopia(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── invertirRango ───────────────────────────────────────

    @Nested
    @DisplayName("invertirRango")
    class InvertirRango {

        @Test
        @DisplayName("Invertir rango interno: {1,2,3,4,5} con [1,3] → {1,4,3,2,5}")
        void deberiaInvertirRango() {
            int[] datos = {1, 2, 3, 4, 5};
            Ej05_InversionDeArray.invertirRango(datos, 1, 3);
            assertThat(datos).containsExactly(1, 4, 3, 2, 5);
        }

        @Test
        @DisplayName("Invertir rango completo equivale a invertir todo")
        void deberiaInvertirRangoCompleto() {
            int[] datos = {1, 2, 3};
            Ej05_InversionDeArray.invertirRango(datos, 0, 2);
            assertThat(datos).containsExactly(3, 2, 1);
        }

        @Test
        @DisplayName("Rango de un solo elemento no cambia nada")
        void deberiaNoModificarSiRangoUnitario() {
            int[] datos = {1, 2, 3};
            Ej05_InversionDeArray.invertirRango(datos, 1, 1);
            assertThat(datos).containsExactly(1, 2, 3);
        }

        @Test
        @DisplayName("Rango inválido lanza IndexOutOfBoundsException")
        void deberiaLanzarExcepcionSiRangoInvalido() {
            int[] datos = {1, 2, 3};
            assertThatThrownBy(() -> Ej05_InversionDeArray.invertirRango(datos, -1, 2))
                    .isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> Ej05_InversionDeArray.invertirRango(datos, 0, 5))
                    .isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> Ej05_InversionDeArray.invertirRango(datos, 2, 1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    // ─── esPalindromo ────────────────────────────────────────

    @Nested
    @DisplayName("esPalindromo")
    class EsPalindromo {

        @Test
        @DisplayName("{1, 2, 3, 2, 1} es palíndromo")
        void deberiaDetectarPalindromo() {
            assertThat(Ej05_InversionDeArray.esPalindromo(new int[]{1, 2, 3, 2, 1})).isTrue();
        }

        @Test
        @DisplayName("{1, 2, 2, 1} es palíndromo (longitud par)")
        void deberiaDetectarPalindromoPar() {
            assertThat(Ej05_InversionDeArray.esPalindromo(new int[]{1, 2, 2, 1})).isTrue();
        }

        @Test
        @DisplayName("{1, 2, 3} NO es palíndromo")
        void deberiaDetectarNoPalindromo() {
            assertThat(Ej05_InversionDeArray.esPalindromo(new int[]{1, 2, 3})).isFalse();
        }

        @Test
        @DisplayName("Un solo elemento es palíndromo")
        void elementoUnicoEsPalindromo() {
            assertThat(Ej05_InversionDeArray.esPalindromo(new int[]{42})).isTrue();
        }

        @Test
        @DisplayName("Array vacío es palíndromo")
        void vacioEsPalindromo() {
            assertThat(Ej05_InversionDeArray.esPalindromo(new int[]{})).isTrue();
        }

        @Test
        @DisplayName("Null lanza IllegalArgumentException")
        void nullLanzaExcepcion() {
            assertThatThrownBy(() -> Ej05_InversionDeArray.esPalindromo(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── invertirStrings ─────────────────────────────────────

    @Nested
    @DisplayName("invertirStrings")
    class InvertirStrings {

        @Test
        @DisplayName("Invierte array de Strings in-place")
        void deberiaInvertirStrings() {
            String[] datos = {"A", "B", "C", "D"};
            Ej05_InversionDeArray.invertirStrings(datos);
            assertThat(datos).containsExactly("D", "C", "B", "A");
        }

        @Test
        @DisplayName("Maneja nulls dentro del array")
        void deberiaManejarNulls() {
            String[] datos = {"X", null, "Z"};
            Ej05_InversionDeArray.invertirStrings(datos);
            assertThat(datos).containsExactly("Z", null, "X");
        }
    }

    // ─── rotarDerecha ────────────────────────────────────────

    @Nested
    @DisplayName("rotarDerecha")
    class RotarDerecha {

        @Test
        @DisplayName("{1,2,3,4,5} rotado 2 → {4,5,1,2,3}")
        void deberiaRotarDerecha() {
            int[] datos = {1, 2, 3, 4, 5};
            Ej05_InversionDeArray.rotarDerecha(datos, 2);
            assertThat(datos).containsExactly(4, 5, 1, 2, 3);
        }

        @Test
        @DisplayName("Rotación de 0 posiciones no cambia nada")
        void rotarCeroNoModifica() {
            int[] datos = {1, 2, 3};
            Ej05_InversionDeArray.rotarDerecha(datos, 0);
            assertThat(datos).containsExactly(1, 2, 3);
        }

        @Test
        @DisplayName("Rotación = length no cambia nada")
        void rotarLengthNoModifica() {
            int[] datos = {1, 2, 3};
            Ej05_InversionDeArray.rotarDerecha(datos, 3);
            assertThat(datos).containsExactly(1, 2, 3);
        }

        @Test
        @DisplayName("Rotación > length usa módulo")
        void rotarConModulo() {
            int[] datos = {1, 2, 3, 4, 5};
            Ej05_InversionDeArray.rotarDerecha(datos, 7); // 7 % 5 = 2
            assertThat(datos).containsExactly(4, 5, 1, 2, 3);
        }
    }

    // ─── rotarIzquierda ──────────────────────────────────────

    @Nested
    @DisplayName("rotarIzquierda")
    class RotarIzquierda {

        @Test
        @DisplayName("{1,2,3,4,5} rotado 2 → {3,4,5,1,2}")
        void deberiaRotarIzquierda() {
            int[] datos = {1, 2, 3, 4, 5};
            Ej05_InversionDeArray.rotarIzquierda(datos, 2);
            assertThat(datos).containsExactly(3, 4, 5, 1, 2);
        }

        @Test
        @DisplayName("Rotación > length usa módulo")
        void rotarConModulo() {
            int[] datos = {1, 2, 3, 4, 5};
            Ej05_InversionDeArray.rotarIzquierda(datos, 7); // 7 % 5 = 2
            assertThat(datos).containsExactly(3, 4, 5, 1, 2);
        }
    }
}
