package com.masterclass.collections.nivel06_linkedhashmap_treemap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * EJERCICIO 18 — LinkedHashMap: Caché LRU (Least Recently Used)
 * ===============================================================
 * Teoría de referencia: teoria/04_HashMap_Avanzado_LinkedHashMap_TreeMap.md  (§ 2.3 / 2.4)
 *
 * Objetivo: Construir una caché LRU funcional usando LinkedHashMap con
 * accessOrder = true y la sobrecarga de removeEldestEntry.
 *
 * Restricción: Toda la lógica de evicción debe delegarse en LinkedHashMap.
 *              No implementes tu propia estructura de datos.
 */
public class Ejercicio18_LinkedHashMapLRUCache {

    // TODO 1: Implementa `crearCacheLRU`.
    //   - Retorna un LinkedHashMap<String, String> configurado como caché LRU.
    //   - La capacidad máxima es el parámetro `capacidad`.
    //   - Usa el constructor de 3 parámetros: (initialCapacity, loadFactor, accessOrder=true).
    //   - Sobrescribe removeEldestEntry para que retorne true cuando size() > capacidad.
    //   - Esto se hace creando una clase anónima que extiende LinkedHashMap.
    public static LinkedHashMap<String, String> crearCacheLRU(int capacidad) {
        return null;
    }

    // TODO 2: Implementa `agregarACache`.
    //   - Inserta la pareja clave-valor en la caché LRU.
    //   - Si la caché supera su capacidad, la entrada menos recientemente usada
    //     se elimina automáticamente (gracias a removeEldestEntry).
    //   - El método no retorna nada.
    public static void agregarACache(LinkedHashMap<String, String> cache,
                                      String clave, String valor) {
        // implementa aquí
    }

    // TODO 3: Implementa `consultarCache`.
    //   - Retorna el valor asociado a `clave` si existe.
    //   - Si la clave no existe, retorna null.
    //   - El simple hecho de llamar a get() en un LinkedHashMap con accessOrder=true
    //     mueve la entrada al TAIL (la marca como recientemente usada).
    public static String consultarCache(LinkedHashMap<String, String> cache, String clave) {
        return null;
    }

    // TODO 4: Implementa `obtenerOrdenActual`.
    //   - Retorna un String[] con las claves de la caché en su orden actual.
    //   - En accessOrder=true, las más antiguas en acceso van primero
    //     y la más recientemente accedida va al final.
    //   - Convierte keySet() a un array de String.
    public static String[] obtenerOrdenActual(LinkedHashMap<String, String> cache) {
        return null;
    }

    // TODO 5: Implementa `simularAccesosYVerEviccion`.
    //   - Crea una caché LRU con capacidad 3 usando crearCacheLRU().
    //   - Inserta 3 entradas con claves "A", "B", "C".
    //   - Consulta la clave "A" (esto la mueve al TAIL, la más reciente).
    //   - Inserta una 4ª entrada "D". Como "B" es ahora la menos reciente,
    //     debe ser la expulsada.
    //   - Retorna el array de claves resultante (debe ser ["C", "A", "D"]).
    public static String[] simularAccesosYVerEviccion() {
        return null;
    }

    // TODO 6: Implementa `cacheTieneEspacio`.
    //   - Recibe la caché y la capacidad máxima (int).
    //   - Retorna true si el número de entradas es estrictamente menor que capacidad.
    //   - Usa size() del mapa.
    public static boolean cacheTieneEspacio(LinkedHashMap<String, String> cache, int capacidad) {
        return false;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 18 — LinkedHashMap: Caché LRU ===\n");

        // -- crearCacheLRU --
        LinkedHashMap<String, String> cache = crearCacheLRU(3);
        System.out.println("Caché creada (vacía): " + cache);

        // -- agregarACache --
        agregarACache(cache, "user:1", "Ana");
        agregarACache(cache, "user:2", "Luis");
        agregarACache(cache, "user:3", "Marta");
        System.out.println("Caché con 3 entradas: " + cache);

        // -- consultarCache --
        System.out.println("Consultar user:1: " + consultarCache(cache, "user:1"));

        // -- obtenerOrdenActual --
        System.out.println("Orden tras consultar user:1: " +
                java.util.Arrays.toString(obtenerOrdenActual(cache)));

        // -- Evicción al insertar la 4ª --
        agregarACache(cache, "user:4", "Pedro");
        System.out.println("\nTras insertar user:4 (evicción de user:2): " + cache);

        // -- simularAccesosYVerEviccion --
        System.out.println("\nSimulación completa: " +
                java.util.Arrays.toString(simularAccesosYVerEviccion()));
    }
}
