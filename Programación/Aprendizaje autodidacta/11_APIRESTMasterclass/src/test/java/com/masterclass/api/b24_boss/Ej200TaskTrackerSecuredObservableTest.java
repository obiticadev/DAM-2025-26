package com.masterclass.api.b24_boss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej200TaskTrackerSecuredObservableTest {

    @Test
    void testEsTokenJwtConRol() {
        assertTrue(Ej200TaskTrackerSecuredObservable.esTokenJwtConRol("Bearer eyJhbGciOiJIUzI1NiIsInJvbGVzPVJPTEVfQURNSU4ifQ", "ROLE_ADMIN"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esTokenJwtConRol("Bearer eyJhbGciOiJIUzI1NiIsInJvbGVzPVJPTEVfVVNFUiJ9", "ROLE_ADMIN"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esTokenJwtConRol("InvalidToken", "ROLE_ADMIN"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esTokenJwtConRol(null, "ROLE_ADMIN"));
    }

    @Test
    void testTieneAccesoAutorizado() {
        assertTrue(Ej200TaskTrackerSecuredObservable.tieneAccesoAutorizado("ADMIN", "owner1", "user2"));
        assertTrue(Ej200TaskTrackerSecuredObservable.tieneAccesoAutorizado("USER", "owner1", "owner1"));
        assertFalse(Ej200TaskTrackerSecuredObservable.tieneAccesoAutorizado("USER", "owner1", "user2"));
        assertFalse(Ej200TaskTrackerSecuredObservable.tieneAccesoAutorizado(null, "owner1", "owner1"));
    }

    @Test
    void testEsOrigenCorsSeguro() {
        assertTrue(Ej200TaskTrackerSecuredObservable.esOrigenCorsSeguro("https://app.empresa.com"));
        assertTrue(Ej200TaskTrackerSecuredObservable.esOrigenCorsSeguro("https://localhost:3000"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esOrigenCorsSeguro("*"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esOrigenCorsSeguro("http://insecure.site"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esOrigenCorsSeguro(null));
    }

    @Test
    void testEsClaveDeCacheValida() {
        assertTrue(Ej200TaskTrackerSecuredObservable.esClaveDeCacheValida("tasks::project-123::page-1"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esClaveDeCacheValida("users::profile-123"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esClaveDeCacheValida(null));
    }

    @Test
    void testEsActuatorHealthUp() {
        assertTrue(Ej200TaskTrackerSecuredObservable.esActuatorHealthUp("{\"status\":\"UP\"}"));
        assertTrue(Ej200TaskTrackerSecuredObservable.esActuatorHealthUp("{\"status\":\"healthy\"}"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esActuatorHealthUp("{\"status\":\"DOWN\"}"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esActuatorHealthUp(null));
    }

    @Test
    void testEsTraceIdEnMdcValido() {
        assertTrue(Ej200TaskTrackerSecuredObservable.esTraceIdEnMdcValido("f81d4fae-7dec-11d0-a765-00a0c91e6bf6"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esTraceIdEnMdcValido("f81d4fae7dec11d0a76500a0c91e6bf6"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esTraceIdEnMdcValido(null));
    }

    @Test
    void testEsUrlDeConexionPostgresTestcontainers() {
        assertTrue(Ej200TaskTrackerSecuredObservable.esUrlDeConexionPostgresTestcontainers("jdbc:tc:postgresql:15-alpine:///databasename"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esUrlDeConexionPostgresTestcontainers("jdbc:postgresql://localhost:5432/db"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esUrlDeConexionPostgresTestcontainers(null));
    }

    @Test
    void testEsConfiguracionTestcontainersReusable() {
        assertTrue(Ej200TaskTrackerSecuredObservable.esConfiguracionTestcontainersReusable("testcontainers.reuse.enable=true"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esConfiguracionTestcontainersReusable("testcontainers.reuse.enable=false"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esConfiguracionTestcontainersReusable(null));
    }

    @Test
    void testEsCheckstyleArchivoValido() {
        assertTrue(Ej200TaskTrackerSecuredObservable.esCheckstyleArchivoValido("target/checkstyle-result.xml"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esCheckstyleArchivoValido("target/checkstyle-result.html"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esCheckstyleArchivoValido(null));
    }

    @Test
    void testEsSpringBootAppClase() {
        assertTrue(Ej200TaskTrackerSecuredObservable.esSpringBootAppClase("com.masterclass.api.TrackerApplication"));
        assertTrue(Ej200TaskTrackerSecuredObservable.esSpringBootAppClase("TaskTrackerApp"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esSpringBootAppClase("Main"));
        assertFalse(Ej200TaskTrackerSecuredObservable.esSpringBootAppClase(null));
    }
}
