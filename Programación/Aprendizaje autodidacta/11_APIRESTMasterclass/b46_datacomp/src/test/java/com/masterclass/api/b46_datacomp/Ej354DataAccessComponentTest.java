package com.masterclass.api.b46_datacomp;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej354DataAccessComponentTest {

    private static Map<Integer, Cliente> tresClientes() {
        Map<Integer, Cliente> m = new LinkedHashMap<>();
        m.put(1, new Cliente(1, "Ada"));
        m.put(2, new Cliente(2, "Linus"));
        m.put(3, new Cliente(3, "Grace"));
        return m;
    }

    @Test
    void buscar() {
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent(tresClientes());
        comp.setTamanoPagina(2);
        List<Object> eventos = new ArrayList<>();
        comp.addPropertyChangeListener(e -> eventos.add(e.getPropertyName()));
        assertEquals(List.of(new Cliente(1, "Ada"), new Cliente(2, "Linus")), comp.buscar(0));
        assertEquals(List.of(new Cliente(3, "Grace")), comp.buscar(1));
        assertEquals(List.of(), comp.buscar(-1)); // caso límite
        assertTrue(eventos.contains("cargaCompletada")); // disparó el evento de carga
    }

    @Test
    void guardar() {
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent(new LinkedHashMap<>());
        assertEquals(new Cliente(7, "Hedy"), comp.guardar(new Cliente(7, "Hedy")));
        assertEquals(new Cliente(7, "Hedy"), comp.buscar(0).get(0)); // quedó guardado
        assertNull(comp.guardar(null)); // caso límite
    }

    @Test
    void borrar() {
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent(tresClientes());
        assertTrue(comp.borrar(1));
        assertFalse(comp.borrar(99)); // caso límite: no existía
    }

    @Test
    void testRetoExtra01_contarClientes() {
        assertEquals(3, new Ej354DataAccessComponent(tresClientes()).contarClientes());
    }

    @Test
    void testRetoExtra02_healthCheck() {
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent(tresClientes());
        assertFalse(comp.healthCheck());
        comp.setUrl("jdbc:h2:mem");
        comp.setUsuario("sa");
        assertTrue(comp.healthCheck());
    }

    @Test
    void testRetoExtra03_ddlCrearTabla() {
        assertEquals("CREATE TABLE clientes (id INT PRIMARY KEY, nombre VARCHAR(255))",
                new Ej354DataAccessComponent().ddlCrearTabla());
    }

    @Test
    void testRetoExtra04_anotacionesJpa() {
        assertEquals(List.of("@Entity", "@Id", "@GeneratedValue"),
                new Ej354DataAccessComponent().anotacionesJpa());
    }

    @Test
    void testRetoExtra05_documentoMongo() {
        Map<String, Object> doc = new Ej354DataAccessComponent().documentoMongo(new Cliente(5, "Mongo"));
        assertEquals(5, doc.get("_id"));
        assertEquals("Mongo", doc.get("nombre"));
    }

    @Test
    void testRetoExtra06_guardarVarios() {
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent(new LinkedHashMap<>());
        assertEquals(3, comp.guardarVarios(List.of(
                new Cliente(1, "Ada"), new Cliente(2, "Linus"), new Cliente(3, "Grace"))));
        assertEquals(3, comp.contarClientes());
    }

    @Test
    void testRetoExtra07_buscarPorIdODisparaError() {
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent(tresClientes());
        List<Object> errores = new ArrayList<>();
        comp.addPropertyChangeListener(e -> {
            if ("error".equals(e.getPropertyName())) {
                errores.add(e.getNewValue());
            }
        });
        assertEquals(new Cliente(1, "Ada"), comp.buscarPorIdODisparaError(1));
        assertNull(comp.buscarPorIdODisparaError(99));
        assertEquals(1, errores.size()); // disparó "error" solo para el id inexistente
    }

    @Test
    void testRetoExtra08_configurarDesdeMapa() {
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent();
        comp.configurarDesdeMapa(Map.of("url", "jdbc:h2:mem", "usuario", "sa", "tamanoPagina", "5"));
        assertEquals("jdbc:h2:mem", comp.getUrl());
        assertEquals(5, comp.getTamanoPagina());
    }

    @Test
    void testRetoExtra09_cicloConexion() {
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent();
        assertEquals("true,false", comp.cicloConexion());
        assertFalse(comp.isConectado());
    }

    @Test
    void testRetoExtra10_resumenComponente() {
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent(tresClientes());
        comp.setUrl("jdbc:h2:mem");
        assertEquals("ComponenteClientesDao[url=jdbc:h2:mem, clientes=3]", comp.resumenComponente());
    }
}
