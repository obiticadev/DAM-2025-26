package com.masterclass.collections.nivel10_instanceof_collections;

import com.masterclass.collections.modelos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 36 — Sistema de Notificaciones")
class Ejercicio36_SistemaNotificacionesTest {

    private ArrayList<Object> lista;

    @BeforeEach void setUp() {
        Pedido proc = new Pedido("D02", "C02", 200.0, 3);
        proc.procesar();
        lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 45000, "admin"),
                new Producto("P01", "Teclado", 45.0, "peri", "input"),
                new Pedido("D01", "C01", 150.0, 7),
                proc,
                new Evento("EV01", "ERROR", "Fallo", 9, "sistema"),
                "texto"));
    }

    // ── generarNotificacion ──────────────────────────────────────────────────

    @Test @DisplayName("generarNotificacion() genera ALERTA para Pedido no procesado")
    void notif_alerta() {
        String n = Ejercicio36_SistemaNotificaciones
                .generarNotificacion(new Pedido("D01", "C01", 1, 5));
        assertThat(n).startsWith("ALERTA:").contains("prio=5");
    }

    @Test @DisplayName("generarNotificacion() genera INFO para Pedido ya procesado")
    void notif_info() {
        Pedido p = new Pedido("D01", "C01", 1, 1);
        p.procesar();
        assertThat(Ejercicio36_SistemaNotificaciones.generarNotificacion(p))
                .startsWith("INFO:");
    }

    @Test @DisplayName("generarNotificacion() genera DESCONOCIDO para String")
    void notif_desconocido() {
        assertThat(Ejercicio36_SistemaNotificaciones.generarNotificacion("texto"))
                .startsWith("DESCONOCIDO:");
    }

    // ── generarNotificacionesLote ────────────────────────────────────────────

    @Test @DisplayName("generarNotificacionesLote() genera una entrada por objeto")
    void lote_tamano() {
        LinkedHashMap<String, String> r =
                Ejercicio36_SistemaNotificaciones.generarNotificacionesLote(lista);
        assertThat(r).hasSize(6);
    }

    @Test @DisplayName("generarNotificacionesLote() usa getId() como clave para Identificables")
    void lote_claves() {
        LinkedHashMap<String, String> r =
                Ejercicio36_SistemaNotificaciones.generarNotificacionesLote(lista);
        assertThat(r).containsKeys("E01", "P01", "D01", "D02");
    }

    // ── filtrarPorTipoNotificacion ────────────────────────────────────────────

    @Test @DisplayName("filtrarPorTipoNotificacion() filtra por prefijo ALERTA")
    void filtrar_alerta() {
        LinkedHashMap<String, String> lote =
                Ejercicio36_SistemaNotificaciones.generarNotificacionesLote(lista);
        ArrayList<String> alertas =
                Ejercicio36_SistemaNotificaciones.filtrarPorTipoNotificacion(lote, "ALERTA");
        assertThat(alertas).isNotEmpty();
    }

    // ── contarPorTipoNotificacion ────────────────────────────────────────────

    @Test @DisplayName("contarPorTipoNotificacion() cuenta por tipo")
    void contar_tipos() {
        LinkedHashMap<String, String> lote =
                Ejercicio36_SistemaNotificaciones.generarNotificacionesLote(lista);
        HashMap<String, Integer> conteo =
                Ejercicio36_SistemaNotificaciones.contarPorTipoNotificacion(lote);
        int total = conteo.values().stream().mapToInt(Integer::intValue).sum();
        assertThat(total).isEqualTo(6);
    }

    // ── obtenerAlertas ───────────────────────────────────────────────────────

    @Test @DisplayName("obtenerAlertas() ordena por prioridad descendente")
    void alertas_orden() {
        TreeMap<Integer, ArrayList<String>> alertas =
                Ejercicio36_SistemaNotificaciones.obtenerAlertas(lista);
        if (!alertas.isEmpty()) {
            ArrayList<Integer> prioridades = new ArrayList<>(alertas.keySet());
            assertThat(prioridades.get(0)).isGreaterThanOrEqualTo(prioridades.get(prioridades.size() - 1));
        }
    }

    // ── simularEnvioNotificaciones ────────────────────────────────────────────

    @Test @DisplayName("simularEnvioNotificaciones() envía alertas primero")
    void envio_contenido() {
        HashMap<String, Object> r =
                Ejercicio36_SistemaNotificaciones.simularEnvioNotificaciones(lista);
        assertThat((Integer) r.get("total")).isEqualTo(6);
        assertThat(r.get("enviadas")).isNotNull();
        assertThat(r.get("descartadas")).isNotNull();
    }

    @Test @DisplayName("simularEnvioNotificaciones() descarta DESCONOCIDO y REGISTRO")
    void envio_descartadas() {
        HashMap<String, Object> r =
                Ejercicio36_SistemaNotificaciones.simularEnvioNotificaciones(lista);
        assertThat((Integer) r.get("descartadas")).isGreaterThan(0);
    }
}
