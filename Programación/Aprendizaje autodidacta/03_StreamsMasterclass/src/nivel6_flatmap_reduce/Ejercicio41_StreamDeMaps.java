package nivel6_flatmap_reduce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 41 — STREAMS SOBRE MAPAS (CON GUÍA)
 * 
 * Objetivo: Iterar y transformar Map.Entry con streams.
 */
public class Ejercicio41_StreamDeMaps {

    public static void demostracion() {
        System.out.println("--- STREAMS SOBRE MAP.ENTRYSET() ---");
        System.out.println("Un Map no tiene .stream() directo, pero su .entrySet() sí.");
        System.out.println("  mapa.entrySet().stream()");
        System.out.println("      .filter(entry -> entry.getValue() > 50000)");
        System.out.println("      .map(entry -> entry.getKey())");
        System.out.println("      .collect(Collectors.toList());");
        System.out.println("Cada entry tiene .getKey() y .getValue().\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 41: FILTRAR Y TRANSFORMAR UN MAP ---");
        Map<String, Double> salarios = new HashMap<>();
        salarios.put("Ana", 55000.0);
        salarios.put("Carlos", 32000.0);
        salarios.put("Lucía", 70000.0);
        salarios.put("Pedro", 30000.0);
        salarios.put("Elena", 45000.0);

        // TODO 1: Filtra las entradas del mapa donde el salario > 40000
        // y recoge los NOMBRES en una lista ordenada A-Z.
        List<String> bienPagados = null; // <- Escribe aquí

        // TODO 2: Crea un nuevo Map<String, String> donde:
        //   clave = nombre del empleado
        //   valor = "ALTO" si salario > 40000, "BAJO" en caso contrario
        Map<String, String> clasificacion = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = bienPagados != null && bienPagados.size() == 3
                && bienPagados.get(0).equals("Ana") && bienPagados.get(2).equals("Lucía");
        boolean v2 = clasificacion != null && clasificacion.size() == 5
                && clasificacion.get("Ana").equals("ALTO")
                && clasificacion.get("Carlos").equals("BAJO");

        if (v1 && v2) {
            System.out.println(">> CORRECTO: Streams sobre Map.entrySet() dominados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] bienPagados=[Ana,Elena,Lucía]. clasificacion: Ana=ALTO, Carlos=BAJO, etc.");
        }
    }
}
