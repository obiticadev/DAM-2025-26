package com.masterclass.api.b32_fxbase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static org.junit.jupiter.api.Assertions.*;

class Ej262SceneSwitchingTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void cambiarRaiz() {
        Parent[] resultado = new Parent[1];
        VBox raizNueva = new VBox(new Label("nueva"));
        IniciadorFx.enFx(() -> {
            Scene escena = new Scene(new VBox(new Label("vieja")), 200, 100);
            resultado[0] = Ej262SceneSwitching.cambiarRaiz(escena, raizNueva);
            assertSame(raizNueva, escena.getRoot());
        });
        assertSame(raizNueva, resultado[0]);
        assertNull(Ej262SceneSwitching.cambiarRaiz(null, raizNueva)); // caso límite
    }

    @Test
    void navegar() {
        VBox inicio = new VBox(new Label("inicio"));
        Map<String, Parent> rutas = Map.of("inicio", inicio);
        assertSame(inicio, Ej262SceneSwitching.navegar(rutas, "inicio"));
        assertNull(Ej262SceneSwitching.navegar(rutas, "noexiste")); // caso límite
    }

    @Test
    void retoExtra01_crearRutas() {
        Map<String, Parent> rutas = Ej262SceneSwitching.crearRutas();
        assertTrue(rutas.isEmpty());
        rutas.put("x", new VBox()); // debe ser mutable
        assertEquals(1, rutas.size());
    }

    @Test
    void retoExtra02_registrar() {
        Map<String, Parent> rutas = Ej262SceneSwitching.registrar(
                Ej262SceneSwitching.registrar(Ej262SceneSwitching.crearRutas(), "a", new VBox()),
                "b", new VBox());
        assertEquals(2, rutas.size());
    }

    @Test
    void retoExtra03_existeRuta() {
        Map<String, Parent> rutas = Ej262SceneSwitching.registrar(Ej262SceneSwitching.crearRutas(), "inicio", new VBox());
        assertTrue(Ej262SceneSwitching.existeRuta(rutas, "inicio"));
        assertFalse(Ej262SceneSwitching.existeRuta(rutas, "otra"));
    }

    @Test
    void retoExtra04_rutasDisponibles() {
        Map<String, Parent> rutas = Ej262SceneSwitching.registrar(
                Ej262SceneSwitching.registrar(Ej262SceneSwitching.crearRutas(), "inicio", new VBox()),
                "ajustes", new VBox());
        assertEquals(List.of("ajustes", "inicio"), Ej262SceneSwitching.rutasDisponibles(rutas));
    }

    @Test
    void retoExtra05_navegarConDefecto() {
        VBox porDefecto = new VBox(new Label("404"));
        Map<String, Parent> rutas = Ej262SceneSwitching.registrar(Ej262SceneSwitching.crearRutas(), "inicio", new VBox());
        assertSame(porDefecto, Ej262SceneSwitching.navegarConDefecto(rutas, "rota", porDefecto));
    }

    @Test
    void retoExtra06_historialPush() {
        Deque<String> h = Ej262SceneSwitching.historialPush(new ArrayDeque<>(), "inicio");
        assertEquals("inicio", h.peek());
    }

    @Test
    void retoExtra07_volverAtras() {
        Deque<String> h = new ArrayDeque<>();
        h.push("a");
        h.push("b");
        h.push("c"); // c en la cima
        assertEquals("b", Ej262SceneSwitching.volverAtras(h));
        Deque<String> una = new ArrayDeque<>();
        una.push("sola");
        assertNull(Ej262SceneSwitching.volverAtras(una)); // caso límite
    }

    @Test
    void retoExtra08_pantallaFinal() {
        assertEquals("perfil", Ej262SceneSwitching.pantallaFinal(List.of("inicio", "ajustes", "perfil")));
        assertNull(Ej262SceneSwitching.pantallaFinal(List.of())); // caso límite
    }

    @Test
    void retoExtra09_pasarDatos() {
        Map<String, Object> ctx = Ej262SceneSwitching.pasarDatos(new java.util.HashMap<>(), "clienteId", 42);
        assertEquals(42, ctx.get("clienteId"));
    }

    @Test
    void retoExtra10_resolverSecuencia() {
        VBox inicio = new VBox();
        VBox perfil = new VBox();
        Map<String, Parent> rutas = Ej262SceneSwitching.registrar(
                Ej262SceneSwitching.registrar(Ej262SceneSwitching.crearRutas(), "inicio", inicio),
                "perfil", perfil);
        List<Parent> resuelto = Ej262SceneSwitching.resolverSecuencia(rutas, List.of("inicio", "rota", "perfil"));
        assertEquals(Arrays.asList(inicio, null, perfil), resuelto);
    }
}
