package com.masterclass.arrays.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 05 - Ejercicio 21: Creación y Recorrido")
class Ej21_CreacionYRecorrido2DTest {

    @Test
    @DisplayName("Debe crear matriz incremental correctamente")
    void testCrearMatrizIncremental() {
        int[][] res = Ej21_CreacionYRecorrido2D.crearMatrizIncremental(2, 3);
        assertThat(res).hasDimensions(2, 3);
        assertThat(res[0]).containsExactly(1, 2, 3);
        assertThat(res[1]).containsExactly(4, 5, 6);
    }

    @Test
    @DisplayName("Debe calcular la suma total del grid")
    void testSumaTotal() {
        int[][] m = {{1, 2}, {3, 4}};
        assertThat(Ej21_CreacionYRecorrido2D.sumaTotal(m)).isEqualTo(10);
    }

    @Test
    @DisplayName("Debe extraer una fila específica")
    void testObtenerFila() {
        int[][] m = {{10, 20}, {30, 40}};
        assertThat(Ej21_CreacionYRecorrido2D.obtenerFila(m, 1)).containsExactly(30, 40);
        assertThatThrownBy(() -> Ej21_CreacionYRecorrido2D.obtenerFila(m, 5))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Debe extraer una columna específica")
    void testObtenerColumna() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        assertThat(Ej21_CreacionYRecorrido2D.obtenerColumna(m, 1)).containsExactly(2, 5);
        assertThat(Ej21_CreacionYRecorrido2D.obtenerColumna(m, 2)).containsExactly(3, 6);
    }

    @Test
    @DisplayName("Debe contar las ocurrencias de un valor")
    void testContarOcurrencias() {
        int[][] m = {{1, 5, 1}, {2, 1, 3}};
        assertThat(Ej21_CreacionYRecorrido2D.contarOcurrencias(m, 1)).isEqualTo(3);
        assertThat(Ej21_CreacionYRecorrido2D.contarOcurrencias(m, 9)).isEqualTo(0);
    }

    @Test
    @DisplayName("Debe rellenar con patrón tablero (0/1)")
    void testPatternTablero() {
        int[][] m = new int[2][2];
        Ej21_CreacionYRecorrido2D.rellenarPatronTablero(m);
        assertThat(m[0][0]).isEqualTo(1);
        assertThat(m[0][1]).isEqualTo(0);
        assertThat(m[1][0]).isEqualTo(0);
        assertThat(m[1][1]).isEqualTo(1);
    }

    @Test
    @DisplayName("Debe buscar la posición {f, c} de un elemento")
    void testBuscarPosicion() {
        int[][] m = {{10, 20}, {30, 40}};
        assertThat(Ej21_CreacionYRecorrido2D.buscarPosicion(m, 30)).containsExactly(1, 0);
        assertThat(Ej21_CreacionYRecorrido2D.buscarPosicion(m, 99)).containsExactly(-1, -1);
    }
}
