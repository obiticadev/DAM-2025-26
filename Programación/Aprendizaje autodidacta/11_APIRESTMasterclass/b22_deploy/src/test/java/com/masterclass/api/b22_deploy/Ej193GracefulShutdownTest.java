package com.masterclass.api.b22_deploy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej193GracefulShutdownTest {

    @Test
    void testEsqueleto() {
        assertTrue(true, "Test para Ej193GracefulShutdown");
    }

    @Test
    void testEsShutdownGracefulActivo() {
        assertTrue(Ej193GracefulShutdown.esShutdownGracefulActivo("graceful"));
        assertTrue(Ej193GracefulShutdown.esShutdownGracefulActivo("  GRACEFUL  "));
        assertFalse(Ej193GracefulShutdown.esShutdownGracefulActivo("immediate"));
    }

    @Test
    void testParsearSegundosTimeout() {
        assertEquals(30, Ej193GracefulShutdown.parsearSegundosTimeout("30s"));
        assertEquals(120, Ej193GracefulShutdown.parsearSegundosTimeout("2m"));
        assertEquals(15, Ej193GracefulShutdown.parsearSegundosTimeout("15"));
        assertEquals(-1, Ej193GracefulShutdown.parsearSegundosTimeout("abc"));
    }

    @Test
    void testEsEntrypointFormaExec() {
        assertTrue(Ej193GracefulShutdown.esEntrypointFormaExec("[\"java\", \"-jar\"]"));
        assertFalse(Ej193GracefulShutdown.esEntrypointFormaExec("java -jar"));
    }

    @Test
    void testDetectarHilosActivosTomcat() {
        assertTrue(Ej193GracefulShutdown.detectarHilosActivosTomcat(5));
        assertFalse(Ej193GracefulShutdown.detectarHilosActivosTomcat(0));
    }

    @Test
    void testSimularEndpointPeticionLenta() {
        assertTrue(Ej193GracefulShutdown.simularEndpointPeticionLenta(10));
        assertFalse(Ej193GracefulShutdown.simularEndpointPeticionLenta(-10));
    }

    @Test
    void testObtenerCodigoRespuesta() {
        assertEquals(503, Ej193GracefulShutdown.obtenerCodigoRespuesta(true));
        assertEquals(200, Ej193GracefulShutdown.obtenerCodigoRespuesta(false));
    }

    @Test
    void testEsHikariPoolCerradoCorrectamente() {
        assertTrue(Ej193GracefulShutdown.esHikariPoolCerradoCorrectamente("CLOSED"));
        assertFalse(Ej193GracefulShutdown.esHikariPoolCerradoCorrectamente("ACTIVE"));
    }

    @Test
    void testCalcularTiempoTranscurridoMs() {
        assertEquals(500, Ej193GracefulShutdown.calcularTiempoTranscurridoMs(1000, 1500));
    }

    @Test
    void testEsPreStopHookConfigurado() {
        assertTrue(Ej193GracefulShutdown.esPreStopHookConfigurado("sleep 15"));
        assertFalse(Ej193GracefulShutdown.esPreStopHookConfigurado(null));
    }

    @Test
    void testGenerarLogShutdown() {
        String log = Ej193GracefulShutdown.generarLogShutdown("tomcat", "active");
        assertEquals("[SHUTDOWN] [TOMCAT] - active", log);
    }
}
