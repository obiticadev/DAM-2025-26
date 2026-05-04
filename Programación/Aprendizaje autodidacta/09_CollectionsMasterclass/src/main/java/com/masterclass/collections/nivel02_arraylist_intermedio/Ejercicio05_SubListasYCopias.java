package com.masterclass.collections.nivel02_arraylist_intermedio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * EJERCICIO 05 — SubListas, Copias y Operaciones Bulk
 * =====================================================
 * Teoría de referencia: teoria/01_ArrayList_Fundamentos.md  (§ 8 — Bulk operations)
 *
 * Objetivo: Comprender la diferencia entre vistas y copias, y dominar las
 * operaciones de combinación de listas (addAll, removeAll, retainAll).
 */
public class Ejercicio05_SubListasYCopias {

    // TODO 1: Implementa `copiaIndependiente`.
    //   - Retorna un NUEVO ArrayList<String> con los mismos elementos que `original`.
    //   - Debe ser una copia PROFUNDA de primer nivel (modificar la copia NO afecta al original).
    //   - Usa el constructor de ArrayList que acepta una Collection.
    public static ArrayList<String> copiaIndependiente(ArrayList<String> original) {
        return null;
    }

    // TODO 2: Implementa `fusionar`.
    //   - Retorna un NUEVO ArrayList<String> que contiene todos los elementos de `a`
    //     seguidos de todos los elementos de `b`.
    //   - Las listas `a` y `b` no deben modificarse.
    //   - Usa addAll() para la operación de fusión.
    public static ArrayList<String> fusionar(ArrayList<String> a, ArrayList<String> b) {
        return null;
    }

    // TODO 3: Implementa `interseccion`.
    //   - Retorna un NUEVO ArrayList<String> con los elementos que aparecen
    //     en AMBAS listas (intersección de conjuntos).
    //   - Parte de una copia de `a` y usa retainAll(b) sobre esa copia.
    //   - Las listas originales no se modifican.
    public static ArrayList<String> interseccion(ArrayList<String> a, ArrayList<String> b) {
        return null;
    }

    // TODO 4: Implementa `diferencia`.
    //   - Retorna un NUEVO ArrayList<String> con los elementos de `a` que NO
    //     están en `b` (diferencia de conjuntos A - B).
    //   - Parte de una copia de `a` y usa removeAll(b).
    public static ArrayList<String> diferencia(ArrayList<String> a, ArrayList<String> b) {
        return null;
    }

    // TODO 5: Implementa `llenarConValor`.
    //   - Crea un ArrayList<String> de `cantidad` elementos, todos iguales a `valor`.
    //   - Usa Collections.nCopies() para crear la lista de origen, luego envuélvela
    //     en un ArrayList.
    public static ArrayList<String> llenarConValor(String valor, int cantidad) {
        return null;
    }

    // TODO 6: Implementa `listaInmutable`.
    //   - Retorna una vista no modificable del ArrayList recibido.
    //   - Cualquier intento de add/set/remove sobre el resultado debe lanzar
    //     UnsupportedOperationException.
    //   - Usa Collections.unmodifiableList().
    public static List<String> listaInmutable(ArrayList<String> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run para comprobar visualmente
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 05 — SubListas y Copias ===\n");

        ArrayList<String> a = new ArrayList<>(List.of("A", "B", "C", "D"));
        ArrayList<String> b = new ArrayList<>(List.of("C", "D", "E", "F"));

        // -- copiaIndependiente --
        ArrayList<String> copia = copiaIndependiente(a);
        copia.add("EXTRA");
        System.out.println("Original a: " + a + "  (no debe contener EXTRA)");
        System.out.println("Copia:      " + copia);

        // -- fusionar --
        System.out.println("\nFusión a+b: " + fusionar(a, b));

        // -- interseccion --
        System.out.println("Intersección a∩b: " + interseccion(a, b));

        // -- diferencia --
        System.out.println("Diferencia a-b: " + diferencia(a, b));

        // -- llenarConValor --
        ArrayList<String> rellena = llenarConValor("X", 5);
        System.out.println("\nLlena con 'X' x5: " + rellena);

        // -- listaInmutable --
        List<String> inmutable = listaInmutable(a);
        System.out.println("\nInmutable: " + inmutable);
        try {
            inmutable.add("NO");
            System.out.println("ERROR: debería haber lanzado excepción");
        } catch (UnsupportedOperationException e) {
            System.out.println("Correcto: UnsupportedOperationException al intentar add()");
        }
    }
}
