package com.masterclass.arrays.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej14 - QuickSort")
class Ej14_QuickSortTest {

    @Nested @DisplayName("sort")
    class Sort {
        @Test @DisplayName("Ordenar array desordenado")
        void deberiaOrdenar() {
            int[] datos = {10, 7, 8, 9, 1, 5};
            Ej14_QuickSort.sort(datos);
            assertThat(datos).containsExactly(1, 5, 7, 8, 9, 10);
        }
        @Test @DisplayName("Array con duplicados")
        void conDuplicados() {
            int[] datos = {3, 3, 1, 2, 1};
            Ej14_QuickSort.sort(datos);
            assertThat(datos).containsExactly(1, 1, 2, 3, 3);
        }
        @Test @DisplayName("Array ya ordenado")
        void yaOrdenado() {
            int[] datos = {1, 2, 3, 4, 5};
            Ej14_QuickSort.sort(datos);
            assertThat(datos).containsExactly(1, 2, 3, 4, 5);
        }
        @Test @DisplayName("Array inversamente ordenado")
        void inverso() {
            int[] datos = {5, 4, 3, 2, 1};
            Ej14_QuickSort.sort(datos);
            assertThat(datos).containsExactly(1, 2, 3, 4, 5);
        }
        @Test @DisplayName("Null lanza excepción")
        void nullLanza() {
            assertThatThrownBy(() -> Ej14_QuickSort.sort(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
        @Test @DisplayName("Un solo elemento")
        void unElemento() {
            int[] datos = {42};
            Ej14_QuickSort.sort(datos);
            assertThat(datos).containsExactly(42);
        }
        @Test @DisplayName("Array grande")
        void grande() {
            int[] datos = {50, 23, 9, 18, 61, 32, 45, 7, 29, 14, 55, 3, 41, 67, 12};
            Ej14_QuickSort.sort(datos);
            for (int i = 0; i < datos.length - 1; i++) {
                assertThat(datos[i]).isLessThanOrEqualTo(datos[i + 1]);
            }
        }
    }

    @Nested @DisplayName("partition")
    class Partition {
        @Test @DisplayName("Partición coloca pivot en su sitio")
        void particion() {
            int[] datos = {3, 7, 8, 5, 2, 1, 9, 5, 4};
            int pivotIdx = Ej14_QuickSort.partition(datos, 0, datos.length - 1);
            int pivotVal = datos[pivotIdx];
            // Todos a la izquierda del pivot deben ser <= pivot
            for (int i = 0; i < pivotIdx; i++) {
                assertThat(datos[i]).isLessThanOrEqualTo(pivotVal);
            }
            // Todos a la derecha del pivot deben ser >= pivot
            for (int i = pivotIdx + 1; i < datos.length; i++) {
                assertThat(datos[i]).isGreaterThanOrEqualTo(pivotVal);
            }
        }
    }

    @Nested @DisplayName("swap")
    class Swap {
        @Test @DisplayName("Intercambia posiciones")
        void swap() {
            int[] datos = {1, 2, 3};
            Ej14_QuickSort.swap(datos, 0, 2);
            assertThat(datos).containsExactly(3, 2, 1);
        }
    }

    @Nested @DisplayName("sortDescendente")
    class SortDescendente {
        @Test @DisplayName("Mayor a menor")
        void desc() {
            int[] datos = {3, 1, 4, 1, 5, 9};
            Ej14_QuickSort.sortDescendente(datos);
            assertThat(datos).containsExactly(9, 5, 4, 3, 1, 1);
        }
    }

    @Nested @DisplayName("sortConMedianaDeTres")
    class MedianaDeTres {
        @Test @DisplayName("Ordena correctamente con mediana de tres")
        void ordena() {
            int[] datos = {9, 8, 7, 6, 5, 4, 3, 2, 1};
            Ej14_QuickSort.sortConMedianaDeTres(datos);
            assertThat(datos).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
        }
    }

    @Nested @DisplayName("partitionConPivotAleatorio")
    class PivotAleatorio {
        @Test @DisplayName("Partición con pivot aleatorio es válida")
        void pivotAleatorio() {
            int[] datos = {5, 3, 8, 1, 9, 2, 7};
            int pivotIdx = Ej14_QuickSort.partitionConPivotAleatorio(datos, 0, datos.length - 1);
            int pivotVal = datos[pivotIdx];
            for (int i = 0; i < pivotIdx; i++) {
                assertThat(datos[i]).isLessThanOrEqualTo(pivotVal);
            }
            for (int i = pivotIdx + 1; i < datos.length; i++) {
                assertThat(datos[i]).isGreaterThanOrEqualTo(pivotVal);
            }
        }
    }
}
