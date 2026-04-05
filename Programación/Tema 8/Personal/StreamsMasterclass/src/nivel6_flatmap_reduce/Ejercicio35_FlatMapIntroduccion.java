package nivel6_flatmap_reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EJERCICIO 35 — FLATMAP: APLANAR LISTAS DE LISTAS (CON GUÍA)
 * 
 * Objetivo: Entender flatMap como la herramienta para aplanar estructuras anidadas.
 */
public class Ejercicio35_FlatMapIntroduccion {

    public static void demostracion() {
        System.out.println("--- FLATMAP: APLANAR COLECCIONES ANIDADAS ---");
        System.out.println("Si tienes una List<List<String>>, un .map() te daría Stream<List<String>>.");
        System.out.println(".flatMap() aplana cada sublista en un solo Stream<String> continuo.");
        System.out.println("Piensa en flatMap como: map + flatten.");
        System.out.println("  listasDeListas.stream()");
        System.out.println("      .flatMap(lista -> lista.stream())");
        System.out.println("      .collect(Collectors.toList()); // -> una sola lista plana\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 35: APLANAR EQUIPOS DE DESARROLLO ---");
        List<List<String>> equipos = new ArrayList<>();
        equipos.add(Arrays.asList("Ana", "Luis", "Carlos"));
        equipos.add(Arrays.asList("Marta", "Lucía"));
        equipos.add(Arrays.asList("Pedro", "Elena", "Marcos", "Sara"));

        // TODO: Usa flatMap para obtener una ÚNICA lista con todos los nombres de todos los equipos.
        List<String> todosLosNombres = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        if (todosLosNombres != null && todosLosNombres.size() == 9
                && todosLosNombres.get(0).equals("Ana")
                && todosLosNombres.get(8).equals("Sara")) {
            System.out.println(">> CORRECTO: flatMap aplana 3 listas en 1 con 9 elementos.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Esperado: 9 nombres en una sola lista, desde Ana hasta Sara.");
        }
    }
}
