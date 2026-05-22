package com.masterclass.api.b22_deploy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej194ReverseProxyTraefikTest {

    @Test
    void testEsqueleto() {
        assertTrue(true, "Test para Ej194ReverseProxyTraefik");
    }

    @Test
    void testEsPuertoProxyValido() {
        assertTrue(Ej194ReverseProxyTraefik.esPuertoProxyValido(80));
        assertTrue(Ej194ReverseProxyTraefik.esPuertoProxyValido(443));
        assertFalse(Ej194ReverseProxyTraefik.esPuertoProxyValido(8080));
    }

    @Test
    void testEsRutaSocketDocker() {
        assertTrue(Ej194ReverseProxyTraefik.esRutaSocketDocker("/var/run/docker.sock"));
        assertTrue(Ej194ReverseProxyTraefik.esRutaSocketDocker("\\\\.\\pipe\\docker_engine"));
        assertFalse(Ej194ReverseProxyTraefik.esRutaSocketDocker("/other/path"));
    }

    @Test
    void testValidarLabelTraefikEnable() {
        assertTrue(Ej194ReverseProxyTraefik.validarLabelTraefikEnable("traefik.enable", "true"));
        assertFalse(Ej194ReverseProxyTraefik.validarLabelTraefikEnable("traefik.enable", "false"));
        assertFalse(Ej194ReverseProxyTraefik.validarLabelTraefikEnable("traefik.other", "true"));
    }

    @Test
    void testGenerarRuleHost() {
        assertEquals("Host(`api.mi-dominio.com`)", Ej194ReverseProxyTraefik.generarRuleHost("api.mi-dominio.com"));
        assertThrows(IllegalArgumentException.class, () -> Ej194ReverseProxyTraefik.generarRuleHost(null));
    }

    @Test
    void testExtraerDominioDeRule() {
        assertEquals("api.mi-dominio.com", Ej194ReverseProxyTraefik.extraerDominioDeRule("Host(`api.mi-dominio.com`)"));
        assertNull(Ej194ReverseProxyTraefik.extraerDominioDeRule("Host(api.mi-dominio.com)"));
    }

    @Test
    void testEsLabelTls() {
        assertTrue(Ej194ReverseProxyTraefik.esLabelTls("traefik.http.routers.api.tls"));
        assertFalse(Ej194ReverseProxyTraefik.esLabelTls("traefik.http.routers.api.rule"));
    }

    @Test
    void testGenerarEntrypointRedirect() {
        assertEquals("traefik.http.routers.api-http.middlewares=redirect-to-https", 
            Ej194ReverseProxyTraefik.generarEntrypointRedirect("api-http", "https"));
    }

    @Test
    void testEsCertResolverValido() {
        assertTrue(Ej194ReverseProxyTraefik.esCertResolverValido("letsencrypt"));
        assertFalse(Ej194ReverseProxyTraefik.esCertResolverValido("lets encrypt"));
    }

    @Test
    void testEsLabelServicioPuerto() {
        assertTrue(Ej194ReverseProxyTraefik.esLabelServicioPuerto("traefik.http.services.api.loadbalancer.server.port", "8080"));
        assertFalse(Ej194ReverseProxyTraefik.esLabelServicioPuerto("traefik.http.services.api.loadbalancer.server.port", "abc"));
        assertFalse(Ej194ReverseProxyTraefik.esLabelServicioPuerto("other.key", "8080"));
    }

    @Test
    void testGenerarHeaderCORSLabel() {
        String label = Ej194ReverseProxyTraefik.generarHeaderCORSLabel("cors-headers", "http://localhost:3000");
        assertTrue(label.contains("cors-headers"));
        assertTrue(label.contains("localhost"));
    }
}
