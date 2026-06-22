package com.masterclass.api.b34_fxfxml;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

import com.masterclass.api.b34_fxfxml.Ej276MvvmViewModel.ModeloVista;

import static org.junit.jupiter.api.Assertions.*;

class Ej276MvvmViewModelTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    private ModeloVista vmCon(String nombre, String email) {
        ModeloVista vm = new ModeloVista();
        vm.nombreProperty().set(nombre);
        vm.emailProperty().set(email);
        return vm;
    }

    @Test
    void reglaValidez() {
        ModeloVista vm = new ModeloVista();
        BooleanBinding valido = Ej276MvvmViewModel.reglaValidez(vm);
        assertFalse(valido.get()); // vacío
        vm.nombreProperty().set("Ana");
        vm.emailProperty().set("ana@correo.com");
        assertTrue(valido.get()); // reactivo: sin volver a llamar al método
    }

    @Test
    void aceptar() {
        ModeloVista ok = vmCon("Ana", "ana@correo.com");
        assertEquals("Alta correcta", Ej276MvvmViewModel.aceptar(ok));
        assertEquals(1, ok.altasProperty().get());

        ModeloVista mal = vmCon("", "x"); // caso límite: inválido
        assertEquals("Revisa el formulario", Ej276MvvmViewModel.aceptar(mal));
        assertEquals(0, mal.altasProperty().get());
    }

    @Test
    void retoExtra01_saludo() {
        ModeloVista vm = new ModeloVista();
        StringBinding s = Ej276MvvmViewModel.saludo(vm);
        vm.nombreProperty().set("Ana");
        assertEquals("Hola, Ana", s.get());
    }

    @Test
    void retoExtra02_estaValido() {
        assertFalse(Ej276MvvmViewModel.estaValido(new ModeloVista()));
        assertTrue(Ej276MvvmViewModel.estaValido(vmCon("Ana", "a@b.com")));
    }

    @Test
    void retoExtra03_resetear() {
        ModeloVista vm = vmCon("Ana", "a@b.com");
        assertTrue(Ej276MvvmViewModel.resetear(vm));
        assertEquals("", vm.emailProperty().get());
    }

    @Test
    void retoExtra04_botonHabilitado() {
        ModeloVista vm = vmCon("Ana", "a@b.com");
        BooleanBinding habil = Ej276MvvmViewModel.botonHabilitado(vm);
        assertTrue(habil.get());
        vm.cargandoProperty().set(true);
        assertFalse(habil.get()); // reactivo: deshabilitado mientras carga
    }

    @Test
    void retoExtra05_mensajeError() {
        ModeloVista vm = new ModeloVista();
        StringBinding msg = Ej276MvvmViewModel.mensajeError(vm);
        assertEquals("Revisa el formulario", msg.get());
        vm.nombreProperty().set("Ana");
        vm.emailProperty().set("a@b.com");
        assertEquals("", msg.get());
    }

    @Test
    void retoExtra06_hayCambios() {
        StringProperty actual = new SimpleStringProperty("Ana");
        assertFalse(Ej276MvvmViewModel.hayCambios("Ana", actual));
        actual.set("Berta");
        assertTrue(Ej276MvvmViewModel.hayCambios("Ana", actual));
    }

    @Test
    void retoExtra07_deshacer() {
        StringProperty campo = new SimpleStringProperty("nuevo");
        assertEquals("viejo", Ej276MvvmViewModel.deshacer(campo, "viejo"));
        assertEquals("viejo", campo.get());
    }

    @Test
    void retoExtra08_procesarTanda() {
        ModeloVista vm = new ModeloVista();
        String[][] intentos = {
                {"Ana", "a@b.com"},   // válido
                {"", "x"},            // inválido
                {"Berto", "b@b.com"}, // válido
        };
        assertEquals(2, Ej276MvvmViewModel.procesarTanda(vm, intentos));
    }

    @Test
    void retoExtra09_enlazarCampo() {
        ModeloVista vm = new ModeloVista();
        assertEquals("Carla", Ej276MvvmViewModel.enlazarCampo(new TextField(), vm));
        assertEquals("Carla", vm.nombreProperty().get());
    }

    @Test
    void retoExtra10_flujoMvvm() {
        assertEquals(1, Ej276MvvmViewModel.flujoMvvm(new ModeloVista(), "Ana", "a@b.com"));
        assertEquals(0, Ej276MvvmViewModel.flujoMvvm(new ModeloVista(), "", "x"));
    }
}
