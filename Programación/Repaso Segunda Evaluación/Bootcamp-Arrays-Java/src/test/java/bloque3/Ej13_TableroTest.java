package bloque3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej13 - Tablero")
class Ej13_TableroTest {

    @Test @DisplayName("Constructor valido crea tablero")
    void constructor_valido() {
        Ej13_Tablero t = new Ej13_Tablero(3, 4);
        assertEquals(3, t.getFilas());
        assertEquals(4, t.getColumnas());
    }

    @Test @DisplayName("Constructor lanza excepcion si filas <= 0")
    void constructor_filasNeg() {
        assertThrows(IllegalArgumentException.class, () -> new Ej13_Tablero(0, 4));
        assertThrows(IllegalArgumentException.class, () -> new Ej13_Tablero(-1, 4));
    }

    @Test @DisplayName("Constructor lanza excepcion si columnas <= 0")
    void constructor_colsNeg() {
        assertThrows(IllegalArgumentException.class, () -> new Ej13_Tablero(3, 0));
    }

    @Test @DisplayName("Constructor lanza excepcion si filas > 10")
    void constructor_filasMax() {
        assertThrows(IllegalArgumentException.class, () -> new Ej13_Tablero(11, 4));
    }

    @Test @DisplayName("Constructor lanza excepcion si columnas > 10")
    void constructor_colsMax() {
        assertThrows(IllegalArgumentException.class, () -> new Ej13_Tablero(3, 11));
    }

    @Test @DisplayName("getValor/setValor: rango valido")
    void getSetValor_valido() {
        Ej13_Tablero t = new Ej13_Tablero(3, 4);
        assertTrue(t.setValor(1, 2, 5));
        assertEquals(5, t.getValor(1, 2));
    }

    @Test @DisplayName("getValor: fuera de rango devuelve -1")
    void getValor_fueraRango() {
        Ej13_Tablero t = new Ej13_Tablero(3, 4);
        assertEquals(-1, t.getValor(9, 0));
        assertEquals(-1, t.getValor(-1, 0));
    }

    @Test @DisplayName("setValor: fuera de rango devuelve false")
    void setValor_fueraRango() {
        Ej13_Tablero t = new Ej13_Tablero(3, 4);
        assertFalse(t.setValor(9, 0, 5));
    }

    @Test @DisplayName("estaVacio: true al iniciar, false tras setValor")
    void estaVacio() {
        Ej13_Tablero t = new Ej13_Tablero(2, 2);
        assertTrue(t.estaVacio());
        t.setValor(0, 0, 1);
        assertFalse(t.estaVacio());
    }

    @Test @DisplayName("mostrar: formato correcto")
    void mostrar() {
        Ej13_Tablero t = new Ej13_Tablero(2, 2);
        t.setValor(0, 0, 1);
        t.setValor(1, 1, 2);
        String esperado = "1 0\n0 2";
        assertEquals(esperado, t.mostrar());
    }
}
