package bloque4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej23 - Integracion")
class Ej23_IntegracionTest {

    @Test @DisplayName("Constructor carga sesiones")
    void constructor() {
        Ej23_Integracion cine = new Ej23_Integracion();
        assertTrue(cine.listarSesiones().size() >= 3);
    }

    @Test @DisplayName("buscarSesion: encuentra")
    void buscar() {
        Ej23_Integracion cine = new Ej23_Integracion();
        var sesion = cine.buscarSesion(cine.listarSesiones().get(0).getNumSesion());
        assertNotNull(sesion);
    }

    @Test @DisplayName("buscarSesion: no existe null")
    void buscar_null() {
        assertNull(new Ej23_Integracion().buscarSesion(9999));
    }

    @Test @DisplayName("Sesion: reservar y liberar")
    void reservarLiberar() {
        var sesion = new Ej23_Integracion.Sesion(1, "Test", 3, 4, 5.0);
        int libresInicial = sesion.asientosLibres();
        assertTrue(sesion.reservar(0, 0));
        assertEquals(libresInicial - 1, sesion.asientosLibres());
        assertFalse(sesion.reservar(0, 0)); // ya ocupado
        assertTrue(sesion.liberar(0, 0));
        assertEquals(libresInicial, sesion.asientosLibres());
    }

    @Test @DisplayName("Sesion: fuera de rango")
    void fueraRango() {
        var sesion = new Ej23_Integracion.Sesion(1, "Test", 2, 2, 5.0);
        assertFalse(sesion.reservar(9, 9));
        assertFalse(sesion.liberar(9, 9));
    }

    @Test @DisplayName("Sesion: recaudacion")
    void recaudacion() {
        var sesion = new Ej23_Integracion.Sesion(1, "Test", 2, 2, 10.0);
        sesion.reservar(0, 0);
        sesion.reservar(1, 1);
        assertEquals(20.0, sesion.recaudacion(), 0.01);
    }

    @Test @DisplayName("recaudacionTotal suma todas las sesiones")
    void recaudacionTotal() {
        Ej23_Integracion cine = new Ej23_Integracion();
        assertTrue(cine.recaudacionTotal() >= 0);
    }
}
