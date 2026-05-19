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
}
