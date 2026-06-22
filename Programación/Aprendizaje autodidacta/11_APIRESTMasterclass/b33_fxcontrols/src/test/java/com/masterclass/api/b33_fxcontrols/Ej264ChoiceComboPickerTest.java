package com.masterclass.api.b33_fxcontrols;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;

import static org.junit.jupiter.api.Assertions.*;

class Ej264ChoiceComboPickerTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void construirCombo() {
        ComboBox<String> combo = Ej264ChoiceComboPicker.construirCombo(List.of("Rojo", "Verde", "Azul"), "Verde");
        assertEquals(3, combo.getItems().size());
        assertEquals("Verde", combo.getValue());
    }

    @Test
    void valorSpinner() {
        assertEquals(5, Ej264ChoiceComboPicker.valorSpinner(0, 10, 5));
        assertEquals(0, Ej264ChoiceComboPicker.valorSpinner(0, 10, 0)); // caso límite: el mínimo
    }

    @Test
    void retoExtra01_crearSlider() {
        assertEquals(50.0, Ej264ChoiceComboPicker.crearSlider(0, 100, 50).getValue());
    }

    @Test
    void retoExtra02_porcentajeSlider() {
        assertEquals(25.0, Ej264ChoiceComboPicker.porcentajeSlider(new Slider(0, 200, 50)));
    }

    @Test
    void retoExtra03_seleccionarPrimero() {
        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll("uno", "dos");
        assertEquals("uno", Ej264ChoiceComboPicker.seleccionarPrimero(combo));
    }

    @Test
    void retoExtra04_itemsChoiceBox() {
        assertEquals(3, Ej264ChoiceComboPicker.itemsChoiceBox(List.of("a", "b", "c")));
        assertEquals(0, Ej264ChoiceComboPicker.itemsChoiceBox(List.of())); // caso límite
    }

    @Test
    void retoExtra05_valorODefault() {
        ComboBox<String> vacio = new ComboBox<>();
        assertEquals("ninguno", Ej264ChoiceComboPicker.valorODefault(vacio, "ninguno"));
        ComboBox<String> conValor = new ComboBox<>();
        conValor.setValue("X");
        assertEquals("X", Ej264ChoiceComboPicker.valorODefault(conValor, "ninguno"));
    }

    @Test
    void retoExtra06_incrementarSpinner() {
        Spinner<Integer> s = new Spinner<>(0, 10, 3);
        assertEquals(5, Ej264ChoiceComboPicker.incrementarSpinner(s, 2));
    }

    @Test
    void retoExtra07_fijarFecha() {
        DatePicker dp = new DatePicker();
        LocalDate hoy = LocalDate.of(2024, 6, 22);
        assertEquals(hoy, Ej264ChoiceComboPicker.fijarFecha(dp, hoy));
    }

    @Test
    void retoExtra08_diasEntre() {
        DatePicker a = new DatePicker(LocalDate.of(2024, 1, 1));
        DatePicker b = new DatePicker(LocalDate.of(2024, 1, 8));
        assertEquals(7, Ej264ChoiceComboPicker.diasEntre(a, b));
    }

    @Test
    void retoExtra09_comboEditableTexto() {
        ComboBox<String> combo = new ComboBox<>();
        assertEquals("personalizado", Ej264ChoiceComboPicker.comboEditableTexto(combo, "personalizado"));
        assertTrue(combo.isEditable());
    }

    @Test
    void retoExtra10_snapSliderTicks() {
        Slider s = new Slider(0, 100, 50);
        assertTrue(Ej264ChoiceComboPicker.snapSliderTicks(s, 25));
        assertEquals(25, s.getMajorTickUnit());
    }
}
