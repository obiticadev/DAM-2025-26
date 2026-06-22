package com.masterclass.api.b32_fxbase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static org.junit.jupiter.api.Assertions.*;

class Ej256SceneGraphTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    /** raiz(VBox) -> [ cab(Label#cab), fila(HBox) -> [ btn(Button#ok), eti(Label#eti) ] ] */
    private VBox arbolEjemplo() {
        Label cab = new Label("cabecera");
        cab.setId("cab");
        Button btn = new Button("ok");
        btn.setId("ok");
        Label eti = new Label("eti");
        eti.setId("eti");
        HBox fila = new HBox(btn, eti);
        fila.setId("fila");
        return new VBox(cab, fila);
    }

    @Test
    void buscarPorId() {
        VBox raiz = arbolEjemplo();
        assertEquals("ok", Ej256SceneGraph.buscarPorId(raiz, "ok").getId()); // hondo
        assertNull(Ej256SceneGraph.buscarPorId(raiz, "noexiste")); // caso límite
    }

    @Test
    void profundidadArbol() {
        VBox raiz = arbolEjemplo(); // raiz -> fila -> btn = 3 niveles
        assertEquals(3, Ej256SceneGraph.profundidadArbol(raiz));
        assertEquals(0, Ej256SceneGraph.profundidadArbol(null)); // caso límite
    }

    @Test
    void retoExtra01_contarHojas() {
        // hojas: cab, btn, eti = 3
        assertEquals(3, Ej256SceneGraph.contarHojas(arbolEjemplo()));
    }

    @Test
    void retoExtra02_recolectarTodosLosIds() {
        assertEquals(List.of("cab", "fila", "ok", "eti"),
                Ej256SceneGraph.recolectarTodosLosIds(arbolEjemplo()));
    }

    @Test
    void retoExtra03_existeId() {
        assertTrue(Ej256SceneGraph.existeId(arbolEjemplo(), "eti"));
        assertFalse(Ej256SceneGraph.existeId(arbolEjemplo(), "fantasma"));
    }

    @Test
    void retoExtra04_rutaHastaId() {
        assertEquals(List.of("fila", "ok"), Ej256SceneGraph.rutaHastaId(arbolEjemplo(), "ok"));
        assertEquals(List.of(), Ej256SceneGraph.rutaHastaId(arbolEjemplo(), "fantasma"));
    }

    @Test
    void retoExtra05_contarVisibles() {
        VBox raiz = arbolEjemplo();
        // raiz + cab + fila + btn + eti = 5 visibles
        assertEquals(5, Ej256SceneGraph.contarVisibles(raiz));
        raiz.getChildrenUnmodifiable().get(0).setVisible(false);
        assertEquals(4, Ej256SceneGraph.contarVisibles(raiz));
    }

    @Test
    void retoExtra06_contarNoGestionados() {
        VBox raiz = arbolEjemplo();
        assertEquals(0, Ej256SceneGraph.contarNoGestionados(raiz));
        raiz.getChildrenUnmodifiable().get(0).setManaged(false);
        assertEquals(1, Ej256SceneGraph.contarNoGestionados(raiz));
    }

    @Test
    void retoExtra07_indiceZ() {
        Label a = new Label("a");
        Label b = new Label("b");
        VBox raiz = new VBox(a, b);
        assertEquals(1, Ej256SceneGraph.indiceZ(raiz, b));
        assertEquals(-1, Ej256SceneGraph.indiceZ(raiz, new Label("fuera"))); // caso límite
    }

    @Test
    void retoExtra08_traerAlFrente() {
        Label a = new Label("a");
        Label b = new Label("b");
        Label c = new Label("c");
        VBox raiz = new VBox(a, b, c);
        assertEquals(2, Ej256SceneGraph.traerAlFrente(raiz, a));
        assertEquals(a, raiz.getChildrenUnmodifiable().get(2));
    }

    @Test
    void retoExtra09_anchuraMaxima() {
        // nivel 0: raiz (1); nivel 1: 3 hijos -> anchura 3
        VBox raiz = new VBox(new Label("1"), new Label("2"), new Label("3"));
        assertEquals(3, Ej256SceneGraph.anchuraMaxima(raiz));
    }

    @Test
    void retoExtra10_buscarPorTipo() {
        VBox raiz = arbolEjemplo();
        Node encontrado = Ej256SceneGraph.buscarPorTipo(raiz, Button.class);
        assertNotNull(encontrado);
        assertEquals("ok", encontrado.getId());
        assertNull(Ej256SceneGraph.buscarPorTipo(raiz, javafx.scene.control.Slider.class));
    }
}
