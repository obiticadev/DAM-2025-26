package bloque1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej06 - Estadisticas por Filas y Columnas")
class Ej06_EstadisticasTest {

    private final int[][] m = {
        {10, 20, 30},  // suma = 60
        {5, 15, 25},   // suma = 45
        {40, 10, 50}   // suma = 100
    };
    // sumas por columna: 55, 45, 105

    @Test
    @DisplayName("sumaFila: calcula la suma de una fila")
    void sumaFila_basico() {
        assertEquals(60, Ej06_Estadisticas.sumaFila(m, 0));
        assertEquals(45, Ej06_Estadisticas.sumaFila(m, 1));
        assertEquals(100, Ej06_Estadisticas.sumaFila(m, 2));
    }

    @Test
    @DisplayName("sumaFila: devuelve 0 si fila fuera de rango")
    void sumaFila_fueraRango() {
        assertEquals(0, Ej06_Estadisticas.sumaFila(m, 5));
        assertEquals(0, Ej06_Estadisticas.sumaFila(m, -1));
    }

    @Test
    @DisplayName("sumaColumna: calcula la suma de una columna")
    void sumaColumna_basico() {
        assertEquals(55, Ej06_Estadisticas.sumaColumna(m, 0));
        assertEquals(45, Ej06_Estadisticas.sumaColumna(m, 1));
        assertEquals(105, Ej06_Estadisticas.sumaColumna(m, 2));
    }

    @Test
    @DisplayName("sumaColumna: devuelve 0 si columna fuera de rango")
    void sumaColumna_fueraRango() {
        assertEquals(0, Ej06_Estadisticas.sumaColumna(m, 10));
    }

    @Test
    @DisplayName("sumasPorFila: array de sumas por fila")
    void sumasPorFila_basico() {
        int[] resultado = Ej06_Estadisticas.sumasPorFila(m);
        assertNotNull(resultado);
        assertArrayEquals(new int[]{60, 45, 100}, resultado);
    }

    @Test
    @DisplayName("sumasPorColumna: array de sumas por columna")
    void sumasPorColumna_basico() {
        int[] resultado = Ej06_Estadisticas.sumasPorColumna(m);
        assertNotNull(resultado);
        assertArrayEquals(new int[]{55, 45, 105}, resultado);
    }

    @Test
    @DisplayName("maximoGlobal: devuelve el maximo de toda la matriz")
    void maximoGlobal_basico() {
        assertEquals(50, Ej06_Estadisticas.maximoGlobal(m));
    }

    @Test
    @DisplayName("maximoGlobal: funciona con negativos")
    void maximoGlobal_negativos() {
        int[][] neg = {{-5, -3}, {-10, -1}};
        assertEquals(-1, Ej06_Estadisticas.maximoGlobal(neg));
    }

    @Test
    @DisplayName("filaMayorSuma: devuelve el indice de la fila con mayor suma")
    void filaMayorSuma_basico() {
        assertEquals(2, Ej06_Estadisticas.filaMayorSuma(m)); // fila 2 suma 100
    }

    @Test
    @DisplayName("filaMayorSuma: primera fila en caso de empate")
    void filaMayorSuma_empate() {
        int[][] empate = {{10, 10}, {10, 10}, {5, 5}};
        assertEquals(0, Ej06_Estadisticas.filaMayorSuma(empate)); // filas 0 y 1 empatan, devuelve 0
    }

    @Test
    @DisplayName("mediaGlobal: calcula la media correcta como double")
    void mediaGlobal_basico() {
        // suma total = 60 + 45 + 100 = 205, celdas = 9
        double esperado = 205.0 / 9.0;
        assertEquals(esperado, Ej06_Estadisticas.mediaGlobal(m), 0.001);
    }

    @Test
    @DisplayName("mediaGlobal: resultado decimal, no truncado")
    void mediaGlobal_decimal() {
        int[][] simple = {{1, 2}, {3, 4}};
        // suma = 10, celdas = 4, media = 2.5
        assertEquals(2.5, Ej06_Estadisticas.mediaGlobal(simple), 0.001);
    }
}
