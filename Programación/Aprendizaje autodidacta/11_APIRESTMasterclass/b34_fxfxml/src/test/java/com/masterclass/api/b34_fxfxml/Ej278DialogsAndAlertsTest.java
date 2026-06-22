package com.masterclass.api.b34_fxfxml;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import com.masterclass.api.b34_fxfxml.Ej278DialogsAndAlerts.Confirmador;
import com.masterclass.api.b34_fxfxml.Ej278DialogsAndAlerts.Selector;

import static org.junit.jupiter.api.Assertions.*;

class Ej278DialogsAndAlertsTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    private List<String> lista(String... xs) {
        return new ArrayList<>(List.of(xs));
    }

    @Test
    void eliminarSiConfirma() {
        Confirmador si = m -> true;
        Confirmador no = m -> false;
        assertTrue(Ej278DialogsAndAlerts.eliminarSiConfirma(si, lista("a", "b"), "a"));
        assertFalse(Ej278DialogsAndAlerts.eliminarSiConfirma(no, lista("a", "b"), "a")); // caso límite: no confirma
    }

    @Test
    void resultadoBotones() {
        assertEquals("aceptar", Ej278DialogsAndAlerts.resultadoBotones(ButtonType.OK));
        assertEquals("cancelar", Ej278DialogsAndAlerts.resultadoBotones(ButtonType.CANCEL));
        assertEquals("", Ej278DialogsAndAlerts.resultadoBotones(null)); // caso límite
    }

    @Test
    void retoExtra01_interpretar() {
        assertEquals("Sí", Ej278DialogsAndAlerts.interpretar(true));
        assertEquals("No", Ej278DialogsAndAlerts.interpretar(false));
    }

    @Test
    void retoExtra02_esBotonOk() {
        assertTrue(Ej278DialogsAndAlerts.esBotonOk(ButtonType.OK));
        assertFalse(Ej278DialogsAndAlerts.esBotonOk(ButtonType.CANCEL));
    }

    @Test
    void retoExtra03_textoDe() {
        assertEquals("Guardar", Ej278DialogsAndAlerts.textoDe(new ButtonType("Guardar")));
    }

    @Test
    void retoExtra04_opcionPorDefecto() {
        assertEquals("rojo", Ej278DialogsAndAlerts.opcionPorDefecto(List.of("rojo", "verde")));
        assertEquals("", Ej278DialogsAndAlerts.opcionPorDefecto(List.of())); // caso límite
    }

    @Test
    void retoExtra05_elegir() {
        Selector elige = ops -> Optional.of("verde");
        Selector cancela = ops -> Optional.empty();
        assertEquals("verde", Ej278DialogsAndAlerts.elegir(elige, List.of("rojo", "verde")));
        assertEquals("", Ej278DialogsAndAlerts.elegir(cancela, List.of("rojo", "verde")));
    }

    @Test
    void retoExtra06_resultadoDeOptional() {
        assertEquals("aceptar", Ej278DialogsAndAlerts.resultadoDeOptional(Optional.of(ButtonType.OK)));
        assertEquals("cancelar", Ej278DialogsAndAlerts.resultadoDeOptional(Optional.of(ButtonType.CANCEL)));
        assertEquals("cerrado", Ej278DialogsAndAlerts.resultadoDeOptional(Optional.empty()));
    }

    @Test
    void retoExtra07_crearConfirmacion() {
        // Crear un Alert exige el hilo de JavaFX: lo ejecutamos en él (enFx reenvía fallos al test).
        IniciadorFx.enFx(() -> {
            Alert a = Ej278DialogsAndAlerts.crearConfirmacion("Confirmar", "¿Seguro?");
            assertEquals("Confirmar", a.getTitle());
            assertEquals(AlertType.CONFIRMATION, a.getAlertType());
        });
    }

    @Test
    void retoExtra08_numeroDeBotones() {
        IniciadorFx.enFx(() -> {
            Alert a = new Alert(AlertType.CONFIRMATION);
            assertEquals(2, Ej278DialogsAndAlerts.numeroDeBotones(a)); // Aceptar + Cancelar
        });
    }

    @Test
    void retoExtra09_configurarBotones() {
        IniciadorFx.enFx(() -> {
            Alert a = new Alert(AlertType.CONFIRMATION);
            int n = Ej278DialogsAndAlerts.configurarBotones(a,
                    List.of(new ButtonType("Guardar"), new ButtonType("Descartar"), ButtonType.CANCEL));
            assertEquals(3, n);
        });
    }

    @Test
    void retoExtra10_vaciarSiConfirma() {
        Confirmador si = m -> true;
        Confirmador no = m -> false;
        List<String> conSi = lista("a", "b", "c");
        assertEquals(3, Ej278DialogsAndAlerts.vaciarSiConfirma(si, conSi));
        assertTrue(conSi.isEmpty());

        List<String> conNo = lista("a", "b");
        assertEquals(0, Ej278DialogsAndAlerts.vaciarSiConfirma(no, conNo));
        assertEquals(2, conNo.size()); // intacta
    }
}
