package com.masterclass.arrays.nivel04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej20 - Búsqueda en Matriz 2D")
class Ej20_BusquedaEnMatrizTest {

    private final int[][] MATRIZ_ORDENADA = {
            { 1,  4,  7, 11},
            { 2,  5,  8, 12},
            { 3,  6,  9, 16},
            {10, 13, 14, 17}
    };

    @Nested @DisplayName("buscarFuerzaBruta")
    class BuscarFuerzaBruta {
        @Test @DisplayName("Encuentra elemento existente")
        void encuentra() {
            int[] pos = Ej20_BusquedaEnMatriz.buscarFuerzaBruta(MATRIZ_ORDENADA, 14);
            assertThat(pos).containsExactly(3, 2);
        }
        @Test @DisplayName("No existe → null")
        void noExiste() {
            assertThat(Ej20_BusquedaEnMatriz.buscarFuerzaBruta(MATRIZ_ORDENADA, 15)).isNull();
        }
        @Test @DisplayName("Null lanza excepción")
        void nullLanza() {
            assertThatThrownBy(() -> Ej20_BusquedaEnMatriz.buscarFuerzaBruta(null, 1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("buscarStaircase")
    class BuscarStaircase {
        @Test @DisplayName("Encuentra 14 en [3][2]")
        void encuentra14() {
            int[] pos = Ej20_BusquedaEnMatriz.buscarStaircase(MATRIZ_ORDENADA, 14);
            assertThat(pos).containsExactly(3, 2);
        }
        @Test @DisplayName("Encuentra 1 en [0][0] (esquina)")
        void encuentraEsquina() {
            int[] pos = Ej20_BusquedaEnMatriz.buscarStaircase(MATRIZ_ORDENADA, 1);
            assertThat(pos).containsExactly(0, 0);
        }
        @Test @DisplayName("Encuentra 17 en [3][3] (última posición)")
        void encuentraUltima() {
            int[] pos = Ej20_BusquedaEnMatriz.buscarStaircase(MATRIZ_ORDENADA, 17);
            assertThat(pos).containsExactly(3, 3);
        }
        @Test @DisplayName("No existe → null")
        void noExiste() {
            assertThat(Ej20_BusquedaEnMatriz.buscarStaircase(MATRIZ_ORDENADA, 15)).isNull();
            assertThat(Ej20_BusquedaEnMatriz.buscarStaircase(MATRIZ_ORDENADA, 0)).isNull();
            assertThat(Ej20_BusquedaEnMatriz.buscarStaircase(MATRIZ_ORDENADA, 20)).isNull();
        }
        @Test @DisplayName("Coherente con fuerza bruta")
        void coherente() {
            for (int target : new int[]{1, 5, 9, 13, 17, 0, 15, 20}) {
                int[] fb = Ej20_BusquedaEnMatriz.buscarFuerzaBruta(MATRIZ_ORDENADA, target);
                int[] st = Ej20_BusquedaEnMatriz.buscarStaircase(MATRIZ_ORDENADA, target);
                if (fb == null) {
                    assertThat(st).isNull();
                } else {
                    assertThat(st).isNotNull();
                    // Ambos deben encontrar el mismo valor
                    assertThat(MATRIZ_ORDENADA[st[0]][st[1]]).isEqualTo(target);
                }
            }
        }
    }

    @Nested @DisplayName("buscarBinariaPorFilas")
    class BuscarBinariaPorFilas {
        @Test @DisplayName("Encuentra con binaria por filas")
        void encuentra() {
            int[] pos = Ej20_BusquedaEnMatriz.buscarBinariaPorFilas(MATRIZ_ORDENADA, 9);
            assertThat(pos).isNotNull();
            assertThat(MATRIZ_ORDENADA[pos[0]][pos[1]]).isEqualTo(9);
        }
        @Test @DisplayName("No existe → null")
        void noExiste() {
            assertThat(Ej20_BusquedaEnMatriz.buscarBinariaPorFilas(MATRIZ_ORDENADA, 15)).isNull();
        }
    }

    @Nested @DisplayName("contarMenores")
    class ContarMenores {
        @Test @DisplayName("Contar menores que 5")
        void menoresQue5() {
            // Elementos menores que 5: 1,2,3,4 → 4
            assertThat(Ej20_BusquedaEnMatriz.contarMenores(MATRIZ_ORDENADA, 5)).isEqualTo(4);
        }
        @Test @DisplayName("Contar menores que 1: ninguno")
        void menoresQue1() {
            assertThat(Ej20_BusquedaEnMatriz.contarMenores(MATRIZ_ORDENADA, 1)).isEqualTo(0);
        }
        @Test @DisplayName("Contar menores que 100: todos")
        void menoresQue100() {
            assertThat(Ej20_BusquedaEnMatriz.contarMenores(MATRIZ_ORDENADA, 100)).isEqualTo(16);
        }
    }

    @Nested @DisplayName("existeEnMatriz")
    class ExisteEnMatriz {
        @Test @DisplayName("Existe → true")
        void existe() {
            assertThat(Ej20_BusquedaEnMatriz.existeEnMatriz(MATRIZ_ORDENADA, 8)).isTrue();
        }
        @Test @DisplayName("No existe → false")
        void noExiste() {
            assertThat(Ej20_BusquedaEnMatriz.existeEnMatriz(MATRIZ_ORDENADA, 15)).isFalse();
        }
    }

    @Nested @DisplayName("buscarMinimo")
    class BuscarMinimo {
        @Test @DisplayName("Mínimo de la matriz")
        void minimo() {
            int[] r = Ej20_BusquedaEnMatriz.buscarMinimo(MATRIZ_ORDENADA);
            assertThat(r[0]).isEqualTo(1);
            assertThat(r[1]).isEqualTo(0);
            assertThat(r[2]).isEqualTo(0);
        }
    }

    @Nested @DisplayName("buscarMaximo")
    class BuscarMaximo {
        @Test @DisplayName("Máximo de la matriz")
        void maximo() {
            int[] r = Ej20_BusquedaEnMatriz.buscarMaximo(MATRIZ_ORDENADA);
            assertThat(r[0]).isEqualTo(17);
            assertThat(r[1]).isEqualTo(3);
            assertThat(r[2]).isEqualTo(3);
        }
    }
}
