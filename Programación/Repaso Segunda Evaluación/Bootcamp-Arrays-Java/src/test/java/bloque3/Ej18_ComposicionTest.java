package bloque3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej18 - Composicion")
class Ej18_ComposicionTest {

    private Ej18_Composicion sala;

    @BeforeEach
    void setUp() {
        Ej18_Celda.resetContador();
        sala = new Ej18_Composicion(3, 4, 5.25);
    }

    @Test @DisplayName("Constructor: dimensiones correctas")
    void constructor() {
        assertEquals(3, sala.getFilas());
        assertEquals(4, sala.getColumnas());
    }

    @Test @DisplayName("Reservar: posicion valida libre devuelve true")
    void reservar_valido() {
        assertTrue(sala.reservar(0, 0));
    }

    @Test @DisplayName("Reservar: posicion ya ocupada devuelve false")
    void reservar_ocupado() {
        sala.reservar(0, 0);
        assertFalse(sala.reservar(0, 0));
    }

    @Test @DisplayName("Reservar: fuera de rango devuelve false")
    void reservar_fueraRango() {
        assertFalse(sala.reservar(9, 0));
    }

    @Test @DisplayName("Liberar: libera por id de reserva")
    void liberar_valido() {
        sala.reservar(0, 0); // id = 1
        assertTrue(sala.liberar(1));
        // Ahora se puede reservar de nuevo
        assertTrue(sala.reservar(0, 0));
    }

    @Test @DisplayName("Liberar: id inexistente devuelve false")
    void liberar_inexistente() {
        assertFalse(sala.liberar(999));
    }

    @Test @DisplayName("Recaudacion: celdas ocupadas * precio")
    void recaudacion() {
        sala.reservar(0, 0);
        sala.reservar(1, 1);
        assertEquals(10.50, sala.recaudacion(), 0.01);
    }

    @Test @DisplayName("Celdas independientes: modificar una no afecta otra")
    void celdasIndependientes() {
        sala.reservar(0, 0);
        // La celda [0][1] debe seguir libre
        assertTrue(sala.reservar(0, 1));
    }

    @Test @DisplayName("Mostrar: contiene L y O")
    void mostrar() {
        sala.reservar(0, 0);
        String s = sala.mostrar();
        assertTrue(s.contains("O"));
        assertTrue(s.contains("L"));
    }
}
