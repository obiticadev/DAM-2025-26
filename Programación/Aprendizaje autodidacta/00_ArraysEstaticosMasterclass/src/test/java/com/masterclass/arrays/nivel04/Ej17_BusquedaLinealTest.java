package com.masterclass.arrays.nivel04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej17 - Búsqueda Lineal")
class Ej17_BusquedaLinealTest {

    @Nested @DisplayName("buscarPrimero")
    class BuscarPrimero {
        @Test @DisplayName("Encuentra primera ocurrencia")
        void encuentra() {
            assertThat(Ej17_BusquedaLineal.buscarPrimero(new int[]{4, 2, 7, 2, 9}, 2)).isEqualTo(1);
        }
        @Test @DisplayName("Elemento no existe → -1")
        void noExiste() {
            assertThat(Ej17_BusquedaLineal.buscarPrimero(new int[]{1, 2, 3}, 99)).isEqualTo(-1);
        }
        @Test @DisplayName("Null lanza excepción")
        void nullLanza() {
            assertThatThrownBy(() -> Ej17_BusquedaLineal.buscarPrimero(null, 1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("buscarUltimo")
    class BuscarUltimo {
        @Test @DisplayName("Encuentra última ocurrencia")
        void encuentra() {
            assertThat(Ej17_BusquedaLineal.buscarUltimo(new int[]{4, 2, 7, 2, 9}, 2)).isEqualTo(3);
        }
        @Test @DisplayName("Elemento único")
        void unico() {
            assertThat(Ej17_BusquedaLineal.buscarUltimo(new int[]{1, 2, 3}, 2)).isEqualTo(1);
        }
        @Test @DisplayName("No existe → -1")
        void noExiste() {
            assertThat(Ej17_BusquedaLineal.buscarUltimo(new int[]{1, 2, 3}, 99)).isEqualTo(-1);
        }
    }

    @Nested @DisplayName("contarOcurrencias")
    class ContarOcurrencias {
        @Test @DisplayName("Contar varias ocurrencias")
        void contar() {
            assertThat(Ej17_BusquedaLineal.contarOcurrencias(new int[]{3, 1, 3, 5, 3}, 3)).isEqualTo(3);
        }
        @Test @DisplayName("Sin ocurrencias → 0")
        void cero() {
            assertThat(Ej17_BusquedaLineal.contarOcurrencias(new int[]{1, 2, 3}, 99)).isEqualTo(0);
        }
        @Test @DisplayName("Null o vacío → 0")
        void nullVacio() {
            assertThat(Ej17_BusquedaLineal.contarOcurrencias(null, 1)).isEqualTo(0);
            assertThat(Ej17_BusquedaLineal.contarOcurrencias(new int[]{}, 1)).isEqualTo(0);
        }
    }

    @Nested @DisplayName("buscarTodosIndices")
    class BuscarTodosIndices {
        @Test @DisplayName("Devuelve todos los índices")
        void todosIndices() {
            int[] indices = Ej17_BusquedaLineal.buscarTodosIndices(new int[]{4, 2, 7, 2, 9, 2}, 2);
            assertThat(indices).containsExactly(1, 3, 5);
        }
        @Test @DisplayName("Sin ocurrencias → array vacío")
        void sinOcurrencias() {
            assertThat(Ej17_BusquedaLineal.buscarTodosIndices(new int[]{1, 2, 3}, 99)).isEmpty();
        }
    }

    @Nested @DisplayName("contiene")
    class Contiene {
        @Test @DisplayName("Existe → true")
        void existe() {
            assertThat(Ej17_BusquedaLineal.contiene(new int[]{1, 3, 5}, 3)).isTrue();
        }
        @Test @DisplayName("No existe → false")
        void noExiste() {
            assertThat(Ej17_BusquedaLineal.contiene(new int[]{1, 3, 5}, 4)).isFalse();
        }
    }

    @Nested @DisplayName("buscarMinimo")
    class BuscarMinimo {
        @Test @DisplayName("Encuentra mínimo y su índice")
        void minimo() {
            int[] resultado = Ej17_BusquedaLineal.buscarMinimo(new int[]{5, 3, 8, 1, 9});
            assertThat(resultado).containsExactly(1, 3);
        }
        @Test @DisplayName("Vacío lanza excepción")
        void vacioLanza() {
            assertThatThrownBy(() -> Ej17_BusquedaLineal.buscarMinimo(new int[]{}))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("buscarMaximo")
    class BuscarMaximo {
        @Test @DisplayName("Encuentra máximo y su índice")
        void maximo() {
            int[] resultado = Ej17_BusquedaLineal.buscarMaximo(new int[]{5, 3, 8, 1, 9});
            assertThat(resultado).containsExactly(9, 4);
        }
    }

    @Nested @DisplayName("buscarEnStrings")
    class BuscarEnStrings {
        @Test @DisplayName("Encuentra string con equals")
        void encuentra() {
            String[] datos = {"hola", "mundo", "java"};
            assertThat(Ej17_BusquedaLineal.buscarEnStrings(datos, "mundo")).isEqualTo(1);
        }
        @Test @DisplayName("No existe → -1")
        void noExiste() {
            assertThat(Ej17_BusquedaLineal.buscarEnStrings(new String[]{"a", "b"}, "c")).isEqualTo(-1);
        }
        @Test @DisplayName("Buscar null encuentra primera posición null")
        void buscarNull() {
            String[] datos = {"hola", null, "mundo"};
            assertThat(Ej17_BusquedaLineal.buscarEnStrings(datos, null)).isEqualTo(1);
        }
    }
}
