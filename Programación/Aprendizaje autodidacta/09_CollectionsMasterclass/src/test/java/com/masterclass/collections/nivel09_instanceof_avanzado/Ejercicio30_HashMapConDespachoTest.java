package com.masterclass.collections.nivel09_instanceof_avanzado;

import com.masterclass.collections.modelos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 30 — HashMap con Despacho por Interfaz")
class Ejercicio30_HashMapConDespachoTest {

    private ArrayList<Object> lista;

    @BeforeEach void setUp() {
        lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 45000, "admin"),
                new Producto("P01", "Teclado", 45.0, "periféricos", "input"),
                new Pedido("D01", "C01", 150.0, 3),
                new Evento("EV01", "ERROR", "Fallo", 1, "sistema"),
                new Pedido("D02", "C02", 200.0, 5)));
    }

    @Test @DisplayName("indexarPorId() crea mapa con claves correctas")
    void indexar_contenido() {
        HashMap<String, Object> idx = Ejercicio30_HashMapConDespacho.indexarPorId(lista);
        assertThat(idx).containsKeys("E01", "P01", "D01", "D02");
        assertThat(idx).doesNotContainKey("EV01"); // Evento no es Identificable
    }

    @Test @DisplayName("indexarPorTipoYCategoria() agrupa Clasificables correctamente")
    void indexarTipoCat() {
        HashMap<String, ArrayList<Object>> r =
                Ejercicio30_HashMapConDespacho.indexarPorTipoYCategoria(lista);
        assertThat(r).containsKey("periféricos:input");
        assertThat(r.get("periféricos:input")).hasSize(1);
    }

    @Test @DisplayName("buscarPorIdYVerificarInterfaz() retorna CUMPLE/NO_CUMPLE correctamente")
    void verificar_contenido() {
        HashMap<String, Object> idx = Ejercicio30_HashMapConDespacho.indexarPorId(lista);
        assertThat(Ejercicio30_HashMapConDespacho.buscarPorIdYVerificarInterfaz(idx, "E01", "Auditable"))
                .isEqualTo("CUMPLE");
        assertThat(Ejercicio30_HashMapConDespacho.buscarPorIdYVerificarInterfaz(idx, "P01", "Procesable"))
                .isEqualTo("NO_CUMPLE");
        assertThat(Ejercicio30_HashMapConDespacho.buscarPorIdYVerificarInterfaz(idx, "X99", "Auditable"))
                .isEqualTo("NO_ENCONTRADO");
        assertThat(Ejercicio30_HashMapConDespacho.buscarPorIdYVerificarInterfaz(idx, "E01", "FakeInterfaz"))
                .isEqualTo("INTERFAZ_DESCONOCIDA");
    }

    @Test @DisplayName("obtenerPrioridadMaxima() retorna la más alta entre Procesables")
    void prioridadMax() {
        assertThat(Ejercicio30_HashMapConDespacho.obtenerPrioridadMaxima(lista)).isEqualTo(5);
    }

    @Test @DisplayName("filtrarPorPrioridadMinima() filtra correctamente")
    void filtrarPrioridad() {
        ArrayList<Object> r = Ejercicio30_HashMapConDespacho.filtrarPorPrioridadMinima(lista, 3);
        assertThat(r).hasSize(2); // D01(3) + D02(5)
    }
}
