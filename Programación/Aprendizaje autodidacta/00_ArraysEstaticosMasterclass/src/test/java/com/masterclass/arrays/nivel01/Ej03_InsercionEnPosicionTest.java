package com.masterclass.arrays.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 03 — Inserción en Posición
 */
@DisplayName("Ej03 - Inserción en Posición")
class Ej03_InsercionEnPosicionTest {

    // ─── insertarEnPosicion ──────────────────────────────────

    @Nested
    @DisplayName("insertarEnPosicion")
    class InsertarEnPosicion {

        @Test
        @DisplayName("Insertar en medio del array")
        void deberiaInsertarEnMedio() {
            int[] datos = {10, 20, 40, 50, 0, 0};
            int nuevoSize = Ej03_InsercionEnPosicion.insertarEnPosicion(datos, 4, 2, 30);
            assertThat(nuevoSize).isEqualTo(5);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(30);
            assertThat(datos[3]).isEqualTo(40);
            assertThat(datos[4]).isEqualTo(50);
        }

        @Test
        @DisplayName("Insertar al principio (posición 0)")
        void deberiaInsertarAlPrincipio() {
            int[] datos = {20, 30, 0, 0};
            int nuevoSize = Ej03_InsercionEnPosicion.insertarEnPosicion(datos, 2, 0, 10);
            assertThat(nuevoSize).isEqualTo(3);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(30);
        }

        @Test
        @DisplayName("Insertar al final (posición = tamanioLogico)")
        void deberiaInsertarAlFinal() {
            int[] datos = {10, 20, 0, 0};
            int nuevoSize = Ej03_InsercionEnPosicion.insertarEnPosicion(datos, 2, 2, 30);
            assertThat(nuevoSize).isEqualTo(3);
            assertThat(datos[2]).isEqualTo(30);
        }

        @Test
        @DisplayName("Array lleno debe lanzar IllegalStateException")
        void deberiaLanzarExcepcionSiLleno() {
            int[] datos = {1, 2, 3};
            assertThatThrownBy(() -> Ej03_InsercionEnPosicion.insertarEnPosicion(datos, 3, 1, 99))
                    .isInstanceOf(IllegalStateException.class);
        }

        @Test
        @DisplayName("Posición inválida debe lanzar IndexOutOfBoundsException")
        void deberiaLanzarExcepcionSiPosicionInvalida() {
            int[] datos = {10, 20, 0, 0};
            assertThatThrownBy(() -> Ej03_InsercionEnPosicion.insertarEnPosicion(datos, 2, -1, 99))
                    .isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> Ej03_InsercionEnPosicion.insertarEnPosicion(datos, 2, 3, 99))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("Array null debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiNull() {
            assertThatThrownBy(() -> Ej03_InsercionEnPosicion.insertarEnPosicion(null, 0, 0, 10))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── insertarAlInicio ────────────────────────────────────

    @Nested
    @DisplayName("insertarAlInicio")
    class InsertarAlInicio {

        @Test
        @DisplayName("Insertar al inicio desplaza todos los elementos")
        void deberiaInsertarAlInicio() {
            int[] datos = {20, 30, 40, 0, 0};
            int nuevoSize = Ej03_InsercionEnPosicion.insertarAlInicio(datos, 3, 10);
            assertThat(nuevoSize).isEqualTo(4);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(30);
            assertThat(datos[3]).isEqualTo(40);
        }
    }

    // ─── insertarAlFinal ─────────────────────────────────────

    @Nested
    @DisplayName("insertarAlFinal")
    class InsertarAlFinal {

        @Test
        @DisplayName("Insertar al final no desplaza nada")
        void deberiaInsertarAlFinal() {
            int[] datos = {10, 20, 0, 0};
            int nuevoSize = Ej03_InsercionEnPosicion.insertarAlFinal(datos, 2, 30);
            assertThat(nuevoSize).isEqualTo(3);
            assertThat(datos[2]).isEqualTo(30);
        }

        @Test
        @DisplayName("Insertar en array lleno lanza IllegalStateException")
        void deberiaLanzarExcepcionSiLleno() {
            assertThatThrownBy(() -> Ej03_InsercionEnPosicion.insertarAlFinal(new int[]{1, 2}, 2, 3))
                    .isInstanceOf(IllegalStateException.class);
        }
    }

    // ─── insertarVariosAlFinal ───────────────────────────────

    @Nested
    @DisplayName("insertarVariosAlFinal")
    class InsertarVarios {

        @Test
        @DisplayName("Insertar varios elementos al final")
        void deberiaInsertarVarios() {
            int[] datos = {10, 0, 0, 0, 0};
            int nuevoSize = Ej03_InsercionEnPosicion.insertarVariosAlFinal(datos, 1, new int[]{20, 30, 40});
            assertThat(nuevoSize).isEqualTo(4);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(30);
            assertThat(datos[3]).isEqualTo(40);
        }

        @Test
        @DisplayName("Sin capacidad suficiente lanza IllegalStateException sin modificar")
        void deberiaLanzarExcepcionSinCapacidad() {
            int[] datos = {10, 20, 0};
            assertThatThrownBy(() -> Ej03_InsercionEnPosicion.insertarVariosAlFinal(datos, 2, new int[]{30, 40}))
                    .isInstanceOf(IllegalStateException.class);
            // Verificar que no se modificó
            assertThat(datos[2]).isEqualTo(0);
        }
    }

    // ─── insertarOrdenado ────────────────────────────────────

    @Nested
    @DisplayName("insertarOrdenado")
    class InsertarOrdenado {

        @Test
        @DisplayName("Insertar manteniendo el orden")
        void deberiaInsertarOrdenado() {
            int[] datos = {10, 30, 50, 0, 0};
            int nuevoSize = Ej03_InsercionEnPosicion.insertarOrdenado(datos, 3, 20);
            assertThat(nuevoSize).isEqualTo(4);
            assertThat(datos[0]).isEqualTo(10);
            assertThat(datos[1]).isEqualTo(20);
            assertThat(datos[2]).isEqualTo(30);
            assertThat(datos[3]).isEqualTo(50);
        }

        @Test
        @DisplayName("Insertar el mayor al final")
        void deberiaInsertarAlFinalSiEsMayor() {
            int[] datos = {10, 20, 30, 0};
            int nuevoSize = Ej03_InsercionEnPosicion.insertarOrdenado(datos, 3, 40);
            assertThat(nuevoSize).isEqualTo(4);
            assertThat(datos[3]).isEqualTo(40);
        }

        @Test
        @DisplayName("Insertar el menor al inicio")
        void deberiaInsertarAlInicioSiEsMenor() {
            int[] datos = {20, 30, 40, 0};
            int nuevoSize = Ej03_InsercionEnPosicion.insertarOrdenado(datos, 3, 5);
            assertThat(nuevoSize).isEqualTo(4);
            assertThat(datos[0]).isEqualTo(5);
        }
    }

    // ─── crearConCapacidadExtra ──────────────────────────────

    @Nested
    @DisplayName("crearConCapacidadExtra")
    class CrearConCapacidadExtra {

        @Test
        @DisplayName("Debe devolver array más grande con los datos originales")
        void deberiaCrearConCapacidadExtra() {
            int[] original = {10, 20, 30};
            int[] resultado = Ej03_InsercionEnPosicion.crearConCapacidadExtra(original, 3);
            assertThat(resultado).hasSize(6);
            assertThat(resultado[0]).isEqualTo(10);
            assertThat(resultado[1]).isEqualTo(20);
            assertThat(resultado[2]).isEqualTo(30);
            assertThat(resultado[3]).isEqualTo(0);
        }
    }

    // ─── contarEspacioLibre ──────────────────────────────────

    @Nested
    @DisplayName("contarEspacioLibre")
    class ContarEspacioLibre {

        @Test
        @DisplayName("Debe calcular posiciones libres correctamente")
        void deberiaContarEspacioLibre() {
            int[] datos = new int[10];
            assertThat(Ej03_InsercionEnPosicion.contarEspacioLibre(datos, 3)).isEqualTo(7);
            assertThat(Ej03_InsercionEnPosicion.contarEspacioLibre(datos, 10)).isEqualTo(0);
        }

        @Test
        @DisplayName("Argumentos inválidos lanzan excepción")
        void deberiaValidar() {
            assertThatThrownBy(() -> Ej03_InsercionEnPosicion.contarEspacioLibre(null, 0))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> Ej03_InsercionEnPosicion.contarEspacioLibre(new int[5], -1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
