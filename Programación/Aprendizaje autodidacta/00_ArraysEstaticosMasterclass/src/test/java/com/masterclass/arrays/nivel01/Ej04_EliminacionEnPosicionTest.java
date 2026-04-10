package com.masterclass.arrays.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 04 — Eliminación en Posición
 */
@DisplayName("Ej04 - Eliminación en Posición")
class Ej04_EliminacionEnPosicionTest {

    // ─── eliminarEnPosicion ──────────────────────────────────

    @Nested
    @DisplayName("eliminarEnPosicion")
    class EliminarEnPosicion {

        @Test
        @DisplayName("Eliminar del medio desplaza elementos a la izquierda")
        void deberiaEliminarEnMedio() {
            int[] datos = {10, 20, 30, 40, 50, 0, 0};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarEnPosicion(datos, 5, 2);
            assertThat(nuevoSize).isEqualTo(4);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(40);
            assertThat(datos[3]).isEqualTo(50);
            assertThat(datos[4]).isEqualTo(0); // limpiada
        }

        @Test
        @DisplayName("Eliminar el primer elemento")
        void deberiaEliminarPrimero() {
            int[] datos = {10, 20, 30, 0};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarEnPosicion(datos, 3, 0);
            assertThat(nuevoSize).isEqualTo(2);
            assertThat(datos[0]).isEqualTo(20);
            assertThat(datos[1]).isEqualTo(30);
        }

        @Test
        @DisplayName("Eliminar el último elemento")
        void deberiaEliminarUltimo() {
            int[] datos = {10, 20, 30, 0};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarEnPosicion(datos, 3, 2);
            assertThat(nuevoSize).isEqualTo(2);
            assertThat(datos[2]).isEqualTo(0);
        }

        @Test
        @DisplayName("Posición inválida lanza IndexOutOfBoundsException")
        void deberiaLanzarExcepcionPosicionInvalida() {
            int[] datos = {10, 20, 30};
            assertThatThrownBy(() -> Ej04_EliminacionEnPosicion.eliminarEnPosicion(datos, 3, -1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> Ej04_EliminacionEnPosicion.eliminarEnPosicion(datos, 3, 3))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("Array vacío lanza IllegalStateException")
        void deberiaLanzarExcepcionSiVacio() {
            assertThatThrownBy(() -> Ej04_EliminacionEnPosicion.eliminarEnPosicion(new int[5], 0, 0))
                    .isInstanceOf(IllegalStateException.class);
        }

        @Test
        @DisplayName("Array null lanza IllegalArgumentException")
        void deberiaLanzarExcepcionSiNull() {
            assertThatThrownBy(() -> Ej04_EliminacionEnPosicion.eliminarEnPosicion(null, 3, 0))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── eliminarPrimero ─────────────────────────────────────

    @Nested
    @DisplayName("eliminarPrimero")
    class EliminarPrimero {

        @Test
        @DisplayName("Elimina posición 0 y desplaza")
        void deberiaEliminarPrimero() {
            int[] datos = {100, 200, 300, 0};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarPrimero(datos, 3);
            assertThat(nuevoSize).isEqualTo(2);
            assertThat(datos[0]).isEqualTo(200);
            assertThat(datos[1]).isEqualTo(300);
        }

        @Test
        @DisplayName("Sin elementos lanza IllegalStateException")
        void deberiaLanzarSiVacio() {
            assertThatThrownBy(() -> Ej04_EliminacionEnPosicion.eliminarPrimero(new int[5], 0))
                    .isInstanceOf(IllegalStateException.class);
        }
    }

    // ─── eliminarUltimo ──────────────────────────────────────

    @Nested
    @DisplayName("eliminarUltimo")
    class EliminarUltimo {

        @Test
        @DisplayName("Elimina último sin desplazar")
        void deberiaEliminarUltimo() {
            int[] datos = {10, 20, 30, 0};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarUltimo(datos, 3);
            assertThat(nuevoSize).isEqualTo(2);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(0);
        }
    }

    // ─── eliminarPrimeraOcurrencia ───────────────────────────

    @Nested
    @DisplayName("eliminarPrimeraOcurrencia")
    class EliminarPrimeraOcurrencia {

        @Test
        @DisplayName("Elimina solo la primera aparición del valor")
        void deberiaEliminarPrimera() {
            int[] datos = {5, 3, 7, 3, 9, 0};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarPrimeraOcurrencia(datos, 5, 3);
            assertThat(nuevoSize).isEqualTo(4);
            assertThat(datos[0]).isEqualTo(5);
            assertThat(datos[1]).isEqualTo(7);
            assertThat(datos[2]).isEqualTo(3); // segundo 3 permanece
            assertThat(datos[3]).isEqualTo(9);
        }

        @Test
        @DisplayName("Valor no encontrado no modifica nada")
        void noDeberiaModificarSiNoExiste() {
            int[] datos = {1, 2, 3, 0};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarPrimeraOcurrencia(datos, 3, 99);
            assertThat(nuevoSize).isEqualTo(3);
        }
    }

    // ─── eliminarTodasOcurrencias ────────────────────────────

    @Nested
    @DisplayName("eliminarTodasOcurrencias")
    class EliminarTodas {

        @Test
        @DisplayName("Elimina todas las apariciones del valor")
        void deberiaEliminarTodas() {
            int[] datos = {3, 1, 3, 2, 3, 0, 0};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarTodasOcurrencias(datos, 5, 3);
            assertThat(nuevoSize).isEqualTo(2);
            assertThat(datos[0]).isEqualTo(1);
            assertThat(datos[1]).isEqualTo(2);
        }

        @Test
        @DisplayName("Sin ocurrencias no modifica nada")
        void noModificaSiNoExiste() {
            int[] datos = {1, 2, 3, 0};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarTodasOcurrencias(datos, 3, 99);
            assertThat(nuevoSize).isEqualTo(3);
        }

        @Test
        @DisplayName("Eliminar todos los elementos")
        void deberiaDejarlotodoVacio() {
            int[] datos = {5, 5, 5, 5};
            int nuevoSize = Ej04_EliminacionEnPosicion.eliminarTodasOcurrencias(datos, 4, 5);
            assertThat(nuevoSize).isEqualTo(0);
        }
    }

    // ─── compactar ───────────────────────────────────────────

    @Nested
    @DisplayName("compactar")
    class Compactar {

        @Test
        @DisplayName("Mueve no-ceros al inicio manteniendo orden")
        void deberiaCompactar() {
            int[] datos = {3, 0, 5, 0, 0, 7};
            int nuevoSize = Ej04_EliminacionEnPosicion.compactar(datos, 6);
            assertThat(nuevoSize).isEqualTo(3);
            assertThat(datos[0]).isEqualTo(3);
            assertThat(datos[1]).isEqualTo(5);
            assertThat(datos[2]).isEqualTo(7);
            // posiciones sobrantes
            assertThat(datos[3]).isEqualTo(0);
            assertThat(datos[4]).isEqualTo(0);
            assertThat(datos[5]).isEqualTo(0);
        }

        @Test
        @DisplayName("Array sin ceros no cambia")
        void noDeberiaModificarSiNoHayCeros() {
            int[] datos = {1, 2, 3};
            int nuevoSize = Ej04_EliminacionEnPosicion.compactar(datos, 3);
            assertThat(nuevoSize).isEqualTo(3);
            assertThat(datos).containsExactly(1, 2, 3);
        }
    }

    // ─── extraerElemento ─────────────────────────────────────

    @Nested
    @DisplayName("extraerElemento")
    class ExtraerElemento {

        @Test
        @DisplayName("Debe devolver el valor eliminado")
        void deberiaDevolverValor() {
            int[] datos = {10, 20, 30, 40, 0};
            int extraido = Ej04_EliminacionEnPosicion.extraerElemento(datos, 4, 1);
            assertThat(extraido).isEqualTo(20);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(30);
            assertThat(datos[2]).isEqualTo(40);
        }

        @Test
        @DisplayName("Posición inválida lanza excepción")
        void deberiaLanzarExcepcion() {
            assertThatThrownBy(() -> Ej04_EliminacionEnPosicion.extraerElemento(new int[]{1, 2}, 2, 5))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }
}
