package bloque4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej20 - Menu (solo partes testeables sin Scanner)")
class Ej20_MenuTest {

    @Test @DisplayName("Constructor crea tablero con dimensiones correctas")
    void constructor() {
        Ej20_Menu m = new Ej20_Menu(3, 4);
        assertNotNull(m.getTablero());
        assertEquals(3, m.getTablero().length);
        assertEquals(4, m.getTablero()[0].length);
    }

    @Test @DisplayName("Tablero inicializado a ceros")
    void tableroInicial() {
        Ej20_Menu m = new Ej20_Menu(2, 2);
        for (int[] fila : m.getTablero())
            for (int v : fila) assertEquals(0, v);
    }
}
