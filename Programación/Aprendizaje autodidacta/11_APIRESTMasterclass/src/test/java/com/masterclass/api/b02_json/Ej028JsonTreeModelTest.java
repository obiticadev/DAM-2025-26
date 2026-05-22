package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej028JsonTreeModelTest {

    public record Persona(String nombre, int edad) {}

    @Test
    void primerId() {
        assertEquals(7, Ej028JsonTreeModel.primerIdDeDatos("{\"datos\":[{\"id\":7},{\"id\":9}]}"));
    }

    @Test
    void rutaInexistente() {
        assertEquals(-1, Ej028JsonTreeModel.primerIdDeDatos("{\"otros\":[]}"));
    }

    @Test
    void tamanio() {
        assertEquals(2, Ej028JsonTreeModel.tamanioDatos("{\"datos\":[{\"id\":7},{\"id\":9}]}"));
        assertEquals(0, Ej028JsonTreeModel.tamanioDatos("{\"x\":1}"));
    }

    @Test
    void retoExtra01_buscarPorRuta() {
        String json = "{\"usuario\":{\"direccion\":{\"ciudad\":\"Madrid\",\"cp\":28001}}}";
        assertEquals("Madrid", Ej028JsonTreeModel.buscarPorRuta(json, "usuario.direccion.ciudad"));
        assertEquals("28001", Ej028JsonTreeModel.buscarPorRuta(json, "usuario.direccion.cp"));
        assertEquals("", Ej028JsonTreeModel.buscarPorRuta(json, "usuario.direccion.pais"));
    }

    @Test
    void retoExtra02_crearJsonProgramatico() {
        String json = Ej028JsonTreeModel.crearJsonProgramatico("Ana", 30, List.of("cine", "viajes"));
        assertTrue(json.contains("\"nombre\":\"Ana\""));
        assertTrue(json.contains("\"edad\":30"));
        assertTrue(json.contains("\"aficiones\":[\"cine\",\"viajes\"]"));
    }

    @Test
    void retoExtra03_inyectarAtributo() {
        String original = "{\"nombre\":\"Pedro\"}";
        String modificado = Ej028JsonTreeModel.inyectarAtributo(original, "activo", true);
        assertTrue(modificado.contains("\"nombre\":\"Pedro\""));
        assertTrue(modificado.contains("\"activo\":true"));
        
        String modificado2 = Ej028JsonTreeModel.inyectarAtributo(original, "puntos", 99);
        assertTrue(modificado2.contains("\"puntos\":99"));
    }

    @Test
    void retoExtra04_eliminarAtributo() {
        String original = "{\"nombre\":\"Pedro\",\"edad\":20,\"activo\":true}";
        String modificado = Ej028JsonTreeModel.eliminarAtributo(original, "edad");
        assertTrue(modificado.contains("\"nombre\":\"Pedro\""));
        assertTrue(modificado.contains("\"activo\":true"));
        assertFalse(modificado.contains("\"edad\""));
    }

    @Test
    void retoExtra05_inspeccionarTipoDeNodo() {
        String json = "{\"nombre\":\"Luis\",\"edad\":40,\"aficiones\":[\"deporte\"],\"direccion\":{},\"activo\":false,\"vacio\":null}";
        assertEquals("TEXTUAL", Ej028JsonTreeModel.inspeccionarTipoDeNodo(json, "nombre"));
        assertEquals("NUMBER", Ej028JsonTreeModel.inspeccionarTipoDeNodo(json, "edad"));
        assertEquals("ARRAY", Ej028JsonTreeModel.inspeccionarTipoDeNodo(json, "aficiones"));
        assertEquals("OBJECT", Ej028JsonTreeModel.inspeccionarTipoDeNodo(json, "direccion"));
        assertEquals("BOOLEAN", Ej028JsonTreeModel.inspeccionarTipoDeNodo(json, "activo"));
        assertEquals("NULL", Ej028JsonTreeModel.inspeccionarTipoDeNodo(json, "vacio"));
        assertEquals("MISSING", Ej028JsonTreeModel.inspeccionarTipoDeNodo(json, "inexistente"));
    }

    @Test
    void retoExtra06_extraerValoresDeArray() {
        String json = "[{\"id\":1,\"nombre\":\"Ramon\"},{\"id\":2,\"nombre\":\"Maria\"},{\"id\":3}]";
        List<String> nombres = Ej028JsonTreeModel.extraerValoresDeArray(json, "nombre");
        assertEquals(2, nombres.size());
        assertTrue(nombres.contains("Ramon"));
        assertTrue(nombres.contains("Maria"));
    }

    @Test
    void retoExtra07_fusionarArboles() {
        String jsonA = "{\"config\":{\"puerto\":80},\"version\":\"1.0\"}";
        String jsonB = "{\"config\":{\"puerto\":8080,\"tls\":true},\"version\":\"1.1\",\"activo\":true}";
        String fusionado = Ej028JsonTreeModel.fusionarArboles(jsonA, jsonB);
        
        assertTrue(fusionado.contains("\"puerto\":8080"));
        assertTrue(fusionado.contains("\"tls\":true"));
        assertTrue(fusionado.contains("\"version\":\"1.1\""));
        assertTrue(fusionado.contains("\"activo\":true"));
    }

    @Test
    void retoExtra08_buscarClaveRecursiva() {
        String json = "{\"a\":{\"b\":{\"c\":{\"d\":{\"claveBuscada\":\"encontrado\"}}}}}";
        String valor = Ej028JsonTreeModel.buscarClaveRecursiva(json, "claveBuscada");
        assertEquals("encontrado", valor);
        
        String jsonArray = "{\"items\":[{\"id\":1},{\"detalles\":{\"secreto\":\"12345\"}}]}";
        String secreto = Ej028JsonTreeModel.buscarClaveRecursiva(jsonArray, "secreto");
        assertEquals("12345", secreto);
    }

    @Test
    void retoExtra09_convertirSubarbolADto() {
        String json = "{\"empresa\":\"ACME\",\"responsable\":{\"nombre\":\"Carlos\",\"edad\":45}}";
        Persona p = Ej028JsonTreeModel.convertirSubarbolADto(json, "responsable", Persona.class);
        assertNotNull(p);
        assertEquals("Carlos", p.nombre());
        assertEquals(45, p.edad());
    }

    @Test
    void retoExtra10_calcularPromedioDeCampo() {
        String json = "{\"tienda\":{\"ventas\":[{\"id\":1,\"monto\":10.0},{\"id\":2,\"monto\":20.0},{\"id\":3,\"monto\":30.0}]}}";
        double promedio = Ej028JsonTreeModel.calcularPromedioDeCampo(json, "tienda.ventas", "monto");
        assertEquals(20.0, promedio, 0.0001);
    }
}
