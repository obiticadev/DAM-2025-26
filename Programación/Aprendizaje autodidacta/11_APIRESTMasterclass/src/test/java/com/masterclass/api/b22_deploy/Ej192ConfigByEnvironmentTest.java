package com.masterclass.api.b22_deploy;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class Ej192ConfigByEnvironmentTest {

    @Test
    void testEsqueleto() {
        assertTrue(true, "Test para Ej192ConfigByEnvironment");
    }

    @Test
    void testEsPropiedadMarcadaComoSecreta() {
        assertTrue(Ej192ConfigByEnvironment.esPropiedadMarcadaComoSecreta("spring.datasource.password"));
        assertTrue(Ej192ConfigByEnvironment.esPropiedadMarcadaComoSecreta("app.jwt-secret"));
        assertFalse(Ej192ConfigByEnvironment.esPropiedadMarcadaComoSecreta("spring.application.name"));
    }

    @Test
    void testParsearFallbackValor() {
        assertEquals("jdbc:postgresql://localhost:5432/app", 
            Ej192ConfigByEnvironment.parsearFallbackValor("${DB_URL:jdbc:postgresql://localhost:5432/app}"));
        assertEquals("default-secret", Ej192ConfigByEnvironment.parsearFallbackValor("${JWT_SECRET:default-secret}"));
        assertEquals("direct-value", Ej192ConfigByEnvironment.parsearFallbackValor("direct-value"));
    }

    @Test
    void testEsUrlBaseDatosValida() {
        assertTrue(Ej192ConfigByEnvironment.esUrlBaseDatosValida("jdbc:postgresql://localhost:5432/mydb"));
        assertFalse(Ej192ConfigByEnvironment.esUrlBaseDatosValida("jdbc:mysql://localhost:3306/mydb"));
    }

    @Test
    void testConvertirEnvVarToCamelCase() {
        assertEquals("springDatasourceUsername", Ej192ConfigByEnvironment.convertirEnvVarToCamelCase("SPRING_DATASOURCE_USERNAME"));
        assertEquals("appJwtSecret", Ej192ConfigByEnvironment.convertirEnvVarToCamelCase("APP_JWT_SECRET"));
    }

    @Test
    void testValidarJwtSecretLargo() {
        assertTrue(Ej192ConfigByEnvironment.validarJwtSecretLargo("supersecretkeywithmorethan32characters"));
        assertFalse(Ej192ConfigByEnvironment.validarJwtSecretLargo("short"));
    }

    @Test
    void testEsPerfilActivo() {
        assertTrue(Ej192ConfigByEnvironment.esPerfilActivo("prod,security", "prod"));
        assertTrue(Ej192ConfigByEnvironment.esPerfilActivo("dev,test,prod", "test"));
        assertFalse(Ej192ConfigByEnvironment.esPerfilActivo("dev,test", "prod"));
    }

    @Test
    void testContienePlaceholders() {
        assertTrue(Ej192ConfigByEnvironment.contienePlaceholders("hello ${user.name}"));
        assertFalse(Ej192ConfigByEnvironment.contienePlaceholders("hello world"));
    }

    @Test
    void testGenerarStringConFallback() {
        assertEquals("${DB_PASSWORD:root}", Ej192ConfigByEnvironment.generarStringConFallback("DB_PASSWORD", "root"));
        assertThrows(IllegalArgumentException.class, () -> Ej192ConfigByEnvironment.generarStringConFallback(null, "fallback"));
    }

    @Test
    void testEsVariableDeEntornoValida() {
        assertTrue(Ej192ConfigByEnvironment.esVariableDeEntornoValida("SPRING_DATASOURCE_URL"));
        assertFalse(Ej192ConfigByEnvironment.esVariableDeEntornoValida("spring_datasource_url"));
        assertFalse(Ej192ConfigByEnvironment.esVariableDeEntornoValida("SPRING-DATASOURCE-URL"));
    }

    @Test
    void testSobreescribirPropiedad() {
        Map<String, String> original = new HashMap<>();
        original.put("key1", "val1");
        Map<String, String> updated = Ej192ConfigByEnvironment.sobreescribirPropiedad(original, "key2", "val2");
        assertEquals("val1", updated.get("key1"));
        assertEquals("val2", updated.get("key2"));
        assertEquals(1, original.size());
    }
}
