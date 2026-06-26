package com.masterclass.api.b47_pruebas;

import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej362SecuritySmokeChecklistTest {

    private static Ej362SecuritySmokeChecklist.ConfigApp cfgSegura() {
        return new Ej362SecuritySmokeChecklist.ConfigApp(
                true, true, true, true, "https://mi-app.com",
                true, 12, false, false
        );
    }

    private static Ej362SecuritySmokeChecklist.ConfigApp cfgInsegura() {
        return new Ej362SecuritySmokeChecklist.ConfigApp(
                false, false, false, false, "*",
                false, 5, true, true
        );
    }

    @Test
    void checklistSeguridad_sinHallazgos() {
        var hallazgos = Ej362SecuritySmokeChecklist.checklistSeguridad(cfgSegura());
        assertTrue(hallazgos.isEmpty());
    }

    @Test
    void checklistSeguridad_todosLosProblemas() {
        var hallazgos = Ej362SecuritySmokeChecklist.checklistSeguridad(cfgInsegura());
        assertFalse(hallazgos.isEmpty());
        // Al menos un CRITICO
        assertTrue(hallazgos.stream().anyMatch(h -> h.severidad() == Ej362SecuritySmokeChecklist.Severidad.CRITICO));
        // Los CRITICO van primero
        assertEquals(Ej362SecuritySmokeChecklist.Severidad.CRITICO, hallazgos.get(0).severidad());
    }

    @Test
    void checklistSeguridad_null() {
        assertEquals(List.of(), Ej362SecuritySmokeChecklist.checklistSeguridad(null));
    }

    @Test
    void retoExtra01_contieneInyeccionSQL() {
        assertTrue(Ej362SecuritySmokeChecklist.contieneInyeccionSQL("' or 1=1")); // minúsculas
        assertTrue(Ej362SecuritySmokeChecklist.contieneInyeccionSQL("drop table users"));
        assertFalse(Ej362SecuritySmokeChecklist.contieneInyeccionSQL("SELECT nombre FROM clientes"));
        assertFalse(Ej362SecuritySmokeChecklist.contieneInyeccionSQL(null));
    }

    @Test
    void retoExtra02_emailValido() {
        assertTrue(Ej362SecuritySmokeChecklist.emailValido("user@example.com"));
        assertFalse(Ej362SecuritySmokeChecklist.emailValido("a@b")); // sin punto en dominio
        assertFalse(Ej362SecuritySmokeChecklist.emailValido(null));
        assertFalse(Ej362SecuritySmokeChecklist.emailValido("noatsign.com"));
    }

    @Test
    void retoExtra03_contarExcepcionesFuzzing() {
        var entradas = List.of("normal", null, "");
        int excepciones = Ej362SecuritySmokeChecklist.contarExcepcionesFuzzing(
                s -> { if (s == null) throw new NullPointerException(); return s; },
                entradas
        );
        assertEquals(1, excepciones);
        assertEquals(0, Ej362SecuritySmokeChecklist.contarExcepcionesFuzzing(null, entradas));
    }

    @Test
    void retoExtra04_dependenciasVulnerables() {
        var deps = Map.of("libA", 9.0, "libB", 5.0, "libC", 7.5);
        var vuln = Ej362SecuritySmokeChecklist.dependenciasVulnerables(deps, 7.0);
        assertTrue(vuln.contains("libA"));
        assertTrue(vuln.contains("libC"));
        assertFalse(vuln.contains("libB"));
        assertEquals(List.of(), Ej362SecuritySmokeChecklist.dependenciasVulnerables(null, 7.0));
    }

    @Test
    void retoExtra05_tienePermiso() {
        assertTrue(Ej362SecuritySmokeChecklist.tienePermiso("ADMIN", "delete"));
        assertTrue(Ej362SecuritySmokeChecklist.tienePermiso("USER", "read"));
        assertFalse(Ej362SecuritySmokeChecklist.tienePermiso("USER", "write"));
        assertFalse(Ej362SecuritySmokeChecklist.tienePermiso("GUEST", "read"));
        assertFalse(Ej362SecuritySmokeChecklist.tienePermiso("user", "read")); // case-sensitive
        assertFalse(Ej362SecuritySmokeChecklist.tienePermiso(null, "read"));
    }

    @Test
    void retoExtra06_jwtAlgoritmoValido() {
        String headerNone = Base64.getEncoder().encodeToString("alg=none".getBytes());
        String jwtNone = headerNone + ".payload.signature";
        assertFalse(Ej362SecuritySmokeChecklist.jwtAlgoritmoValido(jwtNone, "HS256"));

        String headerHS256 = Base64.getEncoder().encodeToString("alg=HS256".getBytes());
        String jwtOk = headerHS256 + ".payload.signature";
        assertTrue(Ej362SecuritySmokeChecklist.jwtAlgoritmoValido(jwtOk, "HS256"));

        assertFalse(Ej362SecuritySmokeChecklist.jwtAlgoritmoValido(null, "HS256"));
        assertFalse(Ej362SecuritySmokeChecklist.jwtAlgoritmoValido("solo.dos", "HS256"));
    }

    @Test
    void retoExtra07_logContieneDatosSensibles() {
        assertTrue(Ej362SecuritySmokeChecklist.logContieneDatosSensibles("User TOKEN: abc123")); // mayúsculas
        assertTrue(Ej362SecuritySmokeChecklist.logContieneDatosSensibles("password=secret"));
        assertFalse(Ej362SecuritySmokeChecklist.logContieneDatosSensibles("Login successful for user admin"));
        assertFalse(Ej362SecuritySmokeChecklist.logContieneDatosSensibles(null));
    }

    @Test
    void retoExtra08_tokenCSRFValido() {
        assertTrue(Ej362SecuritySmokeChecklist.tokenCSRFValido(java.util.UUID.randomUUID().toString()));
        assertFalse(Ej362SecuritySmokeChecklist.tokenCSRFValido("123"));
        assertFalse(Ej362SecuritySmokeChecklist.tokenCSRFValido(null));
    }

    @Test
    void retoExtra09_owaspTop10() {
        var top10 = Ej362SecuritySmokeChecklist.owaspTop10();
        assertEquals(10, top10.size());
        assertEquals("A01 Broken Access Control", top10.get(0));
    }

    @Test
    void retoExtra10_aptoParaProduccion() {
        var ok = List.of(
                new Ej362SecuritySmokeChecklist.Hallazgo("h1", Ej362SecuritySmokeChecklist.Severidad.ALTO),
                new Ej362SecuritySmokeChecklist.Hallazgo("h2", Ej362SecuritySmokeChecklist.Severidad.ALTO)
        );
        assertTrue(Ej362SecuritySmokeChecklist.aptoParaProduccion(ok)); // exactamente 2 ALTO → apto

        var conCritico = List.of(
                new Ej362SecuritySmokeChecklist.Hallazgo("h1", Ej362SecuritySmokeChecklist.Severidad.CRITICO)
        );
        assertFalse(Ej362SecuritySmokeChecklist.aptoParaProduccion(conCritico));

        var tresAltos = List.of(
                new Ej362SecuritySmokeChecklist.Hallazgo("a", Ej362SecuritySmokeChecklist.Severidad.ALTO),
                new Ej362SecuritySmokeChecklist.Hallazgo("b", Ej362SecuritySmokeChecklist.Severidad.ALTO),
                new Ej362SecuritySmokeChecklist.Hallazgo("c", Ej362SecuritySmokeChecklist.Severidad.ALTO)
        );
        assertFalse(Ej362SecuritySmokeChecklist.aptoParaProduccion(tresAltos));

        assertTrue(Ej362SecuritySmokeChecklist.aptoParaProduccion(List.of()));
        assertTrue(Ej362SecuritySmokeChecklist.aptoParaProduccion(null));
    }
}
