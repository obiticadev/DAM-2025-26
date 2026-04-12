package bloque1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej03 - Busqueda en Array Bidimensional")
class Ej03_BusquedaTest {

    private final int[][] m = {
        {1, 2, 3, 2},
        {4, 5, 2, 6},
        {7, 2, 8, 9}
    };

    @Test
    @DisplayName("buscarPrimero: encuentra la primera ocurrencia")
    void buscarPrimero_existe() {
        int[] pos = Ej03_Busqueda.buscarPrimero(m, 2);
        assertNotNull(pos);
        assertArrayEquals(new int[]{0, 1}, pos);
    }

    @Test
    @DisplayName("buscarPrimero: devuelve null si no existe")
    void buscarPrimero_noExiste() {
        assertNull(Ej03_Busqueda.buscarPrimero(m, 99));
    }

    @Test
    @DisplayName("buscarUltimo: encuentra la ultima ocurrencia")
    void buscarUltimo_existe() {
        int[] pos = Ej03_Busqueda.buscarUltimo(m, 2);
        assertNotNull(pos);
        assertArrayEquals(new int[]{2, 1}, pos);
    }

    @Test
    @DisplayName("buscarUltimo: devuelve null si no existe")
    void buscarUltimo_noExiste() {
        assertNull(Ej03_Busqueda.buscarUltimo(m, 99));
    }

    @Test
    @DisplayName("contarOcurrencias: cuenta todas las apariciones")
    void contarOcurrencias_multiple() {
        assertEquals(4, Ej03_Busqueda.contarOcurrencias(m, 2));
    }

    @Test
    @DisplayName("contarOcurrencias: 0 si no aparece")
    void contarOcurrencias_cero() {
        assertEquals(0, Ej03_Busqueda.contarOcurrencias(m, 99));
    }

    @Test
    @DisplayName("existeEnFila: true si el valor esta en la fila")
    void existeEnFila_true() {
        assertTrue(Ej03_Busqueda.existeEnFila(m, 0, 2));
    }

    @Test
    @DisplayName("existeEnFila: false si no esta en la fila")
    void existeEnFila_false() {
        assertFalse(Ej03_Busqueda.existeEnFila(m, 2, 6));
    }

    @Test
    @DisplayName("existeEnFila: false si fila fuera de rango")
    void existeEnFila_fueraRango() {
        assertFalse(Ej03_Busqueda.existeEnFila(m, 10, 2));
    }

    @Test
    @DisplayName("existeEnColumna: true si el valor esta en la columna")
    void existeEnColumna_true() {
        assertTrue(Ej03_Busqueda.existeEnColumna(m, 1, 2));
    }

    @Test
    @DisplayName("existeEnColumna: false si no esta en la columna")
    void existeEnColumna_false() {
        assertFalse(Ej03_Busqueda.existeEnColumna(m, 0, 2));
    }

    @Test
    @DisplayName("existeEnColumna: false si columna fuera de rango")
    void existeEnColumna_fueraRango() {
        assertFalse(Ej03_Busqueda.existeEnColumna(m, 50, 2));
    }

    @Test
    @DisplayName("buscarTodas: devuelve todas las posiciones")
    void buscarTodas_multiple() {
        int[][] resultado = Ej03_Busqueda.buscarTodas(m, 2);
        assertNotNull(resultado);
        assertEquals(4, resultado.length);
        assertArrayEquals(new int[]{0, 1}, resultado[0]);
        assertArrayEquals(new int[]{0, 3}, resultado[1]);
        assertArrayEquals(new int[]{1, 2}, resultado[2]);
        assertArrayEquals(new int[]{2, 1}, resultado[3]);
    }

    @Test
    @DisplayName("buscarTodas: array vacio si no se encuentra")
    void buscarTodas_vacio() {
        int[][] resultado = Ej03_Busqueda.buscarTodas(m, 99);
        assertNotNull(resultado);
        assertEquals(0, resultado.length);
    }

    @Test
    @DisplayName("enRango: true para posicion valida")
    void enRango_true() {
        assertTrue(Ej03_Busqueda.enRango(m, 2, 3));
    }

    @Test
    @DisplayName("enRango: false para fila negativa")
    void enRango_filaNegativa() {
        assertFalse(Ej03_Busqueda.enRango(m, -1, 0));
    }

    @Test
    @DisplayName("enRango: false para columna fuera de rango")
    void enRango_columnaFuera() {
        assertFalse(Ej03_Busqueda.enRango(m, 0, 4));
    }
}
