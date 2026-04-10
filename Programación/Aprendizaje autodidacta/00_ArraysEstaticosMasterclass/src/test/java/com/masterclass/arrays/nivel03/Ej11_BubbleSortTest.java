package com.masterclass.arrays.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej11 - Bubble Sort")
class Ej11_BubbleSortTest {

    @Nested
    @DisplayName("sortAscendente")
    class SortAscendente {
        @Test @DisplayName("Ordenar array desordenado")
        void deberiaOrdenar() {
            int[] datos = {5, 3, 8, 1, 2};
            Ej11_BubbleSort.sortAscendente(datos);
            assertThat(datos).containsExactly(1, 2, 3, 5, 8);
        }
        @Test @DisplayName("Array ya ordenado no cambia")
        void yaOrdenado() {
            int[] datos = {1, 2, 3, 4, 5};
            Ej11_BubbleSort.sortAscendente(datos);
            assertThat(datos).containsExactly(1, 2, 3, 4, 5);
        }
        @Test @DisplayName("Array con duplicados")
        void conDuplicados() {
            int[] datos = {3, 1, 3, 2, 1};
            Ej11_BubbleSort.sortAscendente(datos);
            assertThat(datos).containsExactly(1, 1, 2, 3, 3);
        }
        @Test @DisplayName("Array con negativos")
        void conNegativos() {
            int[] datos = {-3, 5, -1, 0, 2};
            Ej11_BubbleSort.sortAscendente(datos);
            assertThat(datos).containsExactly(-3, -1, 0, 2, 5);
        }
        @Test @DisplayName("Null lanza excepción")
        void nullLanza() {
            assertThatThrownBy(() -> Ej11_BubbleSort.sortAscendente(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("sortDescendente")
    class SortDescendente {
        @Test @DisplayName("Ordenar de mayor a menor")
        void deberiaOrdenarDesc() {
            int[] datos = {3, 1, 4, 1, 5};
            Ej11_BubbleSort.sortDescendente(datos);
            assertThat(datos).containsExactly(5, 4, 3, 1, 1);
        }
    }

    @Nested @DisplayName("sortConContador")
    class SortConContador {
        @Test @DisplayName("Devuelve comparaciones y swaps")
        void deberiaContar() {
            int[] datos = {3, 1, 2};
            int[] stats = Ej11_BubbleSort.sortConContador(datos);
            assertThat(stats).isNotNull().hasSize(2);
            assertThat(stats[0]).isGreaterThan(0); // comparaciones
            assertThat(datos).containsExactly(1, 2, 3);
        }
        @Test @DisplayName("Array ya ordenado: 0 swaps")
        void yaOrdenado() {
            int[] datos = {1, 2, 3, 4};
            int[] stats = Ej11_BubbleSort.sortConContador(datos);
            assertThat(stats[1]).isEqualTo(0);
        }
    }

    @Nested @DisplayName("estaOrdenado")
    class EstaOrdenado {
        @Test @DisplayName("Detecta array ordenado")
        void ordenado() { assertThat(Ej11_BubbleSort.estaOrdenado(new int[]{1, 2, 3, 4})).isTrue(); }
        @Test @DisplayName("Detecta array desordenado")
        void desordenado() { assertThat(Ej11_BubbleSort.estaOrdenado(new int[]{1, 3, 2})).isFalse(); }
        @Test @DisplayName("Vacío está ordenado")
        void vacio() { assertThat(Ej11_BubbleSort.estaOrdenado(new int[]{})).isTrue(); }
    }

    @Nested @DisplayName("sortRango")
    class SortRango {
        @Test @DisplayName("Ordena solo el rango indicado")
        void deberiaOrdenarRango() {
            int[] datos = {9, 5, 3, 8, 1, 7};
            Ej11_BubbleSort.sortRango(datos, 1, 4);
            assertThat(datos[0]).isEqualTo(9);
            assertThat(datos[5]).isEqualTo(7);
            assertThat(datos[1]).isLessThanOrEqualTo(datos[2]);
            assertThat(datos[2]).isLessThanOrEqualTo(datos[3]);
            assertThat(datos[3]).isLessThanOrEqualTo(datos[4]);
        }
        @Test @DisplayName("Rango inválido lanza excepción")
        void rangoInvalido() {
            assertThatThrownBy(() -> Ej11_BubbleSort.sortRango(new int[]{1, 2}, -1, 1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    @Nested @DisplayName("swap")
    class Swap {
        @Test @DisplayName("Intercambia dos posiciones")
        void deberiaSwap() {
            int[] datos = {10, 20, 30};
            Ej11_BubbleSort.swap(datos, 0, 2);
            assertThat(datos).containsExactly(30, 20, 10);
        }
    }

    @Nested @DisplayName("sortBidireccional")
    class SortBidireccional {
        @Test @DisplayName("Cocktail sort ordena correctamente")
        void deberiaOrdenar() {
            int[] datos = {5, 1, 4, 2, 8, 0, 2};
            Ej11_BubbleSort.sortBidireccional(datos);
            assertThat(datos).containsExactly(0, 1, 2, 2, 4, 5, 8);
        }
    }
}
