package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej178ApiDocAnnotationsTest {

    @Test
    void resuelveSummaryYSchema() {
        Map<String, String> r = Ej178ApiDocAnnotations.resolver(
                new AnnotationMeta178("Crea usuario", "ignorado", "Long", true));
        assertEquals("Crea usuario", r.get("description"));
        assertEquals("integer (required)", r.get("schema"));
    }

    @Test
    void caeADescriptionYSinDescripcion() {
        assertEquals("desc",
                Ej178ApiDocAnnotations.resolver(new AnnotationMeta178("", "desc", "String", false)).get("description"));
        assertEquals("sin descripcion",
                Ej178ApiDocAnnotations.resolver(new AnnotationMeta178(" ", " ", "String", false)).get("description"));
    }

    @Test
    void nullInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej178ApiDocAnnotations.resolver(null));
    }

    @Test
    void testRetoExtra01_esRequerido() {
        // Valida campo requerido.
        assertTrue(Ej178ApiDocAnnotations.esRequerido(new AnnotationMeta178("a", "b", "c", true)));
    }

    @Test
    void testRetoExtra02_obtenerTipoJava() {
        // Obtiene tipo original.
        assertEquals("String", Ej178ApiDocAnnotations.obtenerTipoJava(new AnnotationMeta178("a", "b", "String", true)));
    }

    @Test
    void testRetoExtra03_obtenerSummary() {
        // Obtiene summary.
        assertEquals("a", Ej178ApiDocAnnotations.obtenerSummary(new AnnotationMeta178("a", "b", "c", true)));
    }

    @Test
    void testRetoExtra04_obtenerDescription() {
        // Obtiene descripcion.
        assertEquals("b", Ej178ApiDocAnnotations.obtenerDescription(new AnnotationMeta178("a", "b", "c", true)));
    }

    @Test
    void testRetoExtra05_tieneSummary() {
        // Verifica summary.
        assertTrue(Ej178ApiDocAnnotations.tieneSummary(new AnnotationMeta178("a", "b", "c", true)));
    }

    @Test
    void testRetoExtra06_tieneDescription() {
        // Verifica descripcion.
        assertTrue(Ej178ApiDocAnnotations.tieneDescription(new AnnotationMeta178("a", "b", "c", true)));
    }

    @Test
    void testRetoExtra07_crearAnotacion() {
        // Crea anotacion simulada.
        assertNotNull(Ej178ApiDocAnnotations.crearAnotacion("a", "b", "c", true));
    }

    @Test
    void testRetoExtra08_esObjetoType() {
        // Valida si es tipo Objeto.
        assertTrue(Ej178ApiDocAnnotations.esObjetoType("User"));
    }

    @Test
    void testRetoExtra09_esEnteroType() {
        // Valida si es tipo Entero.
        assertTrue(Ej178ApiDocAnnotations.esEnteroType("int"));
    }

    @Test
    void testRetoExtra10_formatearRequerido() {
        // Texto representativo.
        assertEquals("required", Ej178ApiDocAnnotations.formatearRequerido(new AnnotationMeta178("a", "b", "c", true)));
    }

}