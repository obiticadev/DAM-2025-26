package com.masterclass.api.b43_erp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej334DataMappingEtlTest {

    @Test
    void mapearCliente() {
        ClienteErp c = Ej334DataMappingEtl.mapearCliente(
                Map.of("idExterno", " cli-1 ", "nombre", " Acme ", "email", "INFO@ACME.ES"));
        assertEquals("CLI-1", c.idExterno());
        assertEquals("Acme", c.nombre());
        assertEquals("info@acme.es", c.email());
        assertEquals("ES", c.pais()); // por defecto
        assertThrows(IllegalArgumentException.class,
                () -> Ej334DataMappingEtl.mapearCliente(Map.of("nombre", "x"))); // sin id
    }

    @Test
    void validarMaestro() {
        assertTrue(Ej334DataMappingEtl.validarMaestro(new ClienteErp("CLI-1", "Acme", "a@b.com", "ES")).isEmpty());
        assertFalse(Ej334DataMappingEtl.validarMaestro(new ClienteErp("", "", "x", "ESP")).isEmpty());
        assertEquals(List.of("cliente nulo"), Ej334DataMappingEtl.validarMaestro(null)); // caso límite
    }

    @Test
    void testRetoExtra01_normalizarTexto() {
        assertEquals("a b", Ej334DataMappingEtl.normalizarTexto("  a   b "));
    }

    @Test
    void testRetoExtra02_normalizarCodigoPais() {
        assertEquals("ES", Ej334DataMappingEtl.normalizarCodigoPais("España"));
        assertEquals("DE", Ej334DataMappingEtl.normalizarCodigoPais("de"));
    }

    @Test
    void testRetoExtra03_valorODefecto() {
        assertEquals("ES", Ej334DataMappingEtl.valorODefecto("", "ES"));
        assertEquals("X", Ej334DataMappingEtl.valorODefecto(" X ", "ES"));
    }

    @Test
    void testRetoExtra04_normalizarTelefono() {
        assertEquals("+34600123456", Ej334DataMappingEtl.normalizarTelefono("+34 600-123 456"));
    }

    @Test
    void testRetoExtra05_mapearEstado() {
        assertEquals("A", Ej334DataMappingEtl.mapearEstado("Activo"));
        assertEquals("?", Ej334DataMappingEtl.mapearEstado("loquesea"));
    }

    @Test
    void testRetoExtra06_convertirFechaIso() {
        assertEquals("2026-03-07", Ej334DataMappingEtl.convertirFechaIso("07/03/2026"));
        assertThrows(IllegalArgumentException.class, () -> Ej334DataMappingEtl.convertirFechaIso("2026-03"));
    }

    @Test
    void testRetoExtra07_truncar() {
        assertEquals("abc", Ej334DataMappingEtl.truncar("abcdef", 3));
        assertEquals("ab", Ej334DataMappingEtl.truncar("ab", 5));
    }

    @Test
    void testRetoExtra08_esFilaCompleta() {
        List<String> req = List.of("idExterno", "nombre", "email");
        assertTrue(Ej334DataMappingEtl.esFilaCompleta(
                Map.of("idExterno", "1", "nombre", "A", "email", "a@b.com"), req));
        assertFalse(Ej334DataMappingEtl.esFilaCompleta(
                Map.of("idExterno", "1", "nombre", "A"), req));
    }

    @Test
    void testRetoExtra09_contarValidos() {
        List<ClienteErp> in = List.of(
                new ClienteErp("CLI-1", "Acme", "a@b.com", "ES"),
                new ClienteErp("", "", "x", "ESP"));
        assertEquals(1, Ej334DataMappingEtl.contarValidos(in));
    }

    @Test
    void testRetoExtra10_mapearLista() {
        List<Map<String, String>> filas = List.of(
                Map.of("idExterno", "1", "nombre", "A"),
                Map.of("nombre", "sin id"),
                Map.of("idExterno", "2", "nombre", "B"));
        assertEquals(2, Ej334DataMappingEtl.mapearLista(filas).size());
    }
}
