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

    @Test
    void extra1_toDtoConDescuento() {
        var entity = new ProductoEntity(1L, "Laptop", 1000.0);
        // precioConIva = 1210.0. 10% de descuento -> 1210.0 - 121.0 = 1089.0
        var dto = Ej064ManualMapper.pasoExtra01(entity, 10.0);
        assertNotNull(dto);
        assertEquals(1089.0, dto.precioConIva(), 0.0001);
        
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra01(null, 10.0));
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra01(entity, -5.0));
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra01(entity, 105.0));
    }

    @Test
    void extra2_toDtoListRobusto() {
        var input = java.util.Arrays.asList(
            new ProductoEntity(1L, "A", 100.0),
            null,
            new ProductoEntity(2L, "B", -10.0),
            new ProductoEntity(3L, "C", 50.0)
        );
        var result = Ej064ManualMapper.pasoExtra02(input);
        assertEquals(2, result.size());
        assertEquals(121.0, result.get(0).precioConIva(), 0.0001);
        assertEquals(60.5, result.get(1).precioConIva(), 0.0001);
        
        assertTrue(Ej064ManualMapper.pasoExtra02(null).isEmpty());
    }

    @Test
    void extra3_toEntityInverso() {
        var dto = new Ej064ManualMapper.ProductoDto(1L, "Laptop", 1210.0);
        var entity = Ej064ManualMapper.pasoExtra03(dto);
        assertNotNull(entity);
        assertEquals(1000.0, entity.precioSinIva(), 0.0001);
        assertEquals("Laptop", entity.nombre());
        
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra03(null));
    }

    @Test
    void extra4_agruparPorCategoria() {
        var input = List.of(
            new ProductoEntity(1L, "A", 20.0),  // 24.2 con IVA -> BARATO
            new ProductoEntity(2L, "B", 60.0),  // 72.6 con IVA -> MEDIO
            new ProductoEntity(3L, "C", 100.0)  // 121.0 con IVA -> CARO
        );
        var map = Ej064ManualMapper.pasoExtra04(input);
        assertNotNull(map);
        assertEquals(1, map.get("BARATO").size());
        assertEquals(1, map.get("MEDIO").size());
        assertEquals(1, map.get("CARO").size());
        assertEquals("A", map.get("BARATO").get(0).nombre());
        
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra04(null));
    }

    @Test
    void extra5_redondeoPreciso() {
        assertEquals(10.33, Ej064ManualMapper.pasoExtra05(10.33333), 0.0001);
        assertEquals(10.34, Ej064ManualMapper.pasoExtra05(10.335), 0.0001);
        assertEquals(10.33, Ej064ManualMapper.pasoExtra05(10.334), 0.0001);
        
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra05(-1.0));
    }

    @Test
    void extra6_toDtoListPaginada() {
        var input = List.of(
            new ProductoEntity(1L, "A", 10.0),
            new ProductoEntity(2L, "B", 20.0),
            new ProductoEntity(3L, "C", 30.0),
            new ProductoEntity(4L, "D", 40.0)
        );
        
        var page1 = Ej064ManualMapper.pasoExtra06(input, 0, 2);
        assertEquals(2, page1.size());
        assertEquals("A", page1.get(0).nombre());
        assertEquals("B", page1.get(1).nombre());
        
        var page2 = Ej064ManualMapper.pasoExtra06(input, 2, 5);
        assertEquals(2, page2.size());
        assertEquals("C", page2.get(0).nombre());
        assertEquals("D", page2.get(1).nombre());
        
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra06(null, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra06(input, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra06(input, 0, 0));
    }

    @Test
    void extra7_viabilidadComercial() {
        var eValido = new ProductoEntity(1L, "A", 10.0);
        var eInvalido = new ProductoEntity(2L, "B", 0.0);
        
        assertTrue(Ej064ManualMapper.pasoExtra07(eValido, 5));
        assertFalse(Ej064ManualMapper.pasoExtra07(eValido, 0));
        assertFalse(Ej064ManualMapper.pasoExtra07(eInvalido, 10));
        
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra07(null, 5));
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra07(eValido, -1));
    }

    @Test
    void extra8_buscarMasBarato() {
        var input = List.of(
            new ProductoEntity(1L, "Caro", 100.0),
            new ProductoEntity(2L, "Barato", 10.0),
            new ProductoEntity(3L, "Medio", 50.0)
        );
        var barato = Ej064ManualMapper.pasoExtra08(input);
        assertNotNull(barato);
        assertEquals("Barato", barato.nombre());
        assertEquals(12.1, barato.precioConIva(), 0.0001);
        
        assertNull(Ej064ManualMapper.pasoExtra08(List.of()));
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra08(null));
    }

    @Test
    void extra9_toDtoDivisa() {
        var input = List.of(new ProductoEntity(1L, "A", 100.0)); // 121.0 con IVA
        // Tasa 1.10 -> 121.0 * 1.10 = 133.10
        var divisaList = Ej064ManualMapper.pasoExtra09(input, 1.10);
        assertEquals(1, divisaList.size());
        assertEquals(133.1, divisaList.get(0).precioConIva(), 0.0001);
        
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra09(null, 1.10));
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra09(input, -1.0));
    }

    @Test
    void extra10_pasoExtra10() {
        assertEquals("EUR", Ej064ManualMapper.pasoExtra10("es-ES"));
        assertEquals("USD", Ej064ManualMapper.pasoExtra10("en-US"));
        assertEquals("GBP", Ej064ManualMapper.pasoExtra10("en-GB"));
        
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra10(null));
        assertThrows(IllegalArgumentException.class, () -> Ej064ManualMapper.pasoExtra10(""));
    }
}
