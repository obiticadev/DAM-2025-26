package com.masterclass.arrays.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej15 - MergeSort")
class Ej15_MergeSortTest {

    @Nested @DisplayName("sort")
    class Sort {
        @Test @DisplayName("Ordenar array desordenado")
        void deberiaOrdenar() {
            int[] datos = {38, 27, 43, 3, 9, 82, 10};
            Ej15_MergeSort.sort(datos);
            assertThat(datos).containsExactly(3, 9, 10, 27, 38, 43, 82);
        }
        @Test @DisplayName("Con duplicados")
        void conDuplicados() {
            int[] datos = {5, 2, 5, 1, 2};
            Ej15_MergeSort.sort(datos);
            assertThat(datos).containsExactly(1, 2, 2, 5, 5);
        }
        @Test @DisplayName("Array ya ordenado")
        void yaOrdenado() {
            int[] datos = {1, 2, 3};
            Ej15_MergeSort.sort(datos);
            assertThat(datos).containsExactly(1, 2, 3);
        }
        @Test @DisplayName("Null lanza excepción")
        void nullLanza() {
            assertThatThrownBy(() -> Ej15_MergeSort.sort(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("mergeArrays")
    class MergeArrays {
        @Test @DisplayName("Fusionar dos arrays ordenados")
        void fusionar() {
            int[] resultado = Ej15_MergeSort.mergeArrays(new int[]{1, 5, 8}, new int[]{2, 4, 9});
            assertThat(resultado).containsExactly(1, 2, 4, 5, 8, 9);
        }
        @Test @DisplayName("Fusionar con array vacío")
        void conVacio() {
            assertThat(Ej15_MergeSort.mergeArrays(new int[]{}, new int[]{1, 2}))
                    .containsExactly(1, 2);
            assertThat(Ej15_MergeSort.mergeArrays(new int[]{3, 4}, new int[]{}))
                    .containsExactly(3, 4);
        }
        @Test @DisplayName("Fusionar con duplicados entre arrays")
        void conDuplicados() {
            int[] resultado = Ej15_MergeSort.mergeArrays(new int[]{1, 3, 5}, new int[]{1, 2, 5});
            assertThat(resultado).containsExactly(1, 1, 2, 3, 5, 5);
        }
        @Test @DisplayName("Null lanza excepción")
        void nullLanza() {
            assertThatThrownBy(() -> Ej15_MergeSort.mergeArrays(null, new int[]{1}))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("sortBottomUp")
    class SortBottomUp {
        @Test @DisplayName("Bottom-up ordena correctamente")
        void ordena() {
            int[] datos = {6, 3, 8, 1, 5, 2, 7, 4};
            Ej15_MergeSort.sortBottomUp(datos);
            assertThat(datos).containsExactly(1, 2, 3, 4, 5, 6, 7, 8);
        }
        @Test @DisplayName("Tamaño no potencia de 2")
        void noPotencia() {
            int[] datos = {5, 1, 3};
            Ej15_MergeSort.sortBottomUp(datos);
            assertThat(datos).containsExactly(1, 3, 5);
        }
    }

    @Nested @DisplayName("contarInversiones")
    class ContarInversiones {
        @Test @DisplayName("Array ordenado: 0 inversiones")
        void ceroInversiones() {
            assertThat(Ej15_MergeSort.contarInversiones(new int[]{1, 2, 3, 4})).isEqualTo(0);
        }
        @Test @DisplayName("Array inverso: n*(n-1)/2 inversiones")
        void maxInversiones() {
            // {4,3,2,1} → 6 inversiones: (4,3)(4,2)(4,1)(3,2)(3,1)(2,1)
            assertThat(Ej15_MergeSort.contarInversiones(new int[]{4, 3, 2, 1})).isEqualTo(6);
        }
        @Test @DisplayName("{2, 1, 3} → 1 inversión")
        void unaInversion() {
            assertThat(Ej15_MergeSort.contarInversiones(new int[]{2, 1, 3})).isEqualTo(1);
        }
        @Test @DisplayName("No modifica el array original")
        void noModifica() {
            int[] datos = {3, 1, 2};
            Ej15_MergeSort.contarInversiones(datos);
            assertThat(datos).containsExactly(3, 1, 2);
        }
    }

    @Nested @DisplayName("estaOrdenado")
    class EstaOrdenado {
        @Test @DisplayName("Detecta correctamente")
        void detecta() {
            assertThat(Ej15_MergeSort.estaOrdenado(new int[]{1, 2, 3})).isTrue();
            assertThat(Ej15_MergeSort.estaOrdenado(new int[]{3, 1, 2})).isFalse();
            assertThat(Ej15_MergeSort.estaOrdenado(new int[]{})).isTrue();
        }
    }
}
