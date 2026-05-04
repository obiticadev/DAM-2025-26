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

@DisplayName("Ejercicio 29 — Filtrado Avanzado")
class Ejercicio29_FiltradoAvanzadoTest {

    private ArrayList<Object> lista;
    private final Empleado emp = new Empleado("E01", "Ana", "IT", 45000, "admin");
    private final Producto prod = new Producto("P01", "Teclado", 45.0, "peri", "input");
    private final Pedido ped = new Pedido("D01", "C01", 150.0, 3);
    private final Evento ev = new Evento("EV01", "ERROR", "Fallo", 1, "sistema");

    @BeforeEach
    void setUp() {
        lista = new ArrayList<>(List.of(emp, prod, ped, ev, "texto"));
    }

    @Test @DisplayName("filtrarIdentificablesNoProcesables() excluye Pedido")
    void identifNoProcesables() {
        ArrayList<Object> r = Ejercicio29_FiltradoAvanzado
                .filtrarIdentificablesNoProcesables(lista);
        assertThat(r).hasSize(2).contains(emp, prod);
        assertThat(r).doesNotContain(ped);
    }

    @Test @DisplayName("filtrarSoloUnInterfaz() no incluye objetos con varias interfaces")
    void soloUnaInterfaz() {
        // emp=2(Identif+Audit), prod=2(Identif+Clasif), ped=2(Identif+Proces),
        // ev=2(Proces+Audit), "texto"=0 → ninguno tiene exactamente 1
        ArrayList<Object> r = Ejercicio29_FiltradoAvanzado.filtrarSoloUnInterfaz(lista);
        assertThat(r).isEmpty();
    }

    @Test @DisplayName("agruparPorClaseConcreta() agrupa correctamente")
    void agrupar_contenido() {
        HashMap<String, ArrayList<Object>> r =
                Ejercicio29_FiltradoAvanzado.agruparPorClaseConcreta(lista);
        assertThat(r.get("Empleado")).hasSize(1);
        assertThat(r.get("Producto")).hasSize(1);
        assertThat(r.get("String")).hasSize(1);
    }

    @Test @DisplayName("obtenerIdsDeProcesablesPendientes() retorna IDs no procesados")
    void procesPendientes() {
        ArrayList<String> r = Ejercicio29_FiltradoAvanzado
                .obtenerIdsDeProcesablesPendientes(lista);
        // Pedido es Procesable + Identificable y no está procesado
        assertThat(r).contains("D01");
    }

    @Test @DisplayName("transformarAMapa() crea HashMap con id→nombre")
    void transformar_contenido() {
        HashMap<String, String> r = Ejercicio29_FiltradoAvanzado.transformarAMapa(lista);
        assertThat(r.get("E01")).isEqualTo("Ana");
        assertThat(r.get("P01")).isEqualTo("Teclado");
        assertThat(r.get("D01")).isEqualTo("Pedido-D01");
        assertThat(r).hasSize(3); // solo 3 Identificables
    }
}
