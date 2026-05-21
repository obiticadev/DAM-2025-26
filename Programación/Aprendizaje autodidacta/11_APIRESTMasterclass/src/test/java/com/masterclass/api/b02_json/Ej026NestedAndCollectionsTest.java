package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import com.masterclass.api.b02_json.Ej026NestedAndCollections.Linea;
import com.masterclass.api.b02_json.Ej026NestedAndCollections.Pedido;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej026NestedAndCollectionsTest {

    @Test
    void serializaAnidado() {
        String j = Ej026NestedAndCollections.toJson(new Pedido(1L, List.of(new Linea("cafe", 2))));
        assertTrue(j.contains("\"lineas\""));
        assertTrue(j.contains("\"producto\":\"cafe\""));
    }

    @Test
    void deserializaLista() {
        var lineas = Ej026NestedAndCollections.lineasFromJson(
                "[{\"producto\":\"cafe\",\"cantidad\":2},{\"producto\":\"te\",\"cantidad\":1}]");
        assertEquals(2, lineas.size());
        assertEquals("te", lineas.get(1).producto());
    }

    @Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_mapearListaDeMapas() {
        String json = "[{\"id\":10,\"nombre\":\"Cafe\"},{\"id\":20,\"nombre\":\"Te\"}]";
        List<Map<String, Object>> result = Ej026NestedAndCollections.mapearListaDeMapas(json);
        assertEquals(2, result.size());
        assertEquals(10, result.get(0).get("id"));
        assertEquals("Te", result.get(1).get("nombre"));
    }

    @Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_serializarListaDePedidos() {
        List<Pedido> pedidos = List.of(
            new Pedido(1L, List.of(new Linea("Azucar", 10))),
            new Pedido(2L, List.of(new Linea("Sal", 5)))
        );
        String json = Ej026NestedAndCollections.serializarListaDePedidos(pedidos);
        assertTrue(json.contains("\"Azucar\""));
        assertTrue(json.contains("\"Sal\""));
        assertTrue(json.contains("\"lineas\""));
    }

    @Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_deserializarPedidoConNulosIgnorados() {
        String json = "{\"id\":100,\"lineas\":[{\"producto\":\"Agua\",\"cantidad\":2},null,{\"producto\":\"Zumo\",\"cantidad\":1}]}";
        Pedido p = Ej026NestedAndCollections.deserializarPedidoConNulosIgnorados(json);
        assertNotNull(p);
        assertEquals(100L, p.id());
        assertEquals(2, p.lineas().size());
        assertEquals("Agua", p.lineas().get(0).producto());
        assertEquals("Zumo", p.lineas().get(1).producto());
    }

    @Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_extraerMapaClaveValor() {
        String json = "{\"servidor\":\"localhost\",\"puerto\":\"8080\",\"activo\":\"true\"}";
        Map<String, String> config = Ej026NestedAndCollections.extraerMapaClaveValor(json);
        assertEquals("localhost", config.get("servidor"));
        assertEquals("8080", config.get("puerto"));
        assertEquals("true", config.get("activo"));
    }

    @Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_mapearTipoComplejo() {
        String json = "{\"desayuno\":[{\"producto\":\"Tostada\",\"cantidad\":2},{\"producto\":\"Cafe\",\"cantidad\":1}],\"cena\":[{\"producto\":\"Sopa\",\"cantidad\":1}]}";
        Map<String, List<Linea>> menu = Ej026NestedAndCollections.mapearTipoComplejo(json);
        assertNotNull(menu);
        assertEquals(2, menu.get("desayuno").size());
        assertEquals("Sopa", menu.get("cena").get(0).producto());
    }

    @Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_escribirComoArrayDeBytes() {
        List<Linea> lineas = List.of(new Linea("Galletas", 4));
        byte[] bytes = Ej026NestedAndCollections.escribirComoArrayDeBytes(lineas);
        assertTrue(bytes.length > 0);
        String str = new String(bytes);
        assertTrue(str.contains("Galletas"));
    }

    @Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_leerColeccionDeBytes() {
        byte[] bytes = "[{\"producto\":\"Leche\",\"cantidad\":3}]".getBytes();
        List<Linea> lineas = Ej026NestedAndCollections.leerColeccionDeBytes(bytes);
        assertEquals(1, lineas.size());
        assertEquals("Leche", lineas.get(0).producto());
        assertEquals(3, lineas.get(0).cantidad());
    }

    @Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_contarTotalProductos() {
        Pedido p = new Pedido(5L, List.of(
            new Linea("Manzana", 3),
            new Linea("Pera", 7),
            new Linea("Platano", 2)
        ));
        int total = Ej026NestedAndCollections.contarTotalProductos(p);
        assertEquals(12, total);
    }

    @Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_serializarSoloLineasValidas() {
        Pedido p = new Pedido(9L, List.of(
            new Linea("Valido", 5),
            new Linea("Invalido1", 0),
            new Linea("Invalido2", -2),
            new Linea("Valido2", 3)
        ));
        String json = Ej026NestedAndCollections.serializarSoloLineasValidas(p);
        assertTrue(json.contains("Valido"));
        assertTrue(json.contains("Valido2"));
        assertFalse(json.contains("Invalido1"));
        assertFalse(json.contains("Invalido2"));
    }

    @Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_deserializarMapGenerico() {
        String json = "{\"nombre\":\"Juan\",\"edad\":25,\"intereses\":[\"lectura\",\"deporte\"],\"activo\":true}";
        Map<String, Object> map = Ej026NestedAndCollections.deserializarMapGenerico(json);
        assertEquals("Juan", map.get("nombre"));
        assertEquals(25, map.get("edad"));
        assertEquals(true, map.get("activo"));
        assertTrue(map.get("intereses") instanceof List);
    }
}
