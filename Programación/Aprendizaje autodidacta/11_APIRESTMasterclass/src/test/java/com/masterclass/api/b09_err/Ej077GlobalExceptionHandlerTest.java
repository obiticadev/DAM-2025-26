package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej077GlobalExceptionHandlerTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej077GlobalExceptionHandler()).build();

    @Test
    void existenteOk() throws Exception {
        mvc.perform(get("/api/recurso/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("recurso-3"));
    }

    @Test
    void inexistenteSeTraduceA404() throws Exception {
        mvc.perform(get("/api/recurso/4"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("no existe: 4"));
    }

    @Test
    void testRetoExtra01_esExcepcionDeNegocio() {
        // Determina si una excepcion es de negocio y no de Spring o Java standard.
        assertTrue(Ej077GlobalExceptionHandler.esExcepcionDeNegocio(new RecursoNoEncontrado("")));
    }

    @Test
    void testRetoExtra02_esExcepcionGravedadAlta() {
        // Categoriza la gravedad de fallos críticos del sistema.
        assertTrue(Ej077GlobalExceptionHandler.esExcepcionGravedadAlta(new NullPointerException()));
    }

    @Test
    void testRetoExtra03_obtenerCodigoError() {
        // Mapea una excepcion a un codigo alfanumerico predefinido de error.
        assertEquals("ERR-NULO", Ej077GlobalExceptionHandler.obtenerCodigoError(new NullPointerException()));
    }

    @Test
    void testRetoExtra04_formatearMensajeError() {
        // Formatea mensajes de error combinandolos con coherencia.
        assertEquals("Default: Custom", Ej077GlobalExceptionHandler.formatearMensajeError("Default", "Custom"));
    }

    @Test
    void testRetoExtra05_extraerDetalleCausa() {
        // Extrae recursivamente la causa raiz de una excepcion.
        assertEquals("raiz", Ej077GlobalExceptionHandler.extraerDetalleCausa(new RuntimeException(new Exception("raiz"))));
    }

    @Test
    void testRetoExtra06_esErrorBaseDatos() {
        // Verifica si la excepcion proviene del stack de persistencia.
        assertTrue(Ej077GlobalExceptionHandler.esErrorBaseDatos(new java.sql.SQLException()));
    }

    @Test
    void testRetoExtra07_crearMensajeInformativo() {
        // Genera el texto amigable de recurso no hallado.
        assertTrue(Ej077GlobalExceptionHandler.crearMensajeInformativo("User", 5L).contains("User"));
    }

    @Test
    void testRetoExtra08_esErrorSintaxis() {
        // Comprueba si es un error del analizador o parseador JSON.
        assertTrue(Ej077GlobalExceptionHandler.esErrorSintaxis(new IllegalArgumentException("json")));
    }

    @Test
    void testRetoExtra09_construirCuerpoLog() {
        // Genera el formato estructurado para auditorias en logs.
        assertTrue(Ej077GlobalExceptionHandler.construirCuerpoLog("T1", "err").contains("T1"));
    }

    @Test
    void testRetoExtra10_esErrorRed() {
        // Verifica si el problema se debe a conectividad o socket abortado.
        assertTrue(Ej077GlobalExceptionHandler.esErrorRed(new java.io.IOException("Connection refused")));
    }

}