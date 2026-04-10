package com.masterclass.arrays.nivel02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 06 — Copia Manual
 */
@DisplayName("Ej06 - Copia Manual de Arrays")
class Ej06_CopiaManualTest {

    // ─── copiarCompleto ──────────────────────────────────────

    @Nested
    @DisplayName("copiarCompleto")
    class CopiarCompleto {

        @Test
        @DisplayName("Copia tiene los mismos valores que el original")
        void deberiaCopiarValores() {
            int[] original = {10, 20, 30, 40};
            int[] copia = Ej06_CopiaManual.copiarCompleto(original);
            assertThat(copia).containsExactly(10, 20, 30, 40);
        }

        @Test
        @DisplayName("Copia es un objeto distinto del original")
        void deberiaSerObjetoDistinto() {
            int[] original = {1, 2, 3};
            int[] copia = Ej06_CopiaManual.copiarCompleto(original);
            assertThat(copia).isNotSameAs(original);
        }

        @Test
        @DisplayName("Modificar original no afecta a la copia")
        void deberiaSarIndependiente() {
            int[] original = {10, 20, 30};
            int[] copia = Ej06_CopiaManual.copiarCompleto(original);
            original[0] = 999;
            assertThat(copia[0]).isEqualTo(10);
        }

        @Test
        @DisplayName("Array vacío se copia correctamente")
        void deberiaCopiarVacio() {
            int[] copia = Ej06_CopiaManual.copiarCompleto(new int[]{});
            assertThat(copia).isNotNull().isEmpty();
        }

        @Test
        @DisplayName("Null lanza IllegalArgumentException")
        void deberiaLanzarSiNull() {
            assertThatThrownBy(() -> Ej06_CopiaManual.copiarCompleto(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── copiarParcial ───────────────────────────────────────

    @Nested
    @DisplayName("copiarParcial")
    class CopiarParcial {

        @Test
        @DisplayName("Copia parcial [1, 4) → {20, 30, 40}")
        void deberiaCopiarRango() {
            int[] original = {10, 20, 30, 40, 50};
            int[] copia = Ej06_CopiaManual.copiarParcial(original, 1, 4);
            assertThat(copia).containsExactly(20, 30, 40);
        }

        @Test
        @DisplayName("Rango completo devuelve copia total")
        void deberiaCopiartRangoCompleto() {
            int[] original = {1, 2, 3};
            int[] copia = Ej06_CopiaManual.copiarParcial(original, 0, 3);
            assertThat(copia).containsExactly(1, 2, 3);
        }

        @Test
        @DisplayName("desde == hasta devuelve array vacío")
        void deberiaDevovlerVacioSiRangoVacio() {
            int[] copia = Ej06_CopiaManual.copiarParcial(new int[]{1, 2, 3}, 2, 2);
            assertThat(copia).isEmpty();
        }

        @Test
        @DisplayName("Rango inválido lanza IndexOutOfBoundsException")
        void deberiaLanzarExcepcionRangoInvalido() {
            assertThatThrownBy(() -> Ej06_CopiaManual.copiarParcial(new int[]{1, 2, 3}, -1, 2))
                    .isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> Ej06_CopiaManual.copiarParcial(new int[]{1, 2, 3}, 0, 5))
                    .isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> Ej06_CopiaManual.copiarParcial(new int[]{1, 2, 3}, 3, 1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    // ─── sonIndependientes ───────────────────────────────────

    @Nested
    @DisplayName("sonIndependientes")
    class SonIndependientes {

        @Test
        @DisplayName("Dos arrays con mismo contenido pero distintas referencias → true")
        void deberiaDetectarIndependencia() {
            int[] a = {1, 2, 3};
            int[] b = {1, 2, 3};
            assertThat(Ej06_CopiaManual.sonIndependientes(a, b)).isTrue();
        }

        @Test
        @DisplayName("Misma referencia → false (son alias, NO independientes)")
        void deberiaDetectarAlias() {
            int[] a = {1, 2, 3};
            int[] b = a; // alias
            assertThat(Ej06_CopiaManual.sonIndependientes(a, b)).isFalse();
        }

        @Test
        @DisplayName("Contenido distinto → false")
        void deberiaDetectarContenidoDistinto() {
            assertThat(Ej06_CopiaManual.sonIndependientes(new int[]{1, 2}, new int[]{1, 3})).isFalse();
        }

        @Test
        @DisplayName("Null devuelve false")
        void deberiaDevovlerFalseSiNull() {
            assertThat(Ej06_CopiaManual.sonIndependientes(null, new int[]{1})).isFalse();
            assertThat(Ej06_CopiaManual.sonIndependientes(new int[]{1}, null)).isFalse();
        }
    }

    // ─── copiarConDesplazamiento ─────────────────────────────

    @Nested
    @DisplayName("copiarConDesplazamiento")
    class CopiarConDesplazamiento {

        @Test
        @DisplayName("Offset 2: {10,20,30} → {0, 0, 10, 20, 30}")
        void deberiaCopiarConOffset() {
            int[] resultado = Ej06_CopiaManual.copiarConDesplazamiento(new int[]{10, 20, 30}, 2);
            assertThat(resultado).containsExactly(0, 0, 10, 20, 30);
        }

        @Test
        @DisplayName("Offset 0 devuelve copia del mismo tamaño")
        void offsetCero() {
            int[] resultado = Ej06_CopiaManual.copiarConDesplazamiento(new int[]{1, 2}, 0);
            assertThat(resultado).containsExactly(1, 2);
        }
    }

    // ─── concatenar ──────────────────────────────────────────

    @Nested
    @DisplayName("concatenar")
    class Concatenar {

        @Test
        @DisplayName("{1,2} + {3,4,5} → {1,2,3,4,5}")
        void deberiaConcatenar() {
            int[] resultado = Ej06_CopiaManual.concatenar(new int[]{1, 2}, new int[]{3, 4, 5});
            assertThat(resultado).containsExactly(1, 2, 3, 4, 5);
        }

        @Test
        @DisplayName("Concatenar con array vacío devuelve copia del otro")
        void deberiaConcatenarConVacio() {
            int[] resultado = Ej06_CopiaManual.concatenar(new int[]{}, new int[]{1, 2});
            assertThat(resultado).containsExactly(1, 2);
        }
    }

    // ─── clonarBidimensional ─────────────────────────────────

    @Nested
    @DisplayName("clonarBidimensional")
    class ClonarBidimensional {

        @Test
        @DisplayName("El clon tiene los mismos valores")
        void deberiaClonarValores() {
            int[][] original = {{1, 2}, {3, 4}, {5, 6}};
            int[][] clon = Ej06_CopiaManual.clonarBidimensional(original);
            assertThat(clon[0]).containsExactly(1, 2);
            assertThat(clon[1]).containsExactly(3, 4);
            assertThat(clon[2]).containsExactly(5, 6);
        }

        @Test
        @DisplayName("Cada fila del clon es un objeto distinto")
        void filasDebenSerDistintas() {
            int[][] original = {{1, 2}, {3, 4}};
            int[][] clon = Ej06_CopiaManual.clonarBidimensional(original);
            assertThat(clon[0]).isNotSameAs(original[0]);
            assertThat(clon[1]).isNotSameAs(original[1]);
        }

        @Test
        @DisplayName("Soporta jagged arrays")
        void deberiaSoportarJagged() {
            int[][] original = {{1}, {2, 3}, {4, 5, 6}};
            int[][] clon = Ej06_CopiaManual.clonarBidimensional(original);
            assertThat(clon[0]).hasSize(1);
            assertThat(clon[1]).hasSize(2);
            assertThat(clon[2]).hasSize(3);
        }
    }

    // ─── verificarIndependencia2D ────────────────────────────

    @Nested
    @DisplayName("verificarIndependencia2D")
    class VerificarIndependencia2D {

        @Test
        @DisplayName("Clon deep-copy es independiente")
        void deberiaVerificarIndependencia() {
            int[][] original = {{1, 2}, {3, 4}};
            int[][] clon = Ej06_CopiaManual.clonarBidimensional(original);
            assertThat(Ej06_CopiaManual.verificarIndependencia2D(original, clon)).isTrue();
        }

        @Test
        @DisplayName("Mismas filas (shallow) NO es independiente")
        void deberiaDetectarShallowCopy() {
            int[][] original = {{1, 2}, {3, 4}};
            int[][] shallow = new int[][]{original[0], original[1]}; // mismas refs
            assertThat(Ej06_CopiaManual.verificarIndependencia2D(original, shallow)).isFalse();
        }
    }
}
