package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 32: BST — Inserción y Búsqueda
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.1
 *
 * CONTEXTO:
 * Un Binary Search Tree mantiene la invariante: izquierdo < padre < derecho.
 * Esto permite buscar cualquier elemento en O(log n) promedio (similar a
 * búsqueda binaria, pero sobre una estructura enlazada dinámica).
 *
 * Implementa un BST completo con:
 * - insertar(int valor): inserta respetando la propiedad BST.
 * - buscar(int valor): retorna true si el valor existe.
 * - minimo(): retorna el valor más pequeño del árbol.
 * - maximo(): retorna el valor más grande del árbol.
 * - inorder(): imprime todos los valores en orden ascendente.
 *
 * RESTRICCIONES:
 * - Inserción recursiva (comparar con nodo actual y bajar al subárbol correcto).
 * - Si el valor ya existe, no insertar duplicados.
 * - Sin usar java.util (ni TreeMap, ni TreeSet).
 *
 * COMPLEJIDAD OBJETIVO:
 * - Insertar/Buscar: O(log n) promedio, O(n) peor caso (degenerado).
 * ============================================================================
 */
public class Ejercicio32_BSTInsercionBusqueda {

    private static class Nodo {
        int valor;
        Nodo izquierdo, derecho;
        Nodo(int valor) {
            this.valor = valor;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    private Nodo raiz;

    public Ejercicio32_BSTInsercionBusqueda() {
        this.raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo nodo, int valor) {
        // TODO 1: Caso base: si nodo es null, crear y retornar un nuevo Nodo(valor).
        //         Si valor < nodo.valor → nodo.izquierdo = insertarRec(nodo.izquierdo, valor).
        //         Si valor > nodo.valor → nodo.derecho = insertarRec(nodo.derecho, valor).
        //         Si valor == nodo.valor → ignorar (no duplicados).
        //         Retornar nodo.
        return nodo;
    }

    public boolean buscar(int valor) {
        // TODO 2: Implementar búsqueda recursiva o iterativa.
        //         Desde la raíz: si valor < nodo → ir izquierda.
        //         Si valor > nodo → ir derecha. Si == → encontrado (true).
        //         Si llegas a null → no existe (false).
        return false;
    }

    public int minimo() {
        // TODO 3: El mínimo de un BST es el nodo más a la IZQUIERDA.
        //         Desde la raíz, seguir izquierdo hasta que izquierdo sea null.
        //         Validar que el árbol no esté vacío.
        return -1;
    }

    public int maximo() {
        // TODO 4: El máximo de un BST es el nodo más a la DERECHA.
        //         Desde la raíz, seguir derecho hasta que derecho sea null.
        return -1;
    }

    public void inorder() {
        inorderRec(raiz);
        System.out.println();
    }

    private void inorderRec(Nodo nodo) {
        // TODO 5: Recorrido inorder recursivo (ya lo hiciste en Ej.31).
        //         izquierdo → imprimir → derecho.
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 32: BST Inserción y Búsqueda ===\n");

        Ejercicio32_BSTInsercionBusqueda bst = new Ejercicio32_BSTInsercionBusqueda();

        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10, 35};
        for (int v : valores) {
            bst.insertar(v);
        }

        System.out.print("Inorder (ascendente): ");
        bst.inorder(); // 10 20 30 35 40 50 60 70 80

        System.out.println("buscar(40) = " + bst.buscar(40));  // true
        System.out.println("buscar(55) = " + bst.buscar(55));  // false
        System.out.println("buscar(10) = " + bst.buscar(10));  // true

        System.out.println("minimo() = " + bst.minimo());      // 10
        System.out.println("maximo() = " + bst.maximo());      // 80

        // Insertar duplicado (no debería cambiar nada)
        bst.insertar(50);
        System.out.print("Tras insertar 50 duplicado: ");
        bst.inorder();

        System.out.println("\n=== FIN EJERCICIO 32 ===");
    }
}
