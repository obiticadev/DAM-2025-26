package com.masterclass.arrays.nivel01;

/**
 * EJERCICIO 04 — Eliminación en Posición
 * =======================================
 * Nivel de Referencia Teórica: teoria/01_Fundamentos_Arrays.md
 *
 * Aprenderás a eliminar elementos de posiciones arbitrarias gestionando
 * el desplazamiento hacia la izquierda y manteniendo coherente el
 * tamaño lógico del array.
 *
 * CONVENCIÓN: 'tamanioLogico' indica cuántos elementos válidos hay.
 * Tras una eliminación, el tamaño lógico decrece en 1. La última
 * posición ocupada se limpia al valor por defecto (0).
 */
public class Ej04_EliminacionEnPosicion {

    // TODO 1: Implementar eliminarEnPosicion(int[] array, int tamanioLogico, int posicion)
    //   Especificación técnica:
    //   - Eliminar el elemento en 'posicion' desplazando todos los elementos a su derecha
    //     una posición hacia la IZQUIERDA (desde posicion+1 hasta tamanioLogico-1).
    //   - Limpiar la última posición lógica (tamanioLogico-1) asignándole 0.
    //   - Devolver el nuevo tamaño lógico (tamanioLogico - 1).
    //   - Si posicion < 0 o posicion >= tamanioLogico, lanzar IndexOutOfBoundsException.
    //   - Si el array es null, lanzar IllegalArgumentException.
    //   - Si tamanioLogico es 0, lanzar IllegalStateException ("No hay elementos para eliminar").
    public static int eliminarEnPosicion(int[] array, int tamanioLogico, int posicion) {
        return -1;
    }

    // TODO 2: Implementar eliminarPrimero(int[] array, int tamanioLogico)
    //   Especificación técnica:
    //   - Eliminar el primer elemento (posición 0).
    //   - Desplazar todos los demás una posición a la izquierda.
    //   - Devolver el nuevo tamaño lógico.
    //   - Si no hay elementos, lanzar IllegalStateException.
    public static int eliminarPrimero(int[] array, int tamanioLogico) {
        return -1;
    }

    // TODO 3: Implementar eliminarUltimo(int[] array, int tamanioLogico)
    //   Especificación técnica:
    //   - Eliminar el último elemento lógico (posición tamanioLogico-1).
    //   - No requiere desplazamiento, solo limpiar la posición y decrementar.
    //   - Devolver el nuevo tamaño lógico.
    //   - Si no hay elementos, lanzar IllegalStateException.
    public static int eliminarUltimo(int[] array, int tamanioLogico) {
        return -1;
    }

    // TODO 4: Implementar eliminarPrimeraOcurrencia(int[] array, int tamanioLogico, int valor)
    //   Especificación técnica:
    //   - Buscar la PRIMERA posición donde aparezca 'valor' (recorrido izquierda→derecha).
    //   - Si lo encuentra, eliminarlo desplazando y devolver el nuevo tamaño lógico.
    //   - Si NO lo encuentra, devolver el tamaño lógico sin modificar (no hacer nada).
    //   - Si el array es null, lanzar IllegalArgumentException.
    public static int eliminarPrimeraOcurrencia(int[] array, int tamanioLogico, int valor) {
        return -1;
    }

    // TODO 5: Implementar eliminarTodasOcurrencias(int[] array, int tamanioLogico, int valor)
    //   Especificación técnica:
    //   - Eliminar TODAS las apariciones de 'valor' en el array.
    //   - Estrategia recomendada: usar un puntero de escritura que solo avanza cuando
    //     el elemento actual NO es 'valor' (compactación single-pass).
    //   - Limpiar las posiciones sobrantes al final asignándoles 0.
    //   - Devolver el nuevo tamaño lógico tras todas las eliminaciones.
    //   - Si el array es null, lanzar IllegalArgumentException.
    public static int eliminarTodasOcurrencias(int[] array, int tamanioLogico, int valor) {
        return -1;
    }

    // TODO 6: Implementar compactar(int[] array, int tamanioLogico)
    //   Especificación técnica:
    //   - Asumir que el valor 0 representa "posición vacía" en el contexto de este ejercicio.
    //   - Mover todos los elementos NO-cero al principio del array, manteniendo su orden relativo.
    //   - Rellenar las posiciones sobrantes con 0.
    //   - Devolver el nuevo tamaño lógico (cantidad de elementos no-cero).
    //   - Ejemplo: {3, 0, 5, 0, 0, 7} → {3, 5, 7, 0, 0, 0}, devuelve 3.
    public static int compactar(int[] array, int tamanioLogico) {
        return -1;
    }

    // TODO 7: Implementar extraerElemento(int[] array, int tamanioLogico, int posicion)
    //   Especificación técnica:
    //   - Similar a eliminar, pero DEVOLVER el valor eliminado en lugar del nuevo tamaño.
    //   - Guardar el valor de array[posicion] ANTES de desplazar.
    //   - Desplazar elementos a la izquierda y limpiar la última posición.
    //   - Devolver el valor que estaba en la posición eliminada.
    //   - NOTA: el caller deberá decrementar su tamanioLogico manualmente.
    //   - Si posicion es inválida, lanzar IndexOutOfBoundsException.
    public static int extraerElemento(int[] array, int tamanioLogico, int posicion) {
        return -1;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // Pulsa "Run" para comprobar visualmente tu implementación.
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 04: Eliminación en Posición ===");
        System.out.println();

        // Descomenta y prueba tus métodos aquí:
        // int[] datos = {10, 20, 30, 40, 50, 0, 0, 0};
        // int size = 5;
        //
        // System.out.print("Original: ");
        // for (int i = 0; i < size; i++) System.out.print(datos[i] + " ");
        // System.out.println();
        //
        // size = eliminarEnPosicion(datos, size, 2); // Elimina el 30
        // System.out.print("Tras eliminar pos 2: ");
        // for (int i = 0; i < size; i++) System.out.print(datos[i] + " ");
        // System.out.println(" (size=" + size + ")");

        System.out.println();
        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
