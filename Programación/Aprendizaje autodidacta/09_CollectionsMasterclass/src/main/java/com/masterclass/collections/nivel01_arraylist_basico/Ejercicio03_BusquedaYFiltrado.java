package com.masterclass.collections.nivel01_arraylist_basico;

import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 03 — Búsqueda y Filtrado en ArrayList
 * ================================================
 * Teoría de referencia: teoria/01_ArrayList_Fundamentos.md  (§ 5 — Búsqueda y filtrado)
 *
 * Objetivo: Dominar indexOf, lastIndexOf, subList y removeIf para localizar
 * y filtrar elementos de un ArrayList.
 *
 * Restricción global: NO se permite usar Streams.
 */
public class Ejercicio03_BusquedaYFiltrado {

    // TODO 1: Implementa `primerIndice`.
    //   - Retorna la posición de la primera ocurrencia de `objetivo` en la lista.
    //   - Si no existe, retorna -1.
    //   - Usa el método de ArrayList que hace exactamente esto (sin bucles).
    public static int primerIndice(ArrayList<String> lista, String objetivo) {
        return -1;
    }

    // TODO 2: Implementa `ultimoIndice`.
    //   - Retorna la posición de la ÚLTIMA ocurrencia de `objetivo`.
    //   - Si no existe, retorna -1.
    //   - Análogo a primerIndice pero para la última aparición.
    public static int ultimoIndice(ArrayList<String> lista, String objetivo) {
        return -1;
    }

    // TODO 3: Implementa `extraerSubLista`.
    //   - Retorna una COPIA INDEPENDIENTE (no una vista) de los elementos
    //     entre `desde` (incluido) y `hasta` (excluido).
    //   - Clave: subList() devuelve una vista, no una copia. Envuélvela en
    //     un nuevo ArrayList para que sea independiente.
    //   - Si desde >= hasta, retorna una lista vacía.
    public static ArrayList<String> extraerSubLista(ArrayList<String> lista, int desde, int hasta) {
        return null;
    }

    // TODO 4: Implementa `eliminarSiEmpiezaPor`.
    //   - Elimina de la lista (modificación en sitio) todos los String que
    //     comiencen con el prefijo `prefijo` (case-sensitive).
    //   - Usa removeIf() con una expresión lambda.
    //   - Retorna el número de elementos eliminados (tamaño antes - tamaño después).
    public static int eliminarSiEmpiezaPor(ArrayList<String> lista, String prefijo) {
        return -1;
    }

    // TODO 5: Implementa `filtrarPorLongitudMinima`.
    //   - Retorna un NUEVO ArrayList<String> con solo los elementos cuya
    //     longitud sea >= `longMin`.
    //   - La lista original no se modifica.
    //   - No uses Streams; usa un bucle for-each y add() condicional.
    public static ArrayList<String> filtrarPorLongitudMinima(ArrayList<String> lista, int longMin) {
        return null;
    }

    // TODO 6: Implementa `contieneTodos`.
    //   - Retorna true si la lista contiene TODOS los elementos de `requeridos`.
    //   - Usa el método de Collection que hace esto directamente (una línea).
    public static boolean contieneTodos(ArrayList<String> lista, List<String> requeridos) {
        return false;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run para comprobar visualmente
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 03 — Búsqueda y Filtrado ===\n");

        ArrayList<String> ciudades = new ArrayList<>();
        ciudades.add("Madrid");
        ciudades.add("Barcelona");
        ciudades.add("Madrid");
        ciudades.add("Valencia");
        ciudades.add("Bilbao");
        ciudades.add("Madrid");
        ciudades.add("Burgos");

        System.out.println("Lista: " + ciudades);

        // -- primerIndice --
        System.out.println("\nprimerIndice('Madrid'): " + primerIndice(ciudades, "Madrid"));
        System.out.println("primerIndice('Sevilla'): " + primerIndice(ciudades, "Sevilla"));

        // -- ultimoIndice --
        System.out.println("ultimoIndice('Madrid'): " + ultimoIndice(ciudades, "Madrid"));

        // -- extraerSubLista --
        ArrayList<String> sub = extraerSubLista(ciudades, 1, 4);
        System.out.println("\nSubLista [1,4): " + sub);
        // Comprueba independencia: modificar sub NO debe afectar ciudades
        sub.add("EXTRA");
        System.out.println("ciudades tras modificar sub (debe ser igual): " + ciudades);

        // -- eliminarSiEmpiezaPor --
        ArrayList<String> copia = new ArrayList<>(ciudades);
        int eliminados = eliminarSiEmpiezaPor(copia, "B");
        System.out.println("\nEliminados con prefijo 'B': " + eliminados);
        System.out.println("Lista tras eliminar: " + copia);

        // -- filtrarPorLongitudMinima --
        ArrayList<String> largas = filtrarPorLongitudMinima(ciudades, 7);
        System.out.println("\nCiudades con longitud >= 7: " + largas);
        System.out.println("ciudades original (intacta): " + ciudades);

        // -- contieneTodos --
        List<String> buscadas = List.of("Madrid", "Bilbao");
        List<String> faltantes = List.of("Madrid", "Sevilla");
        System.out.println("\n¿Contiene [Madrid, Bilbao]? " + contieneTodos(ciudades, buscadas));
        System.out.println("¿Contiene [Madrid, Sevilla]? " + contieneTodos(ciudades, faltantes));
    }
}
