package com.masterclass.api.b34_fxfxml;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Parent;

import static org.junit.jupiter.api.Assertions.*;

class Ej277MultiViewNavigationTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    private Map<String, String> rutasDemo() {
        Map<String, String> r = new HashMap<>();
        r.put("inicio", Ej271FxmlLoaderBasics.VISTA_271);
        r.put("form", Ej272ControllerInjection.VISTA_272);
        return r;
    }

    @Test
    void irA() throws Exception {
        assertNotNull(Ej277MultiViewNavigation.irA(rutasDemo(), "form"));
        assertNull(Ej277MultiViewNavigation.irA(rutasDemo(), "noexiste")); // caso límite: ruta 404
    }

    @Test
    void navegarA() {
        Deque<String> h = new ArrayDeque<>();
        assertEquals("lista", Ej277MultiViewNavigation.navegarA(h, "lista"));
        assertEquals("detalle", Ej277MultiViewNavigation.navegarA(h, "detalle")); // la cima es la actual
        assertNull(Ej277MultiViewNavigation.navegarA(null, "x")); // caso límite: historial null
    }

    @Test
    void retoExtra01_crearRutas() {
        Map<String, String> r = Ej277MultiViewNavigation.crearRutas();
        assertNotNull(r);
        r.put("x", "y"); // debe ser mutable
        assertEquals(1, r.size());
    }

    @Test
    void retoExtra02_registrar() {
        Map<String, String> r = Ej277MultiViewNavigation.registrar(new HashMap<>(), "inicio", "/a.fxml");
        assertEquals("/a.fxml", r.get("inicio"));
    }

    @Test
    void retoExtra03_existeRuta() {
        assertTrue(Ej277MultiViewNavigation.existeRuta(rutasDemo(), "inicio"));
        assertFalse(Ej277MultiViewNavigation.existeRuta(rutasDemo(), "ajustes"));
    }

    @Test
    void retoExtra04_pantallasDisponibles() {
        assertEquals(List.of("form", "inicio"), Ej277MultiViewNavigation.pantallasDisponibles(rutasDemo()));
    }

    @Test
    void retoExtra05_rutaConDefecto() {
        assertEquals("/def.fxml", Ej277MultiViewNavigation.rutaConDefecto(rutasDemo(), "noexiste", "/def.fxml"));
    }

    @Test
    void retoExtra06_volverAtras() {
        Deque<String> h = new ArrayDeque<>();
        h.push("lista");
        h.push("detalle");
        assertEquals("lista", Ej277MultiViewNavigation.volverAtras(h));

        Deque<String> sola = new ArrayDeque<>();
        sola.push("unica");
        assertNull(Ej277MultiViewNavigation.volverAtras(sola)); // caso límite: una sola pantalla
    }

    @Test
    void retoExtra07_pasarDato() {
        Map<String, Object> ctx = new HashMap<>();
        Ej277MultiViewNavigation.pasarDato(ctx, "id", "42");
        assertEquals("42", ctx.get("id"));
    }

    @Test
    void retoExtra08_recuperarDato() {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("id", "42");
        assertEquals("42", Ej277MultiViewNavigation.recuperarDato(ctx, "id", String.class));
        assertNull(Ej277MultiViewNavigation.recuperarDato(ctx, "noexiste", String.class));
    }

    @Test
    void retoExtra09_controladorDeRuta() throws Exception {
        Map<String, String> r = rutasDemo();
        assertInstanceOf(Controlador272.class, Ej277MultiViewNavigation.controladorDeRuta(r, "form"));
        assertNull(Ej277MultiViewNavigation.controladorDeRuta(r, "noexiste"));
    }

    @Test
    void retoExtra10_abrirDetalle() throws Exception {
        Map<String, String> r = new HashMap<>();
        r.put("detalle", Ej271FxmlLoaderBasics.VISTA_271);
        Map<String, Object> ctx = new HashMap<>();
        Parent vista = Ej277MultiViewNavigation.abrirDetalle(r, ctx, "42");
        assertNotNull(vista);
        assertEquals("42", ctx.get("id"));
    }
}
