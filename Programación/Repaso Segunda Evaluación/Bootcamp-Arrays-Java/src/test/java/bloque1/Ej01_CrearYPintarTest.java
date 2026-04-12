package bloque1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej01 - Crear y Pintar Arrays Bidimensionales")
class Ej01_CrearYPintarTest {

    @Test
    @DisplayName("crearMatriz: devuelve array con dimensiones correctas")
    void crearMatriz_dimensiones() {
        int[][] m = Ej01_CrearYPintar.crearMatriz(3, 4);
        assertNotNull(m);
        assertEquals(3, m.length);
        assertEquals(4, m[0].length);
    }

    @Test
    @DisplayName("crearMatriz: todos los valores son 0")
    void crearMatriz_valores() {
        int[][] m = Ej01_CrearYPintar.crearMatriz(2, 3);
        for (int[] fila : m) {
            for (int val : fila) {
                assertEquals(0, val);
            }
        }
    }

    @Test
    @DisplayName("crearMatrizConsecutiva: valores del 1 al filas*columnas")
    void crearMatrizConsecutiva_valores() {
        int[][] m = Ej01_CrearYPintar.crearMatrizConsecutiva(2, 3);
        assertNotNull(m);
        assertArrayEquals(new int[]{1, 2, 3}, m[0]);
        assertArrayEquals(new int[]{4, 5, 6}, m[1]);
    }

    @Test
    @DisplayName("crearMatrizConsecutiva: matriz 1x1")
    void crearMatrizConsecutiva_1x1() {
        int[][] m = Ej01_CrearYPintar.crearMatrizConsecutiva(1, 1);
        assertNotNull(m);
        assertEquals(1, m[0][0]);
    }

    @Test
    @DisplayName("contarCeldas: calcula filas * columnas")
    void contarCeldas_basico() {
        int[][] m = new int[3][5];
        assertEquals(15, Ej01_CrearYPintar.contarCeldas(m));
    }

    @Test
    @DisplayName("contarCeldas: matriz 1x1")
    void contarCeldas_1x1() {
        int[][] m = new int[1][1];
        assertEquals(1, Ej01_CrearYPintar.contarCeldas(m));
    }

    @Test
    @DisplayName("pintarMatriz: formato correcto con espacios y saltos de linea")
    void pintarMatriz_formato() {
        int[][] m = {{1, 2}, {3, 4}};
        String esperado = "1 2\n3 4";
        assertEquals(esperado, Ej01_CrearYPintar.pintarMatriz(m));
    }

    @Test
    @DisplayName("pintarMatriz: matriz 1x1")
    void pintarMatriz_1x1() {
        int[][] m = {{7}};
        assertEquals("7", Ej01_CrearYPintar.pintarMatriz(m));
    }

    @Test
    @DisplayName("pintarMatrizConCabecera: incluye numeros de fila y columna")
    void pintarMatrizConCabecera_formato() {
        int[][] m = {{1, 2}, {3, 4}};
        String resultado = Ej01_CrearYPintar.pintarMatrizConCabecera(m);
        assertNotNull(resultado);
        assertTrue(resultado.contains("1"));
        assertTrue(resultado.contains("2"));
        // Verifica que hay al menos 2 lineas (cabecera + datos)
        String[] lineas = resultado.split("\n");
        assertTrue(lineas.length >= 3, "Debe tener cabecera + 2 filas de datos");
    }

    @Test
    @DisplayName("pintarMatrizSimbolos: 0 se muestra como punto, otros como X")
    void pintarMatrizSimbolos_formato() {
        int[][] m = {{0, 1}, {2, 0}};
        String esperado = ". X\nX .";
        assertEquals(esperado, Ej01_CrearYPintar.pintarMatrizSimbolos(m));
    }

    @Test
    @DisplayName("pintarMatrizSimbolos: todo ceros")
    void pintarMatrizSimbolos_todoCeros() {
        int[][] m = {{0, 0}, {0, 0}};
        String esperado = ". .\n. .";
        assertEquals(esperado, Ej01_CrearYPintar.pintarMatrizSimbolos(m));
    }

    @Test
    @DisplayName("estaVacia: true si todo es 0")
    void estaVacia_true() {
        int[][] m = new int[3][3];
        assertTrue(Ej01_CrearYPintar.estaVacia(m));
    }

    @Test
    @DisplayName("estaVacia: false si hay algun valor distinto de 0")
    void estaVacia_false() {
        int[][] m = new int[3][3];
        m[1][1] = 5;
        assertFalse(Ej01_CrearYPintar.estaVacia(m));
    }
}
