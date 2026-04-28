package com.masterclass.collections.nivel03_linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * EJERCICIO 09 — LinkedList vs ArrayList: Aplicación Práctica
 * =============================================================
 * Teoría de referencia: teoria/02_LinkedList_Estructura_y_Deque.md  (§ 5 — Comparativa)
 *
 * Objetivo: Consolidar cuándo elegir LinkedList o ArrayList mediante escenarios
 * prácticos que demuestran las diferencias de coste.
 * Este ejercicio trabaja con la interfaz List<String> para que el código sea
 * independiente de la implementación concreta.
 *
 * Nota de diseño: Los métodos reciben List<String> (no ArrayList ni LinkedList)
 * porque el algoritmo debe funcionar con cualquier implementación.
 */
public class Ejercicio09_LinkedListVsArrayList {

    // TODO 1: Implementa `insertarEnPosicionCero`.
    //   - Inserta `elemento` en la posición 0 de la lista.
    //   - Usa el método de List que acepta índice y elemento.
    //   - El coste es O(1) para LinkedList y O(n) para ArrayList.
    public static void insertarEnPosicionCero(List<String> lista, String elemento) {
        // implementa aquí
    }

    // TODO 2: Implementa `extraerPrimero`.
    //   - Elimina y retorna el primer elemento de la lista.
    //   - Usa el método de List que acepta el índice a eliminar.
    //   - Retorna null si la lista está vacía.
    public static String extraerPrimero(List<String> lista) {
        return null;
    }

    // TODO 3: Implementa `estadisticasNumericas`.
    //   - Recibe una List<Integer> y retorna un resumen en este formato exacto:
    //     "min=X max=Y suma=Z promedio=W.WW"
    //   - Calcula min, max, suma y promedio iterando la lista con for-each.
    //   - Si la lista está vacía retorna "lista vacía".
    //   - El promedio debe tener exactamente 2 decimales (usa String.format).
    public static String estadisticasNumericas(List<Integer> lista) {
        return "";
    }

    // TODO 4: Implementa `rotarIzquierda`.
    //   - Rota la lista una posición a la izquierda:
    //     [A, B, C, D] → [B, C, D, A]
    //   - El primer elemento pasa al final.
    //   - Modifica la lista en sitio. No retorna nada.
    //   - Pista: extrae el primero y añádelo al final.
    public static void rotarIzquierda(List<String> lista) {
        // implementa aquí
    }

    // TODO 5: Implementa `intercalar`.
    //   - Dado [A, B, C] e [1, 2, 3] retorna [A, 1, B, 2, C, 3].
    //   - Crea y retorna un NUEVO ArrayList<String> alternando elementos de `a` y `b`.
    //   - Si tienen longitudes distintas, añade los sobrantes al final en orden.
    //   - Itera ambas listas con sus índices.
    public static ArrayList<String> intercalar(List<String> a, List<String> b) {
        return null;
    }

    // TODO 6 (análisis): Implementa `copiarInverso`.
    //   - Retorna un NUEVO ArrayList<String> con los elementos en orden inverso.
    //   - Para una List genérica (sin saber si es ArrayList o LinkedList),
    //     ¿qué estrategia de iteración minimiza el coste?
    //   - Usa un bucle que itere de (size-1) a 0 con acceso por índice.
    //   - ¿Por qué esto sería O(n²) con LinkedList? (responde en comentario dentro del método)
    public static ArrayList<String> copiarInverso(List<String> lista) {
        // PREGUNTA DE ANÁLISIS: escribe aquí un comentario explicando por qué
        // acceder por índice en un bucle sobre LinkedList es O(n²).

        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run para comprobar visualmente
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 09 — LinkedList vs ArrayList ===\n");

        // Prueba con ambas implementaciones para ver que el comportamiento es igual
        ArrayList<String> arrayList = new ArrayList<>(List.of("B", "C", "D"));
        LinkedList<String> linkedList = new LinkedList<>(List.of("B", "C", "D"));

        System.out.println("-- insertarEnPosicionCero --");
        insertarEnPosicionCero(arrayList, "A");
        insertarEnPosicionCero(linkedList, "A");
        System.out.println("ArrayList: " + arrayList);
        System.out.println("LinkedList: " + linkedList);

        System.out.println("\n-- extraerPrimero --");
        System.out.println("ArrayList extraído: " + extraerPrimero(arrayList));
        System.out.println("ArrayList tras extraer: " + arrayList);

        System.out.println("\n-- estadisticasNumericas --");
        List<Integer> numeros = List.of(5, 2, 8, 1, 9, 3, 7, 4, 6);
        System.out.println(estadisticasNumericas(new ArrayList<>(numeros)));
        System.out.println(estadisticasNumericas(new ArrayList<>()));

        System.out.println("\n-- rotarIzquierda --");
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
        System.out.println("Antes: " + lista);
        rotarIzquierda(lista);
        System.out.println("Después: " + lista);

        System.out.println("\n-- intercalar --");
        List<String> l1 = List.of("X", "Y", "Z");
        List<String> l2 = List.of("1", "2");
        System.out.println("Intercalado: " + intercalar(new ArrayList<>(l1), new ArrayList<>(l2)));

        System.out.println("\n-- copiarInverso --");
        ArrayList<String> origen = new ArrayList<>(List.of("uno", "dos", "tres", "cuatro"));
        System.out.println("Inverso: " + copiarInverso(origen));
        System.out.println("Original intacto: " + origen);
    }
}
