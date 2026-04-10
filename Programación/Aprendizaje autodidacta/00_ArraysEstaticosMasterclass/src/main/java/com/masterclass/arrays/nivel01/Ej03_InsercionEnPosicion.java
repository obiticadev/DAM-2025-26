package com.masterclass.arrays.nivel01;

/**
 * EJERCICIO 03 — Inserción en Posición
 * =====================================
 * Nivel de Referencia Teórica: teoria/01_Fundamentos_Arrays.md
 *
 * Aprenderás a insertar elementos en posiciones arbitrarias de un array,
 * gestionando el desplazamiento de elementos y el concepto crítico de
 * tamaño lógico vs tamaño físico.
 *
 * CONVENCIÓN: Los métodos trabajan con un array que puede tener capacidad
 * sobrante. El parámetro 'tamanioLogico' indica cuántas posiciones están
 * ocupadas realmente (de 0 a tamanioLogico-1).
 */
public class Ej03_InsercionEnPosicion {

    // TODO 1: Implementar insertarEnPosicion(int[] array, int tamanioLogico, int posicion, int valor)
    //   Especificación técnica:
    //   - Insertar 'valor' en la posición 'posicion' dentro del array.
    //   - Para hacer hueco, desplazar todos los elementos desde 'posicion' hasta 'tamanioLogico-1'
    //     una posición hacia la DERECHA. El desplazamiento debe hacerse desde el final hacia
    //     la posición para no sobreescribir datos.
    //   - Devolver el nuevo tamaño lógico (tamanioLogico + 1).
    //   - Si el array está lleno (tamanioLogico == array.length), lanzar IllegalStateException.
    //   - Si posicion < 0 o posicion > tamanioLogico, lanzar IndexOutOfBoundsException.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static int insertarEnPosicion(int[] array, int tamanioLogico, int posicion, int valor) {
        return -1;
    }

    // TODO 2: Implementar insertarAlInicio(int[] array, int tamanioLogico, int valor)
    //   Especificación técnica:
    //   - Insertar 'valor' en la posición 0 (al principio del array).
    //   - Todos los elementos existentes se desplazan una posición a la derecha.
    //   - Devolver el nuevo tamaño lógico.
    //   - Reutilizar internamente la lógica de insertarEnPosicion.
    //   - Mismas validaciones de capacidad que TODO 1.
    public static int insertarAlInicio(int[] array, int tamanioLogico, int valor) {
        return -1;
    }

    // TODO 3: Implementar insertarAlFinal(int[] array, int tamanioLogico, int valor)
    //   Especificación técnica:
    //   - Insertar 'valor' al final de los datos lógicos (posición = tamanioLogico).
    //   - No requiere desplazamiento porque se coloca después del último elemento.
    //   - Devolver el nuevo tamaño lógico.
    //   - Si el array está lleno, lanzar IllegalStateException.
    public static int insertarAlFinal(int[] array, int tamanioLogico, int valor) {
        return -1;
    }

    // TODO 4: Implementar insertarVariosAlFinal(int[] array, int tamanioLogico, int[] valores)
    //   Especificación técnica:
    //   - Insertar todos los elementos del array 'valores' al final de los datos lógicos.
    //   - Comprobar ANTES de empezar que hay capacidad para todos:
    //     (tamanioLogico + valores.length) <= array.length.
    //   - Si no hay capacidad suficiente, lanzar IllegalStateException SIN modificar el array.
    //   - Devolver el nuevo tamaño lógico tras todas las inserciones.
    public static int insertarVariosAlFinal(int[] array, int tamanioLogico, int[] valores) {
        return -1;
    }

    // TODO 5: Implementar insertarOrdenado(int[] array, int tamanioLogico, int valor)
    //   Especificación técnica:
    //   - El array ya está ordenado de menor a mayor (los primeros 'tamanioLogico' elementos).
    //   - Encontrar la posición correcta donde 'valor' mantiene el orden.
    //   - Insertar en esa posición desplazando los elementos necesarios.
    //   - Devolver el nuevo tamaño lógico.
    //   - Si el array está lleno, lanzar IllegalStateException.
    public static int insertarOrdenado(int[] array, int tamanioLogico, int valor) {
        return -1;
    }

    // TODO 6: Implementar crearConCapacidadExtra(int[] original, int capacidadExtra)
    //   Especificación técnica:
    //   - Crear un NUEVO array cuyo tamaño sea original.length + capacidadExtra.
    //   - Copiar todos los elementos de 'original' al nuevo array (posiciones 0 a original.length-1).
    //   - Las posiciones sobrantes quedan con el valor por defecto (0).
    //   - Devolver el nuevo array.
    //   - Si original es null, lanzar IllegalArgumentException.
    //   - Si capacidadExtra < 0, lanzar IllegalArgumentException.
    public static int[] crearConCapacidadExtra(int[] original, int capacidadExtra) {
        return null;
    }

    // TODO 7: Implementar contarEspacioLibre(int[] array, int tamanioLogico)
    //   Especificación técnica:
    //   - Calcular cuántas posiciones libres quedan: array.length - tamanioLogico.
    //   - Si el resultado es 0, el array está lleno.
    //   - Devolver el número de posiciones libres.
    //   - Si array es null, lanzar IllegalArgumentException.
    //   - Si tamanioLogico < 0 o tamanioLogico > array.length, lanzar IllegalArgumentException.
    public static int contarEspacioLibre(int[] array, int tamanioLogico) {
        return -1;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // Pulsa "Run" para comprobar visualmente tu implementación.
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 03: Inserción en Posición ===");
        System.out.println();

        // Descomenta y prueba tus métodos aquí:
        // int[] datos = new int[10]; // Capacidad física = 10
        // int size = 0;
        //
        // size = insertarAlFinal(datos, size, 10);   // {10, _, _, _, _, _, _, _, _, _}
        // size = insertarAlFinal(datos, size, 30);   // {10, 30, _, _, _, _, _, _, _, _}
        // size = insertarEnPosicion(datos, size, 1, 20); // {10, 20, 30, _, _, ...}
        // size = insertarAlInicio(datos, size, 5);   // {5, 10, 20, 30, _, _, ...}
        //
        // System.out.println("Tamaño lógico: " + size);
        // System.out.print("Contenido: ");
        // for (int i = 0; i < size; i++) {
        //     System.out.print(datos[i] + " ");
        // }

        System.out.println();
        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
