package com.masterclass.api.b32_fxbase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static org.junit.jupiter.api.Assertions.*;

class Ej255AppLifecycleTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void construirRaiz() {
        Parent raiz = Ej255AppLifecycle.construirRaiz();
        assertNotNull(raiz);
        assertEquals("raiz", raiz.getId());
        assertEquals(3, raiz.getChildrenUnmodifiable().size());
        assertEquals("lblTitulo", raiz.getChildrenUnmodifiable().get(0).getId());
    }

    @Test
    void contarNodos() {
        VBox hoja = new VBox(new Label("a"), new Label("b"));
        VBox raiz = new VBox(new Label("titulo"), hoja); // raiz + titulo + hoja + 2 labels = 5
        assertEquals(5, Ej255AppLifecycle.contarNodos(raiz));
        assertEquals(0, Ej255AppLifecycle.contarNodos(null)); // caso límite
    }

    @Test
    void retoExtra01_crearEtiqueta() {
        assertEquals("Hola", Ej255AppLifecycle.crearEtiqueta("Hola").getText());
    }

    @Test
    void retoExtra02_crearContenedorVacio() {
        assertTrue(Ej255AppLifecycle.crearContenedorVacio().getChildrenUnmodifiable().isEmpty());
    }

    @Test
    void retoExtra03_apilarEtiquetas() {
        Parent caja = Ej255AppLifecycle.apilarEtiquetas(List.of("uno", "dos", "tres"));
        assertEquals(3, caja.getChildrenUnmodifiable().size());
        assertEquals("uno", ((Label) caja.getChildrenUnmodifiable().get(0)).getText());
    }

    @Test
    void retoExtra04_ordenDeFases() {
        assertEquals(List.of("init", "start", "stop"), Ej255AppLifecycle.ordenDeFases());
    }

    @Test
    void retoExtra05_parametrosNombrados() {
        var args = List.of("--nombre=Ada", "--modo=oscuro", "entrada.txt", "--verbose");
        assertEquals(Map.of("nombre", "Ada", "modo", "oscuro"),
                Ej255AppLifecycle.parametrosNombrados(args));
    }

    @Test
    void retoExtra06_parametrosPosicionales() {
        var args = List.of("--nombre=Ada", "entrada.txt", "salida.txt", "--verbose");
        assertEquals(List.of("entrada.txt", "salida.txt"),
                Ej255AppLifecycle.parametrosPosicionales(args));
    }

    @Test
    void retoExtra07_asignarIds() {
        List<Node> nodos = List.of(new Label(), new Button(), new Label());
        List<Node> resultado = Ej255AppLifecycle.asignarIds(nodos, "campo");
        assertEquals("campo0", resultado.get(0).getId());
        assertEquals("campo2", resultado.get(2).getId());
    }

    @Test
    void retoExtra08_primerHijoConId() {
        Label a = new Label("a");
        a.setId("uno");
        Button b = new Button("b");
        b.setId("dos");
        VBox raiz = new VBox(a, b);
        assertEquals(Optional.of(b), Ej255AppLifecycle.primerHijoConId(raiz, "dos"));
        assertEquals(Optional.empty(), Ej255AppLifecycle.primerHijoConId(raiz, "noexiste"));
    }

    @Test
    void retoExtra09_recolectarIds() {
        Label a = new Label("a");
        a.setId("uno");
        Label sinId = new Label("b");
        Button c = new Button("c");
        c.setId("tres");
        VBox raiz = new VBox(a, sinId, c);
        assertEquals(List.of("uno", "tres"), Ej255AppLifecycle.recolectarIds(raiz));
    }

    @Test
    void retoExtra10_construirArbolAnidado() {
        assertEquals(3, Ej255AppLifecycle.contarNodos(Ej255AppLifecycle.construirArbolAnidado(3)));
        assertEquals(1, Ej255AppLifecycle.contarNodos(Ej255AppLifecycle.construirArbolAnidado(1)));
    }
}
