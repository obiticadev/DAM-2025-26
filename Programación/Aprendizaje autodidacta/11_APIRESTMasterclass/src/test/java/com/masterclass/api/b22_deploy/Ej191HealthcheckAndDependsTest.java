package com.masterclass.api.b22_deploy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej191HealthcheckAndDependsTest {

    @Test
    void testEsqueleto() {
        assertTrue(true, "Test para Ej191HealthcheckAndDepends");
    }

    @Test
    void testEspgIsReadyCommand() {
        assertTrue(Ej191HealthcheckAndDepends.espgIsReadyCommand("pg_isready -U postgres"));
        assertFalse(Ej191HealthcheckAndDepends.espgIsReadyCommand("mysqladmin ping"));
    }

    @Test
    void testValidarTiempoFormato() {
        assertTrue(Ej191HealthcheckAndDepends.validarTiempoFormato("5s"));
        assertTrue(Ej191HealthcheckAndDepends.validarTiempoFormato("10m"));
        assertFalse(Ej191HealthcheckAndDepends.validarTiempoFormato("500ms"));
        assertFalse(Ej191HealthcheckAndDepends.validarTiempoFormato("abc"));
    }

    @Test
    void testGenerarDependsOnConCondition() {
        assertEquals("db: { condition: service_healthy }", Ej191HealthcheckAndDepends.generarDependsOnConCondition("db", "service_healthy"));
        assertThrows(IllegalArgumentException.class, () -> Ej191HealthcheckAndDepends.generarDependsOnConCondition(null, "ok"));
    }

    @Test
    void testEsPuertoSaludValido() {
        assertTrue(Ej191HealthcheckAndDepends.esPuertoSaludValido(8080));
        assertFalse(Ej191HealthcheckAndDepends.esPuertoSaludValido(-80));
    }

    @Test
    void testEsEstadoSaludSoportado() {
        assertTrue(Ej191HealthcheckAndDepends.esEstadoSaludSoportado("healthy"));
        assertTrue(Ej191HealthcheckAndDepends.esEstadoSaludSoportado("starting"));
        assertFalse(Ej191HealthcheckAndDepends.esEstadoSaludSoportado("down"));
    }

    @Test
    void testConstruirUrlActuator() {
        assertEquals("http://localhost:8080/actuator/health", Ej191HealthcheckAndDepends.construirUrlActuator("localhost", 8080, "actuator/health"));
        assertThrows(IllegalArgumentException.class, () -> Ej191HealthcheckAndDepends.construirUrlActuator(null, 80, "/"));
    }

    @Test
    void testParsearRetries() {
        assertEquals(3, Ej191HealthcheckAndDepends.parsearRetries("3"));
        assertEquals(-1, Ej191HealthcheckAndDepends.parsearRetries("-1"));
        assertEquals(-1, Ej191HealthcheckAndDepends.parsearRetries("abc"));
    }

    @Test
    void testEsPingTcpValido() {
        assertTrue(Ej191HealthcheckAndDepends.esPingTcpValido("localhost:5432"));
        assertFalse(Ej191HealthcheckAndDepends.esPingTcpValido("localhost"));
        assertFalse(Ej191HealthcheckAndDepends.esPingTcpValido("localhost:abc"));
    }

    @Test
    void testGenerarBloqueHealthcheck() {
        String res = Ej191HealthcheckAndDepends.generarBloqueHealthcheck("pg_isready", "5s", "3s", 3);
        assertTrue(res.contains("pg_isready"));
        assertTrue(res.contains("5s"));
    }

    @Test
    void testDebeReintentar() {
        assertTrue(Ej191HealthcheckAndDepends.debeReintentar(1, 3));
        assertFalse(Ej191HealthcheckAndDepends.debeReintentar(3, 3));
        assertFalse(Ej191HealthcheckAndDepends.debeReintentar(-1, 3));
    }
}
