package com.masterclass.api.b36_fxstyle;

import org.junit.jupiter.api.Test;

import java.util.List;

import com.masterclass.api.b36_fxstyle.Ej290AccessibilityA11y.Campo;

import static org.junit.jupiter.api.Assertions.*;

class Ej290AccessibilityA11yTest {

    @Test
    void ordenFoco() {
        List<Campo> campos = List.of(
                new Campo("apellidos", 1, 0),
                new Campo("nombre", 0, 0),
                new Campo("email", 0, 1));
        assertEquals(List.of("nombre", "email", "apellidos"), Ej290AccessibilityA11y.ordenFoco(campos));
        assertEquals(List.of(), Ej290AccessibilityA11y.ordenFoco(List.of())); // caso límite
    }

    @Test
    void mnemonicDe() {
        assertEquals("Alt+G", Ej290AccessibilityA11y.mnemonicDe("_Guardar"));
        assertEquals("Alt+U", Ej290AccessibilityA11y.mnemonicDe("G_uardar"));
        assertEquals("", Ej290AccessibilityA11y.mnemonicDe("Guardar")); // caso límite: sin mnemónico
    }

    @Test
    void retoExtra01_textoVisible() {
        assertEquals("Guardar", Ej290AccessibilityA11y.textoVisible("_Guardar"));
    }

    @Test
    void retoExtra02_letraMnemonico() {
        assertEquals("G", Ej290AccessibilityA11y.letraMnemonico("_Guardar"));
        assertEquals("", Ej290AccessibilityA11y.letraMnemonico("Guardar"));
    }

    @Test
    void retoExtra03_textoAccesible() {
        assertEquals("Guardar", Ej290AccessibilityA11y.textoAccesible("Guardar", ""));
        assertEquals("Guardar cambios", Ej290AccessibilityA11y.textoAccesible("icono", "Guardar cambios"));
    }

    @Test
    void retoExtra04_escaparMnemonico() {
        assertEquals("Ctrl _ C", Ej290AccessibilityA11y.escaparMnemonico("Ctrl __ C"));
    }

    @Test
    void retoExtra05_atajoNormalizado() {
        assertEquals("Shortcut+S", Ej290AccessibilityA11y.atajoNormalizado("Ctrl+S"));
        assertEquals("Shortcut+Shift+S", Ej290AccessibilityA11y.atajoNormalizado("Ctrl+Shift+S"));
    }

    @Test
    void retoExtra06_ordenTabValido() {
        assertTrue(Ej290AccessibilityA11y.ordenTabValido(List.of(1, 2, 3)));
        assertFalse(Ej290AccessibilityA11y.ordenTabValido(List.of(1, 3, 2)));
    }

    @Test
    void retoExtra07_contrasteSuficiente() {
        assertTrue(Ej290AccessibilityA11y.contrasteSuficiente(4.5));  // la frontera SÍ pasa
        assertFalse(Ej290AccessibilityA11y.contrasteSuficiente(3.0));
    }

    @Test
    void retoExtra08_nivelContraste() {
        assertEquals("AAA", Ej290AccessibilityA11y.nivelContraste(7.0));
        assertEquals("AA", Ej290AccessibilityA11y.nivelContraste(4.5));
        assertEquals("insuficiente", Ej290AccessibilityA11y.nivelContraste(2.0));
    }

    @Test
    void retoExtra09_rolAccesible() {
        assertEquals("BUTTON", Ej290AccessibilityA11y.rolAccesible("boton"));
        assertEquals("CHECK_BOX", Ej290AccessibilityA11y.rolAccesible("casilla"));
        assertEquals("NODE", Ej290AccessibilityA11y.rolAccesible("???")); // caso límite
    }

    @Test
    void retoExtra10_ordenFocoSaltandoDeshabilitados() {
        List<Campo> campos = List.of(
                new Campo("nombre", 0, 0, true),
                new Campo("email", 0, 1, false),   // deshabilitado: se salta
                new Campo("guardar", 1, 0, true));
        assertEquals(List.of("nombre", "guardar"),
                Ej290AccessibilityA11y.ordenFocoSaltandoDeshabilitados(campos));
    }
}
