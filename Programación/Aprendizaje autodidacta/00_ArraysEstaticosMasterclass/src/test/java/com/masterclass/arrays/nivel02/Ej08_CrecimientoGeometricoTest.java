package com.masterclass.arrays.nivel02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 08 — Crecimiento Geométrico
 */
@DisplayName("Ej08 - Crecimiento Geométrico")
class Ej08_CrecimientoGeometricoTest {

    private Ej08_CrecimientoGeometrico contenedor;

    @BeforeEach
    void setUp() {
        contenedor = new Ej08_CrecimientoGeometrico();
    }

    // ─── Estado inicial ──────────────────────────────────────

    @Nested
    @DisplayName("Estado inicial")
    class EstadoInicial {

        @Test
        @DisplayName("Size inicial debe ser 0")
        void sizeInicialCero() {
            assertThat(contenedor.size()).isEqualTo(0);
        }

        @Test
        @DisplayName("Capacidad inicial debe ser 4")
        void capacidadInicial4() {
            assertThat(contenedor.capacity()).isEqualTo(4);
        }

        @Test
        @DisplayName("toArray() inicial devuelve array vacío")
        void toArrayInicialVacio() {
            assertThat(contenedor.toArray()).isEmpty();
        }
    }

    // ─── add ─────────────────────────────────────────────────

    @Nested
    @DisplayName("add")
    class Add {

        @Test
        @DisplayName("Añadir un elemento incrementa size")
        void deberiaIncrementarSize() {
            contenedor.add(10);
            assertThat(contenedor.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("Añadir 4 elementos NO aumenta la capacidad")
        void noDeberiaCrecer() {
            for (int i = 0; i < 4; i++) contenedor.add(i);
            assertThat(contenedor.size()).isEqualTo(4);
            assertThat(contenedor.capacity()).isEqualTo(4);
        }

        @Test
        @DisplayName("El 5º elemento duplica la capacidad a 8")
        void deberiaCreerAlQuinto() {
            for (int i = 0; i < 5; i++) contenedor.add(i);
            assertThat(contenedor.size()).isEqualTo(5);
            assertThat(contenedor.capacity()).isEqualTo(8);
        }

        @Test
        @DisplayName("Crecer múltiples veces: 4 → 8 → 16")
        void deberiaCreserMultiplesVeces() {
            for (int i = 0; i < 9; i++) contenedor.add(i);
            assertThat(contenedor.size()).isEqualTo(9);
            assertThat(contenedor.capacity()).isEqualTo(16);
        }

        @Test
        @DisplayName("Los valores se mantienen correctamente tras grow")
        void valoresSeMantienenTrasGrow() {
            for (int i = 0; i < 10; i++) contenedor.add(i * 10);
            assertThat(contenedor.toArray()).containsExactly(0, 10, 20, 30, 40, 50, 60, 70, 80, 90);
        }
    }

    // ─── get ─────────────────────────────────────────────────

    @Nested
    @DisplayName("get")
    class Get {

        @Test
        @DisplayName("Recuperar valores por índice")
        void deberiaRecuperarValores() {
            contenedor.add(10);
            contenedor.add(20);
            contenedor.add(30);
            assertThat(contenedor.get(0)).isEqualTo(10);
            assertThat(contenedor.get(1)).isEqualTo(20);
            assertThat(contenedor.get(2)).isEqualTo(30);
        }

        @Test
        @DisplayName("Índice negativo lanza IndexOutOfBoundsException")
        void indiceNegativoLanzaExcepcion() {
            contenedor.add(10);
            assertThatThrownBy(() -> contenedor.get(-1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("Índice >= size lanza IndexOutOfBoundsException")
        void indiceFueraDeSizeLanzaExcepcion() {
            contenedor.add(10);
            assertThatThrownBy(() -> contenedor.get(1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("Índice entre size y capacity lanza excepción (no accesible)")
        void indiceFueraDeSizePeroEnCapacidad() {
            contenedor.add(10);
            // capacity es 4, size es 1. Posición 2 no es accesible.
            assertThatThrownBy(() -> contenedor.get(2))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    // ─── toArray ─────────────────────────────────────────────

    @Nested
    @DisplayName("toArray")
    class ToArray {

        @Test
        @DisplayName("Devuelve solo los elementos lógicos")
        void deberiaDevolverSoloLogicos() {
            contenedor.add(1);
            contenedor.add(2);
            contenedor.add(3);
            int[] result = contenedor.toArray();
            assertThat(result).containsExactly(1, 2, 3);
            assertThat(result).hasSize(3); // no 4 (capacidad)
        }

        @Test
        @DisplayName("toArray devuelve copia independiente")
        void deberiaDevolverCopia() {
            contenedor.add(10);
            int[] arr = contenedor.toArray();
            arr[0] = 999;
            assertThat(contenedor.get(0)).isEqualTo(10); // no afectado
        }
    }
}
