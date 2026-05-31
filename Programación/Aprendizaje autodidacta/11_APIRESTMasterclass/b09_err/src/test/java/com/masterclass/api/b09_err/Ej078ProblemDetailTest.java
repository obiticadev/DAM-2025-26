package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;
import static org.junit.jupiter.api.Assertions.*;

class Ej078ProblemDetailTest {

    @Test
    void construye404() {
        ProblemDetail pd = Ej078ProblemDetail.build(404, "Usuario 7 no existe", "/api/users/7");
        assertEquals(404, pd.getStatus());
        assertEquals("Usuario 7 no existe", pd.getDetail());
        assertEquals("Not Found", pd.getTitle());
        assertNotNull(pd.getProperties());
        assertTrue(pd.getProperties().containsKey("timestamp"));
    }

    @Test
    void statusNoErrorFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej078ProblemDetail.build(200, "ok", "/x"));
    }

    @Test
    void testRetoExtra01_esTipoValidoUri() {
        // Comprueba que el tipo de error tenga un formato URI absoluto valido.
        assertTrue(Ej078ProblemDetail.esTipoValidoUri("https://api.masterclass.com/errors/not-found"));
    }

    @Test
    void testRetoExtra02_validarCodigoEstadoHttp() {
        // Verifica que el codigo este entre 400 y 599.
        assertTrue(Ej078ProblemDetail.validarCodigoEstadoHttp(404));
    }

    @Test
    void testRetoExtra03_extraerPropiedadAdicional() {
        // Obtiene un valor del JSON de detalle del problema.
        assertEquals("val", Ej078ProblemDetail.extraerPropiedadAdicional("{\"key\":\"val\"}", "key"));
    }

    @Test
    void testRetoExtra04_generarInstanciaUri() {
        // Crea la URI de la instancia del error concreto.
        assertEquals("/api/users/42", Ej078ProblemDetail.generarInstanciaUri("/api/users", 42L));
    }

    @Test
    void testRetoExtra05_limpiarCamposSensibles() {
        // Filtra stacktraces o datos de usuario del payload.
        assertFalse(Ej078ProblemDetail.limpiarCamposSensibles("{\"stack\":\"at...\"}").contains("stack"));
    }

    @Test
    void testRetoExtra06_esProblemDetailValido() {
        // Valida los requisitos minimos del RFC 7807.
        assertTrue(Ej078ProblemDetail.esProblemDetailValido("urn:err", "Title", 400));
    }

    @Test
    void testRetoExtra07_formatearFechaIso() {
        // Devuelve la marca temporal en formato ISO 8601 UTC.
        assertTrue(Ej078ProblemDetail.formatearFechaIso(java.time.Instant.now()).endsWith("Z"));
    }

    @Test
    void testRetoExtra08_combinarDetalles() {
        // Une el titulo principal y la causa de forma elegante.
        assertEquals("Main: Sub", Ej078ProblemDetail.combinarDetalles("Main", "Sub"));
    }

    @Test
    void testRetoExtra09_esErrorCliente() {
        // Determina si el codigo corresponde a un error del cliente (4xx).
        assertTrue(Ej078ProblemDetail.esErrorCliente(400));
    }

    @Test
    void testRetoExtra10_esErrorServidor() {
        // Determina si el codigo corresponde a un error del servidor (5xx).
        assertTrue(Ej078ProblemDetail.esErrorServidor(500));
    }

}