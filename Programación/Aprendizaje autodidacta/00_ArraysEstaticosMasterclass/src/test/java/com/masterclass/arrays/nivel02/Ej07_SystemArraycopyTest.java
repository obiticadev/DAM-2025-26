package com.masterclass.arrays.nivel02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 07 — System.arraycopy
 */
@DisplayName("Ej07 - System.arraycopy")
class Ej07_SystemArraycopyTest {

    // ─── copiarConSystemArraycopy ────────────────────────────

    @Nested
    @DisplayName("copiarConSystemArraycopy")
    class CopiarCompleto {

        @Test
        @DisplayName("Debe copiar todos los valores")
        void deberiaCopiarTodo() {
            int[] copia = Ej07_SystemArraycopy.copiarConSystemArraycopy(new int[]{10, 20, 30});
            assertThat(copia).containsExactly(10, 20, 30);
        }

        @Test
        @DisplayName("Copia es objeto distinto del original")
        void deberiaSerDistinto() {
            int[] original = {1, 2, 3};
            int[] copia = Ej07_SystemArraycopy.copiarConSystemArraycopy(original);
            assertThat(copia).isNotSameAs(original);
        }

        @Test
        @DisplayName("Null lanza excepción")
        void deberiaLanzarSiNull() {
            assertThatThrownBy(() -> Ej07_SystemArraycopy.copiarConSystemArraycopy(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── copiarRango ─────────────────────────────────────────

    @Nested
    @DisplayName("copiarRango")
    class CopiarRango {

        @Test
        @DisplayName("copiarRango(arr, 1, 3) → 3 elementos desde pos 1")
        void deberiaCopiarRango() {
            int[] copia = Ej07_SystemArraycopy.copiarRango(new int[]{10, 20, 30, 40, 50}, 1, 3);
            assertThat(copia).containsExactly(20, 30, 40);
        }

        @Test
        @DisplayName("Longitud 0 devuelve array vacío")
        void longitudCeroDevuelveVacio() {
            assertThat(Ej07_SystemArraycopy.copiarRango(new int[]{1, 2}, 0, 0)).isEmpty();
        }

        @Test
        @DisplayName("Rango fuera de límites lanza excepción")
        void deberiaLanzarSiFueraDeLimites() {
            assertThatThrownBy(() -> Ej07_SystemArraycopy.copiarRango(new int[]{1, 2, 3}, 2, 5))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("Parámetros negativos lanzan excepción")
        void deberiaLanzarSiNegativo() {
            assertThatThrownBy(() -> Ej07_SystemArraycopy.copiarRango(new int[]{1, 2}, -1, 1))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> Ej07_SystemArraycopy.copiarRango(new int[]{1, 2}, 0, -1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── concatenarDos ───────────────────────────────────────

    @Nested
    @DisplayName("concatenarDos")
    class ConcatenarDos {

        @Test
        @DisplayName("{1,2} + {3,4,5} → {1,2,3,4,5}")
        void deberiaConcatenar() {
            int[] resultado = Ej07_SystemArraycopy.concatenarDos(new int[]{1, 2}, new int[]{3, 4, 5});
            assertThat(resultado).containsExactly(1, 2, 3, 4, 5);
        }

        @Test
        @DisplayName("Concatenar con vacío devuelve copia del otro")
        void concatenarConVacio() {
            assertThat(Ej07_SystemArraycopy.concatenarDos(new int[]{}, new int[]{7, 8}))
                    .containsExactly(7, 8);
            assertThat(Ej07_SystemArraycopy.concatenarDos(new int[]{7, 8}, new int[]{}))
                    .containsExactly(7, 8);
        }

        @Test
        @DisplayName("Null lanza excepción")
        void deberiaLanzarSiNull() {
            assertThatThrownBy(() -> Ej07_SystemArraycopy.concatenarDos(null, new int[]{1}))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── concatenarMultiples ─────────────────────────────────

    @Nested
    @DisplayName("concatenarMultiples")
    class ConcatenarMultiples {

        @Test
        @DisplayName("Concatenar 3 arrays")
        void deberiaConcatenarTres() {
            int[] resultado = Ej07_SystemArraycopy.concatenarMultiples(
                    new int[]{1, 2}, new int[]{3}, new int[]{4, 5, 6});
            assertThat(resultado).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("Sin argumentos devuelve array vacío")
        void sinArgumentosDevuelveVacio() {
            assertThat(Ej07_SystemArraycopy.concatenarMultiples()).isEmpty();
        }

        @Test
        @DisplayName("Un solo array devuelve copia")
        void unSoloArray() {
            int[] resultado = Ej07_SystemArraycopy.concatenarMultiples(new int[]{42});
            assertThat(resultado).containsExactly(42);
        }
    }

    // ─── insertarConDesplazamiento ───────────────────────────

    @Nested
    @DisplayName("insertarConDesplazamiento")
    class InsertarConDesplazamiento {

        @Test
        @DisplayName("Insertar con System.arraycopy")
        void deberiaInsertar() {
            int[] datos = {10, 20, 40, 50, 0, 0};
            int nuevoSize = Ej07_SystemArraycopy.insertarConDesplazamiento(datos, 4, 2, 30);
            assertThat(nuevoSize).isEqualTo(5);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(30);
            assertThat(datos[3]).isEqualTo(40);
            assertThat(datos[4]).isEqualTo(50);
        }
    }

    // ─── eliminarConDesplazamiento ───────────────────────────

    @Nested
    @DisplayName("eliminarConDesplazamiento")
    class EliminarConDesplazamiento {

        @Test
        @DisplayName("Eliminar con System.arraycopy")
        void deberiaEliminar() {
            int[] datos = {10, 20, 30, 40, 50, 0};
            int nuevoSize = Ej07_SystemArraycopy.eliminarConDesplazamiento(datos, 5, 2);
            assertThat(nuevoSize).isEqualTo(4);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(40);
            assertThat(datos[3]).isEqualTo(50);
            assertThat(datos[4]).isEqualTo(0);
        }
    }

    // ─── duplicarArray ───────────────────────────────────────

    @Nested
    @DisplayName("duplicarArray")
    class DuplicarArray {

        @Test
        @DisplayName("{1,2,3} × 3 → {1,2,3,1,2,3,1,2,3}")
        void deberiaDuplicar() {
            int[] resultado = Ej07_SystemArraycopy.duplicarArray(new int[]{1, 2, 3}, 3);
            assertThat(resultado).containsExactly(1, 2, 3, 1, 2, 3, 1, 2, 3);
        }

        @Test
        @DisplayName("0 repeticiones devuelve array vacío")
        void ceroRepeticiones() {
            assertThat(Ej07_SystemArraycopy.duplicarArray(new int[]{1, 2}, 0)).isEmpty();
        }

        @Test
        @DisplayName("1 repetición devuelve copia")
        void unaRepeticion() {
            int[] resultado = Ej07_SystemArraycopy.duplicarArray(new int[]{5, 10}, 1);
            assertThat(resultado).containsExactly(5, 10);
        }

        @Test
        @DisplayName("Repeticiones negativas lanzan excepción")
        void negativoLanzaExcepcion() {
            assertThatThrownBy(() -> Ej07_SystemArraycopy.duplicarArray(new int[]{1}, -1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
