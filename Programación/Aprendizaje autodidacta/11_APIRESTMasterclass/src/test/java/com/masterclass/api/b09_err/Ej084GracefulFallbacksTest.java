package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej084GracefulFallbacksTest {

    @Test
    void exitoNoUsaFallback() {
        var g = new Ej084GracefulFallbacks();
        assertEquals("ok", g.conFallback(() -> "ok", "fb"));
        assertEquals(0, g.fallos());
    }

    @Test
    void falloDevuelveFallbackYCuenta() {
        var g = new Ej084GracefulFallbacks();
        assertEquals("cache", g.conFallback(() -> {
            throw new RuntimeException("down");
        }, "cache"));
        assertEquals(1, g.fallos());
    }

    @Test
    void testRetoExtra01_esErrorCritico() {
        // Determina si el error requiere desvio al circuito de contingencia.
        assertTrue(Ej084GracefulFallbacks.esErrorCritico(503));
    }

    @Test
    void testRetoExtra02_crearRespuestaFallback() {
        // Genera el payload de contingencia limpia.
        assertTrue(Ej084GracefulFallbacks.crearRespuestaFallback("data").contains("data"));
    }

    @Test
    void testRetoExtra03_esCircuitoAbierto() {
        // Determina si el Circuit Breaker esta en estado OPEN.
        assertTrue(Ej084GracefulFallbacks.esCircuitoAbierto("OPEN"));
    }

    @Test
    void testRetoExtra04_tiempoEsperaSeguro() {
        // Verifica que el retardo de recuperacion sea razonable.
        assertTrue(Ej084GracefulFallbacks.tiempoEsperaSeguro(5000L));
    }

    @Test
    void testRetoExtra05_esFalloServicioExterno() {
        // Determina si proviene del subsistema RestClient/WebClient.
        assertTrue(Ej084GracefulFallbacks.esFalloServicioExterno(new RuntimeException("timeout")));
    }

    @Test
    void testRetoExtra06_esErrorHttpSoportado() {
        // Determina si la peticion es de lectura (GET) y segura para reintentar.
        assertTrue(Ej084GracefulFallbacks.esErrorHttpSoportado("GET"));
    }

    @Test
    void testRetoExtra07_crearMensajeDegradado() {
        // Genera la notificacion de degradacion de servicio.
        assertEquals("Degradado: original", Ej084GracefulFallbacks.crearMensajeDegradado("original"));
    }

    @Test
    void testRetoExtra08_esFalloPersistenciaContingencia() {
        // Determina si la cache de contingencia tambien cayo.
        assertTrue(Ej084GracefulFallbacks.esFalloPersistenciaContingencia(new java.io.IOException()));
    }

    @Test
    void testRetoExtra09_calcularBackoffExponencial() {
        // Calcula retraso exponencial con tope maximo.
        assertEquals(400L, Ej084GracefulFallbacks.calcularBackoffExponencial(2, 100L));
    }

    @Test
    void testRetoExtra10_determinarEstrategiaFallback() {
        // Resuelve la accion correctiva (RETRY, CACHE, ERROR).
        assertEquals("CACHE", Ej084GracefulFallbacks.determinarEstrategiaFallback(503));
    }

}