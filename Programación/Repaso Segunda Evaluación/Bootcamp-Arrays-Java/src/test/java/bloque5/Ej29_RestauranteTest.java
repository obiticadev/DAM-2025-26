package bloque5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej29 - Restaurante")
class Ej29_RestauranteTest {

    @Test @DisplayName("Sala: sentar y liberar")
    void sentarLiberar() {
        var s = new Ej29_Restaurante.Sala("Principal", 3, 4, 6);
        assertTrue(s.sentar(0, 0, 4));
        assertFalse(s.sentar(0, 0, 2)); // ocupado
        assertTrue(s.liberar(0, 0));
        assertTrue(s.sentar(0, 0, 2));
    }

    @Test @DisplayName("Sala: sentar excede capacidad")
    void sentarExcede() {
        var s = new Ej29_Restaurante.Sala("Test", 2, 2, 4);
        assertFalse(s.sentar(0, 0, 5)); // excede max
        assertFalse(s.sentar(0, 0, 0)); // 0 comensales
    }

    @Test @DisplayName("Sala: totalComensales")
    void comensales() {
        var s = new Ej29_Restaurante.Sala("Test", 2, 2, 6);
        s.sentar(0, 0, 3);
        s.sentar(1, 1, 5);
        assertEquals(8, s.totalComensales());
    }

    @Test @DisplayName("DAO: carga salas")
    void dao() {
        var r = new Ej29_Restaurante();
        assertTrue(r.listar().size() >= 2);
    }

    @Test @DisplayName("DAO: salaConMasCapacidad no null")
    void masCapacidad() { assertNotNull(new Ej29_Restaurante().salaConMasCapacidad()); }

    @Test @DisplayName("DAO: totalComensalesGlobal >= 0")
    void totalGlobal() { assertTrue(new Ej29_Restaurante().totalComensalesGlobal() >= 0); }
}
