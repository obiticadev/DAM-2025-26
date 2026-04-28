package com.masterclass.collections.nivel10_instanceof_collections;

import com.masterclass.collections.modelos.*;
import com.masterclass.collections.modelos.interfaces.*;
import java.util.*;

/**
 * EJERCICIO 36 — instanceof + Collections: Sistema de Notificaciones
 * =====================================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 4, 5)
 *
 * Objetivo: Implementar un sistema de notificaciones que genera mensajes
 * personalizados para cada objeto según las interfaces que implementa,
 * los almacena en colecciones apropiadas y permite consultas avanzadas.
 *
 * Restricción: Usa pattern matching en todos los checks de tipo.
 */
public class Ejercicio36_SistemaNotificaciones {

    // TODO 1: Implementa `generarNotificacion`.
    //   - Recibe un Object.
    //   - Genera un mensaje de notificación basado en el tipo de objeto:
    //     * Si es Procesable y NO procesado → "ALERTA: {clase} pendiente de procesar (prio={prioridad})"
    //     * Si es Procesable y YA procesado → "INFO: {clase} ya procesado"
    //     * Si es Auditable y no Procesable → "LOG: {clase} registrado por {creadoPor}"
    //     * Si es Identificable y no Auditable ni Procesable → "REGISTRO: {id} - {nombre}"
    //     * Cualquier otro → "DESCONOCIDO: {clase}"
    //   - Usa getClass().getSimpleName() para {clase}.
    //   - Evalúa en el orden indicado (el primero que cumpla gana).
    public static String generarNotificacion(Object obj) {
        return null;
    }

    // TODO 2: Implementa `generarNotificacionesLote`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un LinkedHashMap<String, String> donde:
    //     clave = getId() si es Identificable, sino "OBJ-{indice}"
    //     valor = resultado de generarNotificacion()
    //   - El LinkedHashMap preserva el orden de inserción.
    public static LinkedHashMap<String, String> generarNotificacionesLote(
            ArrayList<Object> lista) {
        return null;
    }

    // TODO 3: Implementa `filtrarPorTipoNotificacion`.
    //   - Recibe el resultado del TODO 2 y un prefijo ("ALERTA", "INFO", "LOG", etc.).
    //   - Retorna un ArrayList<String> con las claves cuyos valores comienzan
    //     con ese prefijo.
    public static ArrayList<String> filtrarPorTipoNotificacion(
            LinkedHashMap<String, String> notificaciones, String prefijo) {
        return null;
    }

    // TODO 4: Implementa `contarPorTipoNotificacion`.
    //   - Recibe el resultado del TODO 2.
    //   - Retorna un HashMap<String, Integer> donde la clave es el tipo
    //     de notificación (la parte antes de ":") y el valor es cuántas hay.
    //   - Tipos posibles: "ALERTA", "INFO", "LOG", "REGISTRO", "DESCONOCIDO"
    //   - Usa split(":")[0] para extraer el tipo.
    public static HashMap<String, Integer> contarPorTipoNotificacion(
            LinkedHashMap<String, String> notificaciones) {
        return null;
    }

    // TODO 5: Implementa `obtenerAlertas`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un TreeMap<Integer, ArrayList<String>> donde:
    //     clave = prioridad (orden DESCENDENTE)
    //     valor = lista de mensajes de notificación para Procesables NO procesados
    //   - Los ya procesados y no-Procesables se ignoran.
    public static TreeMap<Integer, ArrayList<String>> obtenerAlertas(
            ArrayList<Object> lista) {
        return null;
    }

    // TODO 6 (desafío): Implementa `simularEnvioNotificaciones`.
    //   - Recibe un ArrayList<Object>.
    //   - Simula el envío de notificaciones:
    //     1. Genera las notificaciones con generarNotificacionesLote()
    //     2. Las ALERTA se "envían" primero (las agrega a "enviadas")
    //     3. Las INFO y LOG se "envían" después
    //     4. Las DESCONOCIDO y REGISTRO se descartan
    //   - Retorna un HashMap<String, Object> con:
    //     "enviadas"     → ArrayList<String> (los mensajes en orden de envío)
    //     "descartadas"  → Integer (cuántas se descartaron)
    //     "total"        → Integer (total generadas)
    public static HashMap<String, Object> simularEnvioNotificaciones(
            ArrayList<Object> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 36 — Sistema de Notificaciones ===\n");

        ArrayList<Object> lista = new ArrayList<>();
        lista.add(new Empleado("E01", "Ana", "IT", 45000, "admin"));
        lista.add(new Producto("P01", "Teclado", 45.0, "peri", "input"));
        lista.add(new Pedido("D01", "C01", 150.0, 7));
        Pedido procesado = new Pedido("D02", "C02", 200.0, 3);
        procesado.procesar();
        lista.add(procesado);
        lista.add(new Evento("EV01", "ERROR", "Fallo", 9, "sistema"));
        lista.add("texto");

        System.out.println("--- Notificaciones individuales ---");
        lista.forEach(o -> System.out.println(generarNotificacion(o)));

        LinkedHashMap<String, String> lote = generarNotificacionesLote(lista);
        System.out.println("\n--- Lote ---");
        lote.forEach((k, v) -> System.out.println("  " + k + " → " + v));

        System.out.println("\nALERTAS: " + filtrarPorTipoNotificacion(lote, "ALERTA"));
        System.out.println("Conteo: " + contarPorTipoNotificacion(lote));
        System.out.println("Alertas por prioridad: " + obtenerAlertas(lista));

        System.out.println("\n--- Simulación de envío ---");
        HashMap<String, Object> envio = simularEnvioNotificaciones(lista);
        System.out.println("Enviadas: " + envio.get("enviadas"));
        System.out.println("Descartadas: " + envio.get("descartadas"));
    }
}
