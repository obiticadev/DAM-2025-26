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

@DisplayName("Ejercicio 27 — Pattern Matching Multi-Interfaz")
class Ejercicio27_PatternMatchingMultiInterfazTest {

    private final Empleado emp = new Empleado("E01", "Ana", "IT", 45000, "admin");
    private final Producto prod = new Producto("P01", "Teclado", 45.0, "peri", "input");
    private final Pedido ped = new Pedido("D01", "C01", 150.0, 3);
    private final Evento ev = new Evento("EV01", "ERROR", "Fallo", 1, "sistema");

    @Test @DisplayName("describirObjeto() incluye info de Identificable + Auditable para Empleado")
    void describir_empleado() {
        String desc = Ejercicio27_PatternMatchingMultiInterfaz.describirObjeto(emp);
        assertThat(desc).contains("ID:E01").contains("NOMBRE:Ana").contains("CREADO_POR:admin");
    }

    @Test @DisplayName("describirObjeto() incluye info de Procesable para Pedido")
    void describir_pedido() {
        String desc = Ejercicio27_PatternMatchingMultiInterfaz.describirObjeto(ped);
        assertThat(desc).contains("ID:D01").contains("PRIORIDAD:3");
    }

    @Test @DisplayName("describirObjeto() retorna OBJETO_DESCONOCIDO para String")
    void describir_desconocido() {
        assertThat(Ejercicio27_PatternMatchingMultiInterfaz.describirObjeto("texto"))
                .isEqualTo("OBJETO_DESCONOCIDO");
    }

    @Test @DisplayName("procesarSiProcesable() procesa y retorna true para Pedido")
    void procesar_pedido() {
        Pedido p = new Pedido("D99", "C99", 10.0, 1);
        assertThat(Ejercicio27_PatternMatchingMultiInterfaz.procesarSiProcesable(p)).isTrue();
        assertThat(p.isProcesado()).isTrue();
    }

    @Test @DisplayName("procesarSiProcesable() retorna false para no Procesable")
    void procesar_noProcesable() {
        assertThat(Ejercicio27_PatternMatchingMultiInterfaz.procesarSiProcesable(emp)).isFalse();
    }

    @Test @DisplayName("extraerCreadoPorSiAuditable() retorna el creador para Empleado")
    void extraerCreador_auditable() {
        assertThat(Ejercicio27_PatternMatchingMultiInterfaz.extraerCreadoPorSiAuditable(emp))
                .isEqualTo("admin");
    }

    @Test @DisplayName("extraerCreadoPorSiAuditable() retorna N/A para no Auditable")
    void extraerCreador_noAuditable() {
        assertThat(Ejercicio27_PatternMatchingMultiInterfaz.extraerCreadoPorSiAuditable(prod))
                .isEqualTo("N/A");
    }

    @Test @DisplayName("clasificarPorInterfaces() genera descripciones correctas")
    void clasificar_contenido() {
        ArrayList<Object> lista = new ArrayList<>(List.of(emp, prod, "texto"));
        ArrayList<String> resultado =
                Ejercicio27_PatternMatchingMultiInterfaz.clasificarPorInterfaces(lista);
        assertThat(resultado.get(0)).contains("Empleado").contains("Identificable").contains("Auditable");
        assertThat(resultado.get(1)).contains("Producto").contains("Identificable").contains("Clasificable");
        assertThat(resultado.get(2)).contains("String").contains("ninguna");
    }

    @Test @DisplayName("filtrarPorDobleInterfaz() retorna solo los que son Identif + Audit")
    void filtrarDoble_contenido() {
        ArrayList<Object> lista = new ArrayList<>(List.of(emp, prod, ped, ev, "texto"));
        ArrayList<Object> resultado =
                Ejercicio27_PatternMatchingMultiInterfaz.filtrarPorDobleInterfaz(lista);
        // Solo Empleado es Identificable + Auditable
        assertThat(resultado).hasSize(1).contains(emp);
    }

    @Test @DisplayName("contarPorTipoDeInterfaz() cuenta correctamente las 4 categorías")
    void contarPorTipo_contenido() {
        ArrayList<Object> lista = new ArrayList<>(List.of(emp, prod, ped, ev, "texto"));
        int[] conteo = Ejercicio27_PatternMatchingMultiInterfaz.contarPorTipoDeInterfaz(lista);
        assertThat(conteo[0]).isEqualTo(3); // Identificable: emp, prod, ped
        assertThat(conteo[1]).isEqualTo(2); // Auditable: emp, ev
        assertThat(conteo[2]).isEqualTo(1); // Clasificable: prod
        assertThat(conteo[3]).isEqualTo(2); // Procesable: ped, ev
    }
}
