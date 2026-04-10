package com.masterclass.arrays.nivel04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej18 - Búsqueda Binaria")
class Ej18_BusquedaBinariaTest {

    private final int[] DATOS = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};

    @Nested @DisplayName("buscarIterativo")
    class BuscarIterativo {
        @Test @DisplayName("Encuentra elemento existente")
        void encuentra() {
            assertThat(Ej18_BusquedaBinaria.buscarIterativo(DATOS, 23)).isEqualTo(5);
        }
        @Test @DisplayName("Primer elemento")
        void primero() {
            assertThat(Ej18_BusquedaBinaria.buscarIterativo(DATOS, 2)).isEqualTo(0);
        }
        @Test @DisplayName("Último elemento")
        void ultimo() {
            assertThat(Ej18_BusquedaBinaria.buscarIterativo(DATOS, 91)).isEqualTo(9);
        }
        @Test @DisplayName("No existe → -1")
        void noExiste() {
            assertThat(Ej18_BusquedaBinaria.buscarIterativo(DATOS, 50)).isEqualTo(-1);
        }
        @Test @DisplayName("Array de un elemento")
        void unElemento() {
            assertThat(Ej18_BusquedaBinaria.buscarIterativo(new int[]{42}, 42)).isEqualTo(0);
            assertThat(Ej18_BusquedaBinaria.buscarIterativo(new int[]{42}, 0)).isEqualTo(-1);
        }
        @Test @DisplayName("Null lanza excepción")
        void nullLanza() {
            assertThatThrownBy(() -> Ej18_BusquedaBinaria.buscarIterativo(null, 1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("buscarRecursivo")
    class BuscarRecursivo {
        @Test @DisplayName("Encuentra elemento existente")
        void encuentra() {
            assertThat(Ej18_BusquedaBinaria.buscarRecursivo(DATOS, 38)).isEqualTo(6);
        }
        @Test @DisplayName("No existe → -1")
        void noExiste() {
            assertThat(Ej18_BusquedaBinaria.buscarRecursivo(DATOS, 99)).isEqualTo(-1);
        }
        @Test @DisplayName("Coherente con iterativo")
        void coherente() {
            for (int target : new int[]{2, 5, 8, 12, 16, 23, 38, 56, 72, 91, 0, 100}) {
                assertThat(Ej18_BusquedaBinaria.buscarRecursivo(DATOS, target))
                        .isEqualTo(Ej18_BusquedaBinaria.buscarIterativo(DATOS, target));
            }
        }
    }

    @Nested @DisplayName("buscarPuntoDeInsercion")
    class PuntoDeInsercion {
        @Test @DisplayName("Target existe → devuelve su índice")
        void existe() {
            assertThat(Ej18_BusquedaBinaria.buscarPuntoDeInsercion(DATOS, 23)).isEqualTo(5);
        }
        @Test @DisplayName("Target no existe → devuelve punto de inserción")
        void noExiste() {
            assertThat(Ej18_BusquedaBinaria.buscarPuntoDeInsercion(DATOS, 10)).isEqualTo(3);
        }
        @Test @DisplayName("Menor que todos → 0")
        void menorQueTodos() {
            assertThat(Ej18_BusquedaBinaria.buscarPuntoDeInsercion(DATOS, 1)).isEqualTo(0);
        }
        @Test @DisplayName("Mayor que todos → length")
        void mayorQueTodos() {
            assertThat(Ej18_BusquedaBinaria.buscarPuntoDeInsercion(DATOS, 100)).isEqualTo(10);
        }
    }

    @Nested @DisplayName("contieneEnOrdenado")
    class ContieneEnOrdenado {
        @Test @DisplayName("Elemento existe → true")
        void existe() {
            assertThat(Ej18_BusquedaBinaria.contieneEnOrdenado(DATOS, 56)).isTrue();
        }
        @Test @DisplayName("Elemento no existe → false")
        void noExiste() {
            assertThat(Ej18_BusquedaBinaria.contieneEnOrdenado(DATOS, 57)).isFalse();
        }
    }

    @Nested @DisplayName("buscarEnRango")
    class BuscarEnRango {
        @Test @DisplayName("Busca dentro del rango")
        void enRango() {
            assertThat(Ej18_BusquedaBinaria.buscarEnRango(DATOS, 23, 3, 7)).isEqualTo(5);
        }
        @Test @DisplayName("Fuera del rango → -1")
        void fueraDeRango() {
            assertThat(Ej18_BusquedaBinaria.buscarEnRango(DATOS, 2, 3, 7)).isEqualTo(-1);
        }
    }

    @Nested @DisplayName("contarComparaciones")
    class ContarComparaciones {
        @Test @DisplayName("Array de 10 elementos: máximo ~4 comparaciones")
        void maxComparaciones() {
            int comps = Ej18_BusquedaBinaria.contarComparaciones(DATOS, 23);
            assertThat(comps).isGreaterThan(0).isLessThanOrEqualTo(4);
        }
        @Test @DisplayName("Elemento no existente también cuenta")
        void noExistente() {
            int comps = Ej18_BusquedaBinaria.contarComparaciones(DATOS, 50);
            assertThat(comps).isGreaterThan(0).isLessThanOrEqualTo(4);
        }
    }
}
