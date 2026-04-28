package com.masterclass.collections.nivel04_hashmap_core;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * EJERCICIO 11 — Iteración sobre entradas de HashMap
 * ====================================================
 * Teoría de referencia: teoria/03_HashMap_Core.md  (§ 5 — Iteración)
 *
 * Objetivo: Dominar los tres mecanismos de iteración de HashMap (keySet,
 * values, entrySet) y saber cuándo usar cada uno.
 *
 * Restricción: Cada método debe usar OBLIGATORIAMENTE el mecanismo de
 * iteración indicado en su javadoc. No mezcles mecanismos.
 */
public class Ejercicio11_IteracionEntries {

    // TODO 1: Implementa `todasLasClaves` usando keySet().
    //   - Retorna un ArrayList<String> con todas las claves del mapa.
    //   - El orden en el resultado puede ser cualquiera (HashMap no garantiza orden).
    //   - Usa un bucle for-each sobre mapa.keySet().
    public static ArrayList<String> todasLasClaves(HashMap<String, Integer> mapa) {
        return null;
    }

    // TODO 2: Implementa `sumarValores` usando values().
    //   - Suma todos los valores enteros del mapa y retorna el total.
    //   - Usa un bucle for-each sobre mapa.values().
    public static int sumarValores(HashMap<String, Integer> mapa) {
        return -1;
    }

    // TODO 3: Implementa `clavesConValorMayorQue` usando entrySet().
    //   - Retorna un ArrayList<String> con las claves cuyo valor entero sea > umbral.
    //   - Usa for-each sobre mapa.entrySet() y accede con entry.getKey() / entry.getValue().
    public static ArrayList<String> clavesConValorMayorQue(HashMap<String, Integer> mapa,
                                                            int umbral) {
        return null;
    }

    // TODO 4: Implementa `invertirMapa` usando entrySet().
    //   - Retorna un NUEVO HashMap<String, String> donde claves y valores están intercambiados.
    //   - Si hay valores duplicados, el último en procesarse prevalece como clave.
    //   - Itera con entrySet() y construye el mapa invertido con put().
    public static HashMap<String, String> invertirMapa(HashMap<String, String> mapa) {
        return null;
    }

    // TODO 5: Implementa `formatearEntradas` usando forEach (BiConsumer lambda).
    //   - Retorna un String con todas las entradas en formato "clave=valor" separadas por ", ".
    //   - Ejemplo: para {A=1, B=2} puede retornar "A=1, B=2" (orden indeterminado).
    //   - Usa mapa.forEach((k, v) -> ...) y un StringBuilder para acumular el resultado.
    //   - Elimina la coma y espacio finales si los hubiera.
    public static String formatearEntradas(HashMap<String, Integer> mapa) {
        return "";
    }

    // TODO 6: Implementa `clonarMapa` usando entrySet().
    //   - Retorna un NUEVO HashMap<String, Integer> con los mismos pares clave-valor.
    //   - El nuevo mapa debe ser independiente (modificarlo no afecta al original).
    //   - No uses el constructor copy de HashMap; itera con entrySet() y usa put().
    public static HashMap<String, Integer> clonarMapa(HashMap<String, Integer> mapa) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 11 — Iteración sobre HashMap ===\n");

        HashMap<String, Integer> puntuaciones = new HashMap<>();
        puntuaciones.put("Ana",   95);
        puntuaciones.put("Luis",  72);
        puntuaciones.put("Marta", 88);
        puntuaciones.put("Pedro", 60);
        puntuaciones.put("Eva",   91);

        System.out.println("Mapa: " + puntuaciones);
        System.out.println("Todas las claves: " + todasLasClaves(puntuaciones));
        System.out.println("Suma de valores: " + sumarValores(puntuaciones));
        System.out.println("Claves con valor > 80: " + clavesConValorMayorQue(puntuaciones, 80));
        System.out.println("Formateado: " + formatearEntradas(puntuaciones));

        // -- invertirMapa --
        HashMap<String, String> capitales = new HashMap<>();
        capitales.put("España", "Madrid");
        capitales.put("Francia", "París");
        capitales.put("Alemania", "Berlín");
        System.out.println("\nOriginal: " + capitales);
        System.out.println("Invertido: " + invertirMapa(capitales));

        // -- clonarMapa --
        HashMap<String, Integer> clon = clonarMapa(puntuaciones);
        clon.put("EXTRA", 999);
        System.out.println("\nOriginal sin 'EXTRA': " + puntuaciones.containsKey("EXTRA"));
        System.out.println("Clon con 'EXTRA': " + clon.containsKey("EXTRA"));
    }
}
