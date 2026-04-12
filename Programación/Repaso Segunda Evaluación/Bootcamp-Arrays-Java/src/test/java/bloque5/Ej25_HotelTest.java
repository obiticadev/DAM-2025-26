package bloque5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej25 - Hotel")
class Ej25_HotelTest {

    @BeforeEach void reset() { Ej25_Hotel.Habitacion.resetContador(); }

    @Test @DisplayName("Habitacion: checkin valido")
    void checkin() {
        var h = new Ej25_Hotel.Habitacion("Test", 3, 4, 50);
        int id = h.checkin(0, 0);
        assertTrue(id > 0);
    }

    @Test @DisplayName("Habitacion: checkin ocupado -1")
    void checkin_ocupado() {
        var h = new Ej25_Hotel.Habitacion("Test", 3, 4, 50);
        h.checkin(0, 0);
        assertEquals(-1, h.checkin(0, 0));
    }

    @Test @DisplayName("Habitacion: checkout valido")
    void checkout() {
        var h = new Ej25_Hotel.Habitacion("Test", 3, 4, 50);
        int id = h.checkin(0, 0);
        assertTrue(h.checkout(id));
        assertTrue(h.checkin(0, 0) > 0);
    }

    @Test @DisplayName("Habitacion: ocupacion correcta")
    void ocupacion() {
        var h = new Ej25_Hotel.Habitacion("Test", 2, 2, 50);
        assertEquals(0.0, h.ocupacion(), 0.01);
        h.checkin(0, 0);
        assertEquals(25.0, h.ocupacion(), 0.01);
    }

    @Test @DisplayName("DAO: carga hoteles")
    void dao() {
        var sistema = new Ej25_Hotel();
        assertTrue(sistema.listar().size() >= 2);
    }

    @Test @DisplayName("DAO: hotelConMasDisponibilidad")
    void masDisponibilidad() {
        var sistema = new Ej25_Hotel();
        assertNotNull(sistema.hotelConMasDisponibilidad());
    }
}
