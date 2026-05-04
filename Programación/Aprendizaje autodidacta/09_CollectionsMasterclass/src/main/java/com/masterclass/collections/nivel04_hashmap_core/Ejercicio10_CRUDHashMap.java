package com.masterclass.collections.nivel04_hashmap_core;

import java.util.HashMap;

/**
 * EJERCICIO 10 — CRUD Básico con HashMap
 * =======================================
 * Teoría de referencia: teoria/03_HashMap_Core.md  (§ 4 — Operaciones CRUD)
 *
 * Objetivo: Dominar las operaciones fundamentales de HashMap: inserción,
 * lectura, eliminación y comprobación de existencia.
 *
 * Restricción: NO usar Streams. Solo métodos directos de HashMap/Map.
 */
public class Ejercicio10_CRUDHashMap {

    // TODO 1: Implementa `crearMapa`.
    //   - Recibe un varargs de String con pares clave-valor alternados:
    //     crearMapa("nombre", "Ana", "ciudad", "Madrid") → {"nombre":"Ana", "ciudad":"Madrid"}
    //   - Si el número de argumentos es impar, ignora el último.
    //   - Retorna un HashMap<String, String> con esos pares.
    public static HashMap<String, String> crearMapa(String... paresClaveValor) {
        return null;
    }

    // TODO 2: Implementa `insertar`.
    //   - Inserta el par (clave, valor) en el mapa.
    //   - Si la clave ya existía, sobrescribe el valor.
    //   - Retorna el valor ANTERIOR asociado a esa clave, o null si era nueva.
    public static String insertar(HashMap<String, String> mapa, String clave, String valor) {
        return null;
    }

    // TODO 3: Implementa `obtener`.
    //   - Retorna el valor asociado a `clave`, o null si no existe.
    //   - Un único método de HashMap hace esto directamente.
    public static String obtener(HashMap<String, String> mapa, String clave) {
        return null;
    }

    // TODO 4: Implementa `eliminar`.
    //   - Elimina la entrada con esa clave del mapa.
    //   - Retorna el valor que había asociado, o null si la clave no existía.
    public static String eliminar(HashMap<String, String> mapa, String clave) {
        return null;
    }

    // TODO 5: Implementa `contieneClave`.
    //   - Retorna true si el mapa tiene una entrada con esa clave.
    //   - Coste O(1) — no recorras el mapa con un bucle.
    public static boolean contieneClave(HashMap<String, String> mapa, String clave) {
        return false;
    }

    // TODO 6: Implementa `contieneValor`.
    //   - Retorna true si alguna entrada del mapa tiene ese valor.
    //   - Coste O(n) — HashMap no indexa por valor.
    //   - Usa el método de Map que hace esto directamente (una línea).
    public static boolean contieneValor(HashMap<String, String> mapa, String valor) {
        return false;
    }

    // TODO 7: Implementa `vaciar`.
    //   - Deja el mapa con 0 entradas.
    //   - Un único método de Map hace esto.
    public static void vaciar(HashMap<String, String> mapa) {
        // implementa aquí
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 10 — CRUD Básico HashMap ===\n");

        HashMap<String, String> mapa = crearMapa(
                "nombre", "Ana", "ciudad", "Madrid", "profesion", "Ingeniera");
        System.out.println("Mapa inicial: " + mapa);

        System.out.println("Insertar 'pais'='España': " + insertar(mapa, "pais", "España"));
        System.out.println("Sobreescribir 'ciudad'='Barcelona': " + insertar(mapa, "ciudad", "Barcelona"));
        System.out.println("Mapa tras inserciones: " + mapa);

        System.out.println("\nObtener 'nombre': " + obtener(mapa, "nombre"));
        System.out.println("Obtener 'edad' (no existe): " + obtener(mapa, "edad"));

        System.out.println("\nEliminar 'profesion': " + eliminar(mapa, "profesion"));
        System.out.println("Eliminar 'foo' (no existe): " + eliminar(mapa, "foo"));
        System.out.println("Mapa tras eliminar: " + mapa);

        System.out.println("\n¿Contiene clave 'nombre'? " + contieneClave(mapa, "nombre"));
        System.out.println("¿Contiene clave 'edad'? " + contieneClave(mapa, "edad"));
        System.out.println("¿Contiene valor 'Ana'? " + contieneValor(mapa, "Ana"));
        System.out.println("¿Contiene valor 'París'? " + contieneValor(mapa, "París"));

        vaciar(mapa);
        System.out.println("\nTras vaciar — isEmpty: " + mapa.isEmpty());
    }
}
