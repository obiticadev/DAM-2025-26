package com.masterclass.collections.nivel04_hashmap_core;

import java.util.HashMap;

/**
 * EJERCICIO 12 — getOrDefault, putIfAbsent, replace y replaceAll
 * ================================================================
 * Teoría de referencia: teoria/03_HashMap_Core.md  (§ 6 — Métodos condicionales)
 *
 * Objetivo: Dominar la API condicional de Map que permite operar sobre
 * entradas de forma segura sin comprobaciones manuales de null.
 *
 * Restricción: Cada TODO debe implementarse con el método de API indicado.
 *              No uses if (mapa.containsKey(k)) como sustituto.
 */
public class Ejercicio12_GetOrDefaultYPutIfAbsent {

    // TODO 1: Implementa `obtenerODefecto`.
    //   - Retorna el valor asociado a `clave` si existe, o `defecto` en caso contrario.
    //   - Usa getOrDefault() — una sola línea.
    //   - NO uses un if-else manual.
    public static String obtenerODefecto(HashMap<String, String> mapa,
                                         String clave, String defecto) {
        return null;
    }

    // TODO 2: Implementa `insertarSiNoExiste`.
    //   - Inserta el par (clave, valor) SOLO si la clave no tiene ya un valor.
    //   - Usa putIfAbsent() — retorna null si la clave era nueva, o el valor anterior si ya existía.
    //   - Retorna true si se insertó (era nueva), false si ya existía.
    public static boolean insertarSiNoExiste(HashMap<String, String> mapa,
                                              String clave, String valor) {
        return false;
    }

    // TODO 3: Implementa `reemplazarSiExiste`.
    //   - Actualiza el valor de `clave` a `nuevoValor` SOLO si la clave ya tiene una entrada.
    //   - Usa replace(clave, nuevoValor) — retorna el valor anterior o null si no existía.
    //   - Retorna true si se actualizó, false si la clave no existía.
    public static boolean reemplazarSiExiste(HashMap<String, String> mapa,
                                              String clave, String nuevoValor) {
        return false;
    }

    // TODO 4: Implementa `doblarTodosLosValores`.
    //   - Multiplica por 2 TODOS los valores enteros del mapa en sitio.
    //   - Usa replaceAll((k, v) -> ...) — una línea con lambda.
    //   - El mapa original se modifica; no retorna nada.
    public static void doblarTodosLosValores(HashMap<String, Integer> mapa) {
        // implementa aquí
    }

    // TODO 5: Implementa `normalizarClaves`.
    //   - Convierte todas las claves a mayúsculas creando un NUEVO HashMap.
    //   - El mapa original no se modifica.
    //   - Itera con entrySet() e inserta en el nuevo mapa con putIfAbsent()
    //     para evitar sobreescribir si ya había una clave en mayúsculas.
    public static HashMap<String, Integer> normalizarClaves(HashMap<String, Integer> mapa) {
        return null;
    }

    // TODO 6: Implementa `reemplazarSoloSiValorCorrecto`.
    //   - Usa el replace(K key, V oldValue, V newValue) de tres argumentos.
    //   - Retorna true si la sustitución tuvo lugar (la clave tenía exactamente `valorEsperado`).
    //   - Este overload de replace() es atómico: comprueba y reemplaza en una sola operación.
    public static boolean reemplazarSoloSiValorCorrecto(HashMap<String, String> mapa,
                                                         String clave,
                                                         String valorEsperado,
                                                         String nuevoValor) {
        return false;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 12 — API Condicional de Map ===\n");

        HashMap<String, String> config = new HashMap<>();
        config.put("host", "localhost");
        config.put("puerto", "8080");

        // -- obtenerODefecto --
        System.out.println("host: " + obtenerODefecto(config, "host", "127.0.0.1"));
        System.out.println("timeout (defecto): " + obtenerODefecto(config, "timeout", "30s"));

        // -- insertarSiNoExiste --
        System.out.println("\nInsertar 'host'='remoto' (ya existe): " + insertarSiNoExiste(config, "host", "remoto"));
        System.out.println("Insertar 'timeout'='30s' (nueva): " + insertarSiNoExiste(config, "timeout", "30s"));
        System.out.println("Config: " + config);

        // -- reemplazarSiExiste --
        System.out.println("\nReemplazar 'puerto' a '9090': " + reemplazarSiExiste(config, "puerto", "9090"));
        System.out.println("Reemplazar 'noExiste': " + reemplazarSiExiste(config, "noExiste", "valor"));
        System.out.println("Config: " + config);

        // -- doblarTodosLosValores --
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put("clicks", 10);
        stats.put("vistas", 25);
        stats.put("ventas", 5);
        System.out.println("\nAntes de doblar: " + stats);
        doblarTodosLosValores(stats);
        System.out.println("Después de doblar: " + stats);

        // -- normalizarClaves --
        HashMap<String, Integer> mixto = new HashMap<>();
        mixto.put("nombre", 1);
        mixto.put("Nombre", 2); // duplicado en mayúsculas
        mixto.put("ciudad", 3);
        System.out.println("\nNormalizado: " + normalizarClaves(mixto));

        // -- reemplazarSoloSiValorCorrecto --
        HashMap<String, String> estado = new HashMap<>();
        estado.put("semaforo", "rojo");
        System.out.println("\nCambiar rojo→verde (correcto): " + reemplazarSoloSiValorCorrecto(estado, "semaforo", "rojo", "verde"));
        System.out.println("Cambiar rojo→verde (ya es verde): " + reemplazarSoloSiValorCorrecto(estado, "semaforo", "rojo", "verde"));
        System.out.println("Semáforo: " + estado.get("semaforo"));
    }
}
