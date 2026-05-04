package com.masterclass.collections.nivel07_hashset;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * EJERCICIO 21 — HashSet: Operaciones Básicas y Unicidad
 * ========================================================
 * Teoría de referencia: teoria/05_HashSet_y_Conjuntos.md  (§ 1–3)
 *
 * Objetivo: Comprender la semántica de add(), contains(), remove() en un
 * HashSet y la garantía de unicidad que ofrece.
 *
 * Restricción: NO se permite usar HashMap directamente. Trabaja solo con la API de Set.
 */
public class Ejercicio21_HashSetBasico {

    // TODO 1: Implementa `crearSetDesdeArray`.
    //   - Recibe un String[] y retorna un HashSet<String> con esos elementos.
    //   - Los duplicados del array se eliminan automáticamente al insertarse en el set.
    //   - Usa un bucle for-each o el constructor de HashSet que acepta Collection
    //     (convirtiendo el array a List con Arrays.asList()).
    public static HashSet<String> crearSetDesdeArray(String[] elementos) {
        return null;
    }

    // TODO 2: Implementa `agregarElemento`.
    //   - Añade `elemento` al HashSet.
    //   - Retorna true si el elemento fue añadido (no existía), false si ya estaba.
    //   - Un solo método de Set hace esto directamente.
    public static boolean agregarElemento(HashSet<String> set, String elemento) {
        return false;
    }

    // TODO 3: Implementa `eliminarSiExiste`.
    //   - Elimina `elemento` del HashSet si existe.
    //   - Retorna true si fue eliminado, false si no existía.
    //   - Un solo método de Set hace esto directamente.
    public static boolean eliminarSiExiste(HashSet<String> set, String elemento) {
        return false;
    }

    // TODO 4: Implementa `eliminarDuplicadosDeLista`.
    //   - Recibe un ArrayList<String> con posibles duplicados.
    //   - Retorna un NUEVO ArrayList<String> sin duplicados.
    //   - El orden del resultado NO importa (estamos usando HashSet, sin orden garantizado).
    //   - Convierte la lista a HashSet y luego el HashSet de vuelta a ArrayList.
    public static ArrayList<String> eliminarDuplicadosDeLista(ArrayList<String> lista) {
        return null;
    }

    // TODO 5: Implementa `contarElementosUnicos`.
    //   - Recibe un String[] y retorna cuántos elementos ÚNICOS contiene.
    //   - Crea un HashSet con el array y retorna su tamaño.
    public static int contarElementosUnicos(String[] elementos) {
        return -1;
    }

    // TODO 6: Implementa `todosUnicos`.
    //   - Recibe un ArrayList<String> y retorna true si TODOS los elementos son únicos
    //     (no hay duplicados), false si hay al menos un duplicado.
    //   - Compara el tamaño de la lista con el tamaño de un HashSet creado a partir de ella.
    public static boolean todosUnicos(ArrayList<String> lista) {
        return false;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 21 — HashSet: Operaciones Básicas ===\n");

        // -- crearSetDesdeArray --
        String[] frutas = {"Manzana", "Banana", "Manzana", "Cereza", "Banana", "Dátil"};
        HashSet<String> set = crearSetDesdeArray(frutas);
        System.out.println("Set (sin duplicados): " + set);

        // -- agregarElemento --
        System.out.println("Agregar 'Pera' (nueva): " + agregarElemento(set, "Pera"));
        System.out.println("Agregar 'Manzana' (duplicada): " + agregarElemento(set, "Manzana"));

        // -- eliminarSiExiste --
        System.out.println("\nEliminar 'Banana': " + eliminarSiExiste(set, "Banana"));
        System.out.println("Eliminar 'Kiwi' (no existe): " + eliminarSiExiste(set, "Kiwi"));
        System.out.println("Set actual: " + set);

        // -- eliminarDuplicadosDeLista --
        ArrayList<String> conDups = new ArrayList<>(java.util.List.of("A", "B", "A", "C", "B"));
        System.out.println("\nLista sin duplicados: " + eliminarDuplicadosDeLista(conDups));

        // -- contarElementosUnicos --
        System.out.println("Elementos únicos en frutas[]: " + contarElementosUnicos(frutas));

        // -- todosUnicos --
        System.out.println("¿Todos únicos ['A','B','C']? " +
                todosUnicos(new ArrayList<>(java.util.List.of("A", "B", "C"))));
        System.out.println("¿Todos únicos ['A','B','A']? " +
                todosUnicos(new ArrayList<>(java.util.List.of("A", "B", "A"))));
    }
}
