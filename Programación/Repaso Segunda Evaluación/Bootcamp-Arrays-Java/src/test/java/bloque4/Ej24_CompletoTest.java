package bloque4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej24 - Ejercicio Completo")
class Ej24_CompletoTest {

    private Ej24_Completo centro;

    @BeforeEach
    void setUp() {
        Ej24_Completo.Aula.resetContador();
        centro = new Ej24_Completo();
    }

    @Test @DisplayName("Constructor carga aulas")
    void constructor() { assertTrue(centro.listar().size() >= 3); }

    @Test @DisplayName("buscarAula: encuentra")
    void buscar() {
        var aula = centro.buscarAula(centro.listar().get(0).getNumAula());
        assertNotNull(aula);
    }

    @Test @DisplayName("buscarAula: no existe")
    void buscar_null() { assertNull(centro.buscarAula(9999)); }

    @Test @DisplayName("Aula: asignar y desasignar")
    void asignarDesasignar() {
        var aula = new Ej24_Completo.Aula(99, 2, 2);
        int id = aula.asignar(0, 0);
        assertTrue(id > 0);
        assertEquals(3, aula.plazasLibres());
        assertTrue(aula.desasignar(id));
        assertEquals(4, aula.plazasLibres());
    }

    @Test @DisplayName("Aula: asignar ocupado devuelve -1")
    void asignar_ocupado() {
        var aula = new Ej24_Completo.Aula(99, 2, 2);
        aula.asignar(0, 0);
        assertEquals(-1, aula.asignar(0, 0));
    }

    @Test @DisplayName("Aula: fuera de rango devuelve -1")
    void asignar_fueraRango() {
        var aula = new Ej24_Completo.Aula(99, 2, 2);
        assertEquals(-1, aula.asignar(9, 9));
    }

    @Test @DisplayName("Aula: desasignar inexistente")
    void desasignar_inexistente() {
        var aula = new Ej24_Completo.Aula(99, 2, 2);
        assertFalse(aula.desasignar(999));
    }

    @Test @DisplayName("aulaConMasPlazasLibres")
    void masPlazas() {
        var aula = centro.aulaConMasPlazasLibres();
        assertNotNull(aula);
    }

    @Test @DisplayName("totalPlazasLibres >= 0")
    void totalPlazas() { assertTrue(centro.totalPlazasLibres() > 0); }
}
