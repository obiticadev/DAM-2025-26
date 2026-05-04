package com.masterclass.collections.nivel08_instanceof_basico;

import com.masterclass.collections.modelos.Empleado;
import com.masterclass.collections.modelos.Evento;
import com.masterclass.collections.modelos.Pedido;
import com.masterclass.collections.modelos.Producto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 26 — instanceof Básico")
class Ejercicio26_InstanceofBasicoTest {

    private final Empleado emp = new Empleado("E01", "Ana", "IT", 45000, "admin");
    private final Producto prod = new Producto("P01", "Teclado", 45.0, "peri", "input");
    private final Pedido ped = new Pedido("D01", "C01", 150.0, 3);
    private final Evento ev = new Evento("EV01", "ERROR", "Fallo", 1, "sistema");

    @Test @DisplayName("esIdentificable() true para Empleado, Producto, Pedido")
    void esIdentificable_true() {
        assertThat(Ejercicio26_InstanceofBasico.esIdentificable(emp)).isTrue();
        assertThat(Ejercicio26_InstanceofBasico.esIdentificable(prod)).isTrue();
        assertThat(Ejercicio26_InstanceofBasico.esIdentificable(ped)).isTrue();
    }

    @Test @DisplayName("esIdentificable() false para Evento y String")
    void esIdentificable_false() {
        assertThat(Ejercicio26_InstanceofBasico.esIdentificable(ev)).isFalse();
        assertThat(Ejercicio26_InstanceofBasico.esIdentificable("hola")).isFalse();
    }

    @Test @DisplayName("esIdentificable() false para null")
    void esIdentificable_null() {
        assertThat(Ejercicio26_InstanceofBasico.esIdentificable(null)).isFalse();
    }

    @Test @DisplayName("esAuditable() true para Empleado y Evento")
    void esAuditable_true() {
        assertThat(Ejercicio26_InstanceofBasico.esAuditable(emp)).isTrue();
        assertThat(Ejercicio26_InstanceofBasico.esAuditable(ev)).isTrue();
    }

    @Test @DisplayName("esAuditable() false para Producto y Pedido")
    void esAuditable_false() {
        assertThat(Ejercicio26_InstanceofBasico.esAuditable(prod)).isFalse();
        assertThat(Ejercicio26_InstanceofBasico.esAuditable(ped)).isFalse();
    }

    @Test @DisplayName("esProcesable() true para Pedido y Evento")
    void esProcesable_true() {
        assertThat(Ejercicio26_InstanceofBasico.esProcesable(ped)).isTrue();
        assertThat(Ejercicio26_InstanceofBasico.esProcesable(ev)).isTrue();
    }

    @Test @DisplayName("esProcesable() false para Empleado y Producto")
    void esProcesable_false() {
        assertThat(Ejercicio26_InstanceofBasico.esProcesable(emp)).isFalse();
        assertThat(Ejercicio26_InstanceofBasico.esProcesable(prod)).isFalse();
    }

    @Test @DisplayName("esClasificable() true para Producto")
    void esClasificable_true() {
        assertThat(Ejercicio26_InstanceofBasico.esClasificable(prod)).isTrue();
    }

    @Test @DisplayName("esClasificable() false para Empleado, Pedido, Evento")
    void esClasificable_false() {
        assertThat(Ejercicio26_InstanceofBasico.esClasificable(emp)).isFalse();
        assertThat(Ejercicio26_InstanceofBasico.esClasificable(ped)).isFalse();
        assertThat(Ejercicio26_InstanceofBasico.esClasificable(ev)).isFalse();
    }

    @Test @DisplayName("obtenerIdSiIdentificable() retorna id para Identificables")
    void obtenerId_identificable() {
        assertThat(Ejercicio26_InstanceofBasico.obtenerIdSiIdentificable(emp)).isEqualTo("E01");
        assertThat(Ejercicio26_InstanceofBasico.obtenerIdSiIdentificable(prod)).isEqualTo("P01");
    }

    @Test @DisplayName("obtenerIdSiIdentificable() retorna NO_IDENTIFICABLE para otros")
    void obtenerId_noIdentificable() {
        assertThat(Ejercicio26_InstanceofBasico.obtenerIdSiIdentificable("hola"))
                .isEqualTo("NO_IDENTIFICABLE");
        assertThat(Ejercicio26_InstanceofBasico.obtenerIdSiIdentificable(ev))
                .isEqualTo("NO_IDENTIFICABLE");
    }

    @Test @DisplayName("contarIdentificablesEnLista() cuenta correctamente")
    void contarIdentificables() {
        ArrayList<Object> lista = new ArrayList<>(List.of(emp, prod, ped, ev, "texto"));
        assertThat(Ejercicio26_InstanceofBasico.contarIdentificablesEnLista(lista)).isEqualTo(3);
    }

    @Test @DisplayName("obtenerNombresDeIdentificables() extrae los nombres correctos")
    void obtenerNombres() {
        ArrayList<Object> lista = new ArrayList<>(List.of(emp, prod, ev, "texto", ped));
        ArrayList<String> nombres =
                Ejercicio26_InstanceofBasico.obtenerNombresDeIdentificables(lista);
        assertThat(nombres).containsExactly("Ana", "Teclado", "Pedido-D01");
    }
}
