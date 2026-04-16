package bloque4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

@DisplayName("Ej21 - Entrada Robusta (test con Scanner simulado)")
class Ej21_EntradaTest {

    @Test @DisplayName("pedirEntero: entrada valida")
    void pedirEntero() {
        Scanner sc = new Scanner("42\n");
        int v = Ej21_Entrada.pedirEntero(sc, "Num: ");
        assertEquals(42, v);
    }

    @Test @DisplayName("pedirEnteroEnRango: entrada en rango")
    void pedirEnteroEnRango() {
        Scanner sc = new Scanner("5\n");
        int v = Ej21_Entrada.pedirEnteroEnRango(sc, "Num: ", 1, 10);
        assertEquals(5, v);
    }

    @Test @DisplayName("pedirDouble: entrada valida")
    void pedirDouble() {
        Scanner sc = new Scanner("3.14\n");
        double v = Ej21_Entrada.pedirDouble(sc, "Dec: ");
        assertEquals(3.14, v, 0.01);
    }

    @Test @DisplayName("pedirTextoNoVacio: texto valido")
    void pedirTexto() {
        Scanner sc = new Scanner("Hola\n");
        String v = Ej21_Entrada.pedirTextoNoVacio(sc, "Texto: ");
        assertEquals("Hola", v);
    }

    @Test @DisplayName("confirmar: respuesta s")
    void confirmar_s() {
        Scanner sc = new Scanner("s\n");
        assertTrue(Ej21_Entrada.confirmar(sc, "OK? "));
    }

    @Test @DisplayName("confirmar: respuesta n")
    void confirmar_n() {
        Scanner sc = new Scanner("n\n");
        assertFalse(Ej21_Entrada.confirmar(sc, "OK? "));
    }
}
