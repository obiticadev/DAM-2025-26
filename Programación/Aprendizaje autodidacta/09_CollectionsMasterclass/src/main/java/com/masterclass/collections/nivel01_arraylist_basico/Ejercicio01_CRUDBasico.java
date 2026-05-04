package com.masterclass.collections.nivel01_arraylist_basico;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * EJERCICIO 01 — CRUD Básico con ArrayList
 * =========================================
 * Teoría de referencia: teoria/01_ArrayList_Fundamentos.md  (§ 3 — Operaciones CRUD)
 *
 * Objetivo: Dominar las operaciones fundamentales de creación, lectura,
 * actualización y eliminación sobre ArrayList sin usar streams ni métodos de ayuda.
 *
 * Restricción global: NO se permite usar Streams en ningún método de este ejercicio.
 */
public class Ejercicio01_CRUDBasico {

    // TODO 1: Implementa `crearLista`.
    //   - Recibe un varargs de String (String... elementos).
    //   - Debe retornar un ArrayList<String> que contenga esos elementos en el mismo orden.
    //   - Usa el constructor de ArrayList que acepta una Collection (no hagas add() en bucle).
    public static ArrayList<String> crearLista(String... elementos) {
        return null;
    }

    // TODO 2: Implementa `agregarAlInicio`.
    //   - Inserta `elemento` en la posición 0 de la lista.
    //   - NO se permite usar addFirst() (ese método es de LinkedList/Deque).
    //   - Usa el método de ArrayList que acepta índice + elemento.
    public static void agregarAlInicio(ArrayList<String> lista, String elemento) {
        // implementa aquí
    }

    // TODO 3: Implementa `eliminarPorIndice`.
    //   - Elimina el elemento en la posición `indice` y lo retorna.
    //   - Lanza IndexOutOfBoundsException si el índice está fuera de rango (ArrayList ya lo hace).
    public static String eliminarPorIndice(ArrayList<String> lista, int indice) {
        return null;
    }

    // TODO 4: Implementa `actualizarElemento`.
    //   - Sustituye el elemento en `indice` por `nuevoValor`.
    //   - El método no retorna nada (void). La modificación se aplica sobre la lista recibida.
    public static void actualizarElemento(ArrayList<String> lista, int indice, String nuevoValor) {
        // implementa aquí
    }

    // TODO 5: Implementa `contiene`.
    //   - Retorna true si `elemento` está en la lista, false en caso contrario.
    //   - Usa el método de la API de ArrayList que hace exactamente eso, sin bucles.
    public static boolean contiene(ArrayList<String> lista, String elemento) {
        return false;
    }

    // TODO 6: Implementa `tamano`.
    //   - Retorna el número de elementos actuales en la lista.
    //   - Un solo método de ArrayList hace esto directamente.
    public static int tamano(ArrayList<String> lista) {
        return -1;
    }

    // TODO 7: Implementa `vaciarLista`.
    //   - Deja la lista con 0 elementos.
    //   - Un solo método de Collection hace esto.
    //   - Tras llamar a este método, lista.isEmpty() debe ser true.
    public static void vaciarLista(ArrayList<String> lista) {
        // implementa aquí
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run para comprobar visualmente
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 01 — CRUD Básico ArrayList ===\n");

        // -- crearLista --
        ArrayList<String> lista = crearLista("Manzana", "Banana", "Cereza", "Dátil");
        System.out.println("Lista inicial: " + lista);

        // -- agregarAlInicio --
        agregarAlInicio(lista, "Aguacate");
        System.out.println("Después de agregarAlInicio('Aguacate'): " + lista);

        // -- eliminarPorIndice --
        String eliminado = eliminarPorIndice(lista, 2);
        System.out.println("Elemento eliminado en índice 2: " + eliminado);
        System.out.println("Lista tras eliminar índice 2: " + lista);

        // -- actualizarElemento --
        actualizarElemento(lista, 0, "AGUACATE");
        System.out.println("Lista tras actualizar índice 0: " + lista);

        // -- contiene --
        System.out.println("¿Contiene 'Banana'? " + contiene(lista, "Banana"));
        System.out.println("¿Contiene 'Cereza'? " + contiene(lista, "Cereza"));

        // -- tamano --
        System.out.println("Tamaño actual: " + tamano(lista));

        // -- vaciarLista --
        vaciarLista(lista);
        System.out.println("¿Lista vacía tras vaciarLista()? " + lista.isEmpty());
        System.out.println("Lista final: " + lista);
    }
}
