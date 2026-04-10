package com.masterclass.arrays.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej12 - Selection Sort")
class Ej12_SelectionSortTest {

    @Nested @DisplayName("sortAscendente")
    class SortAscendente {
        @Test @DisplayName("Ordenar correctamente")
        void deberiaOrdenar() {
            int[] datos = {29, 10, 14, 37, 13};
            Ej12_SelectionSort.sortAscendente(datos);
            assertThat(datos).containsExactly(10, 13, 14, 29, 37);
        }
        @Test @DisplayName("Con negativos y ceros")
        void conNegativosYCeros() {
            int[] datos = {0, -5, 3, -1, 7};
            Ej12_SelectionSort.sortAscendente(datos);
            assertThat(datos).containsExactly(-5, -1, 0, 3, 7);
        }
        @Test @DisplayName("Un solo elemento")
        void unElemento() {
            int[] datos = {42};
            Ej12_SelectionSort.sortAscendente(datos);
            assertThat(datos).containsExactly(42);
        }
        @Test @DisplayName("Null lanza excepción")
        void nullLanza() {
            assertThatThrownBy(() -> Ej12_SelectionSort.sortAscendente(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("sortDescendente")
    class SortDescendente {
        @Test @DisplayName("Ordena de mayor a menor")
        void desc() {
            int[] datos = {3, 1, 4, 1, 5};
            Ej12_SelectionSort.sortDescendente(datos);
            assertThat(datos).containsExactly(5, 4, 3, 1, 1);
        }
    }

    @Nested @DisplayName("encontrarIndiceMinimo")
    class IndiceMinimo {
        @Test @DisplayName("Encuentra el mínimo desde una posición")
        void minDesde() {
            int[] datos = {5, 3, 8, 1, 9};
            assertThat(Ej12_SelectionSort.encontrarIndiceMinimo(datos, 0)).isEqualTo(3);
            assertThat(Ej12_SelectionSort.encontrarIndiceMinimo(datos, 2)).isEqualTo(3);
            assertThat(Ej12_SelectionSort.encontrarIndiceMinimo(datos, 4)).isEqualTo(4);
        }
    }

    @Nested @DisplayName("encontrarIndiceMaximo")
    class IndiceMaximo {
        @Test @DisplayName("Encuentra el máximo desde una posición")
        void maxDesde() {
            int[] datos = {5, 3, 8, 1, 9};
            assertThat(Ej12_SelectionSort.encontrarIndiceMaximo(datos, 0)).isEqualTo(4);
            assertThat(Ej12_SelectionSort.encontrarIndiceMaximo(datos, 0)).isEqualTo(4);
        }
    }

    @Nested @DisplayName("sortConContador")
    class SortConContador {
        @Test @DisplayName("Devuelve estadísticas")
        void conContador() {
            int[] datos = {4, 3, 2, 1};
            int[] stats = Ej12_SelectionSort.sortConContador(datos);
            assertThat(stats).isNotNull().hasSize(2);
            assertThat(stats[0]).isGreaterThan(0); // comparaciones
            assertThat(datos).containsExactly(1, 2, 3, 4);
        }
    }

    @Nested @DisplayName("sortDoble")
    class SortDoble {
        @Test @DisplayName("Double selection sort")
        void dobleSelection() {
            int[] datos = {8, 3, 6, 1, 5, 9, 2, 7, 4};
            Ej12_SelectionSort.sortDoble(datos);
            assertThat(datos).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
        }
    }

    @Nested @DisplayName("obtenerKMenores")
    class KMenores {
        @Test @DisplayName("Obtener los 3 menores")
        void tresMenores() {
            int[] datos = {50, 10, 30, 20, 40};
            int[] resultado = Ej12_SelectionSort.obtenerKMenores(datos, 3);
            assertThat(resultado).containsExactly(10, 20, 30);
        }
        @Test @DisplayName("K=0 devuelve vacío")
        void kCero() {
            assertThat(Ej12_SelectionSort.obtenerKMenores(new int[]{1, 2}, 0)).isEmpty();
        }
        @Test @DisplayName("No modifica el original")
        void noModifica() {
            int[] datos = {30, 10, 20};
            Ej12_SelectionSort.obtenerKMenores(datos, 2);
            assertThat(datos).containsExactly(30, 10, 20);
        }
        @Test @DisplayName("K > length lanza excepción")
        void kMayorLanza() {
            assertThatThrownBy(() -> Ej12_SelectionSort.obtenerKMenores(new int[]{1}, 5))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
