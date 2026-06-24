package com.masterclass.api.b43_erp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej335BiAggregationsTest {

    private static final List<PedidoErp> PED = List.of(
            new PedidoErp("SO-1", "CLI-1", "2026-01-10", "Software", 100),
            new PedidoErp("SO-2", "CLI-1", "2026-02-05", "Software", 200),
            new PedidoErp("SO-3", "CLI-2", "2026-02-20", "Hardware", 50));

    @Test
    void ventasPorMes() {
        Map<String, Double> m = Ej335BiAggregations.ventasPorMes(PED);
        assertEquals(100.0, m.get("2026-01"));
        assertEquals(250.0, m.get("2026-02"));
        assertTrue(Ej335BiAggregations.ventasPorMes(List.of()).isEmpty()); // caso límite
    }

    @Test
    void topNClientes() {
        assertEquals(List.of("CLI-1"), Ej335BiAggregations.topNClientes(PED, 1));
        assertEquals(List.of(), Ej335BiAggregations.topNClientes(PED, 0)); // caso límite
    }

    @Test
    void testRetoExtra01_totalVentas() {
        assertEquals(350.0, Ej335BiAggregations.totalVentas(PED));
        assertEquals(0.0, Ej335BiAggregations.totalVentas(List.of()));
    }

    @Test
    void testRetoExtra02_ticketMedio() {
        assertEquals(0.0, Ej335BiAggregations.ticketMedio(List.of()));
        assertTrue(Ej335BiAggregations.ticketMedio(PED) > 116.0);
    }

    @Test
    void testRetoExtra03_totalPorCategoria() {
        assertEquals(300.0, Ej335BiAggregations.totalPorCategoria(PED).get("Software"));
    }

    @Test
    void testRetoExtra04_pedidoMayor() {
        assertEquals("SO-2", Ej335BiAggregations.pedidoMayor(PED).orElseThrow().idExterno());
        assertTrue(Ej335BiAggregations.pedidoMayor(List.of()).isEmpty());
    }

    @Test
    void testRetoExtra05_contarPorCliente() {
        assertEquals(2L, Ej335BiAggregations.contarPorCliente(PED).get("CLI-1"));
    }

    @Test
    void testRetoExtra06_mesPico() {
        assertEquals("2026-02", Ej335BiAggregations.mesPico(PED));
        assertEquals("", Ej335BiAggregations.mesPico(List.of()));
    }

    @Test
    void testRetoExtra07_crecimientoPct() {
        assertEquals(50.0, Ej335BiAggregations.crecimientoPct(100, 150));
        assertEquals(0.0, Ej335BiAggregations.crecimientoPct(0, 150));
    }

    @Test
    void testRetoExtra08_acumulado() {
        assertEquals(List.of(10.0, 30.0, 35.0), Ej335BiAggregations.acumulado(List.of(10.0, 20.0, 5.0)));
    }

    @Test
    void testRetoExtra09_ventasEntreFechas() {
        List<PedidoErp> feb = Ej335BiAggregations.ventasEntreFechas(PED, "2026-02-01", "2026-02-28");
        assertEquals(2, feb.size());
    }

    @Test
    void testRetoExtra10_tasaConversion() {
        assertEquals(25.0, Ej335BiAggregations.tasaConversion(4, 1));
        assertEquals(0.0, Ej335BiAggregations.tasaConversion(0, 1));
    }
}
