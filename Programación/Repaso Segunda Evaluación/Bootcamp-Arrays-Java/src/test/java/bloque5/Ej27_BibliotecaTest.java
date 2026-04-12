package bloque5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej27 - Biblioteca")
class Ej27_BibliotecaTest {

    @Test @DisplayName("Estanteria: colocar y retirar")
    void colocarRetirar() {
        var e = new Ej27_Biblioteca.Estanteria(1, 3, 4);
        assertTrue(e.colocar(0, 0, "ISBN-001"));
        assertFalse(e.colocar(0, 0, "ISBN-002")); // ocupado
        assertTrue(e.retirar("ISBN-001"));
        assertFalse(e.retirar("ISBN-001")); // ya no esta
    }

    @Test @DisplayName("Estanteria: buscar")
    void buscar() {
        var e = new Ej27_Biblioteca.Estanteria(1, 2, 2);
        e.colocar(1, 1, "ISBN-X");
        int[] pos = e.buscar("ISBN-X");
        assertNotNull(pos);
        assertArrayEquals(new int[]{1, 1}, pos);
        assertNull(e.buscar("NO_EXISTE"));
    }

    @Test @DisplayName("Estanteria: contadores")
    void contadores() {
        var e = new Ej27_Biblioteca.Estanteria(1, 2, 2);
        assertEquals(4, e.posicionesLibres());
        e.colocar(0, 0, "A");
        assertEquals(1, e.librosColocados());
        assertEquals(3, e.posicionesLibres());
    }

    @Test @DisplayName("DAO: carga estanterias")
    void dao() {
        var bib = new Ej27_Biblioteca();
        assertTrue(bib.listar().size() >= 3);
    }

    @Test @DisplayName("DAO: totalLibros >= 0")
    void total() { assertTrue(new Ej27_Biblioteca().totalLibros() >= 0); }
}
