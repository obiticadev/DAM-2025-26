package com.masterclass.arrays.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej13 - Insertion Sort")
class Ej13_InsertionSortTest {

    @Nested @DisplayName("sortAscendente")
    class SortAscendente {
        @Test @DisplayName("Ordenar array desordenado")
        void deberiaOrdenar() {
            int[] datos = {12, 11, 13, 5, 6};
            Ej13_InsertionSort.sortAscendente(datos);
            assertThat(datos).containsExactly(5, 6, 11, 12, 13);
        }
        @Test @DisplayName("Array con duplicados")
        void conDuplicados() {
            int[] datos = {4, 2, 4, 1, 2};
            Ej13_InsertionSort.sortAscendente(datos);
            assertThat(datos).containsExactly(1, 2, 2, 4, 4);
        }
        @Test @DisplayName("Array inversamente ordenado")
        void inverso() {
            int[] datos = {5, 4, 3, 2, 1};
            Ej13_InsertionSort.sortAscendente(datos);
            assertThat(datos).containsExactly(1, 2, 3, 4, 5);
        }
        @Test @DisplayName("Null lanza excepción")
        void nullExcepcion() {
            assertThatThrownBy(() -> Ej13_InsertionSort.sortAscendente(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("sortDescendente")
    class SortDescendente {
        @Test @DisplayName("Mayor a menor")
        void desc() {
            int[] datos = {1, 5, 3, 2, 4};
            Ej13_InsertionSort.sortDescendente(datos);
            assertThat(datos).containsExactly(5, 4, 3, 2, 1);
        }
    }

    @Nested @DisplayName("sortConContador")
    class SortConContador {
        @Test @DisplayName("Mejor caso: array ya ordenado")
        void mejorCaso() {
            int[] datos = {1, 2, 3, 4, 5};
            int[] stats = Ej13_InsertionSort.sortConContador(datos);
            assertThat(stats).isNotNull().hasSize(2);
            assertThat(stats[0]).isEqualTo(4);  // n-1 comparaciones
            assertThat(stats[1]).isEqualTo(0);  // 0 desplazamientos
        }
        @Test @DisplayName("Peor caso: array inversamente ordenado")
        void peorCaso() {
            int[] datos = {5, 4, 3, 2, 1};
            int[] stats = Ej13_InsertionSort.sortConContador(datos);
            assertThat(stats[0]).isEqualTo(10); // n*(n-1)/2 = 10
            assertThat(stats[1]).isEqualTo(10);
            assertThat(datos).containsExactly(1, 2, 3, 4, 5);
        }
    }

    @Nested @DisplayName("sortRango")
    class SortRango {
        @Test @DisplayName("Ordena solo el rango indicado")
        void rango() {
            int[] datos = {9, 5, 3, 8, 1, 7};
            Ej13_InsertionSort.sortRango(datos, 1, 4);
            assertThat(datos[0]).isEqualTo(9);
            assertThat(datos[5]).isEqualTo(7);
            // posiciones 1-4 deben estar ordenadas
            for (int i = 1; i < 4; i++) {
                assertThat(datos[i]).isLessThanOrEqualTo(datos[i + 1]);
            }
        }
    }

    @Nested @DisplayName("insertarEnOrdenado")
    class InsertarEnOrdenado {
        @Test @DisplayName("Insertar manteniendo orden")
        void insertar() {
            int[] datos = {10, 30, 50, 0, 0};
            int nuevoSize = Ej13_InsertionSort.insertarEnOrdenado(datos, 3, 20);
            assertThat(nuevoSize).isEqualTo(4);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(30);
            assertThat(datos[3]).isEqualTo(50);
        }
        @Test @DisplayName("Insertar el mayor al final")
        void insertarMayor() {
            int[] datos = {10, 20, 0};
            int nuevoSize = Ej13_InsertionSort.insertarEnOrdenado(datos, 2, 30);
            assertThat(nuevoSize).isEqualTo(3);
            assertThat(datos[2]).isEqualTo(30);
        }
        @Test @DisplayName("Array lleno lanza excepción")
        void lleno() {
            assertThatThrownBy(() -> Ej13_InsertionSort.insertarEnOrdenado(new int[]{1, 2, 3}, 3, 4))
                    .isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested @DisplayName("ordenarPorInsercionBinaria")
    class InsercionBinaria {
        @Test @DisplayName("Ordena correctamente con inserción binaria")
        void ordena() {
            int[] datos = {7, 3, 9, 1, 5};
            Ej13_InsertionSort.ordenarPorInsercionBinaria(datos);
            assertThat(datos).containsExactly(1, 3, 5, 7, 9);
        }
    }

    @Nested @DisplayName("esCasiOrdenado")
    class CasiOrdenado {
        @Test @DisplayName("Array ordenado con desplazamiento 0")
        void ordenado() {
            assertThat(Ej13_InsertionSort.esCasiOrdenado(new int[]{1, 2, 3, 4}, 0)).isTrue();
        }
        @Test @DisplayName("Array con un swap: desplazamiento 1")
        void unSwap() {
            assertThat(Ej13_InsertionSort.esCasiOrdenado(new int[]{2, 1, 3, 4}, 1)).isTrue();
        }
        @Test @DisplayName("Array aleatorio NO es casi-ordenado con desplazamiento bajo")
        void noOrdenado() {
            assertThat(Ej13_InsertionSort.esCasiOrdenado(new int[]{5, 1, 4, 2, 3}, 1)).isFalse();
        }
    }
}
