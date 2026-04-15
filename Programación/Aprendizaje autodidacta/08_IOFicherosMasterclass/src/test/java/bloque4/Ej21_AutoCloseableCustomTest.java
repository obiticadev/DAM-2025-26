package bloque4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej21 - AutoCloseable Custom")
class Ej21_AutoCloseableCustomTest {

    @Test @DisplayName("RegistradorConsulta: se cierra automaticamente con try")
    void cierreAutomatico() {
        Ej21_AutoCloseableCustom.RegistradorConsulta r;
        try (Ej21_AutoCloseableCustom.RegistradorConsulta reg =
                     new Ej21_AutoCloseableCustom.RegistradorConsulta("Luna")) {
            reg.registrar("Peso");
            r = reg;
        }
        assertTrue(r.estaCerrado());
    }

    @Test @DisplayName("RegistradorConsulta: registrar despues de cerrar lanza excepcion")
    void registrarCerrado() {
        Ej21_AutoCloseableCustom.RegistradorConsulta reg =
                new Ej21_AutoCloseableCustom.RegistradorConsulta("Max");
        reg.close();
        assertThrows(IllegalStateException.class, () -> reg.registrar("Algo"));
    }

    @Test @DisplayName("RegistradorConsulta: totalMensajes incluye inicio")
    void totalMensajes() {
        try (Ej21_AutoCloseableCustom.RegistradorConsulta reg =
                     new Ej21_AutoCloseableCustom.RegistradorConsulta("Coco")) {
            reg.registrar("A");
            reg.registrar("B");
            assertEquals(3, reg.totalMensajes()); // inicio + A + B
        }
    }

    @Test @DisplayName("RegistradorConsulta: obtenerLog contiene nombre mascota")
    void obtenerLog() {
        try (Ej21_AutoCloseableCustom.RegistradorConsulta reg =
                     new Ej21_AutoCloseableCustom.RegistradorConsulta("Luna")) {
            reg.registrar("Vacuna");
            String log = reg.obtenerLog();
            assertTrue(log.contains("Luna"));
            assertTrue(log.contains("Vacuna"));
        }
    }

    @Test @DisplayName("RegistradorConsulta: close es idempotente")
    void closeIdempotente() {
        Ej21_AutoCloseableCustom.RegistradorConsulta reg =
                new Ej21_AutoCloseableCustom.RegistradorConsulta("Rex");
        reg.close();
        reg.close(); // no debe lanzar excepcion
        assertTrue(reg.estaCerrado());
    }

    @Test @DisplayName("simularConsulta: devuelve log con inicio y acciones")
    void simularConsulta() {
        String log = Ej21_AutoCloseableCustom.simularConsulta("Luna",
                new String[]{"Peso", "Vacuna"});
        assertTrue(log.contains("Luna"));
        assertTrue(log.contains("Peso"));
        assertTrue(log.contains("Vacuna"));
    }

    @Test @DisplayName("consultaDoble: contiene ambas mascotas")
    void consultaDoble() {
        String doble = Ej21_AutoCloseableCustom.consultaDoble(
                "Luna", new String[]{"Revision"},
                "Max", new String[]{"Radiografia"});
        assertTrue(doble.contains("Luna"));
        assertTrue(doble.contains("Max"));
        assertTrue(doble.contains("==="));
    }

    @Test @DisplayName("simularConsulta: array vacio solo tiene inicio y fin")
    void simularConsulta_vacia() {
        String log = Ej21_AutoCloseableCustom.simularConsulta("Coco", new String[0]);
        assertTrue(log.contains("Inicio"));
        assertTrue(log.contains("Fin"));
    }
}
