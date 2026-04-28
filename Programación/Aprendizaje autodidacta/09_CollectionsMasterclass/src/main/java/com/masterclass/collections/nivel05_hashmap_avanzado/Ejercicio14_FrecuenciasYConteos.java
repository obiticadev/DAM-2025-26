package com.masterclass.collections.nivel05_hashmap_avanzado;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * EJERCICIO 14 — Frecuencias, Conteos y merge()
 * ===============================================
 * Teoría de referencia: teoria/03_HashMap_Core.md  (§ 6 — compute y merge)
 *
 * Objetivo: Usar HashMap para contar apariciones, calcular frecuencias y
 * combinar mapas usando el método merge(), que es la forma idiomática
 * de acumular valores en Java moderno.
 *
 * Restricción: Para los TODOs que dicen "usa merge()", debes usar ese método.
 *              NO implementes el patrón if-containsKey / put manual.
 */
public class Ejercicio14_FrecuenciasYConteos {

    // TODO 1: Implementa `contarFrecuencias`.
    //   - Cuenta cuántas veces aparece cada String en la lista.
    //   - Retorna un HashMap<String, Integer> con elemento → frecuencia.
    //   - Usa mapa.merge(elemento, 1, Integer::sum) para cada elemento.
    //   - Esta es la forma canónica de contar en Java: no uses if/put manualmente.
    public static HashMap<String, Integer> contarFrecuencias(ArrayList<String> lista) {
        return null;
    }

    // TODO 2: Implementa `palabraMasFrecuente`.
    //   - Retorna el String que más veces aparece en la lista.
    //   - Si hay empate, retorna cualquiera de los empatados.
    //   - Estrategia: primero llama a contarFrecuencias(), luego recorre el mapa
    //     con entrySet() buscando el de mayor valor.
    public static String palabraMasFrecuente(ArrayList<String> lista) {
        return null;
    }

    // TODO 3: Implementa `elementosUnicos`.
    //   - Retorna un ArrayList<String> con los elementos que aparecen exactamente 1 vez.
    //   - Usa contarFrecuencias() y filtra los que tengan valor == 1.
    public static ArrayList<String> elementosUnicos(ArrayList<String> lista) {
        return null;
    }

    // TODO 4: Implementa `combinarFrecuencias`.
    //   - Recibe dos mapas de frecuencias y retorna un NUEVO mapa que suma las frecuencias.
    //   - Ejemplo: {A=2, B=3} + {B=1, C=4} → {A=2, B=4, C=4}
    //   - Crea el mapa resultado a partir de una copia de `mapa1`,
    //     luego itera mapa2.entrySet() y usa merge(k, v, Integer::sum).
    public static HashMap<String, Integer> combinarFrecuencias(
            HashMap<String, Integer> mapa1, HashMap<String, Integer> mapa2) {
        return null;
    }

    // TODO 5: Implementa `histograma`.
    //   - Retorna un String multi-línea con cada elemento y su frecuencia en formato:
    //     "elemento : ***" (tantos asteriscos como frecuencia)
    //   - Ordena las líneas por frecuencia descendente.
    //   - Ejemplo: {Java=3, Go=1, Python=2} → líneas: "Java : ***\nPython : **\nGo : *"
    //   - Usa contarFrecuencias(), luego convierte a ArrayList de entradas,
    //     ordénalas por valor desc con sort() y construye el String con StringBuilder.
    public static String histograma(ArrayList<String> lista) {
        return "";
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 14 — Frecuencias y Conteos ===\n");

        ArrayList<String> palabras = new ArrayList<>();
        for (String p : new String[]{"Java", "Python", "Java", "Go", "Python", "Java",
                                     "Rust", "Go", "Java", "TypeScript"}) {
            palabras.add(p);
        }

        System.out.println("Lista: " + palabras);

        HashMap<String, Integer> freq = contarFrecuencias(palabras);
        System.out.println("Frecuencias: " + freq);

        System.out.println("Más frecuente: " + palabraMasFrecuente(palabras));
        System.out.println("Únicos (1 vez): " + elementosUnicos(palabras));

        // -- combinarFrecuencias --
        HashMap<String, Integer> f1 = new HashMap<>();
        f1.put("A", 2); f1.put("B", 3);
        HashMap<String, Integer> f2 = new HashMap<>();
        f2.put("B", 1); f2.put("C", 4);
        System.out.println("\nCombinar {A=2,B=3} + {B=1,C=4}: " + combinarFrecuencias(f1, f2));

        // -- histograma --
        System.out.println("\nHistograma:");
        System.out.println(histograma(palabras));
    }
}
