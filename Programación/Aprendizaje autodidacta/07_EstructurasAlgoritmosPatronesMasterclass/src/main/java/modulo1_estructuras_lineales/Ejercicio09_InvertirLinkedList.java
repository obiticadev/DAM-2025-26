package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 09: Invertir una LinkedList (In-Place)
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.4
 *
 * CONTEXTO:
 * Invertir una lista enlazada es uno de los problemas clásicos más
 * frecuentes en entrevistas técnicas. La clave está en manipular los
 * punteros 'siguiente' de cada nodo SIN usar estructuras auxiliares.
 *
 * Debes implementar dos enfoques:
 * - invertirIterativo(Nodo head): invierte usando un bucle (tres punteros).
 * - invertirRecursivo(Nodo head): invierte usando recursión.
 *
 * RESTRICCIONES:
 * - In-place: No crear nodos nuevos ni usar arrays/listas auxiliares.
 * - Complejidad espacial O(1) para la versión iterativa.
 * - Complejidad espacial O(n) para la recursiva (stack de llamadas).
 *
 * TÉCNICA ITERATIVA (Tres Punteros):
 *   prev = null, actual = head, next = null
 *   Mientras actual != null:
 *     1. Guardar actual.siguiente en 'next'
 *     2. Invertir el puntero: actual.siguiente = prev
 *     3. Avanzar: prev = actual, actual = next
 *   Al final, 'prev' es el nuevo head.
 *
 * COMPLEJIDAD OBJETIVO:
 * - Ambas versiones: O(n) tiempo
 * ============================================================================
 */
public class Ejercicio09_InvertirLinkedList {

    static class Nodo {
        int valor;
        Nodo siguiente;

        Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    public static Nodo invertirIterativo(Nodo head) {
        // TODO 1: Implementar la inversión iterativa con tres punteros:
        //         prev (inicialmente null), actual (inicialmente head), next.
        //         En cada paso: guardar next, invertir puntero, avanzar.
        //         Retornar 'prev' al final (nuevo head).
        return head;
    }

    public static Nodo invertirRecursivo(Nodo head) {
        // TODO 2: Caso base: si head es null o head.siguiente es null, retornar head.
        //         Paso recursivo: invertir el resto de la lista desde head.siguiente.
        //         Luego, hacer que head.siguiente.siguiente apunte a head (invertir enlace).
        //         Y head.siguiente = null (cortar el enlace original).
        //         Retornar el nuevo head que viene de la recursión.
        return head;
    }

    public static String listaToString(Nodo head) {
        // TODO 3: Recorrer la lista desde head construyendo un String con formato:
        //         "10 → 20 → 30 → null". Usar StringBuilder.
        return "null";
    }

    public static Nodo construirLista(int... valores) {
        // TODO 4: Crear una cadena de nodos a partir de los valores recibidos.
        //         Retornar el head de la cadena resultante.
        //         Si no hay valores, retornar null.
        return null;
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 09: Invertir LinkedList ===\n");

        // --- Prueba iterativa ---
        Nodo lista1 = construirLista(10, 20, 30, 40, 50);
        System.out.println("Original:            " + listaToString(lista1));

        lista1 = invertirIterativo(lista1);
        System.out.println("Invertida (iter):    " + listaToString(lista1));
        // Esperado: 50 → 40 → 30 → 20 → 10 → null

        // --- Prueba recursiva ---
        Nodo lista2 = construirLista(1, 2, 3, 4, 5);
        System.out.println("\nOriginal:            " + listaToString(lista2));

        lista2 = invertirRecursivo(lista2);
        System.out.println("Invertida (recur):   " + listaToString(lista2));
        // Esperado: 5 → 4 → 3 → 2 → 1 → null

        // --- Caso borde: lista vacía ---
        Nodo vacia = invertirIterativo(null);
        System.out.println("\nLista vacía: " + listaToString(vacia)); // null

        // --- Caso borde: un solo elemento ---
        Nodo uno = construirLista(42);
        uno = invertirIterativo(uno);
        System.out.println("Un elemento: " + listaToString(uno));     // 42 → null

        System.out.println("\n=== FIN EJERCICIO 09 ===");
    }
}
