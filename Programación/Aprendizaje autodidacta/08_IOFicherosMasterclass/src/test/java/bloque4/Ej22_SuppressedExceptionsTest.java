package bloque4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej22 - Suppressed Exceptions")
class Ej22_SuppressedExceptionsTest {

    @Test @DisplayName("probarCierre: sin fallo devuelve OK")
    void probarCierre_ok() {
        assertEquals("OK", Ej22_SuppressedExceptions.probarCierre(false));
    }

    @Test @DisplayName("probarCierre: con fallo devuelve mensaje error")
    void probarCierre_fallo() {
        String r = Ej22_SuppressedExceptions.probarCierre(true);
        assertTrue(r.contains("Error cerrando") || r.contains("Test"));
    }

    @Test @DisplayName("contarSuppressed: devuelve 1 cuando try y close fallan")
    void contarSuppressed() {
        assertEquals(1, Ej22_SuppressedExceptions.contarSuppressed());
    }

    @Test @DisplayName("informeSuppressed: contiene Principal")
    void informeSuppressed() {
        IOException principal = new IOException("Error principal");
        principal.addSuppressed(new IOException("Error close"));
        String informe = Ej22_SuppressedExceptions.informeSuppressed(principal);
        assertTrue(informe.contains("Principal:"));
        assertTrue(informe.contains("Suppressed 1:"));
    }

    @Test @DisplayName("informeSuppressed: sin suppressed solo muestra principal")
    void informeSuppressed_sinSuppressed() {
        IOException e = new IOException("Solo");
        String informe = Ej22_SuppressedExceptions.informeSuppressed(e);
        assertTrue(informe.contains("Principal:"));
        assertFalse(informe.contains("Suppressed 1:"));
    }

    @Test @DisplayName("cerrarConSuppressed: sin fallos devuelve null")
    void cerrarConSuppressed_ok() {
        assertNull(Ej22_SuppressedExceptions.cerrarConSuppressed(3, -1));
    }

    @Test @DisplayName("cerrarConSuppressed: con fallo devuelve excepcion")
    void cerrarConSuppressed_fallo() {
        Exception e = Ej22_SuppressedExceptions.cerrarConSuppressed(3, 1);
        assertNotNull(e);
    }

    @Test @DisplayName("tieneSuppressedConPalabra: detecta palabra en suppressed")
    void tieneSuppressedConPalabra_true() {
        IOException e = new IOException("principal");
        e.addSuppressed(new IOException("Error cerrando recurso"));
        assertTrue(Ej22_SuppressedExceptions.tieneSuppressedConPalabra(e, "cerrando"));
    }

    @Test @DisplayName("tieneSuppressedConPalabra: null devuelve false")
    void tieneSuppressedConPalabra_null() {
        assertFalse(Ej22_SuppressedExceptions.tieneSuppressedConPalabra(null, "x"));
    }

    @Test @DisplayName("diagnosticarEstado: sin errores devuelve EXITO")
    void diagnosticar_exito() {
        assertEquals("EXITO", Ej22_SuppressedExceptions.diagnosticarEstado(false, false));
    }

    @Test @DisplayName("diagnosticarEstado: error accion devuelve ERROR_ACCION")
    void diagnosticar_accion() {
        assertEquals("ERROR_ACCION", Ej22_SuppressedExceptions.diagnosticarEstado(true, false));
    }

    @Test @DisplayName("diagnosticarEstado: error cierre devuelve ERROR_CIERRE")
    void diagnosticar_cierre() {
        assertEquals("ERROR_CIERRE", Ej22_SuppressedExceptions.diagnosticarEstado(false, true));
    }

    @Test @DisplayName("diagnosticarEstado: ambos errores devuelve ERROR_AMBOS")
    void diagnosticar_ambos() {
        assertEquals("ERROR_AMBOS", Ej22_SuppressedExceptions.diagnosticarEstado(true, true));
    }

    @Test @DisplayName("totalExcepciones: null devuelve 0")
    void totalExcepciones_null() {
        assertEquals(0, Ej22_SuppressedExceptions.totalExcepciones(null));
    }

    @Test @DisplayName("totalExcepciones: principal + 2 suppressed = 3")
    void totalExcepciones_tres() {
        IOException e = new IOException("x");
        e.addSuppressed(new IOException("a"));
        e.addSuppressed(new IOException("b"));
        assertEquals(3, Ej22_SuppressedExceptions.totalExcepciones(e));
    }
}
