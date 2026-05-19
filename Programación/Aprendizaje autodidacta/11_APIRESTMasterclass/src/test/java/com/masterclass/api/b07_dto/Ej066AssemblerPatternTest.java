package com.masterclass.api.b07_dto;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b07_dto.Ej066AssemblerPattern.Linea;
import com.masterclass.api.b07_dto.Ej066AssemblerPattern.Pedido;
import java.util.List;
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
}
