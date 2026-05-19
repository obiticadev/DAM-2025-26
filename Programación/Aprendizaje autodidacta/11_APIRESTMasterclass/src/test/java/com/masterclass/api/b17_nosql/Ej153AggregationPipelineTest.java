package com.masterclass.api.b17_nosql;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej153AggregationPipelineTest {

    private final List<Pedido149> col = List.of(
            new Pedido149("a", "ana", 100),
            new Pedido149("b", "ana", 50),
            new Pedido149("c", "leo", 200),
            new Pedido149("d", "leo", 10));

    @Test
    void agrupaSumaYOrdenaDesc() {
        List<Map<String, Object>> r = Ej153AggregationPipeline.agregar(col, 40);
        assertEquals(2, r.size());
        assertEquals("leo", r.get(0).get("cliente"));
        assertEquals(200.0, ((Number) r.get(0).get("sumaTotal")).doubleValue());
        assertEquals("ana", r.get(1).get("cliente"));
        assertEquals(150.0, ((Number) r.get(1).get("sumaTotal")).doubleValue());
    }

    @Test
    void matchVacioDevuelveVacio() {
        assertTrue(Ej153AggregationPipeline.agregar(col, 9999).isEmpty());
    }

    @Test
    void nullFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej153AggregationPipeline.agregar(null, 0));
    }
}
