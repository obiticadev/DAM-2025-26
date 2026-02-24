package conjuntos;

import modelos.Estudiante;
import java.util.Set;
import java.util.TreeSet;

/**
 * MÓDULO 2.2: TREESET (Implementación de Set y NavigableSet)
 * 
 * TEORÍA:
 * A diferencia del HashSet que no garantiza el orden, el TreeSet GUARDA LOS
 * ELEMENTOS ORDENADOS
 * de forma natural desde el momento en que los insertas.
 * Usa internamente un árbol binario equilibrado (Red-Black Tree).
 * 
 * PROS:
 * - Elementos SIEMPRE ordenados.
 * - Sin duplicados (sigue siendo un Set).
 * - Podemos obtener rangos (ej. buscar el elemento superior a "X", valores
 * entre "A" y "B").
 * 
 * CONTRAS:
 * - Es MUCHO MÁS LENTO que el HashSet (Tiempo logarítmico O(log n)) en
 * añadir/borrar/buscar debido
 * a que tiene que estar acomodando el árbol en cada operación.
 * - Solo admite objetos que implementen la interfaz Comparable (el "orden
 * natural")
 * O que le pases un Comparator propio en su constructor (orden personalizado).
 * 
 * ¿CUÁNDO USARLO?
 * Cuando necesites mantener SIEMPRE una lista ordenada donde no haya repetidos
 * (Ejemplo: Leaderboard / Tabla de puntuaciones única).
 */
public class Ejercicio04_TreeSet {

    public static void demostracion() {
        System.out.println("\n--- DEMOSTRACIÓN: TREESET ---");

        // Creamos un TreeSet de Strings (los Strings ya de por sí implementan
        // Comparable alfabéticamente)
        Set<String> palabras = new TreeSet<>();

        palabras.add("Zebra");
        palabras.add("Avión");
        palabras.add("Gato");
        palabras.add("Gato"); // Duplicado omitido

        System.out.println("1. TreeSet de Strings (orden natural alfabético):");
        System.out.println(palabras); // Imprime: [Avión, Gato, Zebra]

        // Ahora algo más complejo. Recordarás que nuestra clase Estudiante implementa
        // 'Comparable'.
        // Su compareTo fue programado para ordenar de mayor nota a menor nota.
        Set<Estudiante> alumnosEstrella = new TreeSet<>();

        alumnosEstrella.add(new Estudiante("111A", "Marta", 8.5));
        alumnosEstrella.add(new Estudiante("222B", "Juan", 6.0));
        alumnosEstrella.add(new Estudiante("333C", "Laura", 9.8));
        alumnosEstrella.add(new Estudiante("444D", "Pedro", 8.5)); // Misma nota que marta, pero distinta matricula

        System.out.println(
                "\n2. TreeSet de Estudiantes (orden natural por Nota Media invertida, implementado por nosotros):");
        for (Estudiante e : alumnosEstrella) {
            System.out.println(e.getNotaMedia() + " - " + e.getNombre() + " (" + e.getMatricula() + ")");
        }
        System.out.println("--------------------------------\n");
    }

    /**
     * EJERCICIO:
     * Vas a usar un TreeSet exclusivamente para guardar puntuaciones de un juego de
     * manera
     * que siempre estén preparadas de menor a mayor sin hacer esfuero (los tipos
     * Integer ya lo hacen).
     */
    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 4: TREESET ---");

        // TODO: Crea un Set llamado 'puntuaciones' del tipo Integer y haz que sea un
        // TreeSet.
        TreeSet<Integer> puntuaciones = null;

        // TODO: Añade las siguientes puntuaciones asíncronas: 50, 10, 80, 25, 10, 50.
        // Observarás que los duplicados desaparecen.

        // TODO: Utilizando métodos de TREESET (por eso la variable debe ser TreeSet y
        // no solo Set),
        // guarda en estas variables:
        // - minima: la puntuación más baja (first())
        // - maxima: la puntuación más alta (last())
        int minima = 0;
        int maxima = 0;

        if (puntuaciones != null && !puntuaciones.isEmpty()) {
            // Rellena aquí
        }

        // --- CÓDIGO DE COMPROBACIÓN (NO MODIFICAR) ---
        if (puntuaciones.size() == 4 && minima == 10 && maxima == 80) {
            System.out.println(">> ¡CORRECTO! Sabes aprovechar la auto-ordenación natural.\033[0;32m [OK]\033[0m");
            System.out.println("El ranking ha quedado así automáticamente: " + puntuaciones);
        } else {
            System.err.println(
                    ">> ALGO FALLA. \033[0;31m [ERROR]\033[0m Has fallado resolviendo las variables first y last o no se añadieron bien.");
        }
        System.out.println("-------------------------------\n");
    }
}
