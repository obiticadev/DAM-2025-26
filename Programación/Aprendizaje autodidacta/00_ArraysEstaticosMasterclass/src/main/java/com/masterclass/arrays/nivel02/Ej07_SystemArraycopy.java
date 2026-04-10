package com.masterclass.arrays.nivel02;

/**
 * EJERCICIO 07 — System.arraycopy
 * ================================
 * Nivel de Referencia Teórica: teoria/02_Redimensionado_Y_Copia.md
 *
 * Dominarás el método nativo System.arraycopy para copiar bloques de
 * memoria de forma eficiente. Comprenderás sus 5 parámetros, el manejo
 * de offsets, y las diferencias con la copia manual.
 */
public class Ej07_SystemArraycopy {

    // TODO 1: Implementar copiarConSystemArraycopy(int[] original)
    //   Especificación técnica:
    //   - Crear un nuevo int[] del mismo tamaño que 'original'.
    //   - Usar System.arraycopy(original, 0, destino, 0, original.length) para copiar.
    //   - Devolver el nuevo array.
    //   - Si original es null, lanzar IllegalArgumentException.
    public static int[] copiarConSystemArraycopy(int[] original) {
        return null;
    }

    // TODO 2: Implementar copiarRango(int[] original, int desde, int longitud)
    //   Especificación técnica:
    //   - Crear un nuevo array de tamaño 'longitud'.
    //   - Usar System.arraycopy para copiar 'longitud' elementos empezando en 'desde'.
    //   - Si desde + longitud > original.length, lanzar IndexOutOfBoundsException.
    //   - Si original es null, lanzar IllegalArgumentException.
    //   - Si longitud es 0, devolver un array vacío.
    //   - Si desde < 0 o longitud < 0, lanzar IllegalArgumentException.
    public static int[] copiarRango(int[] original, int desde, int longitud) {
        return null;
    }

    // TODO 3: Implementar concatenarDos(int[] a, int[] b)
    //   Especificación técnica:
    //   - Crear un nuevo array de tamaño a.length + b.length.
    //   - Usar System.arraycopy dos veces: primero para 'a', luego para 'b' con offset.
    //   - El offset para 'b' en el destino es a.length.
    //   - Si alguno es null, lanzar IllegalArgumentException.
    public static int[] concatenarDos(int[] a, int[] b) {
        return null;
    }

    // TODO 4: Implementar concatenarMultiples(int[]... arrays)
    //   Especificación técnica:
    //   - Recibir un número variable de arrays (varargs).
    //   - Calcular el tamaño total sumando las longitudes de todos los arrays.
    //   - Crear un array resultado y copiar cada array en secuencia usando System.arraycopy.
    //   - Mantener un offset acumulado para saber dónde empieza cada copia.
    //   - Si algún array individual es null, lanzar IllegalArgumentException.
    //   - Si no se pasan arrays (longitud 0 de varargs), devolver un array vacío.
    public static int[] concatenarMultiples(int[]... arrays) {
        return null;
    }

    // TODO 5: Implementar insertarConDesplazamiento(int[] array, int tamanioLogico, int posicion, int valor)
    //   Especificación técnica:
    //   - Usar System.arraycopy para desplazar los elementos desde 'posicion' hacia la derecha
    //     en UNA sola operación (en vez de un bucle).
    //   - Parámetros de arraycopy: origen=array, posOrigen=posicion, destino=array,
    //     posDestino=posicion+1, longitud=tamanioLogico-posicion.
    //   - Asignar array[posicion] = valor.
    //   - Devolver el nuevo tamaño lógico.
    //   - Validar que hay capacidad y que la posición es válida.
    public static int insertarConDesplazamiento(int[] array, int tamanioLogico, int posicion, int valor) {
        return -1;
    }

    // TODO 6: Implementar eliminarConDesplazamiento(int[] array, int tamanioLogico, int posicion)
    //   Especificación técnica:
    //   - Usar System.arraycopy para desplazar elementos desde posicion+1 hacia la izquierda.
    //   - Una sola llamada a arraycopy reemplaza el bucle de desplazamiento.
    //   - Limpiar la última posición (tamanioLogico - 1) asignándole 0.
    //   - Devolver nuevo tamaño lógico.
    //   - Validar posición e inputs.
    public static int eliminarConDesplazamiento(int[] array, int tamanioLogico, int posicion) {
        return -1;
    }

    // TODO 7: Implementar duplicarArray(int[] original, int repeticiones)
    //   Especificación técnica:
    //   - Crear un nuevo array de tamaño original.length * repeticiones.
    //   - Copiar 'original' 'repeticiones' veces consecutivas usando System.arraycopy.
    //   - Ejemplo: original={1,2,3}, repeticiones=3 → {1,2,3,1,2,3,1,2,3}.
    //   - Si repeticiones es 0, devolver un array vacío.
    //   - Si repeticiones < 0, lanzar IllegalArgumentException.
    //   - Si original es null, lanzar IllegalArgumentException.
    public static int[] duplicarArray(int[] original, int repeticiones) {
        return null;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 07: System.arraycopy ===");
        System.out.println();

        // Descomenta y prueba:
        // int[] copia = copiarConSystemArraycopy(new int[]{10, 20, 30});
        // for (int v : copia) System.out.print(v + " ");
        // System.out.println();
        //
        // int[] concat = concatenarDos(new int[]{1, 2}, new int[]{3, 4, 5});
        // for (int v : concat) System.out.print(v + " ");

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
