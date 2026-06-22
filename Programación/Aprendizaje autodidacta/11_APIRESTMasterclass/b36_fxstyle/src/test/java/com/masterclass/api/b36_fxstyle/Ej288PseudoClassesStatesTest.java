package com.masterclass.api.b36_fxstyle;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Ej288PseudoClassesStatesTest {

    @Test
    void pseudoClasesActivas() {
        assertEquals(List.of(":hover", ":focused"),
                Ej288PseudoClassesStates.pseudoClasesActivas(true, true, false));
        assertEquals(List.of(),
                Ej288PseudoClassesStates.pseudoClasesActivas(false, false, false)); // caso límite
    }

    @Test
    void selectorConPseudo() {
        assertEquals(".boton:hover", Ej288PseudoClassesStates.selectorConPseudo(".boton", "hover"));
        assertEquals(".boton:focused", Ej288PseudoClassesStates.selectorConPseudo(".boton", ":focused")); // ya trae ':'
    }

    @Test
    void retoExtra01_conHover() {
        assertEquals(".boton:hover", Ej288PseudoClassesStates.conHover(".boton"));
    }

    @Test
    void retoExtra02_conEstado() {
        assertEquals(".boton:pressed", Ej288PseudoClassesStates.conEstado(".boton", "pressed"));
    }

    @Test
    void retoExtra03_nombrePseudoClasePropia() {
        assertEquals("en-error", Ej288PseudoClassesStates.nombrePseudoClasePropia("En Error"));
        assertEquals("ya", Ej288PseudoClassesStates.nombrePseudoClasePropia("Ya")); // caso límite
    }

    @Test
    void retoExtra04_estaActiva() {
        assertEquals("error", Ej288PseudoClassesStates.estaActiva("error", true));
        assertEquals("", Ej288PseudoClassesStates.estaActiva("error", false));
    }

    @Test
    void retoExtra05_pseudoEstadoCampo() {
        assertEquals("valido", Ej288PseudoClassesStates.pseudoEstadoCampo(true, false));
        assertEquals("invalido", Ej288PseudoClassesStates.pseudoEstadoCampo(false, false));
        assertEquals("vacio", Ej288PseudoClassesStates.pseudoEstadoCampo(false, true)); // vacío manda
    }

    @Test
    void retoExtra06_selectorNegado() {
        assertEquals(":not(.boton)", Ej288PseudoClassesStates.selectorNegado(".boton"));
    }

    @Test
    void retoExtra07_combinarPseudo() {
        assertEquals(".boton:hover:focused",
                Ej288PseudoClassesStates.combinarPseudo(".boton", List.of("hover", "focused")));
        assertEquals(".boton", Ej288PseudoClassesStates.combinarPseudo(".boton", List.of())); // caso límite
    }

    @Test
    void retoExtra08_estadoPrioritario() {
        assertEquals("hover", Ej288PseudoClassesStates.estadoPrioritario(Set.of("hover", "focused")));
        assertEquals("", Ej288PseudoClassesStates.estadoPrioritario(Set.of())); // caso límite
    }

    @Test
    void retoExtra09_transicionValida() {
        assertTrue(Ej288PseudoClassesStates.transicionValida("hover", "pressed"));
        assertFalse(Ej288PseudoClassesStates.transicionValida("normal", "pressed"));
    }

    @Test
    void retoExtra10_cssPseudoClasePropia() {
        Map<String, String> props = new LinkedHashMap<>();
        props.put("-fx-border-color", "red");
        assertEquals(".campo:error { -fx-border-color: red; }",
                Ej288PseudoClassesStates.cssPseudoClasePropia(".campo", "error", props));
    }
}
