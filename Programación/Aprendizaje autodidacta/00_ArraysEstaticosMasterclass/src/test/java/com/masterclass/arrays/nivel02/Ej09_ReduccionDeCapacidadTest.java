package com.masterclass.arrays.nivel02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 09 — Reducción de Capacidad
 */
@DisplayName("Ej09 - Reducción de Capacidad")
class Ej09_ReduccionDeCapacidadTest {

    private Ej09_ReduccionDeCapacidad contenedor;

    @BeforeEach
    void setUp() {
        contenedor = new Ej09_ReduccionDeCapacidad();
    }

    // ─── Estado inicial ──────────────────────────────────────

    @Nested
    @DisplayName("Estado inicial")
    class EstadoInicial {

        @Test
        @DisplayName("Size inicial es 0")
        void sizeInicial() {
            assertThat(contenedor.size()).isEqualTo(0);
        }

        @Test
        @DisplayName("Capacidad inicial es 8")
        void capacidadInicial() {
            assertThat(contenedor.capacity()).isEqualTo(8);
        }
    }

    // ─── add (grow) ──────────────────────────────────────────

    @Nested
    @DisplayName("add con grow")
    class AddConGrow {

        @Test
        @DisplayName("Añadir 9 elementos duplica capacidad de 8 a 16")
        void deberiaCrecer() {
            for (int i = 0; i < 9; i++) contenedor.add(i);
            assertThat(contenedor.size()).isEqualTo(9);
            assertThat(contenedor.capacity()).isEqualTo(16);
        }

        @Test
        @DisplayName("Valores correctos tras crecimiento")
        void valoresCorrectos() {
            for (int i = 0; i < 10; i++) contenedor.add(i * 5);
            for (int i = 0; i < 10; i++) {
                assertThat(contenedor.get(i)).isEqualTo(i * 5);
            }
        }
    }

    // ─── remove (shrink) ─────────────────────────────────────

    @Nested
    @DisplayName("remove con shrink")
    class RemoveConShrink {

        @Test
        @DisplayName("Remove devuelve el valor eliminado")
        void deberiaDevovlerValor() {
            contenedor.add(10);
            contenedor.add(20);
            contenedor.add(30);
            int removed = contenedor.remove(1);
            assertThat(removed).isEqualTo(20);
            assertThat(contenedor.size()).isEqualTo(2);
        }

        @Test
        @DisplayName("Remove desplaza elementos a la izquierda")
        void deberiaDesplazar() {
            contenedor.add(10);
            contenedor.add(20);
            contenedor.add(30);
            contenedor.remove(0); // eliminar 10
            assertThat(contenedor.get(0)).isEqualTo(20);
            assertThat(contenedor.get(1)).isEqualTo(30);
        }

        @Test
        @DisplayName("Shrink reduce capacidad cuando size <= capacity/4")
        void deberiaShrink() {
            // Llenar hasta capacity 16
            for (int i = 0; i < 9; i++) contenedor.add(i);
            assertThat(contenedor.capacity()).isEqualTo(16);

            // Eliminar hasta que size <= 16/4 = 4
            while (contenedor.size() > 4) {
                contenedor.remove(contenedor.size() - 1);
            }
            assertThat(contenedor.capacity()).isEqualTo(16); // aún no shrink

            // Un remove más: size será 3, que es <= 16/4=4 → shrink a 8
            contenedor.remove(contenedor.size() - 1);
            assertThat(contenedor.size()).isEqualTo(3);
            assertThat(contenedor.capacity()).isEqualTo(8);
        }

        @Test
        @DisplayName("Nunca reduce por debajo de CAPACIDAD_INICIAL (8)")
        void nuncaPorDebajoDeLaMinima() {
            contenedor.add(1);
            contenedor.add(2);
            contenedor.remove(0);
            contenedor.remove(0);
            assertThat(contenedor.size()).isEqualTo(0);
            assertThat(contenedor.capacity()).isGreaterThanOrEqualTo(8);
        }

        @Test
        @DisplayName("Índice inválido lanza IndexOutOfBoundsException")
        void indiceInvalidoLanzaExcepcion() {
            contenedor.add(10);
            assertThatThrownBy(() -> contenedor.remove(-1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> contenedor.remove(1))
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
            for (int i = 0; i < 5; i++) contenedor.add(i * 10);
            int[] result = contenedor.toArray();
            assertThat(result).containsExactly(0, 10, 20, 30, 40);
        }
    }

    // ─── Ciclo grow + shrink ─────────────────────────────────

    @Nested
    @DisplayName("Ciclo completo grow/shrink")
    class CicloCompletoGrowShrink {

        @Test
        @DisplayName("Grow y shrink múltiples veces mantienen datos correctos")
        void cicloDinamico() {
            // Grow: 0..19
            for (int i = 0; i < 20; i++) contenedor.add(i);
            assertThat(contenedor.size()).isEqualTo(20);
            assertThat(contenedor.capacity()).isGreaterThanOrEqualTo(20);

            // Shrink: quitar hasta 3
            while (contenedor.size() > 3) {
                contenedor.remove(contenedor.size() - 1);
            }

            // Verificar datos supervivientes
            assertThat(contenedor.get(0)).isEqualTo(0);
            assertThat(contenedor.get(1)).isEqualTo(1);
            assertThat(contenedor.get(2)).isEqualTo(2);

            // Grow de nuevo
            for (int i = 100; i < 110; i++) contenedor.add(i);
            assertThat(contenedor.size()).isEqualTo(13);
            assertThat(contenedor.get(3)).isEqualTo(100);
        }
    }
}
