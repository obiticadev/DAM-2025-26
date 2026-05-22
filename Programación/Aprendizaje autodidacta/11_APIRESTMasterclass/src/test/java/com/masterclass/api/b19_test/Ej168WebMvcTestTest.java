package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej168WebMvcTestTest {

    @Test
    void ok() {
        Respuesta168 r = Ej168WebMvcTest.handle("GET", "/saludo", "Ada");
        assertEquals(200, r.status);
        assertTrue(r.body.contains("Hola, Ada"));
    }

    @Test
    void faltaNombre() {
        assertEquals(400, Ej168WebMvcTest.handle("GET", "/saludo", "  ").status);
    }

    @Test
    void rutaDesconocida() {
        assertEquals(404, Ej168WebMvcTest.handle("GET", "/otra", "Ada").status);
    }

    @Test
    void metodoNoPermitido() {
        assertEquals(405, Ej168WebMvcTest.handle("POST", "/saludo", "Ada").status);
    }

    @Test
    void nulls() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej168WebMvcTest.handle(null, "/saludo", "Ada"));
    }

    @Test
    void testRetoExtra01_crearRespuestaOK() {
        // Crea una respuesta exitosa.
        assertEquals(200, Ej168WebMvcTest.crearRespuestaOK("c").status);
    }

    @Test
    void testRetoExtra02_crearRespuestaError() {
        // Crea una respuesta de error.
        assertEquals(400, Ej168WebMvcTest.crearRespuestaError(400, "err").status);
    }

    @Test
    void testRetoExtra03_esRespuestaExitosa() {
        // Comprueba si la respuesta es de exito.
        assertTrue(Ej168WebMvcTest.esRespuestaExitosa(new Respuesta168(200, "")));
    }

    @Test
    void testRetoExtra04_obtenerCuerpo() {
        // Extrae el cuerpo de la respuesta.
        assertEquals("x", Ej168WebMvcTest.obtenerCuerpo(new Respuesta168(200, "x")));
    }

    @Test
    void testRetoExtra05_esErrorServidor() {
        // Comprueba si es un error de servidor.
        assertTrue(Ej168WebMvcTest.esErrorServidor(new Respuesta168(500, "")));
    }

    @Test
    void testRetoExtra06_esRutaValida() {
        // Valida si es la ruta del saludo.
        assertTrue(Ej168WebMvcTest.esRutaValida("/saludo"));
    }

    @Test
    void testRetoExtra07_esMetodoSoportado() {
        // Valida si el metodo es soportado.
        assertTrue(Ej168WebMvcTest.esMetodoSoportado("GET"));
    }

    @Test
    void testRetoExtra08_obtenerStatus() {
        // Extrae el codigo de estado.
        assertEquals(200, Ej168WebMvcTest.obtenerStatus(new Respuesta168(200, "")));
    }

    @Test
    void testRetoExtra09_generarJsonSaludo() {
        // Genera el JSON del saludo.
        assertTrue(Ej168WebMvcTest.generarJsonSaludo("Ada").contains("Ada"));
    }

    @Test
    void testRetoExtra10_esErrorCliente() {
        // Comprueba si es un error del cliente.
        assertTrue(Ej168WebMvcTest.esErrorCliente(new Respuesta168(400, "")));
    }

}