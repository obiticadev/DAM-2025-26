package bloque5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej26 - Parking")
class Ej26_ParkingTest {

    @Test @DisplayName("Planta: aparcar y sacar")
    void aparcarSacar() {
        var p = new Ej26_Parking.Planta(0, 2, 3);
        assertTrue(p.aparcar(0, 0, "1234ABC"));
        assertFalse(p.aparcar(0, 0, "9999ZZZ")); // ocupado
        assertTrue(p.sacar("1234ABC"));
        assertTrue(p.aparcar(0, 0, "9999ZZZ")); // ahora libre
    }

    @Test @DisplayName("Planta: plazasLibres")
    void plazas() {
        var p = new Ej26_Parking.Planta(0, 2, 2);
        assertEquals(4, p.plazasLibres());
        p.aparcar(0, 0, "AAA");
        assertEquals(3, p.plazasLibres());
    }

    @Test @DisplayName("Planta: buscarMatricula")
    void buscar() {
        var p = new Ej26_Parking.Planta(1, 2, 2);
        p.aparcar(1, 0, "TEST");
        assertNotNull(p.buscarMatricula("TEST"));
        assertNull(p.buscarMatricula("NOEXISTE"));
    }

    @Test @DisplayName("DAO: carga plantas")
    void dao() {
        var parking = new Ej26_Parking();
        assertTrue(parking.listar().size() >= 2);
    }

    @Test @DisplayName("DAO: totalPlazasLibres")
    void totalPlazas() {
        assertTrue(new Ej26_Parking().totalPlazasLibres() > 0);
    }
}
