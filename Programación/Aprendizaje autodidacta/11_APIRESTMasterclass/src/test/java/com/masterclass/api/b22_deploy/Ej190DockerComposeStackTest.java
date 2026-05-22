package com.masterclass.api.b22_deploy;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej190DockerComposeStackTest {

    @Test
    void testEsqueleto() {
        assertTrue(true, "Test para Ej190DockerComposeStack");
    }

    @Test
    void testValidarVersionCompose() {
        assertTrue(Ej190DockerComposeStack.validarVersionCompose("3.8"));
        assertTrue(Ej190DockerComposeStack.validarVersionCompose("3"));
        assertFalse(Ej190DockerComposeStack.validarVersionCompose("2.1"));
        assertFalse(Ej190DockerComposeStack.validarVersionCompose("abc"));
    }

    @Test
    void testParsearVolumenLocal() {
        assertEquals("./data", Ej190DockerComposeStack.parsearVolumenLocal("./data:/var/lib/postgresql/data"));
        assertEquals("/home/user/db", Ej190DockerComposeStack.parsearVolumenLocal("/home/user/db:/var/lib/postgresql/data"));
        assertNull(Ej190DockerComposeStack.parsearVolumenLocal("no_colon_here"));
    }

    @Test
    void testValidarNombreServicio() {
        assertTrue(Ej190DockerComposeStack.validarNombreServicio("web-app"));
        assertTrue(Ej190DockerComposeStack.validarNombreServicio("db_postgres"));
        assertFalse(Ej190DockerComposeStack.validarNombreServicio("web app"));
        assertFalse(Ej190DockerComposeStack.validarNombreServicio("web.app"));
    }

    @Test
    void testGenerarVariableEntorno() {
        assertEquals("POSTGRES_USER: root", Ej190DockerComposeStack.generarVariableEntorno("POSTGRES_USER", "root"));
        assertThrows(IllegalArgumentException.class, () -> Ej190DockerComposeStack.generarVariableEntorno(null, "val"));
    }

    @Test
    void testEsRedBridge() {
        assertTrue(Ej190DockerComposeStack.esRedBridge("bridge"));
        assertTrue(Ej190DockerComposeStack.esRedBridge("  BRIDGE  "));
        assertTrue(Ej190DockerComposeStack.esRedBridge(null));
        assertFalse(Ej190DockerComposeStack.esRedBridge("overlay"));
    }

    @Test
    void testConstruirMapeoPuerto() {
        assertEquals("8080:8080", Ej190DockerComposeStack.construirMapeoPuerto(8080, 8080));
        assertThrows(IllegalArgumentException.class, () -> Ej190DockerComposeStack.construirMapeoPuerto(-1, 80));
    }

    @Test
    void testContieneServicio() {
        String yaml = "version: '3'\nservices:\n  db:\n    image: postgres\n  api:\n    image: my-api";
        assertTrue(Ej190DockerComposeStack.contieneServicio(yaml, "db"));
        assertTrue(Ej190DockerComposeStack.contieneServicio(yaml, "api"));
        assertFalse(Ej190DockerComposeStack.contieneServicio(yaml, "redis"));
    }

    @Test
    void testEsImagenPostgresValida() {
        assertTrue(Ej190DockerComposeStack.esImagenPostgresValida("postgres"));
        assertTrue(Ej190DockerComposeStack.esImagenPostgresValida("postgres:15-alpine"));
        assertFalse(Ej190DockerComposeStack.esImagenPostgresValida("mysql:latest"));
    }

    @Test
    void testGenerarComandoComposeUp() {
        assertEquals("docker compose -f prod.yml up -d", Ej190DockerComposeStack.generarComandoComposeUp("prod.yml", true));
        assertEquals("docker compose up", Ej190DockerComposeStack.generarComandoComposeUp(null, false));
    }

    @Test
    void testExtraerVariablesEntorno() {
        String serviceYaml = "environment:\n  - POSTGRES_DB=app\n  - POSTGRES_USER: root\n  - # COMMENTED=true";
        List<String> envs = Ej190DockerComposeStack.extraerVariablesEntorno(serviceYaml);
        assertEquals(2, envs.size());
        assertTrue(envs.contains("POSTGRES_DB"));
        assertTrue(envs.contains("POSTGRES_USER"));
    }
}
