package com.masterclass.collections.nivel01_arraylist_basico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * EJERCICIO 02 — Iteración y Bucles sobre ArrayList
 * ==================================================
 * Teoría de referencia: teoria/01_ArrayList_Fundamentos.md  (§ 4 — Formas de iterar)
 *
 * Objetivo: Conocer y aplicar los cuatro mecanismos de iteración de ArrayList:
 *   for-i, for-each, Iterator y ListIterator.
 * Cada método debe usar obligatoriamente el mecanismo indicado en su javadoc.
 *
 * Restricción global: NO se permite usar Streams. Cada método especifica
 * qué tipo de bucle/iterador debes usar.
 */
public class Ejercicio02_IteracionYBucles {

    // TODO 1: Implementa `sumarLongitudes` usando un bucle for-i (índice entero).
    //   - Recorre la lista con for (int i = 0; i < ...; i++) { ... }
    //   - Suma la longitud (número de caracteres) de cada String.
    //   - Retorna la suma total.
    public static int sumarLongitudes(ArrayList<String> lista) {
        return -1;
    }

    // TODO 2: Implementa `convertirAMayusculas` usando un bucle for-each.
    //   - Crea y retorna un NUEVO ArrayList<String> con cada elemento convertido a mayúsculas.
    //   - La lista original NO debe modificarse.
    //   - Usa String.toUpperCase() para la conversión.
    public static ArrayList<String> convertirAMayusculas(ArrayList<String> lista) {
        return null;
    }

    // TODO 3: Implementa `eliminarMenoresQue` usando Iterator (nunca for-each al eliminar).
    //   - Elimina de la lista (en su lugar, la modifica) todos los Integer menores que `umbral`.
    //   - Usa it.remove() para evitar ConcurrentModificationException.
    //   - Retorna el número de elementos eliminados.
    public static int eliminarMenoresQue(ArrayList<Integer> lista, int umbral) {
        return -1;
    }

    // TODO 4: Implementa `invertirEnSitio` usando ListIterator.
    //   - Invierte el contenido de la lista modificándola en su lugar (sin crear una nueva).
    //   - Estrategia: recorre la lista con dos índices (inicio y fin) intercambiando elementos.
    //   - Para acceder a set(index, value) puedes usar el propio ArrayList;
    //     no es obligatorio el ListIterator para intercambiar, pero sí para el recorrido inicial.
    //   - Alternativa válida: usa Collections.reverse() — una línea, mira la API.
    public static void invertirEnSitio(ArrayList<String> lista) {
        // implementa aquí
    }

    // TODO 5: Implementa `contarOcurrencias` usando for-each.
    //   - Cuenta cuántas veces aparece `objetivo` en la lista.
    //   - Usa equals() para la comparación (sin indexOf).
    //   - Retorna el conteo.
    public static int contarOcurrencias(ArrayList<String> lista, String objetivo) {
        return -1;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run para comprobar visualmente
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 02 — Iteración y Bucles ===\n");

        ArrayList<String> palabras = new ArrayList<>();
        palabras.add("Java");
        palabras.add("Collections");
        palabras.add("ArrayList");
        palabras.add("Java");
        palabras.add("Map");

        System.out.println("Lista original: " + palabras);

        // -- sumarLongitudes (for-i) --
        System.out.println("Suma de longitudes: " + sumarLongitudes(palabras));

        // -- convertirAMayusculas (for-each) --
        ArrayList<String> mayusculas = convertirAMayusculas(palabras);
        System.out.println("En mayúsculas (nueva lista): " + mayusculas);
        System.out.println("Lista original (intacta): " + palabras);

        // -- eliminarMenoresQue (Iterator) --
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(10);
        numeros.add(3);
        numeros.add(7);
        numeros.add(1);
        numeros.add(15);
        numeros.add(4);
        System.out.println("\nNúmeros antes: " + numeros);
        int eliminados = eliminarMenoresQue(numeros, 5);
        System.out.println("Eliminados (<5): " + eliminados + " → Lista: " + numeros);

        // -- invertirEnSitio (ListIterator / swap) --
        ArrayList<String> frase = new ArrayList<>();
        frase.add("uno");
        frase.add("dos");
        frase.add("tres");
        frase.add("cuatro");
        System.out.println("\nAntes de invertir: " + frase);
        invertirEnSitio(frase);
        System.out.println("Después de invertir: " + frase);

        // -- contarOcurrencias (for-each) --
        System.out.println("\nOcurrencias de 'Java': " + contarOcurrencias(palabras, "Java"));
        System.out.println("Ocurrencias de 'Python': " + contarOcurrencias(palabras, "Python"));
    }
}
