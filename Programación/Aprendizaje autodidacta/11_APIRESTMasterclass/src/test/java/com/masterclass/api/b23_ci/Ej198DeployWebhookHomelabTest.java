package com.masterclass.api.b23_ci;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej198DeployWebhookHomelabTest {

    @Test
    void testEsWebhookHeaderValido() {
        assertTrue(Ej198DeployWebhookHomelab.esWebhookHeaderValido("sha256=12345abcde"));
        assertTrue(Ej198DeployWebhookHomelab.esWebhookHeaderValido(" SHA256=abcdef "));
        assertFalse(Ej198DeployWebhookHomelab.esWebhookHeaderValido("sha1=12345"));
        assertFalse(Ej198DeployWebhookHomelab.esWebhookHeaderValido(null));
    }

    @Test
    void testValidarPayloadFirma() {
        assertTrue(Ej198DeployWebhookHomelab.validarPayloadFirma("{\"event\":\"push\"}", "signature", "secret123"));
        assertFalse(Ej198DeployWebhookHomelab.validarPayloadFirma("", "signature", "secret123"));
        assertFalse(Ej198DeployWebhookHomelab.validarPayloadFirma("payload", null, "secret123"));
    }

    @Test
    void testGenerarPayloadWebhook() {
        String json = Ej198DeployWebhookHomelab.generarPayloadWebhook("api-server", "v2.0.0", "github-user");
        assertTrue(json.contains("\"repository\":\"api-server\""));
        assertTrue(json.contains("\"tag\":\"v2.0.0\""));
        assertTrue(json.contains("\"actor\":\"github-user\""));
        assertEquals("{}", Ej198DeployWebhookHomelab.generarPayloadWebhook(null, "v1", "user"));
    }

    @Test
    void testEsComandoDockerPull() {
        assertTrue(Ej198DeployWebhookHomelab.esComandoDockerPull("docker pull ghcr.io/app:latest"));
        assertTrue(Ej198DeployWebhookHomelab.esComandoDockerPull("  DOCKER PULL my-app:v1  "));
        assertFalse(Ej198DeployWebhookHomelab.esComandoDockerPull("docker run -d app"));
        assertFalse(Ej198DeployWebhookHomelab.esComandoDockerPull(null));
    }

    @Test
    void testExtraerVersionDeImagen() {
        assertEquals("v1.2.3", Ej198DeployWebhookHomelab.extraerVersionDeImagen("ghcr.io/usuario/app:v1.2.3"));
        assertEquals("latest", Ej198DeployWebhookHomelab.extraerVersionDeImagen("ghcr.io/usuario/app"));
        assertEquals("", Ej198DeployWebhookHomelab.extraerVersionDeImagen(null));
    }

    @Test
    void testEsZeroDowntimeLog() {
        assertTrue(Ej198DeployWebhookHomelab.esZeroDowntimeLog("Graceful shutdown completed successfully."));
        assertTrue(Ej198DeployWebhookHomelab.esZeroDowntimeLog("Tomcat stopped."));
        assertFalse(Ej198DeployWebhookHomelab.esZeroDowntimeLog("Application started on port 8080"));
        assertFalse(Ej198DeployWebhookHomelab.esZeroDowntimeLog(null));
    }

    @Test
    void testGenerarComandoRollback() {
        assertEquals("docker compose up -d --force-recreate my-app:v1.0.0", Ej198DeployWebhookHomelab.generarComandoRollback("my-app", "v1.0.0"));
        assertEquals("", Ej198DeployWebhookHomelab.generarComandoRollback(null, "v1.0.0"));
    }

    @Test
    void testConstruirUrlTelegramNotificacion() {
        assertEquals("https://api.telegram.org/bot123456:ABC/sendMessage?chat_id=987654", Ej198DeployWebhookHomelab.construirUrlTelegramNotificacion("123456:ABC", "987654"));
        assertEquals("", Ej198DeployWebhookHomelab.construirUrlTelegramNotificacion(null, "987654"));
    }

    @Test
    void testEsWebhookPayloadAutentico() {
        assertTrue(Ej198DeployWebhookHomelab.esWebhookPayloadAutentico(true, 150));
        assertFalse(Ej198DeployWebhookHomelab.esWebhookPayloadAutentico(false, 150));
        assertFalse(Ej198DeployWebhookHomelab.esWebhookPayloadAutentico(true, 301));
        assertFalse(Ej198DeployWebhookHomelab.esWebhookPayloadAutentico(true, -5));
    }

    @Test
    void testGenerarMensajeNotificacion() {
        String msg = Ej198DeployWebhookHomelab.generarMensajeNotificacion("API REST", "v3.1", true);
        assertTrue(msg.contains("API REST"));
        assertTrue(msg.contains("v3.1"));
        assertTrue(msg.contains("🚀 EXITO"));
        assertEquals("", Ej198DeployWebhookHomelab.generarMensajeNotificacion(null, "v1", true));
    }
}
