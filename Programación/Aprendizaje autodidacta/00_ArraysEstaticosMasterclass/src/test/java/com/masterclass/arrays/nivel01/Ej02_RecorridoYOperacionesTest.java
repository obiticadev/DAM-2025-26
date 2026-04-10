package com.masterclass.arrays.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Ejercicio 02 — Recorrido y Operaciones
 */
@DisplayName("Ej02 - Recorrido y Operaciones Básicas")
class Ej02_RecorridoYOperacionesTest {

    // ─── calcularSuma ────────────────────────────────────────

    @Nested
    @DisplayName("calcularSuma")
    class CalcularSuma {

        @Test
        @DisplayName("Suma de {1, 2, 3, 4, 5} debe ser 15")
        void deberiaSumarCorrectamente() {
            assertThat(Ej02_RecorridoYOperaciones.calcularSuma(new int[]{1, 2, 3, 4, 5})).isEqualTo(15);
        }

        @Test
        @DisplayName("Suma con números negativos")
        void deberiaManejarNegativos() {
            assertThat(Ej02_RecorridoYOperaciones.calcularSuma(new int[]{-5, 10, -3})).isEqualTo(2);
        }

        @Test
        @DisplayName("Suma de array vacío debe ser 0")
        void deberiaDevolver0ParaArrayVacio() {
            assertThat(Ej02_RecorridoYOperaciones.calcularSuma(new int[]{})).isEqualTo(0);
        }

        @Test
        @DisplayName("Suma de array null debe ser 0")
        void deberiaDevolver0ParaNull() {
            assertThat(Ej02_RecorridoYOperaciones.calcularSuma(null)).isEqualTo(0);
        }
    }

    // ─── calcularMedia ───────────────────────────────────────

    @Nested
    @DisplayName("calcularMedia")
    class CalcularMedia {

        @Test
        @DisplayName("Media de {10, 20, 30} debe ser 20.0")
        void deberiaCalcularMediaCorrecta() {
            assertThat(Ej02_RecorridoYOperaciones.calcularMedia(new int[]{10, 20, 30})).isEqualTo(20.0);
        }

        @Test
        @DisplayName("Media con resultado decimal: {1, 2} → 1.5")
        void deberiaProducirDecimales() {
            assertThat(Ej02_RecorridoYOperaciones.calcularMedia(new int[]{1, 2})).isEqualTo(1.5);
        }

        @Test
        @DisplayName("Media de array vacío debe ser 0.0")
        void deberiaDevolver0ParaVacio() {
            assertThat(Ej02_RecorridoYOperaciones.calcularMedia(new int[]{})).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Media de un solo elemento")
        void deberiaFuncionarConUnElemento() {
            assertThat(Ej02_RecorridoYOperaciones.calcularMedia(new int[]{42})).isEqualTo(42.0);
        }
    }

    // ─── encontrarMaximo ─────────────────────────────────────

    @Nested
    @DisplayName("encontrarMaximo")
    class EncontrarMaximo {

        @Test
        @DisplayName("Máximo de {3, 7, 1, 9, 4} debe ser 9")
        void deberiaEncontrarMaximo() {
            assertThat(Ej02_RecorridoYOperaciones.encontrarMaximo(new int[]{3, 7, 1, 9, 4})).isEqualTo(9);
        }

        @Test
        @DisplayName("Máximo con todos negativos: {-5, -2, -8} → -2")
        void deberiaFuncionarConNegativos() {
            assertThat(Ej02_RecorridoYOperaciones.encontrarMaximo(new int[]{-5, -2, -8})).isEqualTo(-2);
        }

        @Test
        @DisplayName("Máximo con un solo elemento")
        void deberiaFuncionarConUno() {
            assertThat(Ej02_RecorridoYOperaciones.encontrarMaximo(new int[]{42})).isEqualTo(42);
        }

        @Test
        @DisplayName("Array vacío debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiVacio() {
            assertThatThrownBy(() -> Ej02_RecorridoYOperaciones.encontrarMaximo(new int[]{}))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Array null debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiNull() {
            assertThatThrownBy(() -> Ej02_RecorridoYOperaciones.encontrarMaximo(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── encontrarMinimo ─────────────────────────────────────

    @Nested
    @DisplayName("encontrarMinimo")
    class EncontrarMinimo {

        @Test
        @DisplayName("Mínimo de {3, 7, 1, 9, 4} debe ser 1")
        void deberiaEncontrarMinimo() {
            assertThat(Ej02_RecorridoYOperaciones.encontrarMinimo(new int[]{3, 7, 1, 9, 4})).isEqualTo(1);
        }

        @Test
        @DisplayName("Mínimo con negativos: {-5, -2, -8} → -8")
        void deberiaFuncionarConNegativos() {
            assertThat(Ej02_RecorridoYOperaciones.encontrarMinimo(new int[]{-5, -2, -8})).isEqualTo(-8);
        }

        @Test
        @DisplayName("Array null debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiNull() {
            assertThatThrownBy(() -> Ej02_RecorridoYOperaciones.encontrarMinimo(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── contarPares ─────────────────────────────────────────

    @Nested
    @DisplayName("contarPares")
    class ContarPares {

        @Test
        @DisplayName("Contar pares en {1, 2, 3, 4, 5, 6}")
        void deberiaContarPares() {
            assertThat(Ej02_RecorridoYOperaciones.contarPares(new int[]{1, 2, 3, 4, 5, 6})).isEqualTo(3);
        }

        @Test
        @DisplayName("Cero es par")
        void ceroEsPar() {
            assertThat(Ej02_RecorridoYOperaciones.contarPares(new int[]{0, 1, 3})).isEqualTo(1);
        }

        @Test
        @DisplayName("Negativos pares: {-4, -3, -2}")
        void negativosParesDebenContar() {
            assertThat(Ej02_RecorridoYOperaciones.contarPares(new int[]{-4, -3, -2})).isEqualTo(2);
        }

        @Test
        @DisplayName("Array vacío o null devuelve 0")
        void deberiaDevolverCero() {
            assertThat(Ej02_RecorridoYOperaciones.contarPares(new int[]{})).isEqualTo(0);
            assertThat(Ej02_RecorridoYOperaciones.contarPares(null)).isEqualTo(0);
        }
    }

    // ─── generarArraySecuencial ──────────────────────────────

    @Nested
    @DisplayName("generarArraySecuencial")
    class GenerarSecuencial {

        @Test
        @DisplayName("generarArraySecuencial(5, 4) → {5, 6, 7, 8}")
        void deberiaGenerarSecuencia() {
            assertThat(Ej02_RecorridoYOperaciones.generarArraySecuencial(5, 4))
                    .containsExactly(5, 6, 7, 8);
        }

        @Test
        @DisplayName("generarArraySecuencial(0, 3) → {0, 1, 2}")
        void deberiaEmpezarEnCero() {
            assertThat(Ej02_RecorridoYOperaciones.generarArraySecuencial(0, 3))
                    .containsExactly(0, 1, 2);
        }

        @Test
        @DisplayName("Cantidad 0 devuelve array vacío")
        void cantidadCeroDevuelveVacio() {
            assertThat(Ej02_RecorridoYOperaciones.generarArraySecuencial(10, 0)).isEmpty();
        }

        @Test
        @DisplayName("Cantidad negativa lanza excepción")
        void cantidadNegativaLanzaExcepcion() {
            assertThatThrownBy(() -> Ej02_RecorridoYOperaciones.generarArraySecuencial(0, -1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── encontrarIndice ─────────────────────────────────────

    @Nested
    @DisplayName("encontrarIndice")
    class EncontrarIndice {

        @Test
        @DisplayName("Debe encontrar el índice correcto")
        void deberiaEncontrarIndice() {
            assertThat(Ej02_RecorridoYOperaciones.encontrarIndice(new int[]{10, 20, 30, 40}, 30)).isEqualTo(2);
        }

        @Test
        @DisplayName("Debe devolver -1 si no existe")
        void deberiaDevolver_1SiNoExiste() {
            assertThat(Ej02_RecorridoYOperaciones.encontrarIndice(new int[]{10, 20, 30}, 99)).isEqualTo(-1);
        }

        @Test
        @DisplayName("Si hay duplicados, devolver el primer índice")
        void deberiaDevolverPrimero() {
            assertThat(Ej02_RecorridoYOperaciones.encontrarIndice(new int[]{5, 3, 5, 3, 5}, 3)).isEqualTo(1);
        }

        @Test
        @DisplayName("Array null debe lanzar IllegalArgumentException")
        void deberiaLanzarExcepcionSiNull() {
            assertThatThrownBy(() -> Ej02_RecorridoYOperaciones.encontrarIndice(null, 5))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ─── contarOcurrencias ───────────────────────────────────

    @Nested
    @DisplayName("contarOcurrencias")
    class ContarOcurrencias {

        @Test
        @DisplayName("Contar ocurrencias de 3 en {3, 1, 3, 5, 3}")
        void deberiaContarCorrectamente() {
            assertThat(Ej02_RecorridoYOperaciones.contarOcurrencias(new int[]{3, 1, 3, 5, 3}, 3)).isEqualTo(3);
        }

        @Test
        @DisplayName("Valor no presente devuelve 0")
        void deberiaDevolver0SiNoExiste() {
            assertThat(Ej02_RecorridoYOperaciones.contarOcurrencias(new int[]{1, 2, 3}, 99)).isEqualTo(0);
        }

        @Test
        @DisplayName("Array vacío o null devuelve 0")
        void deberiaDevolver0() {
            assertThat(Ej02_RecorridoYOperaciones.contarOcurrencias(new int[]{}, 1)).isEqualTo(0);
            assertThat(Ej02_RecorridoYOperaciones.contarOcurrencias(null, 1)).isEqualTo(0);
        }
    }
}
