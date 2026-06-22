package com.masterclass.api.b33_fxcontrols;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import static org.junit.jupiter.api.Assertions.*;

class Ej263BasicControlsTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void construirLogin() {
        VBox raiz = Ej263BasicControls.construirLogin();
        assertEquals(4, raiz.getChildren().size());
        assertInstanceOf(TextField.class, raiz.lookup("#usuario"));
        assertInstanceOf(PasswordField.class, raiz.lookup("#clave"));
        assertInstanceOf(CheckBox.class, raiz.lookup("#recordar"));
        assertInstanceOf(Button.class, raiz.lookup("#entrar"));
    }

    @Test
    void resumenSeleccion() {
        ToggleGroup g = new ToggleGroup();
        RadioButton efectivo = new RadioButton("Efectivo");
        RadioButton tarjeta = new RadioButton("Tarjeta");
        efectivo.setToggleGroup(g);
        tarjeta.setToggleGroup(g);
        tarjeta.setSelected(true);
        assertEquals("Tarjeta", Ej263BasicControls.resumenSeleccion(g));
        assertEquals("", Ej263BasicControls.resumenSeleccion(new ToggleGroup())); // caso límite: nada marcado
    }

    @Test
    void retoExtra01_crearEtiqueta() {
        assertEquals("Hola", Ej263BasicControls.crearEtiqueta("Hola").getText());
    }

    @Test
    void retoExtra02_alternarMarca() {
        CheckBox c = new CheckBox();
        assertTrue(Ej263BasicControls.alternarMarca(c));
        assertFalse(Ej263BasicControls.alternarMarca(c)); // vuelve al original
    }

    @Test
    void retoExtra03_textoSeguro() {
        PasswordField p = new PasswordField();
        p.setText("s3cr3to");
        assertEquals("s3cr3to", Ej263BasicControls.textoSeguro(p));
    }

    @Test
    void retoExtra04_contarSeleccionados() {
        CheckBox a = new CheckBox();
        a.setSelected(true);
        CheckBox b = new CheckBox();
        CheckBox c = new CheckBox();
        c.setSelected(true);
        assertEquals(2, Ej263BasicControls.contarSeleccionados(List.of(a, b, c)));
        assertEquals(0, Ej263BasicControls.contarSeleccionados(List.of())); // caso límite
    }

    @Test
    void retoExtra05_habilitarSi() {
        Button b = new Button("Ok");
        assertFalse(Ej263BasicControls.habilitarSi(b, true));
        assertTrue(Ej263BasicControls.habilitarSi(b, false));
    }

    @Test
    void retoExtra06_radioSeleccionado() {
        ToggleGroup g = new ToggleGroup();
        RadioButton r = new RadioButton("Sí");
        r.setToggleGroup(g);
        r.setSelected(true);
        assertEquals(Optional.of("Sí"), Ej263BasicControls.radioSeleccionado(g));
        assertEquals(Optional.empty(), Ej263BasicControls.radioSeleccionado(new ToggleGroup())); // caso límite
    }

    @Test
    void retoExtra07_validarNoVacio() {
        TextField conTexto = new TextField("Ana");
        TextField soloEspacios = new TextField("   ");
        assertTrue(Ej263BasicControls.validarNoVacio(conTexto));
        assertFalse(Ej263BasicControls.validarNoVacio(soloEspacios));
    }

    @Test
    void retoExtra08_limpiarFormulario() {
        TextField t = new TextField("texto");
        PasswordField p = new PasswordField();
        p.setText("clave");
        List<TextInputControl> campos = List.of(t, p);
        assertEquals(2, Ej263BasicControls.limpiarFormulario(campos));
        assertEquals("", t.getText());
        assertEquals("", p.getText());
    }

    @Test
    void retoExtra09_concatenarTexto() {
        TextField t = new TextField("hola");
        assertEquals("hola mundo", Ej263BasicControls.concatenarTexto(t, " mundo"));
    }

    @Test
    void retoExtra10_agruparEnToggle() {
        RadioButton a = new RadioButton("A");
        RadioButton b = new RadioButton("B");
        ToggleGroup g = Ej263BasicControls.agruparEnToggle(List.of(a, b));
        assertEquals(2, g.getToggles().size());
        a.setSelected(true);
        b.setSelected(true); // la exclusividad debe desmarcar 'a'
        assertFalse(a.isSelected());
        assertTrue(b.isSelected());
    }
}
