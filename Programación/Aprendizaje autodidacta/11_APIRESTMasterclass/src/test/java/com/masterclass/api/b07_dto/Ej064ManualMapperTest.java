package com.masterclass.api.b07_dto;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b07_dto.Ej064ManualMapper.ProductoEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej064ManualMapperTest {

    @Test
    void toDtoAplicaIva() {
        var d = Ej064ManualMapper.toDto(new ProductoEntity(1L, "x", 100));
        assertEquals(121.0, d.precioConIva(), 0.0001);
        assertEquals("x", d.nombre());
    }

    @Test
    void toDtoListReutiliza() {
        var l = Ej064ManualMapper.toDtoList(List.of(
                new ProductoEntity(1L, "a", 100), new ProductoEntity(2L, "b", 200)));
        assertEquals(2, l.size());
        assertEquals(242.0, l.get(1).precioConIva(), 0.0001);
    }

    @Test
    void nullFalla() {
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.toDto(null));
    }
}
