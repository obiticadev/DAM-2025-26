package com.masterclass.arrays.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej16 - Ordenación de Strings")
class Ej16_OrdenacionStringsTest {

    @Nested @DisplayName("sortInsertionAlfabetico")
    class SortAlfabetico {
        @Test @DisplayName("Ordena strings A→Z")
        void ordena() {
            String[] datos = {"Carlos", "Ana", "Beatriz"};
            Ej16_OrdenacionStrings.sortInsertionAlfabetico(datos);
            assertThat(datos).containsExactly("Ana", "Beatriz", "Carlos");
        }
        @Test @DisplayName("Sensible a mayúsculas (C < a en ASCII)")
        void caseSensitive() {
            String[] datos = {"banana", "Apple"};
            Ej16_OrdenacionStrings.sortInsertionAlfabetico(datos);
            // 'A' (65) < 'b' (98) → Apple va primero
            assertThat(datos).containsExactly("Apple", "banana");
        }
        @Test @DisplayName("Null lanza excepción")
        void nullLanza() {
            assertThatThrownBy(() -> Ej16_OrdenacionStrings.sortInsertionAlfabetico(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested @DisplayName("sortInsertionIgnoreCase")
    class SortIgnoreCase {
        @Test @DisplayName("Ordena ignorando mayúsculas/minúsculas")
        void ordena() {
            String[] datos = {"banana", "Apple", "cherry"};
            Ej16_OrdenacionStrings.sortInsertionIgnoreCase(datos);
            assertThat(datos).containsExactly("Apple", "banana", "cherry");
        }
    }

    @Nested @DisplayName("sortQuickAlfabetico")
    class SortQuick {
        @Test @DisplayName("QuickSort sobre Strings")
        void ordena() {
            String[] datos = {"mango", "apple", "banana", "cherry"};
            Ej16_OrdenacionStrings.sortQuickAlfabetico(datos);
            assertThat(datos).containsExactly("apple", "banana", "cherry", "mango");
        }
    }

    @Nested @DisplayName("sortPorLongitud")
    class SortPorLongitud {
        @Test @DisplayName("Ordena por longitud (más cortos primero)")
        void porLongitud() {
            String[] datos = {"estrella", "sol", "luna", "mar"};
            Ej16_OrdenacionStrings.sortPorLongitud(datos);
            assertThat(datos[0].length()).isLessThanOrEqualTo(datos[1].length());
            assertThat(datos[1].length()).isLessThanOrEqualTo(datos[2].length());
            assertThat(datos[2].length()).isLessThanOrEqualTo(datos[3].length());
        }
    }

    @Nested @DisplayName("sortDescendente")
    class SortDescendente {
        @Test @DisplayName("Ordena Z→A")
        void desc() {
            String[] datos = {"Ana", "Carlos", "Beatriz"};
            Ej16_OrdenacionStrings.sortDescendente(datos);
            assertThat(datos).containsExactly("Carlos", "Beatriz", "Ana");
        }
    }

    @Nested @DisplayName("buscarEnOrdenado")
    class BuscarEnOrdenado {
        @Test @DisplayName("Busca con búsqueda binaria en array ordenado")
        void buscar() {
            String[] datos = {"Ana", "Beatriz", "Carlos", "David", "Eva"};
            assertThat(Ej16_OrdenacionStrings.buscarEnOrdenado(datos, "Carlos")).isEqualTo(2);
            assertThat(Ej16_OrdenacionStrings.buscarEnOrdenado(datos, "Fernando")).isEqualTo(-1);
        }
    }

    @Nested @DisplayName("estaOrdenadoAlfabeticamente")
    class EstaOrdenado {
        @Test @DisplayName("Detecta array ordenado")
        void ordenado() {
            assertThat(Ej16_OrdenacionStrings.estaOrdenadoAlfabeticamente(
                    new String[]{"Ana", "Beatriz", "Carlos"})).isTrue();
        }
        @Test @DisplayName("Detecta array desordenado")
        void desordenado() {
            assertThat(Ej16_OrdenacionStrings.estaOrdenadoAlfabeticamente(
                    new String[]{"Carlos", "Ana"})).isFalse();
        }
    }
}
