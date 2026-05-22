package com.masterclass.api.b23_ci;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej197StaticAnalysisGateTest {

    @Test
    void testEsqueleto() {
        assertTrue(true, "Test para Ej197StaticAnalysisGate");
    }

    @Test
    void testEsPluginJacoco() {
        assertTrue(Ej197StaticAnalysisGate.esPluginJacoco("jacoco-maven-plugin"));
        assertFalse(Ej197StaticAnalysisGate.esPluginJacoco("maven-compiler-plugin"));
    }

    @Test
    void testEsCoberturaSuficiente() {
        assertTrue(Ej197StaticAnalysisGate.esCoberturaSuficiente(85.5, 80.0));
        assertFalse(Ej197StaticAnalysisGate.esCoberturaSuficiente(75.0, 80.0));
        assertThrows(IllegalArgumentException.class, () -> Ej197StaticAnalysisGate.esCoberturaSuficiente(120, 80));
    }

    @Test
    void testEsSonarTokenSecretoValido() {
        assertTrue(Ej197StaticAnalysisGate.esSonarTokenSecretoValido("SONAR_TOKEN", "${{ secrets.SONAR_TOKEN }}"));
        assertFalse(Ej197StaticAnalysisGate.esSonarTokenSecretoValido("OTHER_VAR", "${{ secrets.SONAR_TOKEN }}"));
    }

    @Test
    void testObtenerClasificacionQualityGate() {
        assertEquals("A", Ej197StaticAnalysisGate.obtenerClasificacionQualityGate(0, 3));
        assertEquals("B", Ej197StaticAnalysisGate.obtenerClasificacionQualityGate(1, 10));
        assertEquals("F", Ej197StaticAnalysisGate.obtenerClasificacionQualityGate(5, 50));
    }

    @Test
    void testEsDependenciaVulnerable() {
        assertTrue(Ej197StaticAnalysisGate.esDependenciaVulnerable("log4j", "CVE-2021-44228"));
        assertFalse(Ej197StaticAnalysisGate.esDependenciaVulnerable("spring-core", "none"));
    }

    @Test
    void testEsReglaLinterActiva() {
        assertTrue(Ej197StaticAnalysisGate.esReglaLinterActiva("AvoidStarImport", true));
        assertFalse(Ej197StaticAnalysisGate.esReglaLinterActiva("AvoidStarImport", false));
    }

    @Test
    void testEsRupturaBuild() {
        assertTrue(Ej197StaticAnalysisGate.esRupturaBuild(true, 75.0));
        assertFalse(Ej197StaticAnalysisGate.esRupturaBuild(false, 75.0));
        assertFalse(Ej197StaticAnalysisGate.esRupturaBuild(true, 85.0));
    }

    @Test
    void testFormatearUrlSonarProject() {
        assertEquals("https://sonarcloud.io/dashboard?id=myorg_myproject", 
            Ej197StaticAnalysisGate.formatearUrlSonarProject("myorg", "myproject"));
    }

    @Test
    void testEsArchivoReporteXmlValido() {
        assertTrue(Ej197StaticAnalysisGate.esArchivoReporteXmlValido("jacoco.xml"));
        assertFalse(Ej197StaticAnalysisGate.esArchivoReporteXmlValido("jacoco.exec"));
    }

    @Test
    void testEsRamaPrincipalProtegida() {
        assertTrue(Ej197StaticAnalysisGate.esRamaPrincipalProtegida(true, true));
        assertFalse(Ej197StaticAnalysisGate.esRamaPrincipalProtegida(true, false));
    }
}
