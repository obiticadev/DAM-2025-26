package com.masterclass.arrays.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 01 — Declaración e Inicialización
 */
@DisplayName("Ej01 - Declaración e Inicialización de Arrays")
class Ej01_DeclaracionInicializacionTest {

    // ─── crearArrayEnteros ───────────────────────────────────

    @Nested
    @DisplayName("crearArrayEnteros")
    class CrearArrayEnteros {

        @Test
        @DisplayName("Debe crear un array del tamaño solicitado")
        void deberiaCrearArrayConTamanioCorrecto() {
            int[] result = Ej01_DeclaracionInicializacion.crearArrayEnteros(5);
            assertThat(result).isNotNull();
            assertThat(result).hasSize(5);
        }

        @Test
        @DisplayName("Todos los valores deben ser 0 por defecto")
        void deberiaContenerCerosPorDefecto() {
            int[] result = Ej01_DeclaracionInicializacion.crearArrayEnteros(3);
            assertThat(result).containsExactly(0, 0, 0);
        }

        @Test
        @DisplayName("Tamaño 0 debe devolver array vacío válido")
        void deberiaPermitirTamanioCero() {
            int[] result = Ej01_DeclaracionInicializacion.crearArrayEnteros(0);
            assertThat(result).isNotNull();
            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("Tamaño negativo debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiTamanioNegativo() {
            assertThatThrownBy(() -> Ej01_DeclaracionInicializacion.crearArrayEnteros(-1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── crearArrayDoubles ───────────────────────────────────

    @Nested
    @DisplayName("crearArrayDoubles")
    class CrearArrayDoubles {

        @Test
        @DisplayName("Debe crear un array de doubles con valores 0.0")
        void deberiaContenerCerosDecimales() {
            double[] result = Ej01_DeclaracionInicializacion.crearArrayDoubles(4);
            assertThat(result).hasSize(4);
            assertThat(result).containsExactly(0.0, 0.0, 0.0, 0.0);
        }

        @Test
        @DisplayName("Tamaño negativo debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiNegativo() {
            assertThatThrownBy(() -> Ej01_DeclaracionInicializacion.crearArrayDoubles(-5))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── crearArrayStrings ───────────────────────────────────

    @Nested
    @DisplayName("crearArrayStrings")
    class CrearArrayStrings {

        @Test
        @DisplayName("Todos los valores deben ser null, no cadenas vacías")
        void deberiaContenerNulls() {
            String[] result = Ej01_DeclaracionInicializacion.crearArrayStrings(3);
            assertThat(result).hasSize(3);
            assertThat(result).containsExactly(null, null, null);
        }

        @Test
        @DisplayName("Tamaño negativo debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiNegativo() {
            assertThatThrownBy(() -> Ej01_DeclaracionInicializacion.crearArrayStrings(-1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── crearArrayBooleans ──────────────────────────────────

    @Nested
    @DisplayName("crearArrayBooleans")
    class CrearArrayBooleans {

        @Test
        @DisplayName("Todos los valores deben ser false por defecto")
        void deberiaContenerFalse() {
            boolean[] result = Ej01_DeclaracionInicializacion.crearArrayBooleans(3);
            assertThat(result).hasSize(3);
            for (boolean b : result) {
                assertThat(b).isFalse();
            }
        }
    }

    // ─── crearConLiteral ─────────────────────────────────────

    @Nested
    @DisplayName("crearConLiteral")
    class CrearConLiteral {

        @Test
        @DisplayName("Debe devolver {10, 20, 30, 40, 50}")
        void deberiaDevolver5Elementos() {
            int[] result = Ej01_DeclaracionInicializacion.crearConLiteral();
            assertThat(result).isNotNull();
            assertThat(result).containsExactly(10, 20, 30, 40, 50);
        }

        @Test
        @DisplayName("Debe tener exactamente 5 elementos")
        void deberiaTenerTamanio5() {
            int[] result = Ej01_DeclaracionInicializacion.crearConLiteral();
            assertThat(result).hasSize(5);
        }
    }

    // ─── verificarTodosValoresPorDefecto ─────────────────────

    @Nested
    @DisplayName("verificarTodosValoresPorDefecto")
    class VerificarValores {

        @Test
        @DisplayName("Array recién creado debe pasar la verificación")
        void deberiaDevolverTrueParaArrayNuevo() {
            int[] nuevo = new int[5];
            assertThat(Ej01_DeclaracionInicializacion.verificarTodosValoresPorDefecto(nuevo)).isTrue();
        }

        @Test
        @DisplayName("Array con algún valor distinto de 0 debe fallar")
        void deberiaDevolverFalseSiHayValoresNoDefault() {
            int[] modificado = {0, 0, 1, 0};
            assertThat(Ej01_DeclaracionInicializacion.verificarTodosValoresPorDefecto(modificado)).isFalse();
        }

        @Test
        @DisplayName("Array vacío debe devolver true (vacuamente verdadero)")
        void deberiaDevolverTrueParaArrayVacio() {
            assertThat(Ej01_DeclaracionInicializacion.verificarTodosValoresPorDefecto(new int[0])).isTrue();
        }

        @Test
        @DisplayName("Array null debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiNull() {
            assertThatThrownBy(() -> Ej01_DeclaracionInicializacion.verificarTodosValoresPorDefecto(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── obtenerLongitud ─────────────────────────────────────

    @Nested
    @DisplayName("obtenerLongitud")
    class ObtenerLongitud {

        @Test
        @DisplayName("Debe devolver la longitud correcta")
        void deberiaDevolverlongitudCorrecta() {
            assertThat(Ej01_DeclaracionInicializacion.obtenerLongitud(new int[7])).isEqualTo(7);
            assertThat(Ej01_DeclaracionInicializacion.obtenerLongitud(new int[0])).isEqualTo(0);
        }

        @Test
        @DisplayName("Array null debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiNull() {
            assertThatThrownBy(() -> Ej01_DeclaracionInicializacion.obtenerLongitud(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── crearYRellenarSecuencial ────────────────────────────

    @Nested
    @DisplayName("crearYRellenarSecuencial")
    class CrearYRellenarSecuencial {

        @Test
        @DisplayName("Debe crear array con índices como valores")
        void deberiaRellenarConIndices() {
            int[] result = Ej01_DeclaracionInicializacion.crearYRellenarSecuencial(5);
            assertThat(result).containsExactly(0, 1, 2, 3, 4);
        }

        @Test
        @DisplayName("Tamaño 1 debe devolver {0}")
        void deberiaFuncionarConTamanio1() {
            assertThat(Ej01_DeclaracionInicializacion.crearYRellenarSecuencial(1)).containsExactly(0);
        }

        @Test
        @DisplayName("Tamaño 0 debe devolver array vacío")
        void deberiaPermitirTamanioCero() {
            assertThat(Ej01_DeclaracionInicializacion.crearYRellenarSecuencial(0)).isEmpty();
        }

        @Test
        @DisplayName("Tamaño negativo debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiNegativo() {
            assertThatThrownBy(() -> Ej01_DeclaracionInicializacion.crearYRellenarSecuencial(-3))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
