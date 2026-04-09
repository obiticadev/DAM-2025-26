package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 05: Nodo Simple y Encadenamiento Manual
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.4
 *
 * CONTEXTO:
 * Antes de construir una LinkedList, necesitas entender su unidad atómica:
 * el NODO. Un nodo contiene un dato y una referencia (puntero) al siguiente
 * nodo. Encadenar nodos manualmente es la base de las listas enlazadas,
 * pilas y colas.
 *
 * Este ejercicio es una introducción suave. Vas a:
 * - Crear una clase interna 'Nodo' con campos 'valor' (int) y 'siguiente' (Nodo).
 * - Encadenar 5 nodos manualmente (sin bucles) asignando las referencias.
 * - Recorrer la cadena con un bucle while e imprimir cada valor.
 * - Contar el número total de nodos en la cadena.
 * - Buscar si un valor existe en la cadena.
 *
 * RESTRICCIONES:
 * - El encadenamiento inicial debe ser manual (nodo a nodo, sin bucle).
 * - El recorrido sí debe usar un bucle while (actual != null).
 * - No usar ninguna estructura de java.util.
 *
 * COMPLEJIDAD OBJETIVO:
 * - Recorrido: O(n)
 * - Búsqueda:  O(n)
 * ============================================================================
 */
public class Ejercicio05_NodoSimple {

    // TODO 1: Definir una clase estática interna llamada 'Nodo' con dos campos:
    //         - int valor:       el dato almacenado.
    //         - Nodo siguiente:  referencia al próximo nodo (null si es el último).
    //         Incluir un constructor que reciba el valor e inicialice 'siguiente' a null.

    public static int contarNodos(Object cabeza) {
        // TODO 2: Recibir el nodo cabeza de la cadena. Recorrer con un bucle
        //         while (actual != null) contando los nodos. Retornar el total.
        //         NOTA: Cambia 'Object' por tu tipo Nodo cuando lo implementes.
        return 0;
    }

    public static boolean buscar(Object cabeza, int valorBuscado) {
        // TODO 3: Recibir el nodo cabeza y un valor a buscar. Recorrer la cadena
        //         comparando actual.valor con valorBuscado. Retornar true si
        //         lo encuentras, false si llegas a null sin encontrarlo.
        //         NOTA: Cambia 'Object' por tu tipo Nodo cuando lo implementes.
        return false;
    }

    public static void imprimirCadena(Object cabeza) {
        // TODO 4: Recorrer la cadena desde cabeza e imprimir cada valor
        //         en formato: "10 → 20 → 30 → null".
        //         Usar System.out.print() para mantenerlo en una sola línea.
        //         NOTA: Cambia 'Object' por tu tipo Nodo cuando lo implementes.
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 05: Nodo Simple y Encadenamiento ===\n");

        // TODO 5: Crear 5 nodos con valores 10, 20, 30, 40, 50.
        //         Encadenarlos manualmente:
        //           nodo1.siguiente = nodo2;
        //           nodo2.siguiente = nodo3;
        //           ... etc.
        //         Luego invocar:
        //           imprimirCadena(nodo1);
        //           System.out.println("Total nodos: " + contarNodos(nodo1));
        //           System.out.println("¿Existe 30? " + buscar(nodo1, 30));
        //           System.out.println("¿Existe 99? " + buscar(nodo1, 99));

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 05 ===");
    }
}
