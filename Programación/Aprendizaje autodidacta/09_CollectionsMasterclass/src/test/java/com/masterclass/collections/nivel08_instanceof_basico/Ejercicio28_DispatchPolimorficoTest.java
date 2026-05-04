package com.masterclass.collections.nivel08_instanceof_basico;

import com.masterclass.collections.modelos.Empleado;
import com.masterclass.collections.modelos.Evento;
import com.masterclass.collections.modelos.Pedido;
import com.masterclass.collections.modelos.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 28 — Dispatch Polimórfico")
class Ejercicio28_DispatchPolimorficoTest {

    private ArrayList<Object> lista;

    @BeforeEach
    void setUp() {
        lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 45000, "admin"),
                new Producto("P01", "Teclado", 45.0, "periféricos", "input"),
                new Pedido("D01", "C01", 150.0, 3),
                new Evento("EV01", "ERROR", "Fallo", 1, "sistema"),
                "texto"
        ));
    }

    @Test @DisplayName("separarPorInterfaz() crea todas las claves")
    void separar_claves() {
        HashMap<String, ArrayList<Object>> r =
                Ejercicio28_DispatchPolimorfico.separarPorInterfaz(lista);
        assertThat(r).containsKeys("identificables", "auditables", "procesables",
                "clasificables", "desconocidos");
    }

    @Test @DisplayName("separarPorInterfaz() cuenta correctamente")
    void separar_conteo() {
        HashMap<String, ArrayList<Object>> r =
                Ejercicio28_DispatchPolimorfico.separarPorInterfaz(lista);
        assertThat(r.get("identificables")).hasSize(3); // emp, prod, ped
        assertThat(r.get("auditables")).hasSize(2);     // emp, ev
        assertThat(r.get("procesables")).hasSize(2);    // ped, ev
        assertThat(r.get("clasificables")).hasSize(1);  // prod
        assertThat(r.get("desconocidos")).hasSize(1);   // "texto"
    }

    @Test @DisplayName("procesarTodos() procesa solo los Procesables")
    void procesar_conteo() {
        assertThat(Ejercicio28_DispatchPolimorfico.procesarTodos(lista)).isEqualTo(2);
    }

    @Test @DisplayName("obtenerResumenAuditoria() genera resúmenes para Auditables")
    void auditoria_contenido() {
        ArrayList<String> resumenes =
                Ejercicio28_DispatchPolimorfico.obtenerResumenAuditoria(lista);
        assertThat(resumenes).hasSize(2);
        assertThat(resumenes.get(0)).contains("AUDIT:").contains("creado_por=admin");
    }

    @Test @DisplayName("obtenerCategoriasUnicas() extrae categorías de Clasificables")
    void categorias_contenido() {
        ArrayList<String> cats =
                Ejercicio28_DispatchPolimorfico.obtenerCategoriasUnicas(lista);
        assertThat(cats).containsExactly("periféricos");
    }

    @Test @DisplayName("sumarSalariosTotales() suma solo salarios de Empleados")
    void sumar_salarios() {
        assertThat(Ejercicio28_DispatchPolimorfico.sumarSalariosTotales(lista))
                .isEqualTo(45000.0);
    }

    @Test @DisplayName("generarInformeCompleto() contiene todas las métricas")
    void informe_contenido() {
        HashMap<String, Object> informe =
                Ejercicio28_DispatchPolimorfico.generarInformeCompleto(lista);
        assertThat((Integer) informe.get("total_objetos")).isEqualTo(5);
        assertThat((Integer) informe.get("identificables")).isEqualTo(3);
        assertThat((Integer) informe.get("auditables")).isEqualTo(2);
        assertThat((Integer) informe.get("procesables")).isEqualTo(2);
        assertThat((Integer) informe.get("clasificables")).isEqualTo(1);
        assertThat((Double) informe.get("suma_salarios")).isEqualTo(45000.0);
    }
}
