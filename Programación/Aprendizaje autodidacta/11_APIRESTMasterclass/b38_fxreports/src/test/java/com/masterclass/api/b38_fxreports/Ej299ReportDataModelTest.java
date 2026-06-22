package com.masterclass.api.b38_fxreports;

import com.masterclass.api.b38_fxreports.Ej299ReportDataModel.FacturaDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej299ReportDataModelTest {

    private static final double D = 1e-9;

    private static List<FacturaDto> datos() {
        return Ej299ReportDataModel.facturas(
                new FacturaDto("F-001", "Ana", 100, 21),     // total 121
                new FacturaDto("F-002", "Luis", 200, 42),    // total 242
                new FacturaDto("F-003", "Ana", 50, 10.5));   // total 60.5
    }

    @Test
    void numeroDeRegistros() {
        assertEquals(3, Ej299ReportDataModel.numeroDeRegistros(datos()));
        assertEquals(0, Ej299ReportDataModel.numeroDeRegistros(null)); // caso límite
    }

    @Test
    void totalConIva() {
        assertEquals(121.0, Ej299ReportDataModel.totalConIva(new FacturaDto("F-001", "Ana", 100, 21)), D);
        assertEquals(0.0, Ej299ReportDataModel.totalConIva(null), D); // caso límite
    }

    @Test
    void camposDisponibles() {
        List<String> campos = Ej299ReportDataModel.camposDisponibles();
        assertEquals(List.of("numero", "cliente", "base", "iva", "total"), campos);
    }

    @Test
    void retoExtra01_clienteDe() {
        assertEquals("Ana", Ej299ReportDataModel.clienteDe(new FacturaDto("F-001", "Ana", 100, 21)));
        assertEquals("", Ej299ReportDataModel.clienteDe(null)); // caso límite
    }

    @Test
    void retoExtra02_totalDeLista() {
        assertEquals(423.5, Ej299ReportDataModel.totalDeLista(datos()), D);
        assertEquals(0.0, Ej299ReportDataModel.totalDeLista(List.of()), D); // caso límite
    }

    @Test
    void retoExtra03_filtrarPorCliente() {
        assertEquals(2, Ej299ReportDataModel.filtrarPorCliente(datos(), "Ana").size());
        assertTrue(Ej299ReportDataModel.filtrarPorCliente(datos(), "Nadie").isEmpty()); // caso límite
    }

    @Test
    void retoExtra04_numerosDeFactura() {
        assertEquals(List.of("F-001", "F-002", "F-003"), Ej299ReportDataModel.numerosDeFactura(datos()));
    }

    @Test
    void retoExtra05_facturaMayor() {
        assertEquals("F-002", Ej299ReportDataModel.facturaMayor(datos()).orElseThrow().numero());
        assertTrue(Ej299ReportDataModel.facturaMayor(List.of()).isEmpty()); // caso límite
    }

    @Test
    void retoExtra06_totalPorCliente() {
        Map<String, Double> m = Ej299ReportDataModel.totalPorCliente(datos());
        assertEquals(181.5, m.get("Ana"), D);
        assertEquals(242.0, m.get("Luis"), D);
        assertEquals(2, m.size());
    }

    @Test
    void retoExtra07_baseImponibleTotal() {
        assertEquals(350.0, Ej299ReportDataModel.baseImponibleTotal(datos()), D);
        assertEquals(0.0, Ej299ReportDataModel.baseImponibleTotal(null), D); // caso límite
    }

    @Test
    void retoExtra08_facturaValida() {
        assertTrue(Ej299ReportDataModel.facturaValida(new FacturaDto("F-001", "Ana", 100, 21)));
        assertFalse(Ej299ReportDataModel.facturaValida(new FacturaDto("F-001", "Ana", -5, 21)));
        assertFalse(Ej299ReportDataModel.facturaValida(new FacturaDto(" ", "Ana", 100, 21))); // número vacío
    }

    @Test
    void retoExtra09_aFilaInforme() {
        assertArrayEquals(
                new String[]{"F-001", "Ana", "100.00", "21.00", "121.00"},
                Ej299ReportDataModel.aFilaInforme(new FacturaDto("F-001", "Ana", 100, 21)));
        assertEquals(0, Ej299ReportDataModel.aFilaInforme(null).length); // caso límite
    }

    @Test
    void retoExtra10_ordenarPorTotalDesc() {
        List<FacturaDto> original = datos();
        List<FacturaDto> ordenada = Ej299ReportDataModel.ordenarPorTotalDesc(original);
        assertEquals("F-002", ordenada.get(0).numero()); // mayor total primero
        assertEquals("F-003", ordenada.get(2).numero()); // menor total último
        assertEquals("F-001", original.get(0).numero()); // la original NO se tocó
    }
}
