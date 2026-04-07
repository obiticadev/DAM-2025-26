package nivel14_master_streams;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 49 - TABLAS DOBLES DE JAVA VANILLA (Map<K, Map<K,V>>)
 * 
 * Objetivo: Trabajar con diccionarios de dobles llaves, al estilo tabla de Excel
 * (Fila y Columna), e inicializar los "Sub-Mapas" dinámicamente con computeIfAbsent.
 */
public class Ejercicio49_TablasDobles {

    public static void demostracion() {
        System.out.println("--- DEMO: LA TABLA MATRICIAL ---");
        // Tenemos "Provincia" -> ("Ciudad" -> Poblacion)
        Map<String, Map<String, Integer>> censo = new HashMap<>();

        // Si intentas hacer un .put("Andalucia").put("Cádiz") diréctamente, el primer put casca nullPointerException.
        // Así se rellena una jerarquía condicional limpiamente:
        censo.computeIfAbsent("Andalucía", k -> new HashMap<>()).put("Cádiz", 115000);
        censo.computeIfAbsent("Andalucía", k -> new HashMap<>()).put("Sevilla", 680000);
        
        System.out.println("Matriz generada: " + censo);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 49: EL CALENDARIO ESCOLAR ---");
        
        // Estructura Matricial: Año -> (Mes -> Evento)
        Map<Integer, Map<String, String>> agenda = new HashMap<>();

        // TODO 1: Queremos registrar en el año "2024", bajo el mes "Diciembre", el evento "Navidad".
        // Usa computeIfAbsent para el año 2024 encadenando un .put(...) tal como en la demo.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Ocurre en el código que un script automático intenta registrar otro evento el mismo año: 
        // Año "2024", bajo el mes "Noviembre", el evento "Black Friday".
        // Usa computeIfAbsent() de nuevo. Si lo haces bien, NO se destruirá Diciembre, pues computeIfAbsent 
        // respeta el submapa si ya existía.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean okYear = agenda.containsKey(2024);
        boolean okDic = false;
        boolean okNov = false;

        if (okYear) {
            okDic = "Navidad".equals(agenda.get(2024).get("Diciembre"));
            okNov = "Black Friday".equals(agenda.get(2024).get("Noviembre"));
        }

        if (!okYear) System.err.println("-> [TODO] Falló: La agenda no contiene la llave raíz 2024.");
        else if (!okDic) System.err.println("-> [TODO 1] Falló: Se perdió la llave Diciembre o te machacaste el submapa.");
        else if (!okNov) System.err.println("-> [TODO 2] Falló: No insertaste el evento de Noviembre correctamente.");

        if (okYear && okDic && okNov) {
            System.out.println(">> PERFECTO: Acabas de comprender el fundamento Vanilla del Table de Google Guava (Fase 3). \033[0;32m [OK]\033[0m");
        }
    }
}
