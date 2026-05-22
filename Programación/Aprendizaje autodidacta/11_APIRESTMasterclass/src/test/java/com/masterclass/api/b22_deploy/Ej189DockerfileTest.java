package com.masterclass.api.b22_deploy;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej189DockerfileTest {

    @Test
    void testEsqueleto() {
        // Test placeholder
        assertTrue(true, "Test para Ej189Dockerfile");
    }

    @Test
    void testValidarImagenBase() {
        assertTrue(Ej189Dockerfile.validarImagenBase("maven:3.9-eclipse-temurin-21"));
        assertFalse(Ej189Dockerfile.validarImagenBase("maven"));
        assertFalse(Ej189Dockerfile.validarImagenBase("maven:"));
        assertFalse(Ej189Dockerfile.validarImagenBase(null));
    }

    @Test
    void testParsearPuerto() {
        assertEquals(8080, Ej189Dockerfile.parsearPuerto("EXPOSE 8080"));
        assertEquals(80, Ej189Dockerfile.parsearPuerto("  EXPOSE 80  "));
        assertEquals(-1, Ej189Dockerfile.parsearPuerto("RUN apt-get update"));
        assertEquals(-1, Ej189Dockerfile.parsearPuerto(null));
    }

    @Test
    void testExtraerStageNames() {
        List<String> df = List.of(
            "FROM maven:3.9 AS build",
            "WORKDIR /app",
            "FROM eclipse-temurin:21 AS runtime"
        );
        List<String> stages = Ej189Dockerfile.extraerStageNames(df);
        assertEquals(2, stages.size());
        assertEquals("build", stages.get(0));
        assertEquals("runtime", stages.get(1));
    }

    @Test
    void testContarInstrucciones() {
        List<String> df = List.of(
            "FROM base",
            "RUN apt-get update",
            "RUN apt-get install -y curl",
            "COPY . ."
        );
        assertEquals(2, Ej189Dockerfile.contarInstrucciones(df, "RUN"));
        assertEquals(1, Ej189Dockerfile.contarInstrucciones(df, "COPY"));
        assertEquals(0, Ej189Dockerfile.contarInstrucciones(df, "EXPOSE"));
    }

    @Test
    void testDetectarInstruccionesSospechosas() {
        List<String> df = List.of(
            "RUN apt-get update",
            "RUN sudo apt-get install",
            "RUN chmod 777 /app"
        );
        List<String> sospechosos = Ej189Dockerfile.detectarInstruccionesSospechosas(df);
        assertEquals(2, sospechosos.size());
        assertTrue(sospechosos.get(0).contains("sudo"));
        assertTrue(sospechosos.get(1).contains("chmod 777"));
    }

    @Test
    void testGenerarDirectivaCopy() {
        assertEquals("COPY --from=build /app/target/app.jar app.jar", 
            Ej189Dockerfile.generarDirectivaCopy("build", "/app/target/app.jar", "app.jar"));
        assertThrows(IllegalArgumentException.class, () -> Ej189Dockerfile.generarDirectivaCopy(null, "a", "b"));
    }

    @Test
    void testEsUsuarioSeguro() {
        assertTrue(Ej189Dockerfile.esUsuarioSeguro("USER appuser"));
        assertFalse(Ej189Dockerfile.esUsuarioSeguro("USER root"));
        assertFalse(Ej189Dockerfile.esUsuarioSeguro("USER 0"));
        assertFalse(Ej189Dockerfile.esUsuarioSeguro("RUN adduser"));
    }

    @Test
    void testFormatearEtiqueta() {
        assertEquals("LABEL version=\"1.0.0\"", Ej189Dockerfile.formatearEtiqueta("version", "1.0.0"));
        assertThrows(IllegalArgumentException.class, () -> Ej189Dockerfile.formatearEtiqueta(" ", "1.0.0"));
    }

    @Test
    void testEsExecFormEntrypoint() {
        assertTrue(Ej189Dockerfile.esExecFormEntrypoint("ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]"));
        assertFalse(Ej189Dockerfile.esExecFormEntrypoint("ENTRYPOINT java -jar app.jar"));
        assertFalse(Ej189Dockerfile.esExecFormEntrypoint("RUN [\"java\"]"));
    }

    @Test
    void testSimplificarDockerfile() {
        List<String> df = List.of(
            "# Este es un comentario",
            "FROM base",
            "   ",
            "RUN echo hello"
        );
        List<String> limpio = Ej189Dockerfile.simplificarDockerfile(df);
        assertEquals(2, limpio.size());
        assertEquals("FROM base", limpio.get(0));
        assertEquals("RUN echo hello", limpio.get(1));
    }
}
