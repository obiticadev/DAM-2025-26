package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 78: Strategy — Algoritmos de Ordenamiento Intercambiables
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.3
 *
 * CONTEXTO:
 * Un sistema necesita ordenar datos pero el algoritmo óptimo depende
 * del contexto: pocos datos → InsertionSort, muchos → MergeSort,
 * casi ordenados → BubbleSort. Strategy permite cambiar el algoritmo
 * en runtime sin modificar el código del contexto.
 *
 * Implementa:
 * - Interfaz EstrategiaOrdenamiento: ordenar(int[] datos), getNombre().
 * - Estrategias: BubbleSortStrategy, InsertionSortStrategy, MergeSortStrategy.
 * - Contexto Ordenador: con setEstrategia() y ordenar().
 *
 * RESTRICCIONES:
 * - Cada estrategia implementa su algoritmo completo.
 * - El Ordenador puede cambiar de estrategia en runtime.
 * - Demostrar que los tres algoritmos producen el mismo resultado.
 * - Sin java.util.Arrays.sort().
 * ============================================================================
 */
public class Ejercicio78_StrategyOrdenamiento {

    interface EstrategiaOrdenamiento {
        // TODO 1: void ordenar(int[] datos), String getNombre()
    }

    // TODO 2: Implementar BubbleSortStrategy con el algoritmo completo.

    // TODO 3: Implementar InsertionSortStrategy.

    // TODO 4: Implementar MergeSortStrategy (recursivo).

    // TODO 5: Implementar clase Ordenador (Context):
    //         - Campo: EstrategiaOrdenamiento estrategia.
    //         - setEstrategia(EstrategiaOrdenamiento e).
    //         - void ordenar(int[] datos): delega a la estrategia.
    //         - void seleccionarAutomatica(int[] datos):
    //           if datos.length < 10 → InsertionSort
    //           else if casiOrdenado(datos) → BubbleSort
    //           else → MergeSort

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 78: Strategy Ordenamiento ===\n");

        // TODO 6:
        //         Ordenador ord = new Ordenador();
        //         int[] datos = {38, 27, 43, 3, 9, 82, 10};
        //
        //         ord.setEstrategia(new BubbleSortStrategy());
        //         int[] copia1 = copiar(datos);
        //         ord.ordenar(copia1);
        //         imprimir("Bubble", copia1);
        //
        //         ord.setEstrategia(new InsertionSortStrategy());
        //         int[] copia2 = copiar(datos);
        //         ord.ordenar(copia2);
        //         imprimir("Insertion", copia2);
        //
        //         ord.setEstrategia(new MergeSortStrategy());
        //         int[] copia3 = copiar(datos);
        //         ord.ordenar(copia3);
        //         imprimir("Merge", copia3);

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 78 ===");
    }
}
