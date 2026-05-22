package com.masterclass.api.b07_dto;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b07_dto.Ej066AssemblerPattern.Linea;
import com.masterclass.api.b07_dto.Ej066AssemblerPattern.Pedido;
import com.masterclass.api.b07_dto.Ej066AssemblerPattern.PedidoDetalleDto;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej066AssemblerPatternTest {

    @Test
    void ensamblaDesdeDosFuentes() {
        var d = Ej066AssemblerPattern.ensamblar(new Pedido(1L, "Ana"),
                List.of(new Linea(1L, "cafe", 2), new Linea(1L, "te", 1)));
        assertEquals("Ana", d.cliente());
        assertEquals(3, d.totalUnidades());
        assertEquals(List.of("cafe", "te"), d.productos());
    }

    @Test
    void lineaDeOtroPedidoFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej066AssemblerPattern.ensamblar(new Pedido(1L, "Ana"),
                        List.of(new Linea(99L, "x", 1))));
    }

    @Test
    void sinLineas() {
        var d = Ej066AssemblerPattern.ensamblar(new Pedido(1L, "Ana"), null);
        assertEquals(0, d.totalUnidades());
    }

    @Test
    void retoExtra01_ensamblarLista() {
        var pedidos = List.of(new Pedido(1L, "Ana"), new Pedido(2L, "Pedro"));
        var lineas = List.of(new Linea(1L, "cafe", 2), new Linea(2L, "pan", 1));
        var res = Ej066AssemblerPattern.ensamblarLista(pedidos, lineas);
        assertNull(res);
    }

    @Test
    void retoExtra02_filtrarPorCantidad() {
        var lineas = List.of(new Linea(1L, "cafe", 2), new Linea(1L, "pan", 5));
        var res = Ej066AssemblerPattern.filtrarPorCantidad(lineas, 3);
        assertNull(res);
    }

    @Test
    void retoExtra03_productosUnicos() {
        var lineas = List.of(new Linea(1L, "cafe", 2), new Linea(1L, "pan", 5));
        var res = Ej066AssemblerPattern.productosUnicos(lineas);
        assertNull(res);
    }

    @Test
    void retoExtra04_calcularValorEstimado() {
        var lineas = List.of(new Linea(1L, "cafe", 2), new Linea(1L, "pan", 5));
        assertEquals(0, Ej066AssemblerPattern.calcularValorEstimado(lineas, 10));
    }

    @Test
    void retoExtra05_esPedidoGrande() {
        var dto = new PedidoDetalleDto(1L, "Ana", 20, List.of("cafe"));
        assertFalse(Ej066AssemblerPattern.esPedidoGrande(dto));
    }

    @Test
    void retoExtra06_agruparPorProducto() {
        var lineas = List.of(new Linea(1L, "cafe", 2), new Linea(1L, "cafe", 5));
        var res = Ej066AssemblerPattern.agruparPorProducto(lineas);
        assertNull(res);
    }

    @Test
    void retoExtra07_copiarConClienteNuevo() {
        var dto = new PedidoDetalleDto(1L, "Ana", 20, List.of("cafe"));
        var res = Ej066AssemblerPattern.copiarConClienteNuevo(dto, "Pedro");
        assertNull(res);
    }

    @Test
    void retoExtra08_contarLineasInvalidas() {
        var lineas = List.of(new Linea(1L, "", 2), new Linea(1L, "cafe", 0));
        assertEquals(0, Ej066AssemblerPattern.contarLineasInvalidas(lineas));
    }

    @Test
    void retoExtra09_formatoTicket() {
        var dto = new PedidoDetalleDto(1L, "Ana", 20, List.of("cafe"));
        assertEquals("", Ej066AssemblerPattern.formatoTicket(dto));
    }

    @Test
    void retoExtra10_reconstruirConEnvio() {
        var dto = new PedidoDetalleDto(1L, "Ana", 20, List.of("cafe"));
        var res = Ej066AssemblerPattern.reconstruirConEnvio(dto);
        assertNull(res);
    }
}
