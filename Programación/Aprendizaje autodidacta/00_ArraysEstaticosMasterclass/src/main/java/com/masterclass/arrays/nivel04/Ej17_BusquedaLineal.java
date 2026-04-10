package com.masterclass.arrays.nivel04;

/**
 * EJERCICIO 17 — Búsqueda Lineal
 * ================================
 * Nivel de Referencia Teórica: teoria/04_Busqueda_En_Arrays.md
 *
 * Dominarás todas las variantes de búsqueda secuencial/lineal:
 * primera/última ocurrencia, contar ocurrencias, y recopilar todos
 * los índices donde aparece un valor. Funciona en arrays DESORDENADOS.
 */
public class Ej17_BusquedaLineal {

    // TODO 1: Implementar buscarPrimero(int[] array, int valor)
    //   Especificación técnica:
    //   - Recorrer de izquierda a derecha buscando la PRIMERA ocurrencia de 'valor'.
    //   - Devolver el índice (0-based) donde se encuentra.
    //   - Si no existe, devolver -1.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static int buscarPrimero(int[] array, int valor) {
        return -1;
    }

    // TODO 2: Implementar buscarUltimo(int[] array, int valor)
    //   Especificación técnica:
    //   - Recorrer de DERECHA a IZQUIERDA buscando la ÚLTIMA ocurrencia de 'valor'.
    //   - Devolver el índice donde se encuentra la última aparición.
    //   - Si no existe, devolver -1.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static int buscarUltimo(int[] array, int valor) {
        return -1;
    }

    // TODO 3: Implementar contarOcurrencias(int[] array, int valor)
    //   Especificación técnica:
    //   - Contar cuántas veces aparece 'valor' en el array completo.
    //   - Devolver el recuento total (0 si no aparece).
    //   - Si array es null o vacío, devolver 0.
    public static int contarOcurrencias(int[] array, int valor) {
        return 0;
    }

    // TODO 4: Implementar buscarTodosIndices(int[] array, int valor)
    //   Especificación técnica:
    //   - Encontrar TODOS los índices donde aparece 'valor'.
    //   - Devolver un int[] con todos los índices encontrados (tamaño exacto, sin sobrante).
    //   - Si no aparece, devolver un int[] vacío (new int[0]).
    //   - Estrategia: primero contar ocurrencias para saber el tamaño del resultado,
    //     luego llenar el array resultado en una segunda pasada.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static int[] buscarTodosIndices(int[] array, int valor) {
        return null;
    }

    // TODO 5: Implementar contiene(int[] array, int valor)
    //   Especificación técnica:
    //   - Devolver true si 'valor' existe en el array, false si no.
    //   - Equivale a buscarPrimero() != -1, pero puede cortocircuitar al encontrar.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static boolean contiene(int[] array, int valor) {
        return false;
    }

    // TODO 6: Implementar buscarMinimo(int[] array)
    //   Especificación técnica:
    //   - Devolver un int[2] con: [0]=valor mínimo, [1]=índice del mínimo.
    //   - Si hay varios mínimos iguales, devolver el PRIMER índice.
    //   - Si array es null o vacío, lanzar IllegalArgumentException.
    public static int[] buscarMinimo(int[] array) {
        return null;
    }

    // TODO 7: Implementar buscarMaximo(int[] array)
    //   Especificación técnica:
    //   - Devolver un int[2] con: [0]=valor máximo, [1]=índice del máximo.
    //   - Si hay varios máximos iguales, devolver el PRIMER índice.
    //   - Si array es null o vacío, lanzar IllegalArgumentException.
    public static int[] buscarMaximo(int[] array) {
        return null;
    }

    // TODO 8: Implementar buscarEnStrings(String[] array, String valor)
    //   Especificación técnica:
    //   - Búsqueda lineal en un String[].
    //   - Usar .equals() para la comparación (NO ==).
    //   - Devolver el índice de la primera ocurrencia, o -1 si no existe.
    //   - Si 'valor' es null, buscar la primera posición que contenga null.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static int buscarEnStrings(String[] array, String valor) {
        return -1;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 17: Búsqueda Lineal ===");
        System.out.println();

        // Descomenta y prueba:
        // int[] datos = {4, 2, 7, 2, 9, 2, 5};
        // System.out.println("Primero 2: índice " + buscarPrimero(datos, 2));
        // System.out.println("Último 2: índice " + buscarUltimo(datos, 2));
        // System.out.println("Ocurrencias de 2: " + contarOcurrencias(datos, 2));
        // int[] indices = buscarTodosIndices(datos, 2);
        // System.out.print("Todos los indices de 2: ");
        // for (int i : indices) System.out.print(i + " ");

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
