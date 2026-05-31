package com.masterclass.api.b07_dto;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b07_dto.Ej068NestedDtoGraphs.*;
import java.util.List;
import java.util.Set;
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

    @Test
    void retoExtra01_mapearCiudades() {
        var e1 = new ClienteEntity(1L, "Ana", new DireccionEntity("C/ Mayor", "Madrid"), null);
        var e2 = new ClienteEntity(2L, "Pedro", new DireccionEntity("C/ Real", "Barcelona"), null);
        var res = Ej068NestedDtoGraphs.mapearCiudades(List.of(e1, e2));
        assertNull(res);
    }

    @Test
    void retoExtra02_filtrarPorCiudad() {
        var e1 = new ClienteEntity(1L, "Ana", new DireccionEntity("C/ Mayor", "Madrid"), null);
        var res = Ej068NestedDtoGraphs.filtrarPorCiudad(List.of(e1), "Madrid");
        assertNull(res);
    }

    @Test
    void retoExtra03_obtenerTodosTelefonos() {
        var e1 = new ClienteEntity(1L, "Ana", null, List.of("600"));
        var res = Ej068NestedDtoGraphs.obtenerTodosTelefonos(List.of(e1));
        assertNull(res);
    }

    @Test
    void retoExtra04_esDeMadrid() {
        var e = new ClienteEntity(1L, "Ana", new DireccionEntity("C/ Mayor", "Madrid"), null);
        assertFalse(Ej068NestedDtoGraphs.esDeMadrid(e));
    }

    @Test
    void retoExtra05_crearClienteSinDireccion() {
        var e = new ClienteEntity(1L, "Ana", new DireccionEntity("C/ Mayor", "Madrid"), null);
        var res = Ej068NestedDtoGraphs.crearClienteSinDireccion(e);
        assertNull(res);
    }

    @Test
    void retoExtra06_contarClientesSinDireccion() {
        var e = new ClienteEntity(1L, "Ana", null, null);
        assertEquals(0, Ej068NestedDtoGraphs.contarClientesSinDireccion(List.of(e)));
    }

    @Test
    void retoExtra07_capitalizarCiudad() {
        var e = new ClienteEntity(1L, "Ana", new DireccionEntity("C/ Mayor", "madrid"), null);
        var res = Ej068NestedDtoGraphs.capitalizarCiudad(e);
        assertNull(res);
    }

    @Test
    void retoExtra08_telefonoPrincipal() {
        var e = new ClienteEntity(1L, "Ana", null, List.of("600"));
        assertEquals("", Ej068NestedDtoGraphs.telefonoPrincipal(e));
    }

    @Test
    void retoExtra09_esClienteFrecuente() {
        var e = new ClienteEntity(1L, "Ana", null, List.of("600", "601", "602", "603"));
        assertFalse(Ej068NestedDtoGraphs.esClienteFrecuente(e));
    }

    @Test
    void retoExtra10_actualizarCalle() {
        var dto = new ClienteDto(1L, "Ana", new DireccionDto("Mayor", "Madrid"), 0);
        var res = Ej068NestedDtoGraphs.actualizarCalle(dto, "Nueva Mayor");
        assertNull(res);
    }
}
