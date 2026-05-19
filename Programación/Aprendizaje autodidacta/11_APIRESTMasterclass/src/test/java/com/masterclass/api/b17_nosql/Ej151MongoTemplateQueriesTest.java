package com.masterclass.api.b17_nosql;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej151MongoTemplateQueriesTest {

    private final List<Pedido149> col = List.of(
            new Pedido149("a", "ana", 120),
            new Pedido149("b", "ana", 50),
            new Pedido149("c", "leo", 200));

    @Test
    void filtraPorClienteYTotalMinimo() {
        List<Pedido149> r = Ej151MongoTemplateQueries.find(col,
                Ej151MongoTemplateQueries.criterio("ana", 100));
        assertEquals(1, r.size());
        assertEquals("a", r.get(0).id());
    }

    @Test
    void clienteNullNoFiltraPorCliente() {
        List<Pedido149> r = Ej151MongoTemplateQueries.find(col,
                Ej151MongoTemplateQueries.criterio(null, 100));
        assertEquals(2, r.size());
    }

    @Test
    void sinCoincidenciasListaVacia() {
        assertTrue(Ej151MongoTemplateQueries.find(col,
                Ej151MongoTemplateQueries.criterio("leo", 999)).isEmpty());
    }

    @Test
    void argumentosNullFallan() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej151MongoTemplateQueries.find(null,
                        Ej151MongoTemplateQueries.criterio("ana", 1)));
        assertThrows(IllegalArgumentException.class,
                () -> Ej151MongoTemplateQueries.find(col, null));
    }
}
