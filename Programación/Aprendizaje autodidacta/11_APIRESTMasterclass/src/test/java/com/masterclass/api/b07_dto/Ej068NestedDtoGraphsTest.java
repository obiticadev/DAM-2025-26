package com.masterclass.api.b07_dto;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b07_dto.Ej068NestedDtoGraphs.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej068NestedDtoGraphsTest {

    @Test
    void mapeaGrafoCompleto() {
        var e = new ClienteEntity(1L, "Ana",
                new DireccionEntity("C/ Mayor", "Madrid"), List.of("600", "601"));
        var d = Ej068NestedDtoGraphs.toDto(e);
        assertEquals(1L, d.id());
        assertEquals("Madrid", d.direccion().ciudad());
        assertEquals(2, d.numTelefonos());
    }

    @Test
    void direccionNula() {
        var e = new ClienteEntity(1L, "Ana", null, null);
        var d = Ej068NestedDtoGraphs.toDto(e);
        assertNull(d.direccion());
        assertEquals(0, d.numTelefonos());
    }
}
