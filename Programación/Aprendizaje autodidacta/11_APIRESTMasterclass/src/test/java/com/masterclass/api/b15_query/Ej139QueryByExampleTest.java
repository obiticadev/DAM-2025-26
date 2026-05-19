package com.masterclass.api.b15_query;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b15_query.Ej139QueryByExample.PersonaProbe;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej139QueryByExampleTest {

    @Test
    void condicionesSoloNoNulos() {
        var c = Ej139QueryByExample.condicionesDe(new PersonaProbe("Ana", null, 30));
        assertEquals(2, c.size());
        assertEquals("Ana", c.get("nombre"));
        assertEquals(30, c.get("edad"));
        assertFalse(c.containsKey("ciudad"));
    }

    @Test
    void probeVacio() {
        assertTrue(Ej139QueryByExample.condicionesDe(new PersonaProbe(null, null, null)).isEmpty());
    }

    @Test
    void filtrado() {
        List<Map<String, Object>> datos = List.of(
                Map.of("nombre", "Ana", "ciudad", "Madrid", "edad", 30),
                Map.of("nombre", "Ana", "ciudad", "Soria", "edad", 40),
                Map.of("nombre", "Leo", "ciudad", "Madrid", "edad", 30));
        var r = Ej139QueryByExample.filtrar(datos, new PersonaProbe("Ana", null, null));
        assertEquals(2, r.size());
    }
}
