package bloque1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej04 - Modificacion con Validacion de Rango")
class Ej04_ModificacionTest {

    private int[][] m;

    @BeforeEach
    void setUp() {
        m = new int[3][4];
    }

    @Test
    @DisplayName("establecerValor: posicion valida devuelve true y modifica")
    void establecerValor_valido() {
        assertTrue(Ej04_Modificacion.establecerValor(m, 1, 2, 5));
        assertEquals(5, m[1][2]);
    }

    @Test
    @DisplayName("establecerValor: posicion fuera de rango devuelve false")
    void establecerValor_fueraRango() {
        assertFalse(Ej04_Modificacion.establecerValor(m, 9, 0, 5));
        assertFalse(Ej04_Modificacion.establecerValor(m, -1, 0, 5));
        assertFalse(Ej04_Modificacion.establecerValor(m, 0, -1, 5));
        assertFalse(Ej04_Modificacion.establecerValor(m, 0, 4, 5));
    }

    @Test
    @DisplayName("obtenerValor: posicion valida devuelve el valor real")
    void obtenerValor_valido() {
        m[1][2] = 42;
        assertEquals(42, Ej04_Modificacion.obtenerValor(m, 1, 2, -1));
    }

    @Test
    @DisplayName("obtenerValor: posicion fuera de rango devuelve valor por defecto")
    void obtenerValor_fueraRango() {
        assertEquals(-1, Ej04_Modificacion.obtenerValor(m, 9, 0, -1));
    }

    @Test
    @DisplayName("rellenarFila: rellena toda la fila con el valor")
    void rellenarFila_valido() {
        assertTrue(Ej04_Modificacion.rellenarFila(m, 1, 7));
        for (int j = 0; j < m[1].length; j++) {
            assertEquals(7, m[1][j]);
        }
        // Fila 0 no debe cambiar
        assertEquals(0, m[0][0]);
    }

    @Test
    @DisplayName("rellenarFila: fila fuera de rango devuelve false")
    void rellenarFila_fueraRango() {
        assertFalse(Ej04_Modificacion.rellenarFila(m, 5, 7));
    }

    @Test
    @DisplayName("rellenarColumna: rellena toda la columna")
    void rellenarColumna_valido() {
        assertTrue(Ej04_Modificacion.rellenarColumna(m, 2, 9));
        for (int i = 0; i < m.length; i++) {
            assertEquals(9, m[i][2]);
        }
    }

    @Test
    @DisplayName("rellenarColumna: columna fuera de rango devuelve false")
    void rellenarColumna_fueraRango() {
        assertFalse(Ej04_Modificacion.rellenarColumna(m, 10, 9));
    }

    @Test
    @DisplayName("intercambiar: intercambia dos posiciones validas")
    void intercambiar_valido() {
        m[0][0] = 1;
        m[2][3] = 2;
        assertTrue(Ej04_Modificacion.intercambiar(m, 0, 0, 2, 3));
        assertEquals(2, m[0][0]);
        assertEquals(1, m[2][3]);
    }

    @Test
    @DisplayName("intercambiar: posicion fuera de rango devuelve false")
    void intercambiar_fueraRango() {
        assertFalse(Ej04_Modificacion.intercambiar(m, 0, 0, 9, 9));
    }

    @Test
    @DisplayName("incrementarRegion: incrementa las celdas de la subregion")
    void incrementarRegion_valido() {
        Ej04_Modificacion.incrementarRegion(m, 1, 2, 1, 2);
        assertEquals(0, m[0][0]); // fuera de la region
        assertEquals(1, m[1][1]);
        assertEquals(1, m[1][2]);
        assertEquals(1, m[2][1]);
        assertEquals(1, m[2][2]);
    }

    @Test
    @DisplayName("incrementarRegion: recorta limites fuera de rango sin error")
    void incrementarRegion_recorta() {
        assertDoesNotThrow(() -> Ej04_Modificacion.incrementarRegion(m, -5, 50, -5, 50));
        // Todas las celdas deben haberse incrementado
        assertEquals(1, m[0][0]);
        assertEquals(1, m[2][3]);
    }

    @Test
    @DisplayName("limpiarMatriz: devuelve cuantas celdas no eran 0")
    void limpiarMatriz_cuenta() {
        m[0][0] = 5;
        m[1][1] = 3;
        m[2][2] = 8;
        int modificadas = Ej04_Modificacion.limpiarMatriz(m);
        assertEquals(3, modificadas);
    }

    @Test
    @DisplayName("limpiarMatriz: la matriz queda toda a 0")
    void limpiarMatriz_limpia() {
        m[0][0] = 5;
        m[1][1] = 3;
        Ej04_Modificacion.limpiarMatriz(m);
        for (int[] fila : m) {
            for (int val : fila) {
                assertEquals(0, val);
            }
        }
    }
}
