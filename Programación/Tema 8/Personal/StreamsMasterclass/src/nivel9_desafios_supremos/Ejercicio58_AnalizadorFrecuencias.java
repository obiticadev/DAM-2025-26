package nivel9_desafios_supremos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 58 — ANALIZADOR DE FRECUENCIA DE PALABRAS
 * 
 * SIN PISTAS. Procesa texto como un profesional usando solo streams.
 */
public class Ejercicio58_AnalizadorFrecuencias {

    public static void demostracion() {
        System.out.println("=== DESAFÍO SUPREMO 58: ANALIZADOR DE TEXTO ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 58: FRECUENCIA DE PALABRAS EN TEXTO ---");
        String texto = "el gato negro y el perro blanco jugaban en el parque " +
                        "el perro corría y el gato observaba desde el árbol " +
                        "un gato siempre observa y un perro siempre corre";

        // TODO 1: Divide el texto en palabras, cuenta la frecuencia de cada una,
        // y ordénalas de mayor a menor frecuencia.
        // Resultado: Map<String, Long> (usa LinkedHashMap para mantener el orden)
        Map<String, Long> frecuencias = null; // <- Escribe aquí

        // TODO 2: Obtén las 5 palabras más frecuentes como lista de strings con formato "palabra(N)"
        // Ejemplo: ["el(5)", "gato(3)", ...]
        List<String> top5 = null; // <- Escribe aquí

        // TODO 3: Cuenta cuántas palabras ÚNICAS hay en el texto (sin repeticiones).
        long palabrasUnicas = 0; // <- Escribe aquí

        // TODO 4: Obtén la palabra más larga del texto.
        String masLarga = ""; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = frecuencias != null && frecuencias.get("el") == 5L && frecuencias.get("gato") == 3L;
        boolean v2 = top5 != null && top5.size() == 5 && top5.get(0).equals("el(5)");
        boolean v3 = palabrasUnicas == 16;
        boolean v4 = masLarga.equals("observaba");

        if (v1 && v2 && v3 && v4) {
            System.out.println(">> CORRECTO: Análisis de frecuencia de texto dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] el=5, gato=3, top5 empieza con el(5), 16 únicas, masLarga=observaba.");
        }
    }
}
