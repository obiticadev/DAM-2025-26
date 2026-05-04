package com.masterclass.collections.nivel10_instanceof_collections;

import com.masterclass.collections.modelos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 34 — Registro Polimórfico")
class Ejercicio34_RegistroPolimorficoTest {

    private HashMap<String, Object> registro;

    @BeforeEach void setUp() {
        registro = new HashMap<>();
        Ejercicio34_RegistroPolimorfico.registrar(registro,
                new Empleado("E01", "Ana", "IT", 45000, "admin"));
        Ejercicio34_RegistroPolimorfico.registrar(registro,
                new Producto("P01", "Teclado", 45.0, "peri", "input"));
        Ejercicio34_RegistroPolimorfico.registrar(registro,
                new Pedido("D01", "C01", 150.0, 3));
        Ejercicio34_RegistroPolimorfico.registrar(registro,
                new Evento("EV01", "ERROR", "Fallo", 1, "sistema"));
        Ejercicio34_RegistroPolimorfico.registrar(registro, "texto");
    }

    @Test @DisplayName("registrar() usa getId() para Identificables")
    void registrar_identificable() {
        assertThat(registro).containsKeys("E01", "P01", "D01");
    }

    @Test @DisplayName("registrar() usa ANON-x para no-Identificables")
    void registrar_anonimo() {
        // Evento no es Identificable → ANON-0, "texto" → ANON-1
        assertThat(registro).containsKeys("ANON-0", "ANON-1");
    }

    @Test @DisplayName("buscarPorId() retorna el objeto correcto")
    void buscar_existe() {
        assertThat(Ejercicio34_RegistroPolimorfico.buscarPorId(registro, "E01"))
                .isInstanceOf(Empleado.class);
    }

    @Test @DisplayName("buscarPorId() retorna null si no existe")
    void buscar_noExiste() {
        assertThat(Ejercicio34_RegistroPolimorfico.buscarPorId(registro, "X99"))
                .isNull();
    }

    @Test @DisplayName("listarPorInterfaz() retorna claves de Auditables")
    void listarAuditables() {
        ArrayList<String> r = Ejercicio34_RegistroPolimorfico
                .listarPorInterfaz(registro, "Auditable");
        assertThat(r).containsExactlyInAnyOrder("E01", "ANON-0"); // Empleado + Evento
    }

    @Test @DisplayName("agruparPorNumeroDeInterfaces() agrupa correctamente")
    void agrupar() {
        HashMap<Integer, ArrayList<String>> r =
                Ejercicio34_RegistroPolimorfico.agruparPorNumeroDeInterfaces(registro);
        assertThat(r.get(2)).containsExactlyInAnyOrder("E01", "P01", "D01", "ANON-0");
        assertThat(r.get(0)).containsExactlyInAnyOrder("ANON-1");
    }

    @Test @DisplayName("crearIndiceInvertido() tiene las 4 claves de interfaz")
    void indiceInvertido() {
        HashMap<String, HashSet<String>> r =
                Ejercicio34_RegistroPolimorfico.crearIndiceInvertido(registro);
        assertThat(r).containsKeys("Identificable", "Auditable", "Clasificable", "Procesable");
        assertThat(r.get("Identificable")).containsExactlyInAnyOrder("E01", "P01", "D01");
    }

    @Test @DisplayName("busquedaAvanzada() retorna solo los que cumplen TODAS las interfaces")
    void busqueda() {
        ArrayList<String> r = Ejercicio34_RegistroPolimorfico
                .busquedaAvanzada(registro, new String[]{"Identificable", "Auditable"});
        assertThat(r).containsExactly("E01"); // Solo Empleado
    }

    @Test @DisplayName("busquedaAvanzada() con array vacío retorna todas las claves")
    void busquedaVacia() {
        ArrayList<String> r = Ejercicio34_RegistroPolimorfico
                .busquedaAvanzada(registro, new String[]{});
        assertThat(r).hasSize(5);
    }
}
