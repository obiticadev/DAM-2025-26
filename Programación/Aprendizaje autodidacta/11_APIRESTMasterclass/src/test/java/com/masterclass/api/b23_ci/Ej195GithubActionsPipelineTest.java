package com.masterclass.api.b23_ci;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej195GithubActionsPipelineTest {

    @Test
    void testEsqueleto() {
        assertTrue(true, "Test para Ej195GithubActionsPipeline");
    }

    @Test
    void testEsTriggerPushMain() {
        assertTrue(Ej195GithubActionsPipeline.esTriggerPushMain("push", "main"));
        assertFalse(Ej195GithubActionsPipeline.esTriggerPushMain("push", "feature-branch"));
        assertFalse(Ej195GithubActionsPipeline.esTriggerPushMain("pull_request", "main"));
    }

    @Test
    void testEsUbuntuRunnerValido() {
        assertTrue(Ej195GithubActionsPipeline.esUbuntuRunnerValido("ubuntu-latest"));
        assertTrue(Ej195GithubActionsPipeline.esUbuntuRunnerValido("ubuntu-22.04"));
        assertFalse(Ej195GithubActionsPipeline.esUbuntuRunnerValido("windows-latest"));
    }

    @Test
    void testEsCheckoutActionValida() {
        assertTrue(Ej195GithubActionsPipeline.esCheckoutActionValida("actions/checkout@v4"));
        assertTrue(Ej195GithubActionsPipeline.esCheckoutActionValida("actions/checkout@v3"));
        assertFalse(Ej195GithubActionsPipeline.esCheckoutActionValida("actions/checkout@v2"));
    }

    @Test
    void testEsJdkTemurinConfigured() {
        assertTrue(Ej195GithubActionsPipeline.esJdkTemurinConfigured("temurin", "21"));
        assertFalse(Ej195GithubActionsPipeline.esJdkTemurinConfigured("zulu", "21"));
        assertFalse(Ej195GithubActionsPipeline.esJdkTemurinConfigured("temurin", "8"));
    }

    @Test
    void testValidarCacheKey() {
        assertTrue(Ej195GithubActionsPipeline.validarCacheKey("maven-${{ runner.os }}-${{ hashFiles('**/pom.xml') }}"));
        assertFalse(Ej195GithubActionsPipeline.validarCacheKey("maven-cache"));
    }

    @Test
    void testGenerarComandoMavenVerify() {
        assertEquals("mvn -B clean verify", Ej195GithubActionsPipeline.generarComandoMavenVerify(false));
        assertEquals("mvn -B clean verify -DskipTests=true", Ej195GithubActionsPipeline.generarComandoMavenVerify(true));
    }

    @Test
    void testParsearRutaReporteSurefire() {
        assertEquals("target/surefire-reports", Ej195GithubActionsPipeline.parsearRutaReporteSurefire(null));
        assertEquals("my-module/target/surefire-reports", Ej195GithubActionsPipeline.parsearRutaReporteSurefire("my-module"));
    }

    @Test
    void testEsJobTerminadoExitosamente() {
        assertTrue(Ej195GithubActionsPipeline.esJobTerminadoExitosamente("success"));
        assertFalse(Ej195GithubActionsPipeline.esJobTerminadoExitosamente("failed"));
    }

    @Test
    void testEsRutaArtifactValida() {
        assertTrue(Ej195GithubActionsPipeline.esRutaArtifactValida("**/target/surefire-reports/*.xml"));
        assertFalse(Ej195GithubActionsPipeline.esRutaArtifactValida("src/main/resources"));
    }

    @Test
    void testValidarEstructuraYmlBasica() {
        String yml = "name: CI\non: push\njobs:\n  build:\n    steps:\n      - uses: actions/checkout@v4";
        assertTrue(Ej195GithubActionsPipeline.validarEstructuraYmlBasica(yml));
        assertFalse(Ej195GithubActionsPipeline.validarEstructuraYmlBasica("name: CI"));
    }
}
