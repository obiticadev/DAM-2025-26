package com.masterclass.api.b38_fxreports;

import com.masterclass.api.b38_fxreports.Ej302SubreportsAndCharts.ItemDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class Ej302SubreportsAndChartsTest {

    private static final double D = 1e-9;

    private static List<ItemDto> items() {
        return Ej302SubreportsAndCharts.items(
                new ItemDto(1, "Enero", "Teclado", 10),
                new ItemDto(1, "Enero", "Ratón", 20),
                new ItemDto(2, "Febrero", "Monitor", 5));
    }

    @Test
    void itemsDePedido() {
        assertEquals(2, Ej302SubreportsAndCharts.itemsDePedido(1, items()).size());
        assertTrue(Ej302SubreportsAndCharts.itemsDePedido(99, items()).isEmpty()); // caso límite
    }

    @Test
    void totalPorPedido() {
        Map<Integer, Double> m = Ej302SubreportsAndCharts.totalPorPedido(items());
        assertEquals(30.0, m.get(1), D);
        assertEquals(5.0, m.get(2), D);
        assertTrue(Ej302SubreportsAndCharts.totalPorPedido(null).isEmpty()); // caso límite
    }

    @Test
    void ventasPorMes() {
        Map<String, Double> m = Ej302SubreportsAndCharts.ventasPorMes(items());
        assertEquals(30.0, m.get("Enero"), D);
        assertEquals(5.0, m.get("Febrero"), D);
        assertTrue(Ej302SubreportsAndCharts.ventasPorMes(List.of()).isEmpty()); // caso límite
    }

    @Test
    void retoExtra01_numeroDePedidos() {
        assertEquals(2, Ej302SubreportsAndCharts.numeroDePedidos(items()));
        assertEquals(0, Ej302SubreportsAndCharts.numeroDePedidos(null)); // caso límite
    }

    @Test
    void retoExtra02_totalDePedido() {
        assertEquals(30.0, Ej302SubreportsAndCharts.totalDePedido(items(), 1), D);
        assertEquals(0.0, Ej302SubreportsAndCharts.totalDePedido(items(), 99), D); // caso límite
    }

    @Test
    void retoExtra03_numeroDeItems() {
        assertEquals(2, Ej302SubreportsAndCharts.numeroDeItems(items(), 1));
        assertEquals(0, Ej302SubreportsAndCharts.numeroDeItems(items(), 99)); // caso límite
    }

    @Test
    void retoExtra04_mesConMasVentas() {
        assertEquals(Optional.of("Enero"), Ej302SubreportsAndCharts.mesConMasVentas(items()));
        assertTrue(Ej302SubreportsAndCharts.mesConMasVentas(List.of()).isEmpty()); // caso límite
    }

    @Test
    void retoExtra05_pedidoConMasItems() {
        assertEquals(Optional.of(1), Ej302SubreportsAndCharts.pedidoConMasItems(items()));
        assertTrue(Ej302SubreportsAndCharts.pedidoConMasItems(null).isEmpty()); // caso límite
    }

    @Test
    void retoExtra06_mediaPorPedido() {
        Map<Integer, Double> m = Ej302SubreportsAndCharts.mediaPorPedido(items());
        assertEquals(15.0, m.get(1), D);
        assertEquals(5.0, m.get(2), D);
    }

    @Test
    void retoExtra07_porcentajePorMes() {
        List<ItemDto> datos = Ej302SubreportsAndCharts.items(
                new ItemDto(1, "Enero", "x", 30),
                new ItemDto(2, "Febrero", "y", 10)); // total 40
        Map<String, Double> m = Ej302SubreportsAndCharts.porcentajePorMes(datos);
        assertEquals(75.0, m.get("Enero"), D);
        assertEquals(25.0, m.get("Febrero"), D);
        assertTrue(Ej302SubreportsAndCharts.porcentajePorMes(List.of()).isEmpty()); // caso límite
    }

    @Test
    void retoExtra08_mesesOrdenadosPorVenta() {
        List<ItemDto> datos = Ej302SubreportsAndCharts.items(
                new ItemDto(1, "Enero", "x", 30),
                new ItemDto(2, "Febrero", "y", 5),
                new ItemDto(3, "Marzo", "z", 50));
        assertEquals(List.of("Marzo", "Enero", "Febrero"),
                Ej302SubreportsAndCharts.mesesOrdenadosPorVenta(datos));
    }

    @Test
    void retoExtra09_productosDistintos() {
        List<ItemDto> datos = Ej302SubreportsAndCharts.items(
                new ItemDto(1, "Enero", "Teclado", 10),
                new ItemDto(1, "Enero", "Ratón", 20),
                new ItemDto(2, "Febrero", "Teclado", 5));
        assertEquals(List.of("Teclado", "Ratón"), Ej302SubreportsAndCharts.productosDistintos(datos));
    }

    @Test
    void retoExtra10_acumuladoMensual() {
        List<ItemDto> datos = Ej302SubreportsAndCharts.items(
                new ItemDto(1, "Enero", "x", 10),
                new ItemDto(2, "Febrero", "y", 20),
                new ItemDto(3, "Marzo", "z", 5));
        assertEquals(List.of(10.0, 30.0, 35.0),
                Ej302SubreportsAndCharts.acumuladoMensual(datos, List.of("Enero", "Febrero", "Marzo")));
    }
}
