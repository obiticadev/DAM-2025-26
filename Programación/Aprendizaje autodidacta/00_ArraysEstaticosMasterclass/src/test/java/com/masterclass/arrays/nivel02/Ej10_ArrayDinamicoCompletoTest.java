package com.masterclass.arrays.nivel02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 10 — Array Dinámico Completo (MiArrayList)
 */
@DisplayName("Ej10 - Array Dinámico Completo")
class Ej10_ArrayDinamicoCompletoTest {

    private Ej10_ArrayDinamicoCompleto lista;

    @BeforeEach
    void setUp() {
        lista = new Ej10_ArrayDinamicoCompleto();
    }

    // ─── Constructores ───────────────────────────────────────

    @Nested
    @DisplayName("Constructores")
    class Constructores {

        @Test
        @DisplayName("Constructor sin argumentos: size=0, capacity=4")
        void constructorPorDefecto() {
            assertThat(lista.size()).isEqualTo(0);
            assertThat(lista.capacity()).isEqualTo(4);
            assertThat(lista.isEmpty()).isTrue();
        }

        @Test
        @DisplayName("Constructor con capacidad personalizada")
        void constructorConCapacidad() {
            Ej10_ArrayDinamicoCompleto custom = new Ej10_ArrayDinamicoCompleto(16);
            assertThat(custom.capacity()).isEqualTo(16);
            assertThat(custom.size()).isEqualTo(0);
        }

        @Test
        @DisplayName("Capacidad menor que mínima usa la mínima (4)")
        void capacidadMenorQueMinima() {
            Ej10_ArrayDinamicoCompleto small = new Ej10_ArrayDinamicoCompleto(1);
            assertThat(small.capacity()).isGreaterThanOrEqualTo(4);
        }

        @Test
        @DisplayName("Capacidad negativa lanza excepción")
        void capacidadNegativa() {
            assertThatThrownBy(() -> new Ej10_ArrayDinamicoCompleto(-5))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── add(int) ────────────────────────────────────────────

    @Nested
    @DisplayName("add(valor)")
    class AddSimple {

        @Test
        @DisplayName("Añadir un elemento al final")
        void deberiaAnadir() {
            lista.add(10);
            assertThat(lista.size()).isEqualTo(1);
            assertThat(lista.get(0)).isEqualTo(10);
        }

        @Test
        @DisplayName("Añadir múltiples elementos mantiene orden")
        void deberiaManteenerOrden() {
            lista.add(10);
            lista.add(20);
            lista.add(30);
            assertThat(lista.toArray()).containsExactly(10, 20, 30);
        }

        @Test
        @DisplayName("Auto-grow al sobrepasar capacidad")
        void deberiaCrecer() {
            for (int i = 0; i < 5; i++) lista.add(i);
            assertThat(lista.size()).isEqualTo(5);
            assertThat(lista.capacity()).isEqualTo(8);
        }
    }

    // ─── add(int, int) ───────────────────────────────────────

    @Nested
    @DisplayName("add(index, valor)")
    class AddConIndice {

        @Test
        @DisplayName("Insertar al inicio")
        void insertarAlInicio() {
            lista.add(20);
            lista.add(30);
            lista.add(0, 10);
            assertThat(lista.toArray()).containsExactly(10, 20, 30);
        }

        @Test
        @DisplayName("Insertar en medio")
        void insertarEnMedio() {
            lista.add(10);
            lista.add(30);
            lista.add(1, 20);
            assertThat(lista.toArray()).containsExactly(10, 20, 30);
        }

        @Test
        @DisplayName("Índice inválido lanza IndexOutOfBoundsException")
        void indiceInvalido() {
            lista.add(10);
            assertThatThrownBy(() -> lista.add(-1, 5))
                    .isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> lista.add(2, 5))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    // ─── get / set ───────────────────────────────────────────

    @Nested
    @DisplayName("get y set")
    class GetSet {

        @Test
        @DisplayName("get devuelve el valor correcto")
        void getDevuelveValor() {
            lista.add(10);
            lista.add(20);
            assertThat(lista.get(0)).isEqualTo(10);
            assertThat(lista.get(1)).isEqualTo(20);
        }

        @Test
        @DisplayName("set reemplaza y devuelve el valor anterior")
        void setReemplazaYDevuelve() {
            lista.add(10);
            lista.add(20);
            int anterior = lista.set(0, 99);
            assertThat(anterior).isEqualTo(10);
            assertThat(lista.get(0)).isEqualTo(99);
        }

        @Test
        @DisplayName("get con índice inválido lanza excepción")
        void getIndiceInvalido() {
            assertThatThrownBy(() -> lista.get(0))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("set con índice inválido lanza excepción")
        void setIndiceInvalido() {
            assertThatThrownBy(() -> lista.set(0, 10))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    // ─── remove ──────────────────────────────────────────────

    @Nested
    @DisplayName("remove")
    class Remove {

        @Test
        @DisplayName("Remove devuelve el valor eliminado")
        void deberiaDevovlerValor() {
            lista.add(10);
            lista.add(20);
            lista.add(30);
            int removed = lista.remove(1);
            assertThat(removed).isEqualTo(20);
            assertThat(lista.toArray()).containsExactly(10, 30);
        }

        @Test
        @DisplayName("Remove del último elemento")
        void removeUltimo() {
            lista.add(10);
            lista.add(20);
            lista.remove(1);
            assertThat(lista.size()).isEqualTo(1);
            assertThat(lista.get(0)).isEqualTo(10);
        }

        @Test
        @DisplayName("Auto-shrink al reducir ocupación por debajo del 25%")
        void deberiaShrink() {
            // Forzar grow a 16
            for (int i = 0; i < 9; i++) lista.add(i);
            int capTrasGrow = lista.capacity(); // debe ser 16

            // Eliminar hasta que size <= capacity/4
            while (lista.size() > 2) {
                lista.remove(lista.size() - 1);
            }

            // Verificar que la capacidad se ha reducido (pero nunca debajo de 4)
            assertThat(lista.capacity()).isLessThan(capTrasGrow);
            assertThat(lista.capacity()).isGreaterThanOrEqualTo(4);
        }

        @Test
        @DisplayName("Índice inválido lanza IndexOutOfBoundsException")
        void indiceInvalido() {
            lista.add(10);
            assertThatThrownBy(() -> lista.remove(-1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> lista.remove(1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    // ─── contains / indexOf ──────────────────────────────────

    @Nested
    @DisplayName("contains e indexOf")
    class ContainsIndexOf {

        @Test
        @DisplayName("contains devuelve true si el valor existe")
        void containsTrue() {
            lista.add(10);
            lista.add(20);
            lista.add(30);
            assertThat(lista.contains(20)).isTrue();
        }

        @Test
        @DisplayName("contains devuelve false si el valor no existe")
        void containsFalse() {
            lista.add(10);
            assertThat(lista.contains(99)).isFalse();
        }

        @Test
        @DisplayName("contains en lista vacía devuelve false")
        void containsVacia() {
            assertThat(lista.contains(10)).isFalse();
        }

        @Test
        @DisplayName("indexOf devuelve primer índice")
        void indexOfPrimero() {
            lista.add(10);
            lista.add(20);
            lista.add(10);
            assertThat(lista.indexOf(10)).isEqualTo(0);
            assertThat(lista.indexOf(20)).isEqualTo(1);
        }

        @Test
        @DisplayName("indexOf devuelve -1 si no existe")
        void indexOfInexistente() {
            lista.add(10);
            assertThat(lista.indexOf(99)).isEqualTo(-1);
        }
    }

    // ─── isEmpty / size ──────────────────────────────────────

    @Nested
    @DisplayName("isEmpty y size")
    class IsEmptySize {

        @Test
        @DisplayName("isEmpty es true al crear, false al añadir")
        void isEmptyCorrecta() {
            assertThat(lista.isEmpty()).isTrue();
            lista.add(1);
            assertThat(lista.isEmpty()).isFalse();
        }

        @Test
        @DisplayName("size refleja operaciones add/remove")
        void sizeRefleja() {
            lista.add(1);
            lista.add(2);
            assertThat(lista.size()).isEqualTo(2);
            lista.remove(0);
            assertThat(lista.size()).isEqualTo(1);
        }
    }

    // ─── toArray ─────────────────────────────────────────────

    @Nested
    @DisplayName("toArray")
    class ToArray {

        @Test
        @DisplayName("toArray devuelve copia con solo elementos lógicos")
        void deberiaDevolverCopia() {
            lista.add(1);
            lista.add(2);
            lista.add(3);
            int[] arr = lista.toArray();
            assertThat(arr).containsExactly(1, 2, 3);
            assertThat(arr).hasSize(3);
        }

        @Test
        @DisplayName("toArray devuelve array independiente")
        void deberiaSerIndependiente() {
            lista.add(10);
            int[] arr = lista.toArray();
            arr[0] = 999;
            assertThat(lista.get(0)).isEqualTo(10);
        }
    }

    // ─── toString ────────────────────────────────────────────

    @Nested
    @DisplayName("toString")
    class ToStringTest {

        @Test
        @DisplayName("Lista vacía → '[]'")
        void vacia() {
            assertThat(lista.toString()).isEqualTo("[]");
        }

        @Test
        @DisplayName("Lista con elementos → '[10, 20, 30]'")
        void conElementos() {
            lista.add(10);
            lista.add(20);
            lista.add(30);
            assertThat(lista.toString()).isEqualTo("[10, 20, 30]");
        }

        @Test
        @DisplayName("Un solo elemento → '[42]'")
        void unElemento() {
            lista.add(42);
            assertThat(lista.toString()).isEqualTo("[42]");
        }
    }

    // ─── Estrés: ciclo completo ──────────────────────────────

    @Nested
    @DisplayName("Test de estrés")
    class Estres {

        @Test
        @DisplayName("Insertar y eliminar 100 elementos mantiene integridad")
        void cicloDe100() {
            for (int i = 0; i < 100; i++) {
                lista.add(i);
            }
            assertThat(lista.size()).isEqualTo(100);
            assertThat(lista.get(0)).isEqualTo(0);
            assertThat(lista.get(99)).isEqualTo(99);

            // Eliminar todos desde el principio
            while (!lista.isEmpty()) {
                lista.remove(0);
            }
            assertThat(lista.size()).isEqualTo(0);
            assertThat(lista.isEmpty()).isTrue();
            assertThat(lista.capacity()).isGreaterThanOrEqualTo(4);
        }
    }
}
